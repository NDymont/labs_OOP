#include "WorkflowExecutor.h"

bool WorkflowExecutor::ChekBlocksType(std::list<std::pair<std::shared_ptr<Block>, std::vector<std::string>>>& block_objects)
{
	int number_blocks = block_objects.size();
	int i = 1;
	for (auto block_object : block_objects)
	{
		if (i == 1)
		{
			if (block_object.first->GetType() != BlockType::IN)
			{
				return false;
			}
		}
		else if (i == number_blocks)
		{
			if (block_object.first->GetType() != BlockType::OUT)
			{
				return false;
			}
		}
		else
		{
			if (block_object.first->GetType() != BlockType::INOUT)
			{
				return false;
			}
		}
		++i;
	}
	return true;
}

void WorkflowExecutor::ExecuteWorkflow(std::string file_name)
{
	WorkflowParser parser;
	auto blocks = parser.GetBlocks(file_name);
	std::list <std::pair<std::shared_ptr<Block>, std::vector<std::string>>> block_objects;
	for (auto block : blocks)
	{
		Block* block_object = Maker::Instance().GetBlock(block.first);
		block_objects.emplace_back(block_object, block.second);
	}
	if (!ChekBlocksType(block_objects))
	{
		throw MyException("wrong type");
	}
	std::list<std::string> text;
	for (auto block_object : block_objects)
	{
		text = block_object.first->execute(text, block_object.second);
	}
}