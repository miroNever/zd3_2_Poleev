package com.example.tvapplication

data class Quests(
    val image: Int,
    val title: String,
    val text: String
)

class MyObj(
    val list: ArrayList<Quests> = arrayListOf(
        Quests(
            R.drawable.kazan,
            "Welcome to Kazan",
            "Now you must go to Kazan Expo and find next key. And there are many more interesting information"
        ),
        Quests(
            R.drawable.kazan,
            "Welcome to Kazan",
            "Now you must go to Kazan Expo and find next key. And there are many more interesting information"
        ),
        Quests(
            R.drawable.kazan,
            "Welcome to Kazan",
            "Now you must go to Kazan Expo and find next key. And there are many more interesting information"
        ),
        Quests(
            R.drawable.kazan,
            "Welcome to Kazan",
            "Now you must go to Kazan Expo and find next key. And there are many more interesting information"
        ),
        Quests(
            R.drawable.kazan,
            "Welcome to Kazan",
            "Now you must go to Kazan Expo and find next key. And there are many more interesting information"
        ),
        Quests(
            R.drawable.kazan,
            "Welcome to Kazan",
            "Now you must go to Kazan Expo and find next key. And there are many more interesting information"
        )
    )
)
