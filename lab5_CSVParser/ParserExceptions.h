#pragma once

#include <iostream>
#include <exception>
#include <string>
#include <memory>

class ParserException : public std::exception
{
private:
    std::string _message;

public:
    ParserException(const char* message, int lineNumber, int columnNumber);
    const char* what() const noexcept override;
};

