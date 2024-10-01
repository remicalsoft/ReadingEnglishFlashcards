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
        )
    )

    fun getWordsAndSentences(index: Int): List<String> {
        if(index >= allWords.size){
            return listOf()
        }
        val contentsList = mutableListOf<String>()
        for(i in allWords.indices){
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