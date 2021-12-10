#pragma once
#include "BlockFactory.h"
#include "Read.h"
#include "Block.h"
#include "Write.h"
#include "Sort.h"
#include "Replace.h"
#include "Grep.h"
#include "Dump.h"
#include <memory>
#include "MyException.h"

class Maker
{
	std::map<std::string, BlockFactory*> makers;
	Maker() {}
public:
	static Maker& Instance();
	void RegisterMaker(const std::string& key, BlockFactory* maker);
	Block* GetBlock(std::string& key);
};