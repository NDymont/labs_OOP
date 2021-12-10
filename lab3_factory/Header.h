#pragma once
#include"BlockFactory.h"
#include "Read.h"
#include "Block.h"
#include"Write.h"
#include "Sort.h"
#include "Replace.h"
#include "Grep.h"
#include "Dump.h"
#include <memory>
#include "MyException.h"

class Maker
{
	std::map<std::string, BlockFactory*> makers;
public:
	Maker()
	{
		makers["readfile"] = new ReadFactory();
		makers["writefile"] = new WriteFactory();
		makers["sort"] = new SortFactory();
		makers["replace"] = new ReplaceFactory();
		makers["grep"] = new GrepFactory();
		makers["dump"] = new DumpFactory();
	}
	~Maker()
	{
		for (auto it = makers.begin(); it != makers.end(); ++it)
		{
			delete it->second;
		}
	}
	void RegisterMaker(const std::string& key, BlockFactory* maker)
	{
		if (makers.find(key) != makers.end())
		{
			throw MyException("attempt to re-register the block");
		}
		makers[key] = maker;
	}
	Block* GetBlock(std::string& key)
	{
		auto it = makers.find(key);
		if (it == makers.end())
		{
			throw MyException("wrong blockname");
			this->~Maker();
		}
		return it->second->CreateBlock();
	}
};