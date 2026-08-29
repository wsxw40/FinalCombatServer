#pragma once
#include "networkstream.h"

#include "des.h"
#include "dhkey.h"

// network encoder
struct DesNetworkEncoder : NetworkEncoder
{
 public:
  // constructor
  DesNetworkEncoder();

  // destructor
  ~DesNetworkEncoder();

  // constructor
  void Reset();

  // encode
  void Encode(byte * data, uint size);

  // decode
  void Decode(byte * data, uint size);

 public:
  // get key
  bool GetKey(unsigned char * key, uint size);
  bool GetKeyPublic(unsigned char * key) { if (!key) return false; memcpy(key, key_c, sizeof(key_c)); return true; }

  // set key
  bool SetKey(unsigned char * key, uint size);

 private:
  unsigned char key_a[8];
  unsigned char key_c[8];
  unsigned char key_public[8];
  unsigned char key_private[8];
  unsigned char iv[8];

  CDHKey *dhkey;
  ApexSec::CCFBDes des;

  // counter
  uint encoded_bytes;
  uint decoded_bytes;
};
