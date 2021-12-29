#pragma once
#include <iostream>
#include <tuple>
#include <sstream>

template <size_t StartIndex, size_t EndIndex, typename... Types>
static void PrintTuple(std::ostream& os, const std::tuple<Types...>& tpl)
{
    if constexpr (StartIndex != EndIndex)
    {
        auto& e = std::get<StartIndex>(tpl);
        os << e;
    }
    if constexpr (StartIndex + 1 != EndIndex)
    {
        os << " | ";
        PrintTuple<StartIndex + 1, sizeof...(Types)>(os, tpl);
    }
}

template <typename... Types>
std::ostream& operator<<(std::ostream& os, std::tuple<Types...> const& tp1)
{
    os << "[";
    PrintTuple<0, sizeof...(Types)>(os, tp1);
    os << "]";
    return os;
}