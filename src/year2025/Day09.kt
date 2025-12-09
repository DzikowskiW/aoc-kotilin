package year2025

import println
import readInput
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min


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

    fun isOutside(rect: Pair<Vec2d,Vec2d>, line:Pair<Vec2d, Vec2d>): Boolean {
        val (o1,o2) = line
        val (p1,p2) = rect

        val miny = min(o1.y,o2.y)
        val minx = min(o1.x,o2.x)
        val maxy = max(o1.y,o2.y)
        val maxx = max(o1.x,o2.x)

        val miny1 = min(p1.y, p2.y)
        val minx1 = min(p1.x, p2.x)
        val maxx1 = max(p1.x, p2.x)
        val maxy1 = max(p1.y, p2.y)

        return maxx1<=minx || minx1>= maxx || maxy1<=miny || miny1 >= maxy
    }

    fun part2(input: List<String>): Long {
        val tiles = mutableListOf<Vec2d>()
        val edges = mutableSetOf<Pair<Vec2d,Vec2d>>()
        input.forEach {
            val (yy, xx) = it.split(",").map { it.toLong() }
            tiles.add(Vec2d(yy,xx))
        }

        //edges
        for (i in 1..<tiles.size) {
            edges.add(Pair(tiles[i-1], tiles[i]))
        }
//        edges.add(Pair(tiles.first(), tiles.last()))

        //rects
        val combinations = mutableSetOf<Pair<Vec2d,Vec2d>>()
        tiles.forEach { t -> tiles.forEach {
            if (t != it){
                combinations.add(Pair(it, t))
            }
        } }

        var max = 0L
        // for each rect
        combinations.forEach { rect ->
            //check for max
            val (o1, o2) = rect
            val area = (abs(o1.y - o2.y)+1L) * (abs(o1.x - o2.x)+1L)

            if (area > max) {
                //check for intersection - one edge inside, one outside
                if (edges.all { e -> isOutside(rect, e)}) {
                    max = area
//                    rect.println()
//                    max.println()
                }
            }
        }
        return max
    }

    val testInput = readInput("Day09_test")
    part1(testInput).println()
    part2(testInput).println()

    val input = readInput("Day09")
    part1(input).println()
    part2(input).println()
}
