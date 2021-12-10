#pragma once
#include <iostream>
#include <string>
#include"Block.h"
#include"BlockFactory.h"
#include "MyException.h"


class Replace : public Block
{
	uint number_arguments() override;
	std::list<std::string> execute(std::list<std::string>& text, const std::vector<std::string>& args) override;
	BlockType GetType() override;
};

class ReplaceFactory : public BlockFactory
{
	Block* CreateBlock() override;
};