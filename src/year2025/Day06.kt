package year2025

import println
import readInput


fun main() {

    fun part1(input: List<String>): Long {

        val grid = input.map { line ->
            line.trim().split("\\s+".toRegex())
        }
        val ops = grid.last()
        val nums = grid.dropLast(1)
//        ops.println()
//        nums.println()

        return ops.foldIndexed(0L) { i, sum,op ->
            var res = 0L
            val cols = nums.map{ line -> line[i] }
//            cols.println()
//            op.println()
            if (op == "*") {
                res = sum + cols.fold(1L) { acc, num -> acc * num.toLong() }
            }
            else if (op == "+") {
                res = sum + cols.fold(0L) { acc, num -> acc + num.toLong() }
            }
            else check(false)
            res
        }
    }



    val testInput = readInput("Day06_test")
        part1(testInput).println()
//        check(part2(testInput) == 14L)

    val input = readInput("Day06")
        part1(input).println()
//        part2(input).println()
}
