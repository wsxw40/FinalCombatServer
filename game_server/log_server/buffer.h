#ifndef BUFFER_H_
#define BUFFER_H_

#include "define.h"

#include <stdlib.h>
#include <string.h>

class Buffer {
public:
  Buffer();
  ~Buffer();

public:
  int Init();
  inline void Ruin();

  inline void Reset();

  inline void* BufferPoint();
  inline void* ReadPoint();
  inline void* WritePoint();

  inline int WriteSurplus();
  inline int DataAmount();

  inline void* ReadMove(int offset);
  inline void* WriteMove(int offset);

  inline void PartlyPackageMove();
  void* ReadOnePackage();

  int Write(void* data, int size);

  char* GetLine();

private:
  static const int BUFFER_SIZE_ = 1024 * 1024;
  Byte buffer_[BUFFER_SIZE_];
  Byte* write_;
  Byte* read_;
};

inline void Buffer::Ruin() {
  // free(buffer_);
  write_ = read_ = buffer_;
}

inline void* Buffer::BufferPoint() {
  return buffer_;
}

inline void* Buffer::ReadPoint() {
  return read_;
}

inline void* Buffer::WritePoint() {
  return write_;
}

inline void* Buffer::ReadMove(int offset) {
  read_ += offset;
  return read_;
}

inline void* Buffer::WriteMove(int offset) {
  write_ += offset;
  return write_;
}

inline int Buffer::WriteSurplus() {
  return buffer_ + BUFFER_SIZE_ - write_;
}

inline int Buffer::DataAmount() {
  return write_ - read_;
}

inline void Buffer::PartlyPackageMove() {
  int data_amount = DataAmount();
  memmove(buffer_, read_, data_amount);
  read_ = buffer_;
  write_ = read_ + data_amount;
}

#endif // BUFFER_H_
