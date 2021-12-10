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

uint Dump:: number_arguments() 
{
	return 1;
}

std::list<std::string> Dump::execute(std::list<std::string>& text, const std::vector<std::string>& args)
{
	if (number_arguments() != args.size())
	{
		throw MyException("incorrect number of arguments in block Dump");
	}
	std::ofstream output;
	output.open(args[0]);
	if (!output.is_open())
	{
		throw MyException("file doesn't open in block Dump");
	}
	for (auto it = text.begin(); it != text.end(); ++it)
	{
		output << *it << "\n";
	}
	output.close();
	return text;
}

BlockType Dump::GetType()
{
	return BlockType::INOUT;
}

Block* DumpFactory::CreateBlock()
{
	return new Dump();
}