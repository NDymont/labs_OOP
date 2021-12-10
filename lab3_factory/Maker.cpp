#include"Maker.h"

Maker::Maker()
{
	makers["readfile"] = new ReadFactory();
	makers["writefile"] = new WriteFactory();
	makers["sort"] = new SortFactory();
	makers["replace"] = new ReplaceFactory();
	makers["grep"] = new GrepFactory();
	makers["dump"] = new DumpFactory();
}
Maker::~Maker()
{
	for (auto it = makers.begin(); it != makers.end(); ++it)
	{
		delete it->second;
	}
}
void Maker::RegisterMaker(const std::string& key, BlockFactory* maker)
{
	if (makers.find(key) != makers.end())
	{
		throw MyException("attempt to re-register the block");
	}
	makers[key] = maker;
}
Block* Maker::GetBlock(std::string& key)
{
	auto it = makers.find(key);
	if (it == makers.end())
	{
		throw MyException("wrong blockname");
		this->~Maker();
	}
	return it->second->CreateBlock();
}