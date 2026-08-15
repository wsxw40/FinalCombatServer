#pragma once

#include <unistd.h>
#include <string.h>

template <class T, size_t N>
class FixedArray
{
	public:
		FixedArray(): n(0)
		{
			memset(array, 0, sizeof(array));
		}
		T& operator[] (size_t i)
		{
			//assert(i < N);
			return array[i];
		}
		const T& operator[] (size_t i) const
		{
			return const_cast<FixedArray<T, N> *>(this)->operator[] (i);
		}
		bool operator == (const FixedArray<T, N> &fixed_array) const
		{
			if (n != fixed_array.n)
				return false;

			for (int i = 0; i < N; ++i)
			{
				if ((*this)[i] != fixed_array[i])
				{
					return false;
				}
			}
			return true;
		}
		bool operator < (const FixedArray<T, N>& fixed_array) const
		{
			if (n > fixed_array.n)
				return false;

			if (n < fixed_array.n)
				return true;

			for (int i = 0; i < n; ++i)
			{
				if (array[i] < fixed_array[i])
					return true;
				else if (array[i] > fixed_array[i])
					return false;
			}	
			return false;

		}
		template<size_t NN>
		void Append(const FixedArray<T, NN> &fixed_array)
		{
			//assert(n + fixed_array.GetSize() <= N);

			memcpy(&array[n], &fixed_array[0], fixed_array.GetSize() * sizeof(T));
			n += fixed_array.GetSize();

		}

		void SetSize(size_t n) { this->n = n; }
		size_t GetSize() const { return n; }
		size_t GetFixedSize() const { return N; }

		void Print() const
		{
			for (int i = 0; i < n; ++i)
			{
				printf("%d ", array[i]);
			}
		}
	private:
		size_t n;
		T array[N];
};
