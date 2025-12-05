package year2025

import println
import readInput


fun main() {

    fun part1(input: List<String>): Int {
        var isIng = false
        val ingredients = mutableSetOf<Long>()
        val ranges = mutableSetOf<Pair<Long, Long>>()
        input.forEach { line ->
            if (line == "") isIng=true
            else if (isIng) ingredients.add(line.toLong())
            else {
                val r= line.split("-")
                ranges.add(Pair(r.first().toLong(),r.last().toLong()))
            }
        }
        return ingredients.filter { ing -> ranges.any { (x,y) -> (ing in x..y) } }.size
    }

    fun part2(input: List<String>): Long {
        var isIng = false
        val ingredients = mutableSetOf<Long>()
        val ranges = mutableSetOf<Pair<Long, Long>>()
        input.forEach { line ->
            if (line == "") isIng=true
            else if (isIng) ingredients.add(line.toLong())
            else {
                var (l,r)= line.split("-").map { it.toLong() }
                //check if it is within other range
                if (!ranges.any{ (x,y) -> l>=x && r<= y}) {
                    //check if there are ranges within
                    ranges.removeAll(ranges.filter { (x,y) -> x>= l && y <= r }.toSet())
                    val lrange = ranges.firstOrNull() { (x,y) -> (l in (x..y)) }
                    if (lrange != null) l = lrange.second + 1L
                    val rrange = ranges.firstOrNull() { (x,y) -> (r in (x..y)) }
                    if (rrange != null) r = rrange.first - 1L

                    if (l <= r) {
                        ranges.add(Pair(l,r))
                    }
                }
            }
        }
        return ranges.fold(0L) { acc, (l,r) ->
            acc + r - l + 1
        }
    }

    val testInput = readInput("Day05_test")
        check(part1(testInput) == 3)
        check(part2(testInput) == 14L)

    val input = readInput("Day05")
        part1(input).println()
        part2(input).println()
}
