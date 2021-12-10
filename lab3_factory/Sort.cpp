#include "Sort.h"

uint Sort::number_arguments()
{
	return 0;
}
std::list<std::string>Sort::execute(std::list<std::string>& text, const std::vector<std::string>& args)
{
	std::cout << "SORT\n";
	if (number_arguments() != args.size())
	{
		throw MyException("incorrect number of arguments in block Sort");
	}
	text.sort();
	return text;
}
BlockType Sort::GetType()
{
	return BlockType::INOUT;
}

Block* SortFactory::CreateBlock()
{
	return new Sort();
}
