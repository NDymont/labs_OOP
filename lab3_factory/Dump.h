#pragma once
#include <iostream>
#include <fstream>
#include "Block.h"
#include "Output.h"
#include "BlockFactory.h"
#include "MyException.h"


class Dump : public Output
{
	BlockType GetType() override;
};

class DumpFactory : public BlockFactory
{
	Block* CreateBlock() override;
};