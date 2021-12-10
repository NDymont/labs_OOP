#pragma once
#include <iostream>
#include "Block.h"
#include "Output.h"
#include "BlockFactory.h"
#include "MyException.h"
#include <fstream>


class Write : public Output
{
	BlockType GetType() override;
};

class WriteFactory : public BlockFactory
{
	Block* CreateBlock() override
	{
		return new Write();
	}
};