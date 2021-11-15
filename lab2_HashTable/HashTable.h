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
	Entry* table; //контейнер
	size_t capacity; // максимальный размер
	size_t size; // текущий размер

	uint CalcHash(std::string str) const;
	bool find(const Key& k, uint& index) const;
public:
	HashTable();
	~HashTable();

	HashTable(const HashTable& b); //конструктор копирования
	HashTable(HashTable&& b) noexcept; // Конструктор перемещения

	HashTable& operator=(const HashTable& b); // Оператор присваивания копированием 
	HashTable& operator=(HashTable&& b) noexcept; // Оператор присваивания перемещением

	// Обменивает значения двух хэш-таблиц.
	void swap(HashTable& b);

	// Очищает контейнер.
	void clear();
	// Удаляет элемент по заданному ключу.
	bool erase(const Key& k);
	// Вставка в контейнер. Возвращаемое значение - успешность вставки.
	bool insert(const Key& k, const Value& v);

	// Проверка наличия значения по заданному ключу.
	bool contains(const Key& k) const;

	// Возвращает значение по ключу. Небезопасный метод.
	// В случае отсутствия ключа в контейнере, следует вставить в контейнер
	// значение, созданное конструктором по умолчанию и вернуть ссылку на него. 
	Value& operator[](const Key& k);

	// Возвращает значение по ключу. Бросает исключение при неудаче.
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