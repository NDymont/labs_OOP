#include "Read.h"
#include "Maker.h"

namespace {
	bool RegisterFactory() {
		static ReadFactory f;
		Maker::Instance().RegisterMaker("readfile", &f);
		return true;
	}
	bool fake = RegisterFactory();
}

uint Read:: number_arguments()
{
	return 1;
}

std::list<std::string> Read::execute(std::list<std::string>& text, const std::vector<std::string>& args)
{
	if (number_arguments() != args.size())
	{
		throw MyException("incorrect number of arguments in block Read");
	}
	std::ifstream input;
	input.open(args[0]);
	if (!input.is_open())
	{
		throw MyException("file doesn't open in block Read");
	}
	while (!input.eof()) {
		std::string str;
		getline(input, str);
		text.push_back(str);
	}
	input.close();
	return text;
}

BlockType Read::GetType()
{
	return BlockType::IN;
}

Block* ReadFactory:: CreateBlock()
{
	return new Read();
}