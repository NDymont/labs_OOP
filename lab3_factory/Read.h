#pragma once
#include <iostream>
#include <exception> 
#include <string>
#include <fstream>
#include"Block.h"
#include"BlockFactory.h"
#include"MyException.h"

class Read : public Block
{
	uint number_arguments() override;
	std::list<std::string> execute(std::list<std::string>& text, const std::vector<std::string>& args);
	BlockType GetType() override;
};

class ReadFactory : public BlockFactory
{
	Block* CreateBlock() override;
};