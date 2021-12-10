#pragma once
#include <iostream>
#include <fstream>
#include "Block.h"
#include "BlockFactory.h"
#include "MyException.h"


class Dump : public Block
{
	uint number_arguments() override;
	std::list<std::string> execute(std::list<std::string>& text, const std::vector<std::string>& args) override;
	BlockType GetType() override;
};

class DumpFactory : public BlockFactory
{
	Block* CreateBlock() override;
};