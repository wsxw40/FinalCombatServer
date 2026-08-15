#include <stdio.h>
#include <time.h>
#include <stdlib.h>

#include "desencoder.h"
#include "log.h"

// -----------------------------------------------------------------
// network encoder
// -----------------------------------------------------------------

// constructor
DesNetworkEncoder::DesNetworkEncoder()
{
	dhkey = new CDHKey(64);
    Reset();
}

// destructor
DesNetworkEncoder::~DesNetworkEncoder()
{
	if (dhkey)
		delete dhkey;
}

// reset
void DesNetworkEncoder::Reset()
{
	memset(key_a, 0, sizeof(key_a));
	memset(key_c, 0, sizeof(key_c));
	for ( int i=0; i<8; i++ )
		key_public[i] = i;
	memset(key_private, 0, sizeof(key_private));
	memset(iv, 0, sizeof(iv));
	srand((unsigned)time(NULL));

	dhkey->CreateKeyPair(key_c);
}

// encode
void DesNetworkEncoder::Encode(byte * data, uint size)
{
	// increase encoded bytes
	encoded_bytes += size;

	memset( iv, 0, sizeof(iv) );
	des.encrypt(data, data, size, key_private, iv);
}

// decode
void DesNetworkEncoder::Decode(byte * data, uint size)
{
	// increase decode bytes
	decoded_bytes += size;

	// decrypt data
	memset( iv, 0, sizeof(iv) );
	des.decrypt(data, data, size, key_private, iv);
}

// get key
bool DesNetworkEncoder::GetKey(unsigned char * key, uint size) {
	if (size < sizeof(key_c)) return false;

	memset( iv, 0, sizeof(iv) );
	des.encrypt(key_c, key, sizeof(key_c), key_public, iv);
	return true;
}

// set key
bool DesNetworkEncoder::SetKey(unsigned char * key, uint size) {
	if (size < sizeof(key_a)) return false;

	memset( iv, 0, sizeof(iv) );
	des.decrypt(key, key_a, sizeof(key_a), key_public, iv);
	log_write(LOG_DEBUG1, "SetKey called key_a=%02x%02x%02x%02x%02x%02x%02x%02x",
			key_a[0],key_a[1],key_a[2],key_a[3],key_a[4],key_a[5],key_a[6],key_a[7]);
  dhkey->Agree(key_private, key_a);
	return true;
}
