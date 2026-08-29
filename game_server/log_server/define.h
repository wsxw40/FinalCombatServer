#ifndef TYPE_H_
#define TYPE_H_

#define Byte char

const int LINE_MAX_TYPE = 1024;
const int BUFFER_MAX = 1024;
const int PATH_MAX_TYPE = 256;
const int CONNECTION_MAX = 512;

enum LogMessageType {
  CM_Ident = 10000,
  CM_Log = 10001,
  CM_Level = 10002,
  SM_SetLevel = 10000 + 100,

  kLogText = 1000,
  kLogControl,
  kLogFilter,
};

#endif // TYPE_H_
