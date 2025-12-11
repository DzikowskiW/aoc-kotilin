package year2025

import println
import readInput
import kotlin.math.*


fun main() {
    data class Vec3d(val x:Long, val y: Long,val z: Long) {
        fun dist(o:Vec3d): Double {
            return sqrt((x-o.x).toDouble().pow(2) + (y-o.y).toDouble().pow(2) + (z-o.z).toDouble().pow(2))
        }
        override fun toString(): String = "($x, $y, $z)"
    }


    fun part1(lines: List<String>): Long{
        val graph = hashMapOf<String, MutableSet<String>>()
        var sum = 0L

        fun findPaths(from:String, checked: Set<String>): Long {
//            from.println()
            if (from == "out") return 1L
            if (!graph.contains(from)) return 0L
            if (checked.contains(from)) return 0L
            val newChecked = checked + setOf(from)
            return graph[from]?.sumOf { findPaths(it, newChecked.toSet()) } ?: 0L
        }
        lines.forEach { line ->
            val (k,edges) = line.split(": ")
            edges.trim().split(" ").forEach { e->
                if (graph[k] == null) {
                    graph[k] = mutableSetOf<String>()
                }
                graph[k]!!.add(e)
            }
        }

        graph.println()
        sum = findPaths("you",setOf())
        return sum
    }

    val testInput = readInput("Day11_test")
        part1(testInput).println()
//        part2(testInput).println()

    val input = readInput("Day11")
        part1(input).println()
//        part2(input).println()
}
