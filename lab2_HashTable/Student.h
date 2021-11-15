#pragma once

typedef unsigned int uint;

class Student
{
	uint age;
	uint weight;
public:
	void set_student(uint a, uint w);
	const uint get_age();
	const uint get_weight();
	Student& operator=(const Student& b);
	friend bool operator==(const Student& a, const Student& b);
	friend bool operator!=(const Student& a, const Student& b);
};

