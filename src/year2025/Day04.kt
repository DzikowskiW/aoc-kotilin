package year2025

import println
import readInput


fun main() {

    fun part1(input: List<String>): Int {
        val rolls = mutableSetOf<Pair<Int,Int>>()
        input.forEachIndexed { x, line -> line.forEachIndexed { y, c ->
            if (c == '@') rolls.add(Pair(y,x))
        }}

        return rolls.fold(0){ acc, (y,x) ->
            var neighbors = 0
            for (yy in y-1..y+1)
                for (xx in x-1..x+1)
                    if (rolls.contains(Pair(yy,xx))) neighbors += 1
            if (neighbors <= 4) acc + 1 else acc
        }
    }

    fun part2(input: List<String>): Int {
        val rolls = mutableMapOf<Pair<Int, Int>, Int>()
        val queue = mutableListOf<Pair<Int, Int>>()
        val deleted = mutableSetOf<Pair<Int,Int>>()
        input.forEachIndexed { x, line -> line.forEachIndexed { y, c ->
            if (c == '@') rolls[Pair(y,x, )] = 0
        }}
        rolls.forEach { (y,x), _ ->
            var neighbors = 0
            for (yy in y-1..y+1)
                for (xx in x-1..x+1)
                    if (rolls.contains(Pair(yy,xx))) neighbors += 1
            if (neighbors <= 4) queue.add(Pair(y,x))
            rolls[Pair(y,x)] = neighbors - 1
        }
        while (queue.isNotEmpty()) {
            val (y,x) = queue.removeFirst()
            if (deleted.contains(Pair(y,x))) continue
            deleted.add(Pair(y,x))
            for (yy in y-1..y+1)
                for (xx in x-1..x+1)
                    if (rolls.contains(Pair(yy,xx)) && !deleted.contains(Pair(yy,xx))) {
                        rolls[Pair(yy, xx)] = rolls[Pair(yy, xx)]!! - 1
                        if (rolls[Pair(yy,xx)]!! <= 3) queue.add(Pair(yy,xx))
                    }
        }

        return deleted.count()
    }


    val testInput = readInput("Day04_test")

        check(part1(testInput) == 13)
        check(part2(testInput) == 43)

    val input = readInput("Day04")
        part1(input).println()
        part2(input).println()
}
