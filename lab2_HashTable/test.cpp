#include "gtest/gtest.h"
#include "HashTable.h"

Value Create(int a, int w)
{
	Value data;
	data.set_student(a, w);
	return data;
}

TEST(TestCaseName, Test)
{
	HashTable table1, table2;
	EXPECT_TRUE(table1 == table2);
	EXPECT_EQ(table1.get_size(), 0);
	table1.insert("1", Create(1, 10)); //t1: 1
	EXPECT_FALSE(table1.insert("1", Create(1, 10)));
	table1.insert("2", Create(2, 20)); //t1: 1-(1, 10)  2-(2, 20)

	table2.insert("1", Create(1, 10));
	table2.insert("2", Create(2, 20)); //t2: 1-(1, 10)  2-(2, 20)
	EXPECT_TRUE(table1 == table2);
	table2.erase("2");				   //t2: 1-(1, 10)
	EXPECT_TRUE(table1 != table2);
	table2.insert("2", Create(2, 200)); //t2: 1-(1, 10)  2-(2, 200)
	EXPECT_TRUE(table1 != table2);
	EXPECT_TRUE(table2.contains("2"));
	table2.erase("2");
	EXPECT_FALSE(table2.contains("2"));
	EXPECT_ANY_THROW(table2.at("2"));
	EXPECT_NO_THROW(table2.at("1"));
	table2.clear();					  //t2: 
	EXPECT_TRUE(table2.empty());
	EXPECT_FALSE(table1.empty());
	table2.insert("2", Create(2, 20));
	table2.insert("3", Create(3, 30));
	table2.insert("4", Create(4, 40));//t2: 2 3 4

	HashTable table1_copy;
	table1_copy = table1;
	HashTable table2_copy(table2);

	EXPECT_TRUE(table1 == table1_copy);
	EXPECT_TRUE(table2 == table2_copy);

	table2.swap(table1);
	EXPECT_TRUE(table1 == table2_copy);
	EXPECT_TRUE(table2 == table1_copy);
	table2.swap(table1);

	HashTable table1_moved(std::move(table1));
	EXPECT_TRUE(table1.empty());
	EXPECT_TRUE(table1.no_memory());
	EXPECT_FALSE(table1 == table1_moved);
	EXPECT_TRUE(table1_moved == table1_copy);
	EXPECT_ANY_THROW(table1.insert("aa", Create(11, 111)));

	HashTable table2_moved;
	table2_moved = std::move(table2);
	EXPECT_TRUE(table2.empty());
	EXPECT_TRUE(table2.no_memory());
	EXPECT_FALSE(table2 == table2_moved);

	table1 = std::move(table1_moved); //t1: 1-(1, 10) 2-(2, 20)

	table1["1"] = Create(11, 110);   //t1: 1-(11, 110) 2-(2, 20)
	EXPECT_EQ(table1["1"], Create(11, 110));
	EXPECT_EQ(table1.at("1"), Create(11, 110));

	table1.at("2") = Create(22, 220); //t1: 1-(11, 110) 2-(22, 220)
	EXPECT_EQ(table1["2"], Create(22, 220));
	EXPECT_EQ(table1.at("2"), Create(22, 220));

	table1.clear();
	EXPECT_TRUE(table1.empty());
	EXPECT_FALSE(table1.no_memory());
}