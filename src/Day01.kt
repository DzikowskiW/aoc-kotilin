import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val rows = input.map { row -> row.split("   ").map{ it.toInt() } }
        val left = rows.map { it[0] }.sorted()
        val right = rows.map { it[1] }.sorted()
        return left.zip(right).fold(0) { sum, row -> sum + abs(row.first - row.second) }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val testInput = readInput("Day01_test")
    part1(testInput).println()
    check(part1(testInput) == 11)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
//    part2(input).println()
}
