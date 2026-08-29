#pragma once
#include "common.h"

template<class T>
class ObjectPool
{
public:
	// constructor.
	ObjectPool();

	// destructor.
	~ObjectPool();

	// initialize
	bool Initialize(uint size);

	// terminate
	void Terminate();

	// allocate
	T * Allocate();

	// free object
	void Free(uint uid);

	// get object
	T * Get(uint uid);

	// get size
	uint Size() { return size; }

	// to data array
	operator T* () { return data_array; }
	
	// begin
	T * Begin() { return data_array; }

	// end
	T * End() { return data_array + size; }

	uint GetCount() { return count; }

protected:
	uint size;
	T *  data_array;
	T ** link_array;
	T *  free_obj;
	uint count;
};

// construtor
template<class T>
ObjectPool<T>::ObjectPool()
	: size(0)
	, data_array(NULL)
	, link_array(NULL)
	, free_obj(NULL)
{
}

// destructor.
template <class T>
ObjectPool<T>::~ObjectPool()
{
	Terminate();
}

// initialize
template <class T>
bool ObjectPool<T>::Initialize(uint size)
{
	if (size > 0xffff)
		return false;

	this->size = size;

	// allocate data array
	data_array = new T[size];
	if (data_array == NULL)
		goto err;

	// allocate link array
	link_array = new T*[size];
	if (link_array == NULL)
		goto err;
	
	// initialze link
	for (uint i = 0; i < size; i ++)
	{
		data_array[i].uid = 0x00010000 | i;
		link_array[i] = &data_array[i + 1];
	}
	link_array[size - 1] = NULL;
	free_obj = data_array;

	return true;

err:
	Terminate();
	return false;
}

// terminate
template<class T>
void ObjectPool<T>::Terminate()
{
	if (data_array)
	{
		delete[] data_array;
		data_array = NULL;
	}

	if (link_array)
	{
		delete[] link_array;
		link_array = NULL;
	}

	free_obj = NULL;
	size = 0;
}

// allocate
template<class T>
T * ObjectPool<T>::Allocate()
{
	T * ret = NULL;

	if (free_obj)
	{
		ret = free_obj;
		free_obj = link_array[ret - data_array];
		link_array[ret - data_array] = NULL;
		count++;
	}

	return ret;
}

	// free object
template<class T>
void ObjectPool<T>::Free(uint uid)
{
	T * obj = Get(uid);

	if (obj)
	{
		link_array[obj - data_array] = free_obj;
		free_obj = obj;
		
		// update magic
		ushort magic = uid >> 16;
		magic = magic == 0 ? 1 : magic + 1;
		obj->uid = (magic << 16) | (uid & 0xffff);

		count--;
	}
}

// get object
template<class T>
T * ObjectPool<T>::Get(uint uid)
{
	uint index = uid & 0xffff;
	if (index < size)
	{
		T * obj = &data_array[index];
		if (obj->uid == uid)
			return obj;
	}

	return NULL;
}

