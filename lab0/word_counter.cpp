#include "word_counter.h"

namespace
{
    bool IsDelimiter(char symbol)
    {
        return !((symbol >= '0' && symbol <= '9') || (symbol >= 'A' && symbol <= 'Z') || (symbol >= 'a' && symbol <= 'z'));
    }
    void AddToMap(std::map<std::string, int>& temp_map, std::string word)
    {
        if (temp_map.find(word) != temp_map.end())
        {
            temp_map[word]++;
        }
        else
        {
            temp_map[word] = 1;
        }
    }
    bool Comparator(std::pair<std::string, int> a, std::pair<std::string, int> b)
    {
        return a.second > b.second;
    }
}

namespace Module1
{
    void CountWords::PrintInFile(std::string file_name)
    {
        std::ofstream fout(file_name);
        for (auto it = word_list.begin(); it != word_list.end(); ++it)
        {
            fout << it->first << ";" << it->second << ";"
                << 1. * it->second / amount_words * 100 << "%" << std::endl;
        }
    }
    void CountWords::ReadFile(std::string file_name)
    {
        std::ifstream in(file_name);
        amount_words = 0;
        std::map <std::string, int> word_map;
        char symbol;
        std::string word = "";
        while ((symbol = in.get()) != EOF)
        {
            if (IsDelimiter(symbol))
            {
                if (word.length() > 0)
                {
                    amount_words++;
                    AddToMap(word_map, word);
                    word = "";
                }
            }
            else
            {
                {
                    word += symbol;
                }
            }
        }
        if (word.length() > 0)
        {
            amount_words++;
            AddToMap(word_map, word);
        }
        for (auto i = word_map.begin(); i != word_map.end(); i++)
        {
            word_list.push_back(make_pair(i->first, i->second));
        }
        word_list.sort(Comparator);
    }
}
