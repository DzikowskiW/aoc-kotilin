package year2025

import println
import readInput


fun main() {
    fun part1(input: List<String>): Int {
        var res = 0
        val list = mutableListOf<MutableSet<Pair<Int,Int>>>()
        var row= 0
        input.forEach { line ->
            if (line.contains("x")) {
                // calc shapes
                val (v1,v2)= line.split(":")
                val (x,y)= v1.split("x").map { it.toInt() }
                val nums = v2.trim().split(" ").map { it.toInt() }
                if (x*y >= nums.mapIndexed { i, n -> n* list[i].size}.sum() ){
                    res += 1
                }
            } else if (line.contains(":")) {
                //new shape
                list.add(mutableSetOf())
                row = 0
            } else if (line.contains("#") || line.contains(".")) {
                //shape input
                line.forEachIndexed { col, c -> if (c == '#') list.last().add(Pair(row, col)) }
                row+= 1
            }
        }
        list.map { it.size }.println()
        return res
    }
//    val testInput = readInput("Day12_test")
//    part1(testInput).println()

    val input = readInput("Day12")
    part1(input).println()
}
