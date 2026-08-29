#ifndef AQUEUE_H_
#define AQUEUE_H_

#include <pthread.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

template <typename T, size_t MAX_SIZE = (1024 * 100)>
class AQueue {
public:
  AQueue()
  : input_(NULL)
  , output_(NULL)
  , input_ptr_(NULL)
  , output_ptr_(NULL)
  , output_end_(NULL) {

  }

  ~AQueue() {
    free(input_);
    free(output_);
  }

public:
  int Init() {
    input_ = static_cast<T**>(malloc(sizeof(T*) * MAX_SIZE));
    output_ = static_cast<T**>(malloc(sizeof(T*) * MAX_SIZE));
    input_ptr_ = input_;
    output_ptr_ = output_;
    output_end_ = output_;
    return pthread_mutex_init(&lock_, NULL);
  }

  void Push(T* item) {
    pthread_mutex_lock(&lock_);
    size_t input_size = InputSize();
    if (input_size < MAX_SIZE) {
      *input_ptr_ = item;
      ++input_ptr_;
    } else {
      free(item);
    }
    pthread_mutex_unlock(&lock_);
  }

  T* Pop() {
    while (OutputSize() == 0) {
      Swap();
      usleep(1);
    }
    T* tmp = *output_ptr_;
    ++output_ptr_;
    return tmp;
  }

  void Swap() {
    pthread_mutex_lock(&lock_);
    T** queue_tmp = input_;
    input_ = output_;
    output_ = queue_tmp;
    output_ptr_ = output_;
    output_end_ = input_ptr_;
    input_ptr_ = input_;
    pthread_mutex_unlock(&lock_);
  }

private:
  inline size_t InputSize() const {
    return input_ptr_ - input_;
  }

  inline size_t OutputSize() const {
    return output_end_ - output_ptr_;
  }

private:
  T** input_;
  T** output_;
  T** input_ptr_;
  T** output_ptr_;
  T** output_end_;

  pthread_mutex_t lock_;
};

#endif // AQUEUE_H_
