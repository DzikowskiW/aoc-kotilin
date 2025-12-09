package year2025

import println
import readInput
import kotlin.math.abs


fun main() {
    data class Vec2d(val y: Long, val x: Long)
    fun part1(input: List<String>): Long {
        val tiles = mutableSetOf<Vec2d>()
//        input.forEachIndexed { x, line -> line.forEachIndexed { y, c ->
//            if (c == '#') tiles.add(Vec2d(y,x))
//        }}
        input.forEach {
            val (yy, xx) = it.split(",").map { it.toLong() }
            tiles.add(Vec2d(yy,xx))
        }
//        tiles.println()
        val combinations = mutableSetOf<Pair<Vec2d,Vec2d>>()
        tiles.forEach { t -> tiles.forEach {
            if (t != it){
                combinations.add(Pair(it, t))
            }
        } }
        return combinations.map { (o1, o2) -> abs(o1.y - o2.y+1L) * abs(o1.x - o2.x+1L) }.maxOf { it }
    }



    val testInput = readInput("Day09_test")
    part1(testInput).println()
//    check(part2(testInput) == 43)

    val input = readInput("Day09")
    part1(input).println()
//    part2(input).println()
}
