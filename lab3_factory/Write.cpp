#include "Write.h"
#include "Maker.h"

namespace {
	bool RegisterFactory() {
		static WriteFactory f;
		Maker::Instance().RegisterMaker("writefile", &f);
		return true;
	}
	bool fake = RegisterFactory();
}

BlockType Write::GetType()
{
	return BlockType::OUT;
}