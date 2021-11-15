#include "HashTable.h"

HashTable::HashTable()
{
	capacity = 50;
	size = 0;
	table = new Entry[2 * capacity];
	for (uint i = 0; i < 2 * capacity; ++i)
	{
		table[i].busy = false; // все €чейки не зан€ты
		table[i].was_removed = false;
	}
}

HashTable::~HashTable()
{
	size = 0;
	delete[] table;
}

uint HashTable::CalcHash(std::string str) const
{
	int hash = 0;
	for (uint i = 0; i < str.length(); ++i)
	{
		hash += str[i];
	}
	hash %= capacity;
	return hash;
}

bool HashTable::find(const Key& k, uint& index) const
{
	for (uint i = CalcHash(k); i < 2 * capacity; ++i)
	{
		if (!table[i].busy && !table[i].was_removed)
		{
			return false;
		}
		if (table[i].name == k && table[i].busy)
		{
			index = i;
			return true;
		}
	}
	return false;
}

HashTable::HashTable(const HashTable& b)
{
	this->capacity = b.capacity;
	this->size = b.size;
	this->table = new Entry[2 * this->capacity];
	for (uint i = 0; i < 2 * this->capacity; ++i)
	{
		this->table[i].busy = b.table[i].busy;
		this->table[i].was_removed = b.table[i].was_removed;
		this->table[i].data = b.table[i].data;
		this->table[i].name = b.table[i].name;
	}
}

HashTable::HashTable(HashTable&& b) noexcept
{
	this->capacity = b.capacity;
	this->size = b.size;
	this->table = b.table;
	b.size = 0;
	b.table = nullptr;
}

HashTable& HashTable::operator=(const HashTable& b)
{
	if (&b == this)
	{
		return *this;
	}
	delete[] this->table;
	this->capacity = b.capacity;
	this->size = b.size;
	this->table = new Entry[2 * this->capacity];
	for (uint i = 0; i < 2 * this->capacity; ++i)
	{
		this->table[i].busy = b.table[i].busy;
		this->table[i].was_removed = b.table[i].was_removed;
		this->table[i].data = b.table[i].data;
		this->table[i].name = b.table[i].name;
	}
	return *this;
}

HashTable& HashTable::operator=(HashTable&& b) noexcept
{
	if (&b == this)
	{
		return *this;
	}
	this->capacity = b.capacity;
	this->size = b.size;
	this->table = b.table;
	b.size = 0;
	b.table = nullptr;
	return *this;
}

void HashTable::swap(HashTable& b)
{
	HashTable c = std::move(*this);
	*this = std::move(b);
	b = std::move(c);
}

void HashTable::clear()
{
	size = 0;
	for (uint i = 0; i < 2 * capacity; ++i)
	{
		table[i].busy = false; // все €чейки не зан€ты
		table[i].was_removed = false;
	}
}

bool HashTable::erase(const Key& k)
{
	uint place;
	if (find(k, place))
	{
		table[place].busy = false;
		table[place].was_removed = true;
		--size;
		return true;
	}
	return false;
}

bool HashTable::insert(const Key& k, const Value& v)
{
	if (no_memory())
	{
		throw std::exception::exception("no memory\n");
		//return false;
	}
	if (size >= capacity)
	{
		return false;
	}
	for (uint i = CalcHash(k); i < 2 * capacity; ++i)
	{
		if (table[i].name == k && table[i].busy)
		{
			return false;
		}
		if (!table[i].busy)
		{
			table[i].name = k;
			table[i].data = v;
			table[i].busy = true;
			table[i].was_removed = false;
			++size;
			return true;
		}
	}
	return false;
}

bool HashTable::contains(const Key& k) const
{
	uint place;
	return find(k, place);
}

Value& HashTable::operator[](const Key& k)
{
	uint place;
	if (find(k, place))
	{
		return table[place].data;;
	}
	Value default_data;
	insert(k, default_data);
	return default_data;
}

Value& HashTable::at(const Key& k)
{
	uint place;
	if (find(k, place))
	{
		return table[place].data;
	}
	throw std::exception::exception("key unfound\n");
}

const Value& HashTable::at(const Key& k) const
{
	uint place;
	if (find(k, place))
	{
		return table[place].data;
	}
	throw std::exception::exception("key unfound\n");
}

size_t HashTable::get_size() const
{
	return size;
}

bool HashTable::empty() const
{
	return size == 0;
}

bool HashTable::no_memory() const
{
	return table == nullptr;
}

bool operator==(const HashTable& a, const HashTable& b)
{
	if (a.size != b.size)
	{
		return false;
	}
	int j = 0;
	for (uint i = 0; i < a.size; ++i)
	{
		while (!a.table[j].busy)
		{
			++j;
		}
		Value a_value = a.table[j].data;
		if (!b.contains(a.table[j].name))
		{
			return false;
		}
		Value b_value = b.at(a.table[j].name);
		if (a_value != b_value)
		{
			return false;
		}
		++j;
	}
	return true;
}

bool operator!=(const HashTable& a, const HashTable& b)
{
	return !(a == b);
}