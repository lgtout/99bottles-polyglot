class BeerSong(private val start: Int, private val end: Int) {

    val string: String
        get() = (start downTo end).joinToString(separator = "\n") { Verse(it).string }

    companion object {
        fun verses(start: Int, end: Int): String {
            return BeerSong(start, end).string
        }
    }

    class BottleCount(private val count: Int, private val zeroCount: String) {

        private fun bottles(): String {
            return if (count > 1 || count == 0) "bottles"
            else "bottle"
        }
        private fun firstLineBottleCount(): String {
            return if (count == 0) zeroCount else count.toString()
        }

        val string: String
            get() = "${firstLineBottleCount()} ${bottles()}"

    }

    class FirstLine(
        private val firstBottleCount: BottleCount,
        private val secondBottleCount: BottleCount) {

        val string: String
            get() = "${firstBottleCount.string} of beer on the wall, " +
                    "${secondBottleCount.string} of beer."

    }

    class SecondLine(
        private val count: Int, private val remainder: Int,
        private val remainderBottleCount: BottleCount) {

        private val one: String
            get() = if (remainder == 0) "it" else "one"

        private val secondLineAction: String
            get() = if (count == 0) "Go to the store and buy some more"
            else "Take $one down and pass it around"

        private val secondLineResult: String
            get() = "${remainderBottleCount.string} of beer on the wall."

        val string: String
            get() = "$secondLineAction, $secondLineResult"

    }

    class Verse(private val bottleCount: Int) {

        private val noMoreBottleCount: BottleCount
        private val lowerCaseNoMoreBottleCount: BottleCount
        private val remainderBottleCount: BottleCount
        private val remainder: Int = if (bottleCount == 0) 99 else bottleCount - 1

        init {
            val noMore = "No more"
            val lowerCaseNoMore = noMore.toLowerCase()

            noMoreBottleCount = BottleCount(bottleCount, noMore)
            lowerCaseNoMoreBottleCount = BottleCount(bottleCount, lowerCaseNoMore)
            remainderBottleCount = BottleCount(remainder, lowerCaseNoMore)
        }

        val string: String
            get() = "${FirstLine(noMoreBottleCount, lowerCaseNoMoreBottleCount).string}\n" +
                    "${SecondLine(bottleCount, remainder, remainderBottleCount).string}\n"

    }

}