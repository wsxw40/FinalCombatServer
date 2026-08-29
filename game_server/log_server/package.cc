#include "package.h"

#include <assert.h>
#include <string.h>

Package::Package(void* package)
: package_(package)
, parse_pointer_(static_cast<Byte*>(package_) + sizeof(int32_t))
, package_pointer_(static_cast<Byte*>(package_) + sizeof(int32_t)) {
  assert(package);
}

Package::~Package() {

}

int Package::GetPackageSize() {
  return *(static_cast<int*>(package_));
}

char Package::ReadByte() {
  char* char_pointer = reinterpret_cast<char*>(parse_pointer_);
  char tmp = *char_pointer;
  parse_pointer_ += sizeof(char);
  return tmp;
}

int32_t Package::ReadInt32() {
  int32_t* int_pointer = reinterpret_cast<int*>(parse_pointer_);
  int32_t tmp = *int_pointer;
  parse_pointer_ += sizeof(int32_t);
  return tmp;
}

int Package::ReadString(char* buffer, size_t buffer_size) {
  int32_t string_length = ReadInt32();
  size_t copy_size = buffer_size >= string_length + 1 ? string_length : buffer_size;
  memcpy(buffer, parse_pointer_, copy_size);
  buffer[copy_size] = '\0';
  parse_pointer_ += copy_size;
  return copy_size;
}
