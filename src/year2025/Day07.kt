package year2025

import println
import readInput



fun main() {
    fun part1(input: List<String>): Int {
        val splitters = mutableSetOf<Pair<Int, Int>>()
        val checkedSplitters = mutableSetOf<Pair<Int, Int>>()
        val checkedPoints = mutableSetOf<Pair<Int, Int>>()
        val start = Pair(0, input.first().indexOfFirst { it == 'S' })
        val maxY = input.size
        val maxX = input[0].length

        fun split(point:Pair<Int, Int>):Int {
            if (point in checkedPoints) return 0
            val (y,x) = point
            if (y > maxY) return 0
            if (x !in (0..maxX)) return 0
            checkedPoints.add(point)
            if (point in splitters) {
                checkedSplitters.add(point)
                return 1 + split(Pair(y,x-1 )) + split(Pair(y, x+1))
            } else {
                return split(Pair(y+1, x))
            }
        }

        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                if (c == '^') {
                    splitters.add(Pair(y,x))
                }
            }
        }
//        splitters.println()
//        start.println()
        return split(start)
    }

    fun part2(input: List<String>): Int {
        return 0
    }


    val testInput = readInput("Day07_test")
        part1(testInput).println()
//        part2(testInput).println()

    val input = readInput("Day07")
        part1(input).println()
//        part2(input).println()
}
