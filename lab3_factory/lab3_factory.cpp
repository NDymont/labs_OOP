#include <iostream>
#include "WorkflowExecutor.h"

int main()
{
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