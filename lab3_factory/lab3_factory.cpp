#include <iostream>
#include "WorkflowExecutor.h"

void IntToString(std::string& str, int number)
{
    str = "";
    while (number > 0)
    {
        str = (char)(number % 10 + '0') + str;
        number /= 10;
    }
}

int main()
{
    /*std::string str = "aaa";
    str = str + "bbb";
        IntToString(str, 123456);
    std::cout << str;*/


    WorkflowExecutor executor;
    try
    {
        executor.ExecuteWorkflow("workflow.txt");
    }
    catch (const std::exception& ex)
    {
        std::cout << ex.what();
    }
}