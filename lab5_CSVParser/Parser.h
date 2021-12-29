#pragma once

#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <tuple>
#include <sstream>
#include "ParserExceptions.h"
#include "PrintTuple.h"

template <typename... Args>
class Parser
{
private:
	std::ifstream& _input;
	int _index;
	char _column_delimiter;
	char _line_delimiter;
	char _field_delimiter;
	enum class parsing_state
	{
		READING,
		DELIMITER_READING
	};

public:
	class Iterator
	{
	private:
		Parser<Args...>& _parser;
		std::string _string_buffer;
		std::vector<std::string> _vector_buffer;
		std::vector<int> _pos;
		int _curr_line;
		int _curr_column = 0;
		bool _is_last = false;

		void StringToVector();

		std::tuple<Args...> VectorToTuple()
		{
			_curr_column = 0;
			if (sizeof...(Args) != _vector_buffer.size())
			{
				throw ParserException(
					"Error: tuple size != line elements", _curr_line, _curr_column);
			}
			std::tuple<Args...> tup;
			ForEach(tup, std::index_sequence_for<Args...>(), _vector_buffer, _curr_column, _curr_line);
			return tup;
		}

		template <std::size_t... I>
		void ForEach(std::tuple<Args...>& t, std::index_sequence<I...>,
			std::vector<std::string> parsed_line, int& curr_index, int& line_number)
		{
			((MakeTuple(std::get<I>(t), parsed_line[I], curr_index, line_number)), ...);
		}

	public:
		Iterator(Parser& parser, int index) : _parser(parser)
		{
			_curr_line = index;
			int i = 0;
			while (i < _curr_line
				&& std::getline(_parser._input, _string_buffer, _parser._line_delimiter))
			{
				++i;
			}
			if (!std::getline(_parser._input, _string_buffer, _parser._line_delimiter))
			{
				throw std::runtime_error("Error: invalid line number");
			}
		}

		explicit Iterator(Parser& parser, bool is_last) : _parser(parser)
		{
			_curr_line = 0;
			_is_last = is_last;
		}

		Iterator operator++()
		{
			_curr_line++;
			if (!std::getline(_parser._input, _string_buffer, _parser._line_delimiter))
			{
				_is_last = true;
			}
			return *this;
		}

		bool operator==(Iterator other)
		{
			return this->_is_last == other._is_last;
		}

		bool operator!=(Iterator other)
		{
			return !(*this == other);
		}

		std::tuple<Args...> operator*()
		{
			this->StringToVector();
			return this->VectorToTuple();
		}
	};

	Iterator begin()
	{
		return Iterator(*this, _index);
	}

	Iterator end()
	{
		bool is_end = true;
		return Iterator(*this, is_end);
	}

	Parser(std::ifstream& file, int index = 0)	: _input(file)
	{
		if (index <= 0)
			throw std::runtime_error("Error: invalid line number");
		_index = index - 1;
		_column_delimiter = ',';
		_line_delimiter = '\n';
		_field_delimiter = '"';
	}

	void SetDelimiters(char column_delimiter, char line_delimiter, char field_delimiter)
	{
		_column_delimiter = column_delimiter;
		_line_delimiter = line_delimiter;
		_field_delimiter = field_delimiter;
	}

	char GetColumnDel()
	{
		return _column_delimiter;
	}

	char GetFieldDel()
	{
		return _field_delimiter;
	}

	template <typename T>
	static void MakeTuple(T& t, std::string& str, int& curr_index, int& line_number)
	{
		std::istringstream stream(str);
		if ((stream >> t).fail())
		{
			throw ParserException("Error: incorrect field", line_number, curr_index);
		}
		curr_index++;
	}
};

template <typename... Args>
void Parser<Args...>::Iterator::StringToVector()
{
	char field_delimiter = _parser.GetFieldDel();
	char column_delimiter = _parser.GetColumnDel();
	std::string str = "";
	_vector_buffer.clear();
	_curr_column = 0;
	auto state = parsing_state::READING;
	for (char symbol : _string_buffer)
	{
		switch (state)
		{
		case parsing_state::READING:
			if (symbol == column_delimiter)
			{
				_vector_buffer.push_back(str);
				++_curr_column;
				str = "";
			}
			else if (symbol == field_delimiter)
			{
				state = parsing_state::DELIMITER_READING;
			}
			else
			{
				str = str + symbol;
			}
			break;

		case parsing_state::DELIMITER_READING:
			if (symbol == field_delimiter)
			{
				state = parsing_state::READING;
			}
			else
			{
				str = str + symbol;
			}
			break;
		default:
			break;
		}
	}
	if (state == parsing_state::DELIMITER_READING)
	{
		throw ParserException("Error: incorrect number of field delimiters", _curr_line, _curr_column);
	}
	else if (!str.empty())
	{
		_vector_buffer.push_back(str);
	}
}

