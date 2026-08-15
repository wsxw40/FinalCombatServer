#pragma once

#include <sys/socket.h>
#include <sys/epoll.h>
#include <sys/time.h>
#include <sys/stat.h>
#include <sys/signal.h>
#include <fcntl.h>
#include <netinet/in.h>
#include <netinet/tcp.h>
#include <arpa/inet.h>
#include <string.h>
#include <unistd.h>
#include <errno.h>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <new>
#include <string>
#include <vector>
#include <assert.h>
#include <netdb.h>

typedef unsigned char byte;
typedef unsigned int uint;
typedef unsigned short ushort;

typedef int SOCKET;
typedef int EPOLL;

#define INVALID_SOCKET -1
#define INVALID_UID 0xffffffff
#define elementsof(a) (sizeof(a) / sizeof(a[0]))
#define endof(a) (a + sizeof(a) / sizeof(a[0]))
/// const
const float PI			= 3.1415926535897932384626433832795f;
const float TWOPI		= 2.0f * PI;
const float HALFPI		= 0.5f * PI;
const float RAD2DEG		= 180.0f / PI;
const float DEG2RAD		= PI / 180.0f;
const float EPSILON		= 0.00005f;
const float NATURALE	= 2.71828182845904523536f;
const float F32_MAX		= 3.402823466e+38F;

template<class T> T Abs(const T & v) { return v < 0 ? -v : v; }
template<class T> const T & Min(const T & a, const T & b) { return a < b ? a : b; }
template<class T> const T & Max(const T & a, const T & b) { return a > b ? a : b; }
template<class T> const T & Clamp(const T & v, const T & a, const T & b) { return Max(Min(v, b), a); }

template<class T> void Swap(T & left, T & right)
{
	T temp = right;
	right = left;
	left = temp;
}

struct Vector2
{
	float x, y;

	friend bool operator == (const Vector2 & v1, const Vector2 & v2)
	{
		return (v1.x == v2.x) && (v1.y == v2.y);
	}

	friend bool operator != (const Vector2 & v1, const Vector2 & v2)
	{
		return (v1.x != v2.x) || (v1.y != v2.y);
	}
};

struct Vector3
{
	float x, y, z;

	Vector3() {}
	Vector3(float x, float y, float z) : x(x), y(y), z(z) {}

	friend bool operator == (const Vector3 & v1, const Vector3 & v2)
	{
		return (v1.x == v2.x) && (v1.y == v2.y) && (v1.z == v2.z);
	}

	friend bool operator != (const Vector3 & v1, const Vector3 & v2)
	{
		return (v1.x != v2.x) || (v1.y != v2.y) || (v1.z != v2.z);
	}
};

inline Vector3 operator+(const Vector3 & a, const Vector3 & b)
{
	return Vector3(a.x + b.x, a.y + b.y, a.z + b.z);
}

inline Vector3 operator-(const Vector3 & a, const Vector3 & b)
{
	return Vector3(a.x - b.x, a.y - b.y, a.z - b.z);
}

inline Vector3 operator*(const Vector3 & a, const Vector3 & b)
{
	return Vector3(a.x * b.x, a.y * b.y, a.z * b.z);
}

inline float Length(const Vector3 & a)
{
	return sqrtf(a.x * a.x + a.y * a.y + a.z * a.z);
}

inline float LengthSqr(const Vector3 & a)
{
	return a.x * a.x + a.y * a.y + a.z * a.z;
}

inline void Normalize(const Vector3& in, Vector3& out, float & length)
{
	length = Length(in);
	out = Vector3(in.x/length,in.y/length,in.z/length);
}

struct Quaternion
{
	float x, y, z, w;

	friend bool operator == (const Quaternion & v1, const Quaternion & v2)
	{
		return (v1.x == v2.x) && (v1.y == v2.y) && (v1.z == v2.z);
	}

	friend bool operator != (const Quaternion & v1, const Quaternion & v2)
	{
		return (v1.x != v2.x) || (v1.y != v2.y) || (v1.z != v2.z);
	}

	Vector3 GetZXY() const
	{
		Vector3 v;

		// Extract Sin(pitch)
		float sp = -2.0f * (y*z - w*x);

		// Check for Gimbel lock, giving slight tolerance for numerical imprecision
		if (Abs(sp) > 0.9999f)
		{
			v.x = HALFPI * sp;
			v.y = atan2f(-x*z + w*y, 0.5f - y*y - z*z);
			v.z = 0.0f;
		}
		else
		{
			v.x = asinf(sp);
			v.y = atan2f(x*z + w*y, 0.5f - x*x - y*y);
			v.z = atan2f(x*y + w*z, 0.5f - x*x - z*z);
		}

		return v;
	}

	void SetZXY(const Vector3 & xyz)
	{
		float h = xyz.y * 0.5f;
		float p = xyz.x * 0.5f;
		float b = xyz.z * 0.5f;

		float ch = cosf(h);
		float sh = sinf(h);
		float cp = cosf(p);
		float sp = sinf(p);
		float cb = cosf(b);
		float sb = sinf(b);

		w = ch * cp * cb + sh * sp * sb;
		x = ch * sp * cb + sh * cp * sb;
		y = sh * cp * cb - ch * sp * sb;
		z = ch * cp * sb - sh * sp * cb;
	}
};

struct AxisAlignedBox  
{
	Vector3  Min, Max;

	// set invalid
	void SetInvalid()
	{
		Min.x = Min.y = Min.z = F32_MAX;
		Max.x = Max.y = Max.z = -F32_MAX;
	}

	/// union with
	void UnionWithVector(const Vector3& rVec)
	{
		Min.x = fminf(Min.x, rVec.x);
		Min.y = fminf(Min.y, rVec.y);
		Min.z = fminf(Min.z, rVec.z);

		Max.x = fmaxf(Max.x, rVec.x);
		Max.y = fmaxf(Max.y, rVec.y);
		Max.z = fmaxf(Max.z, rVec.z);
	}

	/// is point inside
	bool IsPointInside(const Vector3& vPoint) const
	{
		return 
			(	
			vPoint.x >= Min.x &&
			vPoint.x <= Max.x &&
			vPoint.y >= Min.y &&
			vPoint.y <= Max.y &&
			vPoint.z >= Min.z &&
			vPoint.z <= Max.z
			);
	}
};

enum EError
{
	ERR_Read,
	ERR_Write,
	ERR_Auth,
	ERR_Closed,
	ERR_Socket,
	ERR_Parse,
};

void set_sockaddr(sockaddr_in & addr, const char * ip, uint port);
bool set_sockaddr(hostent* pHostent , uint dwPort, sockaddr_in & oAddr);
bool parse_sockaddr(sockaddr_in & addr, const char * str);
const char * sockaddr_ntoa(sockaddr_in & addr);
bool run_as_daemon();
const char * getexepath();
void SpltCsv(const char *pBuffer, std::vector<std::string> &DataList, char cSplt);

#define user_name_length		64*4
#define character_name_length	33*4
#define room_name_length		64*4
#define career_key_length		64
#define res_key_length			33
#define group_name_length		64*4
#define avata_part_length		32
#define weapon_attribute_length 12
#define chat_length				200*4+1

#define max_tower_gun_count		16
