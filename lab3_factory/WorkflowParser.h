#pragma once
#include <cstdlib>
#include <iostream>
#include <fstream>
#include <list>
#include <string>
#include <vector>
#include "MyException.h"

typedef unsigned int uint;

class WorkflowParser
{
	std::list<std::string> command_sequence;
	std::list<std::pair <std::string, std::vector<std::string>>> command_list;
	std::ifstream input;
	void FillComandList();
	void FillComandSequence();
	std::vector<std::string> FindInstruction(std::string id);
	
public:
	std::list<std::pair<std::string, std::vector<std::string>>> GetBlocks(std::string file_name);
};