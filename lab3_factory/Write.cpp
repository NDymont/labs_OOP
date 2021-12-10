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

uint Write:: number_arguments() 
{
	return 1;
}

std::list<std::string> Write::execute(std::list<std::string>& text, const std::vector<std::string>& args)
{
	if (number_arguments() != args.size())
	{
		throw MyException("incorrect number of arguments in block Write");
	}
	std::ofstream output;
	output.open(args[0]);
	if (!output.is_open())
	{
		throw MyException("file doesn't open in block Write");
	}
	for (auto it = text.begin(); it != text.end(); ++it)
	{
		output << *it << "\n";
	}
	output.close();
	return text;
}

BlockType Write::GetType()
{
	return BlockType::OUT;
}