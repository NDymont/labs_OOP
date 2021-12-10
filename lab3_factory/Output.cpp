#include "Output.h"

uint Output::number_arguments()
{
	return 1;
}

std::list<std::string> Output::execute(std::list<std::string>& text, 
	const std::vector<std::string>& args)
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