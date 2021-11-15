#pragma once
#include <iostream>
#include <fstream>
#include <cstring>
#include "Student.h"

typedef std::string Key;
typedef unsigned int uint;

typedef Student Value;

struct Entry
{
	Key name;
	Value data;
	bool busy;
	bool was_removed;
};
typedef Entry Entry;

class HashTable
{
private:
	Entry* table; //���������
	size_t capacity; // ������������ ������
	size_t size; // ������� ������

	uint CalcHash(std::string str) const;
	bool find(const Key& k, uint& index) const;
public:
	HashTable();
	~HashTable();

	HashTable(const HashTable& b); //����������� �����������
	HashTable(HashTable&& b) noexcept; // ����������� �����������

	HashTable& operator=(const HashTable& b); // �������� ������������ ������������ 
	HashTable& operator=(HashTable&& b) noexcept; // �������� ������������ ������������

	// ���������� �������� ���� ���-������.
	void swap(HashTable& b);

	// ������� ���������.
	void clear();
	// ������� ������� �� ��������� �����.
	bool erase(const Key& k);
	// ������� � ���������. ������������ �������� - ���������� �������.
	bool insert(const Key& k, const Value& v);

	// �������� ������� �������� �� ��������� �����.
	bool contains(const Key& k) const;

	// ���������� �������� �� �����. ������������ �����.
	// � ������ ���������� ����� � ����������, ������� �������� � ���������
	// ��������, ��������� ������������� �� ��������� � ������� ������ �� ����. 
	Value& operator[](const Key& k);

	// ���������� �������� �� �����. ������� ���������� ��� �������.
	Value& at(const Key& k);
	const Value& at(const Key& k) const;

	size_t get_size() const;
	bool empty() const;
	bool no_memory() const;

	friend bool operator==(const HashTable& a, const HashTable& b);
	friend bool operator!=(const HashTable& a, const HashTable& b);
};

bool operator==(const HashTable& a, const HashTable& b);
bool operator!=(const HashTable& a, const HashTable& b);