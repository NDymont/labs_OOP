#include "Grep.h"
#include "Maker.h"

namespace {
	bool RegisterFactory() {
		static GrepFactory f;
		Maker::Instance().RegisterMaker("grep", &f);
		return true;
	}
	bool fake = RegisterFactory();
}

uint Grep::number_arguments()
{
	return 1;
}

std::list<std::string> Grep::execute(std::list<std::string>& text, const std::vector<std::string>& args)
{
	if (number_arguments() != args.size())
	{
		throw MyException("incorrect number of arguments in block Grep");
	}
	std::list<std::string> new_text;
	for (auto it = text.begin(); it != text.end(); ++it)
	{
		if ((*it).find(args[0]) != std::string::npos)
		{
			new_text.push_back(*it);
		}
	}
	return new_text;
}

BlockType Grep::GetType()
{
	return BlockType::INOUT;
}

Block* GrepFactory::CreateBlock()
{
	return new Grep();
}