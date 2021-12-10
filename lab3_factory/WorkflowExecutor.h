#pragma once
#include <istream>
#include <iostream>
#include <list>
#include "MyException.h"
#include "WorkflowParser.h"
#include "BlockFactory.h"
#include "Maker.h"


class WorkflowExecutor
{
	bool ChekBlocksType(std::list <std::pair<std::shared_ptr<Block>, std::vector<std::string>>>& block_objects);
public:
	void ExecuteWorkflow(std::string file_name);
};