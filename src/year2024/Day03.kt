package year2024

import println
import readInput

fun main() {

    fun part1(input: String) = Regex("mul\\((\\d\\d?\\d?),(\\d\\d?\\d?)\\)")
        .findAll(input)
        .fold(0) { sum, el ->
            sum + el.groupValues[1].toInt() * el.groupValues[2].toInt()
        }

    fun part2(input: String) = Regex("mul\\((\\d\\d?\\d?),(\\d\\d?\\d?)\\)|do\\(\\)|don't\\(\\)")
        .findAll(input)
        .fold(Pair(0, true)) { (sum, enabled), el ->
            when (el.groupValues[0]) {
                "do()" -> Pair(sum, true)
                "don't()" -> Pair(sum, false)
                else -> Pair(if (enabled) sum + el.groupValues[1].toInt() * el.groupValues[2].toInt() else sum, enabled)
            }
        }.first

    val testInput = readInput("Day03_test").joinToString(separator = "")
        check(part1(testInput) == 161)
        check(part2(testInput) == 48)

    val input = readInput("Day03").joinToString(separator = "")
        part1(input).println()
        part2(input).println()
}
