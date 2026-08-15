#include "common.h"
#include "compress.h"
#include "log.h"

// constructor
HuffmanCompressor::HuffmanCompressor()
{
}

// generate compress dict
bool HuffmanCompressor::GenerateDict(uint frequency_table[256], bool encode, bool decode)
{
	struct Node
	{
		double weight;
		int parent;
		int lchild;
		int rchild;
	};

	const uint n = 256;
	const uint ht_size = 2 * n - 1;
	Node ht[ht_size];

	// initialize nodes
	for (int i = 0; i < ht_size; i++)
		ht[i].parent = ht[i].lchild = ht[i].rchild = -1;

	// initialize weights
	for (int i = 0; i < n; i++)
		ht[i].weight = frequency_table[i];

	// build huffman tree 
	for (int i = n; i < ht_size; i++)
	{
		double minL, minR;
		int lchild, rchild;

		minL = minR = 1e300;
		lchild = rchild = -1;

		for (int j = 0; j < i; j++)
		{
			if (ht[j].parent == -1)
			{
				if (ht[j].weight < minL)
				{
					minR = minL;
					minL = ht[j].weight;
					rchild = lchild;
					lchild = j;
				}
				else if (ht[j].weight < minR)
				{
					minR = ht[j].weight;
					rchild = j;
				}
			}
		}

		ht[lchild].parent = ht[rchild].parent = i;
		ht[i].weight = minL + minR;
		ht[i].lchild = lchild;
		ht[i].rchild = rchild;
	}

	// create encode dict
	if (encode)
	{
		for (int i = 0; i < n; i++)
		{
			uint value = 0;
			uint bits = 0;

			uint c = i;
			uint f;

			while ((f = ht[c].parent) != -1)
			{
				if (ht[f].rchild == c)
					value = value << 1 | 1;
				else
					value = value << 1 | 0;

				c = f;
				if (++bits > 24)
					return false;
			}

			encode_dict[i].len = bits;
			encode_dict[i].data = value;
		}
	}

	if (decode)
	{
		// generate decode tree
		for (int i = 0; i < n; i++)
		{
			int lchild = ht[ht_size - i - 1].lchild;
			int rchild = ht[ht_size - i - 1].rchild;

			if (lchild < n)
				decode_tree[i].child[0] = 0x100 | lchild;
			else
				decode_tree[i].child[0] = ht_size - lchild - 1;

			if (rchild < n)
				decode_tree[i].child[1] = 0x100 | rchild;
			else
				decode_tree[i].child[1] = ht_size - rchild - 1;
		}

		// generate decode dict
		for (int i = 0; i < n; i++)
		{
			uint tmp = i;
			uint bits = 8;
			uint node = 0;

			while (node < 256 && bits)
			{
				node = decode_tree[node].child[tmp & 1];
				tmp >>= 1;
				bits --;
			}

			decode_dict[i].node = node;
			decode_dict[i].bits = 8 - bits;
		}
	}

	return true;
}

// encode
uint HuffmanCompressor::Encode(byte * dst, uint dst_len, const byte * src, uint src_len)
{
	uint tmp = 0;
	uint bits = 0;
	uint ret = 0;
	byte * d = dst;
	byte * end = dst + dst_len;

	for (const byte * s = src; s < src + src_len; ++s)
	{
		uint len = encode_dict[*s].len;
		uint data = encode_dict[*s].data;

		tmp |= data << bits;
		bits += len;
		ret += len;

		while (bits >= 8)
		{
			*d++ = tmp;
			tmp >>= 8;
			bits -= 8;

			if (d > end)
				return 0;
		}
	}

	*d = tmp;

	return (ret + 7) / 8;
}

// decode
uint HuffmanCompressor::Decode(byte * dst, uint dst_len, const byte * src, uint src_len)
{
	uint tmp = 0;
	uint bits = 0;
	const byte * s = src;
	const byte * end = src + src_len + 3;

	for (byte * d = dst; d < dst + dst_len; d++)
	{
		// fetch data
		while (bits < 24)
		{
			tmp |= *s++ << bits;
			bits += 8;

			if (s > end)
				return 0;
		}

		// fast index
		uint node = decode_dict[tmp & 0xff].node;
		uint len = decode_dict[tmp & 0xff].bits;
		tmp >>= len;
		bits -= len;

		// search tree
		while (node < 256)
		{
			node = decode_tree[node].child[tmp & 1];
			tmp >>= 1;
			bits --;
		}

		// result
		*d = node;
	}

	return dst_len;
}

// constructor
HuffmanNetworkCompressor::HuffmanNetworkCompressor()
	: encode_dict_update_rate(8 * 1024)
	, decode_dict_update_rate(8 * 1024)
{
	Reset();
}

// reset
void HuffmanNetworkCompressor::Reset()
{
	// initialize frequency table
	memset(encode_frequency_table, 0, sizeof(encode_frequency_table));
	memset(decode_frequency_table, 0, sizeof(decode_frequency_table));

	// reset counter
	encode_src_count = 0;
	encode_dst_count = 0;
	decode_src_count = 0;
	decode_dst_count = 0;

	// generate dict
	compress.GenerateDict(encode_frequency_table);
}

// encode
uint HuffmanNetworkCompressor::Compress(byte * dst, uint dst_size, const byte * src, uint src_size)
{
	// data too large
	if (src_size > 0xffff)
		return 0;

	// size is at beginning of the data
	*reinterpret_cast<ushort*>(dst) = src_size;

	// compress data
	uint result = compress.Encode(dst + 2, dst_size - 2, src, src_size);

	// encode failed
	if (result == 0)
		return 0;

	// plus header size
	result += 2;

	// update frequency table
	for (const byte * c = src; c < src + src_size; c++)
		encode_frequency_table[*c]++;

	encode_src_count += src_size;
	encode_dst_count += result;
	
	// update dict
	if (encode_src_count > encode_dict_update_rate)
	{
		// log
		log_write(LOG_DEBUG5, "compress rate: %.02f", (double)encode_dst_count / (double)encode_src_count);

		// generate new decode dict
		compress.GenerateDict(encode_frequency_table, true, false);

		// reset encode data
		encode_src_count = 0;
		encode_dst_count = 0;
		for (uint * t = encode_frequency_table; t < encode_frequency_table + 256; t++)
			*t >>= 1;
	}

	return result;
}

// decode
uint HuffmanNetworkCompressor::Decompress(byte * dst, uint dst_size, const byte * src, uint src_size)
{
	// data size
	uint data_size = *reinterpret_cast<const ushort*>(src);

	// not enough decode buffer
	if (data_size > dst_size)
		return 0;

	// compress data
	uint result = compress.Decode(dst, data_size, src + 2, src_size - 2);

	// decode failed
	if (result != data_size)
		return 0;

	// update frequency table
	for (byte * c = dst; c < dst + result; c++)
		decode_frequency_table[*c]++;

	decode_src_count += src_size;
	decode_dst_count += result;
	
	// update dict
	if (decode_dst_count > decode_dict_update_rate)
	{
		// log
		log_write(LOG_DEBUG5, "decompress rate: %.02f", (double)decode_src_count / (double)decode_dst_count);

		// generate new decode dict
		compress.GenerateDict(decode_frequency_table, false, true);

		// reset decode data
		decode_src_count = 0;
		decode_dst_count = 0;

		for (uint * t = decode_frequency_table; t < decode_frequency_table + 256; t++)
			*t >>= 1;
	}

	return data_size;
}
