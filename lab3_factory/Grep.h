#pragma once
#include <iostream>
#include <string>
#include "MyException.h"
#include"Block.h"
#include"BlockFactory.h"


class Grep : public Block
{
	uint number_arguments() override;
	std::list<std::string> execute(std::list<std::string>& text, const std::vector<std::string>& args) override;
	BlockType GetType() override;
};

class GrepFactory : public BlockFactory
{
	Block* CreateBlock() override;
};