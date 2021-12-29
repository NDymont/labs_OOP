#include <iostream>
#include <fstream>
#include "Parser.h"

int main()
{   
    try
    {
        std::ifstream input;
        input.open("input1.csv");
        if (!input.is_open())
        {
            throw std::runtime_error("Cannot open the file!");
        }

        Parser<int, std::string, double, char> parser(input, 1);
        for (std::tuple<int, std::string, double, char> it : parser)
        {
            std::cout << it << std::endl;
        }
    }
    catch (std::exception& e)
    {
        std::cout << e.what() << std::endl;
    }

    try
    {
        std::ifstream input;
        input.open("input2.csv");
        if (!input.is_open())
        {
            throw std::runtime_error("Cannot open the file!");
        }

        Parser<int, std::string, double, char> parser(input, 1);
        parser.SetDelimiters(';', '^', '\\');
        for (std::tuple<int, std::string, double, char> it : parser)
        {
            std::cout << it << std::endl;
        }
    }
    catch (std::exception& e)
    {
        std::cout << e.what() << std::endl;
    }
}