#include "WorkflowParser.h"

void WorkflowParser:: FillComandList()
{
	while (!input.eof())
	{
		std::string str;
		getline(input, str);
		if (str == "desc")
		{
			continue;
		}
		if (str == "csed")
		{
			break;
		}
		std::string word = "";
		std::vector<std::string> parameters;
		for (uint i = 0; i <= str.length(); ++i)
		{
			if (str[i] == '_' || str[i] == '.' || (str[i] >= 'a' && str[i] <= 'z') ||
				(str[i] >= 'A' && str[i] <= 'Z') || (str[i] >= '0' && str[i] <= '9'))
			{
				word += str[i];
			}
			else if (word.length() != 0)
			{
				parameters.push_back(word);
				word = "";
			}
			if (i == str.length() && word.length() != 0)
			{
				parameters.push_back(word);
				word = "";
			}
		}
		std::string id;
		if (!parameters.empty())
		{
			id = parameters[0];
			parameters.erase(parameters.begin());
			command_list.push_back(std::make_pair(id, parameters));
		}
	}
}
void WorkflowParser::FillComandSequence()
{
	while (!input.eof())
	{
		std::string str;
		getline(input, str);
		std::string word = "";
		for (uint i = 0; i <= str.length(); ++i)
		{
			if (str[i] >= '0' && str[i] <= '9')
			{
				word += str[i];
			}
			if ((str[i] < '0' || str[i] > '9' || i == str.length()) && word.length() != 0)
			{
				command_sequence.push_back(word);
				word = "";
			}
		}
	}
}

std::vector<std::string> WorkflowParser::FindInstruction(std::string id)
{
	for (auto it = command_list.begin(); it != command_list.end(); ++it)
	{
		if (it->first == id)
		{
			return it->second;
		}
	}
	throw MyException("wrong id");
}

std::list<std::pair<std::string, std::vector<std::string>>> WorkflowParser::GetBlocks(std::string file_name)
{
	input.open(file_name);
	if (!input.is_open())
	{
		throw MyException("workflow file doesn't open");
	}
	FillComandList();
	FillComandSequence();
	input.close();
	std::list<std::pair<std::string, std::vector< std::string>>> ret; //пара: имя команды и параметры
	for (auto it = command_sequence.begin(); it != command_sequence.end(); ++it)
	{
		auto command = FindInstruction(*it);
		std::string name_function = command[0];
		command.erase(command.begin());
		ret.push_back(std::make_pair(name_function, command));
	}
	return ret;
}