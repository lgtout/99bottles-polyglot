object BeerSong {
    fun verses(start: Int, end: Int): String {
        return (start downTo end).joinToString(separator = "\n") {
            bottleVerse(it)
        }
    }

    private fun bottleVerse(bottleCount: Int): String {

        fun bottleCount(count: Int, zeroCount: String): String {

            fun bottles(count: Int): String {
                return if (count > 1 || count == 0) "bottles"
                else "bottle"
            }
            fun firstLineBottleCount(count: Int, zeroCount: String): String {
                return if (count == 0) zeroCount
                else count.toString()
            }

            return "${firstLineBottleCount(count, zeroCount)} ${bottles(count)}"
        }

        val noMore = "No more"
        val lowerCaseNoMore = noMore.toLowerCase()

        fun firstLine(count: Int): String {
            return "${bottleCount(count, noMore)} of beer on the wall, " +
                    "${bottleCount(count, lowerCaseNoMore)} of beer."
        }

        fun secondLine(bottleCount: Int, remainder: Int): String {

            fun one(remainder: Int): String {
                return if (remainder == 0) "it" else "one"
            }

            fun secondLineAction(bottleCount: Int, remainder:Int): String {
                return if (bottleCount == 0) "Go to the store and buy some more"
                else "Take ${one(remainder)} down and pass it around"
            }

            fun secondLineResult(bottleCount: Int): String {
                return "${bottleCount(bottleCount, lowerCaseNoMore)} of beer on the wall."
            }

            return "${secondLineAction(bottleCount, remainder)}, ${secondLineResult(remainder)}"
        }

        val remainder = if (bottleCount == 0) 99 else bottleCount - 1
        return "${firstLine(bottleCount)}\n${secondLine(bottleCount, remainder)}\n"
    }
}