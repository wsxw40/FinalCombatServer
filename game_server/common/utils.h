#pragma once

#include "array.h"

#include <vector>
#include <set>

template <class T, size_t N>
void array_u(FixedArray<T, N> x, int n, int m, std::vector< FixedArray<T, N> > &out)
{
	static std::set< FixedArray<T, N> > u;
	if (m == 1)
	{
		u.clear();
	}

	if (m == n)
	{

		if (u.find(x) != u.end())
			return;

		u.insert(x);
		out.push_back(x);

	}
	else
	{
		for (int i = m; i <= n; ++i)
		{
			std::swap(x[m-1], x[i-1]);
			array_u(x, n, m+1, out);
			std::swap(x[i-1], x[m-1]);
		}
	}
}

template <class T, size_t N>
void split_n(int n, int m, std::vector< FixedArray<T, N> > &out, bool include_n = false)
{
	static FixedArray<T, N+1> x;

	for (int i = 1; i <= n; ++i)
	{
		if (i >= x[m - 1])
		{
			x[m] = i;
			int rest = n - i;
			if (rest == 0 && ((include_n && m > 0) || (!include_n && m > 1)))
			{
				FixedArray<T, N> fixed_array;
				for (int j = 1; j <= m; ++j)
				{
					fixed_array[j-1] = x[j];
				}
				fixed_array.SetSize(m);
				out.push_back(fixed_array);
			}
			else
			{
				split_n<T, N>(rest, m + 1, out);
			}
			x[m] = 0;
		}
	}
}

template <class T, size_t N, size_t NN>
void combin_p(const FixedArray<T, N> &charset, const size_t keys[], const FixedArray<T, NN> &pattern, size_t num, size_t des, size_t loc, std::vector< FixedArray<size_t, NN> > &out)
{
	static FixedArray<size_t, NN> output;

	int len = charset.GetSize();

	if (num == des)
	{
		for (int i = 0; i < pattern.GetSize(); ++i)
		{
			if (charset[output[i]] != pattern[i])
				return;
		}
		output.SetSize(pattern.GetSize());
		out.push_back(output);
		return;
	}
	if (des - num > len - loc) 
		return;

	output[num] = keys[loc];
	combin_p<T, N, NN>(charset, keys, pattern, num + 1, des, loc + 1, out);
	combin_p<T, N, NN>(charset, keys, pattern, num, des, loc + 1, out);
}
