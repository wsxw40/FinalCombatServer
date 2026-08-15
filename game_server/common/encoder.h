#include "networkstream.h"

// xor encoder
struct XORNetworkEncoder : public NetworkEncoder
{
public:
	// constructor
	XORNetworkEncoder();

	// constructor
	void Reset();

	// encode
	void Encode(byte * data, uint size);

	// decode
	void Decode(byte * data, uint size);

private:
	// key
	uint encode_key;
	uint decode_key;

	// counter
	uint encoded_bytes;
	uint decoded_bytes;
};


