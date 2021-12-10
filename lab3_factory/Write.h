#pragma once
#include <iostream>
#include "Block.h"
#include "BlockFactory.h"
#include "MyException.h"
#include <fstream>


class Write : public Block
{
	uint number_arguments() override;
	std::list<std::string> execute(std::list<std::string>& text, const std::vector<std::string>& args) override;
	BlockType GetType() override;
};

class WriteFactory : public BlockFactory
{
	Block* CreateBlock() override
	{
		return new Write();
	}
};