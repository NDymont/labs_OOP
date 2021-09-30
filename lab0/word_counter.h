#pragma once
#include <iostream>
#include <string>
#include <map>
#include <fstream>
#include <cstring>
#include <list>

namespace Module1
{
    class CountWords
    {
        int amount_words;
    public:
        std::list <std::pair<std::string, int>> word_list;
        void PrintInFile(std::string file_name);
        void ReadFile(std::string file_name);
    };
}