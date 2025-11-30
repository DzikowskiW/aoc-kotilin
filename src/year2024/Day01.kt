package year2024

import println
import readInput
import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val rows = input.map { row -> row.split("   ").map{ it.toInt() } }
        val left = rows.map { it[0] }.sorted()
        val right = rows.map { it[1] }.sorted()
        return left.zip(right).fold(0) { sum, row -> sum + abs(row.first - row.second) }
    }

    fun part2(input: List<String>): Int {
        val rows = input.map { row -> row.split("   ").map{ it.toInt() } }
        val left = rows.map { it[0] }.sorted()
        val right = rows.map { it[1] }.sorted()
        val freqMap = right.fold(HashMap<Int, Int>()) { map, num ->
            map[num] = map.getOrDefault(num, 0) + 1
            map
        }
        return left.fold(0) { sum, num ->
            sum + num * freqMap.getOrDefault(num, 0)
        }
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
