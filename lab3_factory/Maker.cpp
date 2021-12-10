#include"Maker.h"

Maker& Maker::Instance()
{
	static Maker m;
	return m;
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
	}
	return it->second->CreateBlock();
}