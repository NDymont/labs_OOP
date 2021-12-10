#pragma once
#include "Block.h"
#include <map>

class BlockFactory
{
public:
	virtual Block* CreateBlock() = 0;
	virtual ~BlockFactory() {}
};