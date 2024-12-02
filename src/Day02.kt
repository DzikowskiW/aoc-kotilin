import kotlin.math.abs

fun main() {

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
        return rows.filter { row ->
            var res = checkRow(row)
            var j = 1
            while (!res && j <= row.size) {
                val checkedRow = row.subList(0,j-1) + row.subList(j, row.size)
                check(checkedRow.size == row.size -1)
                res = checkRow(checkedRow)
                if (res) checkedRow.println()
                j += 1
            }
            res
        }.size
    }

    val testInput = readInput("Day02_test")
      check(part1(testInput) == 2)
      check(part2(testInput) == 4)

    val input = readInput("Day02")
      part1(input).println()
      part2(input).println()
}
