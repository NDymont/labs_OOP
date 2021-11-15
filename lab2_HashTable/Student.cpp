#include "Student.h"

bool operator==(const Student& a, const Student& b)
{
	return a.age == b.age && a.weight == b.weight;
}

bool operator!=(const Student& a, const Student& b)
{
	return !(a == b);
}

void Student::set_student(uint a, uint w)
{
	age = a;
	weight = w;
}

const uint Student::get_age()
{
	return this->age;
}

const uint Student::get_weight()
{
	return weight;
}

Student& Student::operator=(const Student& b)
{
	this->age = b.age;
	this->weight = b.weight;
	return *this;
}