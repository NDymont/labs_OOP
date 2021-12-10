#pragma once
#include <iostream>
#include <fstream>
#include "Block.h"
#include "BlockFactory.h"
#include "MyException.h"


class Output : public Block
{
	uint number_arguments() override;
	std::list<std::string> execute(std::list<std::string>& text, const std::vector<std::string>& args) override;
	virtual BlockType GetType() = 0;
};