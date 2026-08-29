#pragma once

#include <vector>

template <class T>
class TreeNode
{
	public:
		TreeNode();
		TreeNode(TreeNode<T>* father_node, const T& data);
		~TreeNode();
		TreeNode<T> *GetChildNode (const T& key);
		const TreeNode<T> *GetChildNode (const T& key) const;
		const T& GetData() const { return data; }
		TreeNode<T> *AddChildNode(const T&data);
		bool RemoveChildNode(const T&data);
		bool IsLeafNode() const { return cnodes.empty(); }
		bool IsRootNode() const { return fnode == NULL; }
	private:
		typedef std::vector< TreeNode<T>* > NodeList; 
		NodeList cnodes;
		TreeNode<T>* fnode;
		T data;
};

template <class T>
TreeNode<T>::TreeNode(): fnode(NULL)
{
}
template <class T>
TreeNode<T>::TreeNode(TreeNode<T>* father_node, const T& _data): fnode(father_node), data(_data)
{
}

template <class T>
TreeNode<T>::~TreeNode()
{
	for (typename NodeList::iterator iter = cnodes.begin(); iter != cnodes.end(); ++iter)
	{
		delete *iter;
	}
	cnodes.clear();
}
template <class T>
TreeNode<T>* TreeNode<T>::GetChildNode(const T& data)
{
	for (typename NodeList::iterator iter = cnodes.begin(); iter != cnodes.end(); ++iter)
	{
		if ((*iter)->data == data)
		{
			return *iter;
		}
	}
	return NULL;
}
template <class T>
TreeNode<T> *TreeNode<T>::AddChildNode(const T&data)
{
	if (GetChildNode(data) == NULL)
	{
		if (TreeNode<T> *node = new TreeNode<T>(this, data))
		{
			cnodes.push_back(node);
			return node;
		}
	}
	return NULL;
}
template <class T>
bool TreeNode<T>::RemoveChildNode(const T&data)
{
	for (typename NodeList::iterator iter = cnodes.begin(); iter != cnodes.end(); ++iter)
	{
		if ((*iter)->data == data)
		{
			delete *iter;
			cnodes.earse(iter);
			return true;
		}
	}
	return false;
}
template <class T>
const TreeNode<T> *TreeNode<T>::GetChildNode (const T& key) const
{
	return (const_cast<TreeNode<T> *>(this))->GetChildNode(key);
}

