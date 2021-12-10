#include "Replace.h"

uint Replace::number_arguments() 
{
	return 2;
}

std::list<std::string> Replace::execute(std::list<std::string>& text, const std::vector<std::string>& args)
{
	std::cout << "REPLACE\n";
	if (number_arguments() != args.size())
	{
		throw MyException("incorrect number of arguments in block Replace");
	}
	for (auto it = text.begin(); it != text.end(); ++it)
	{
		int position;
		position = (*it).find(args[0], 0);
		while (position != std::string::npos)
		{
			(*it).replace(position, args[0].length(), args[1]);
			position = (*it).find(args[0], position + args[1].length());
		}
	}
	return text;
}

BlockType Replace::GetType()
{
	return BlockType::INOUT;
}

Block* ReplaceFactory::CreateBlock()
{
	return new Replace();
}