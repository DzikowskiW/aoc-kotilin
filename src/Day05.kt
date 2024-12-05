fun main() {

    fun bothParts(input: List<String>):Pair<Int, Int> {
        // create rulebook
        val rulesGraph = input
            .subList(0, input.indexOf(""))
            .map {row -> row.split("|") }
            .fold(hashMapOf<String, MutableList<String>>()) { rules, (left, right) ->
                rules.getOrPut(left) { mutableListOf() }.add(right)
                rules
            }

        // helpers
        fun checkIfValid(pages:List<String>) = pages.withIndex().all { (i, page) ->
            when (i) {
                0 -> true
                else -> rulesGraph[pages[i-1]]?.contains(page) == true
            }
        }

        fun reorder(pages: List<String>): List<String>  {
            val newOrder = mutableListOf(pages[0])
            for (i in 1..<pages.size) {
                var added = false
                for (j in newOrder.indices) {
                    if (rulesGraph[pages[i]]?.contains(newOrder[j]) == true) {
                        newOrder.add(j, pages[i])
                        added = true
                        break
                    }
                }
                if (!added) newOrder.add(pages[i])
            }
            return newOrder.toList()
        }

        //check pages
        return input.subList(input.indexOf("") + 1, input.size)
            .map { row -> row.split(",")}
            .fold(Pair(0,0)) { res, pages ->
                if (checkIfValid(pages)) Pair(res.first + pages[pages.size / 2].toInt(), res.second)
                else Pair(res.first, res.second + reorder(pages)[pages.size / 2].toInt())
            }

    }

    val testInput = readInput("Day05_test")
    check(bothParts(testInput) == Pair(143,123))

    val input = readInput("Day05")
    bothParts(input).println()
}
