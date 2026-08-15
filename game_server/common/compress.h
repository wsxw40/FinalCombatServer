#pragma once
#include "networkstream.h"

// packet compress
class HuffmanCompressor
{
	struct EncodeDict
	{
		uint data : 24;
		byte len;
	};

	struct DecodeTree
	{
		ushort child[2];
	};

	struct DecodeDict
	{
		ushort node;
		ushort bits;
	};

public:
	// constructor
	HuffmanCompressor();

	bool GenerateDict(uint frequency_table[256], bool encode = true, bool decode = true);

	// encode
	uint Encode(byte * dst, uint dst_len, const byte * src, uint src_len);

	// decode
	uint Decode(byte * dst, uint dst_len, const byte * src, uint src_len);

public:
	// encode and decode dict
	EncodeDict encode_dict[256];
	DecodeDict decode_dict[256];
	DecodeTree decode_tree[256];
};

// compressor
struct HuffmanNetworkCompressor : public NetworkCompressor
{
public:
	// constructor
	HuffmanNetworkCompressor();

	// reset
	void Reset();

	// encode
	uint Compress(byte * dst, uint dst_size, const byte * src, uint src_size);

	// decode
	uint Decompress(byte * dst, uint dst_size, const byte * src, uint src_size);

private:
	// the compressor
	HuffmanCompressor compress;

	// character frequency table
	uint encode_frequency_table[256];
	uint decode_frequency_table[256];

	uint encode_src_count;
	uint encode_dst_count;
	uint decode_src_count;
	uint decode_dst_count;

public:
	// update dict rate
	uint encode_dict_update_rate;
	uint decode_dict_update_rate;
};
