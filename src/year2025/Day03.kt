package year2025

import println
import readInput


fun main() {
    fun calc(input: List<String>, len:Int): Long {
        var sum =0L
        input.forEach { line ->
            var num = 0L
            var start = 0
            for (j in len-1 downTo 0) {
                var max = 0
                for (i in (start..<line.length-j)) {
                    val cur = line.get(i).digitToInt()
                    if (cur > max) {
                        max = cur
                        start = i+1
                    }
                }
                num = num * 10 + max
            }
            sum += num
//            num.println()
        }
        return sum
    }

    fun part1(input: List<String>): Long {
        return calc(input, 2)
    }

    fun part2(input: List<String>): Long {
        return calc(input, 12)
    }


    val testInput = readInput("Day03_test")

        check(part1(testInput) == 357L)
        check(part2(testInput) == 3121910778619L)

    val input = readInput("Day03")
        part1(input).println()
        part2(input).println()
}
