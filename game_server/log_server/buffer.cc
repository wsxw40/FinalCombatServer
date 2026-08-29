#include "buffer.h"

#include <assert.h>
#include <stdio.h>

Buffer::Buffer()
: read_(NULL)
, write_(NULL) {

}

Buffer::~Buffer() {
  Ruin();
}

int Buffer::Init() {
  //assert(buffer_size > 0);
  //buffer_size_ = buffer_size;
  //buffer_ = static_cast<Byte*> (malloc(sizeof(Byte) * buffer_size));
  //if (buffer_ != NULL) {
  write_ = read_ = buffer_;
  return 0;
}

void* Buffer::ReadOnePackage() {
  void* package = NULL;
  if (DataAmount() < sizeof(int32_t)) {
    if (buffer_ + BUFFER_SIZE_ - read_ <= sizeof(int32_t))
      PartlyPackageMove();
  } else {
    int package_size = *(reinterpret_cast<int32_t*>(read_));
    if (package_size <= DataAmount()) {
      package = malloc(package_size);
      memcpy(package, read_, package_size);
      read_ += package_size;
    } else {
      if (package_size > WriteSurplus())
        PartlyPackageMove();
    }
  }
  return package;
}

int Buffer::Write(void* data, int size) {
  if (size <= WriteSurplus()) {
    memcpy(write_, data, size);
    write_ += size;
    return size;
  } else {
    PartlyPackageMove();
    if (size <= WriteSurplus()) {
        memcpy(write_, data, size);
        write_ += size;
        return size;
    } else {
      int write_surplus = WriteSurplus();
      memcpy(write_, data, write_surplus);
      write_ += write_surplus;
      return write_surplus;
    }
  }
}

char* Buffer::GetLine() {
  char* line = NULL;
  char* ptr = static_cast<char*>(read_);
  int data_amount = DataAmount();
  for (int i = 0; i < data_amount; ++i, ++ptr) {
    if (*ptr == '\r' && *(ptr + 1) == '\n') {
      int line_size = ptr - read_;
      line = static_cast<char*>(malloc(line_size + 1));
      memcpy(line, read_, line_size);
      line[line_size] = '\0';
      read_ += line_size + 2;
      break;
    }
  }
  return line;
}
