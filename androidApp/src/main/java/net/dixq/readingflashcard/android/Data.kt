package net.dixq.readingflashcard.android

object Data {
    val allWords = listOf(
        //    listOf("act","add","age","ago","aim","air","ale","all","and","ant","any","ape","apple","arc","are","arm","art","as","ask","at"),
        listOf(
            "[How] [are] [you]?",
            "[Hear] [you] [go].",
            "[What] [do] [you] [want]?",
            "[I'm] [putting] [the box]."
        ),
        listOf(
            "[Are] [you] [OK]?",
            "[I] [will] [go] [there].",
            "[Please] [say] [it] [again].",
            "[Let's] [go] [to] [the bed].",
        ),
        listOf(
            "[See] [you] [again].",
            "[I'm] [putting] [the blocks].",
            "[I] [want] [to] [play]",
            "[in] [my] [world].",
        ),
        listOf(
            "[Should] [we] [go] [to] [the bed]?",
            "[Follow] [me].",
            "[I'm] [fighting] [the enemy].",
            "[I] [did] [it]!",
        ),
        listOf(
            "[Can] [you] [give] [me] [the block]?",
            "[Let's] [fight] [the enemy].",
            "[Can] [I] [take] [the box]?",
            "[How] [about] [you]?",
        ),
        listOf(
            "[I'm] [putting] [the blocks].",
            "[I] [want] [to] [play]",
            "[in] [my] [world].",
            "[How] [are] [you]?",
        ),
        listOf(
            "[What] [do] [you] [want] [to] [do]?",
            "[I] [want] [to] [make] [a house].",
            "[What] [are] [you] [doing]?",
            "[I'm] [fighting] [the enemy].",
        ),
        listOf(
            "[I] [eat] [an] [apple].",
            "[I] [ate] [an] [apple].",
            "[We] [did] [it]!",
            "[You] [can] [do] [it]!",
        ),
        listOf(
            "[What] [did] [you] [eat] [for] [dinner]?",
            "[I] [ate] [sashimi].",
            "[What] [did] [you] [do] [today]?",
            "[I] [played] [tennis].",
        ),
        listOf(
            "[Who] [cooked] [dinner] [tonight]?",
            "[Daddy] [cooked] [dinner] [tonight].",
            "[Who] [build] [this] [tower]?",
            "[I] [built] [this] [tower].",
        ),
        listOf(
            "[What] [color] [do] [you] [like]?",
            "[I] [like] [black].",
            "[What] [food] [do] [you] [like]?",
            "[I] [like] [ice] [cream].",
        ),
        listOf(
            "[Can] [you] [hear] [me]?",
            "[Yes] [I] [can].",
            "[Can] [you] [come] [here]?",
            "[Yes] [I] [can].",
        ),
        listOf(
            "[Do] [you] [like] [it]?",
            "[Yes] [I] [do].",
            "[Do] [you] [want]?",
            "[Yes] [I] [do].",
        ),
        listOf(
            "[Can] [I] [help] [you]?",
            "[Yes] [you] [can].",
            "[Can] [I] [call] [you]?",
            "[Yes] [you] [can].",
        ),

    )

    fun getWordsAndSentences(index: Int): List<String> {
        if(index >= allWords.size){
            return listOf()
        }
        val contentsList = mutableListOf<String>()
        for(i in allWords[index].indices){
            val str = allWords[index][i]
            contentsList.addAll(convertToStringArray(str))
            contentsList.add(convertToSentence(str))
        }
        return contentsList
    }

    fun getTitles(): List<String> {
        val titleList = mutableListOf<String>()
        for(i in allWords.indices){
            var string = ""
            for(j in allWords[i].indices){
                string += convertToSentence(allWords[i][j]) + " "
            }
            titleList.add(string)
        }
        return titleList
    }

    fun getAlbumSize(){

    }

    private fun convertToStringArray(str: String): MutableList<String> {
        return Regex("\\[(.*?)\\]").findAll(str).map { it.groupValues[1] }.toMutableList()
    }

    private fun convertToSentence(str: String): String {
        return str.replace(Regex("\\[|\\]"), "")
    }

}