#include "Dump.h"
#include "Maker.h"

namespace {
	bool RegisterFactory() {
		static DumpFactory f;
		Maker::Instance().RegisterMaker("dump", &f);
		return true;
	}
	bool fake = RegisterFactory();
}

BlockType Dump::GetType()
{
	return BlockType::INOUT;
}

Block* DumpFactory::CreateBlock()
{
	return new Dump();
}