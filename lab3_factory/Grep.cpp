#include "Grep.h"

uint Grep::number_arguments()
{
	return 1;
}
std::list<std::string> Grep::execute(std::list<std::string>& text, const std::vector<std::string>& args)
{
	std::cout << "GREP\n";
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