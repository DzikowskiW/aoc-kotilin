fun main() {


    fun part1(input: List<String>):Int  {
        val rulesGraph = hashMapOf<String, MutableList<String>>()

        fun checkIfValid(pageNums:List<String>): Boolean {
            for (i in 1..<pageNums.size) {
                if (rulesGraph[pageNums[i-1]]?.contains(pageNums[i]) != true) {
//                    println("fail ${pageNums[i-1]} does not contain ${pageNums[i]}")
                    return false
                }
            }
            return true
        }

        // read rules
        var i = 0
        while (input[i] != "") {
            val row = input[i]
            val (left, right) = row.split("|")
            rulesGraph.getOrPut(left) { mutableListOf() }.add(right)
            i += 1
        }
        rulesGraph.println()

        //check pages
        i += 1
        var result = 0
        while (i < input.size) {
            val pages = input[i].split(",")
            if (checkIfValid(pages)){
                result += pages[pages.size / 2].toInt()
            }
            i += 1
        }
        return result
    }

    fun part2(input: List<String>):Int {
        return 0
    }

    val testInput = readInput("Day05_test")

//    check(part1(testInput) == 143)
//    part1(testInput).println()
//    check(part2(testInput) == 9)

    val input = readInput("Day05")
        part1(input).println()
//        part2(input).println()
}
