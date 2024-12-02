import kotlin.math.abs

fun main() {

    fun List<Int>.subLists(): List<List<Int>> {
        val lists = mutableListOf<List<Int>>()
        for (i in 1..this.size) {
            lists.add(subList(0, i - 1) + this.subList(i, this.size))
        }
        return lists
    }

    fun checkRow(row:List<Int>):Boolean {
        val slope = row[1] - row[0] > 0
        return row.withIndex().all { (i, el) ->
            i == 0 || ( abs(el - row[i-1]) in 1..3   && slope == (el-row[i-1] > 0))
        }
    }

    fun part1(input: List<String>): Int {
        val rows = input.map { row -> row.split(" ").map{ it.toInt() } }
        return rows.filter { row -> checkRow(row) }.size
    }

    fun part2(input: List<String>): Int {
        val rows = input.map { row -> row.split(" ").map{ it.toInt() } }
        return rows.filter { row -> row.subLists().any { checkRow(it) } }.size
    }

    val testInput = readInput("Day02_test")
      check(part1(testInput) == 2)
      check(part2(testInput) == 4)

    val input = readInput("Day02")
      part1(input).println()
      part2(input).println()
}
