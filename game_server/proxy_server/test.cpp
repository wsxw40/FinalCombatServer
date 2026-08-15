#include "pch.h"
#include "compress.h"

static void compress_test1()
{
	// 100M temp buffer
	const uint bytes_to_kb = 1024;
	const uint buffer_size = 10 * 1024 * bytes_to_kb;
	byte * buffer = new byte[buffer_size];
	byte * result = new byte[buffer_size * 2];
	byte * check = new byte[buffer_size];

	// generate frequency table
	HuffmanCompressor compress;

	// generate random data
	srand(2);

	for (;;)
	{
		uint frequency_table[256] = {0};

		uint mask = (byte)rand() + 1;
		for (byte * b = buffer; b < buffer + buffer_size; b++)
		{
			*b = (byte)(rand() % mask);
			frequency_table[*b]++;
		}

		// generate dict
		double generate_time = Event::GetTime();
		if (!compress.GenerateDict(frequency_table))
		{
			puts("generate dict failed");
			memset(frequency_table, 0, sizeof(frequency_table));
			continue;
		}
		generate_time = Event::GetTime() - generate_time;

		// do compress
		double encode_time = Event::GetTime();
		uint compress_size = compress.Encode(result, buffer_size * 2, buffer, buffer_size);
		encode_time = Event::GetTime() - encode_time;

		// do decompress
		double decode_time = Event::GetTime();
		compress.Decode(check, buffer_size, result, compress_size);
		decode_time = Event::GetTime() - decode_time;

		// check result
		for (uint i = 0; i < buffer_size; i++)
		{
			if (buffer[i] != check[i])
			{
				printf("data check failed, pos=%d, src=0x%x, dst=0x%x\n",
						i, (int)buffer[i], int(check[i]));
				break;
			}
		}


		// print result
		printf("encode: size=%gKB, compressed=%gKB, encode=%gms, decode=%gms, generate=%gms\n",
				(double)buffer_size / bytes_to_kb,
				(double)compress_size / bytes_to_kb,
				encode_time * 1000,
				decode_time * 1000,
				generate_time * 1000);
	}

	delete[] buffer;
	delete[] result;
}

static void compress_test2()
{
	// 100M temp buffer
	const uint bytes_to_kb = 1024;
	const uint buffer_size = 16 * bytes_to_kb;
	byte * buffer = new byte[buffer_size];
	byte * result = new byte[buffer_size * 2];
	byte * check = new byte[buffer_size];

	// test object
	HuffmanNetworkCompressor compress1;
	HuffmanNetworkCompressor compress2;

	compress2.decode_dict_update_rate = compress1.encode_dict_update_rate;
	compress1.decode_dict_update_rate = compress2.encode_dict_update_rate;

	// generate random data
	srand((uint)Event::GetTime());
	srand(2);
	uint src_size = 0;
	uint dst_size = 0;
	double encode_total_time = 0;
	double decode_total_time = 0;

	for (;;)
	{
		uint mask = (byte)rand() + 1;
		for (byte * b = buffer; b < buffer + buffer_size; b++)
			*b = (byte)(rand() % mask);

		// do compress
		double encode_time = Event::GetTime();
		uint compress_size = compress1.Compress(result, buffer_size * 2, buffer, buffer_size);
		encode_time = Event::GetTime() - encode_time;

		// do decompress
		double decode_time = Event::GetTime();
		compress2.Decompress(check, buffer_size, result, compress_size);
		decode_time = Event::GetTime() - decode_time;

		// check result
		for (uint i = 0; i < buffer_size; i++)
		{
			if (buffer[i] != check[i])
			{
				printf("data check failed, pos=%d, src=0x%x, dst=0x%x\n",
						i, (int)buffer[i], int(check[i]));
				break;
			}
		}

		src_size += buffer_size;
		dst_size += compress_size;
		encode_total_time += encode_time;
		decode_total_time += decode_time;

		if (src_size > 10 * 1024 * 1024)
		{
			// print result
			printf("rate=%.2f, encode=%.2fms, decode=%.2fms\n",
					(double)dst_size / (double)src_size,
					encode_total_time * 1000,
					decode_total_time * 1000);

			src_size = 0;
			dst_size = 0;
			encode_total_time = 0;
			decode_total_time = 0;
		}
	}

	delete[] buffer;
	delete[] result;
}

void compress_test()
{
	compress_test2();
}
