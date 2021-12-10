#pragma once
#include <exception> 

class MyException : public std::exception 
{
	const char* text;
public:
	MyException(const char* text)
	{
		this->text = text;
	}
	const char* what() const noexcept override
	{
		return text;
	}
};