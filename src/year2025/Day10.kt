package year2025

import println
import readInput


fun main() {
    fun part1(input: List<String>): Long {
        return input.fold(0L) { acc, line ->
            val lineSplit = line.split(" ").toMutableList()
            val target = lineSplit
                .removeFirst()
                .removeSurrounding("[", "]")
                .map{ it == '#'}
                .toMutableList()
            val jolts = lineSplit.removeLast()
                .removeSurrounding("{", "}")
                .split(",")
                .map { it.toInt() }
            val buttons = lineSplit.map {
                it.removeSurrounding("(", ")").split(",").map { it.toInt() }
            }
            if (target.all { !it }) return acc

            val checked = mutableSetOf<List<Boolean>>()
            val start = List(target.size) { false }
            val q = mutableListOf(Pair(0,start))
            var done = false
            var finalPresses = 0
            while (!done) {
                val (presses, cur) = q.removeFirst()
                if (cur == target) {
                    done = true
                    finalPresses = presses
                } else if (!checked.contains(cur)) {
                    checked.add(cur)
                    buttons.forEach { b ->
                        val next = cur.toMutableList()
                        b.forEach { next[it] = !next[it]  }
                        q.add(Pair(presses + 1, next))
                    }
                }
            }
            acc + finalPresses
        }
    }

    val testInput = readInput("Day10_test")
    part1(testInput).println()
//    part2(testInput).println()

    val input = readInput("Day10")
    part1(input).println()
//    part2(input).println()
}
