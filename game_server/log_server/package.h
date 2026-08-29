#ifndef PACKAGE_H_
#define PACKAGE_H_

#include <sys/types.h>

#include "define.h"

class Package {
public:
  Package(void* package);
  ~Package();

public:
  int GetPackageSize();
  int32_t ReadInt32();
  char ReadByte();
  int ReadString(char* buffer, size_t buffer_size);

private:
  void* package_;
  Byte* parse_pointer_;
  Byte* package_pointer_;
};

#endif // PACKAGE_H_
