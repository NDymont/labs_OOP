#include "word_counter.h"

int main()
{
    Module1::CountWords count_words;
    count_words.ReadFile("book.txt");
    count_words.PrintInFile("out.csv");
    return 0;
}