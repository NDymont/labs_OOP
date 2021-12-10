#pragma once
#include <list>
#include <vector>
#include <string>

typedef unsigned int uint;

enum class BlockType
{
	IN,
	OUT,
	INOUT
};

class Block
{
	virtual uint number_arguments() = 0;
public:
	virtual std::list<std::string> execute (std::list<std::string>& text, const std::vector<std::string>& args) = 0;
	virtual BlockType GetType() = 0;
	virtual ~Block() {};
};