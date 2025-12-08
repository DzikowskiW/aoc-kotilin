package year2025

import println
import readInput
import kotlin.math.*


fun main() {
    data class Vec3d(val x:Int, val y: Int,val z: Int) {
        fun dist(o:Vec3d): Double {
            return sqrt((x-o.x).toDouble().pow(2) + (y-o.y).toDouble().pow(2) + (z-o.z).toDouble().pow(2))
        }
    }
    fun part1(input: List<String>): Int {
        val points = input.map { line -> line.split(',').map { it.toInt() } }.map { Vec3d(it[0], it[1], it[2]) }
        val pairs = mutableSetOf<Pair<Vec3d,Vec3d>>()
        for (i in 0..<points.size){
            for (j in 0..<points.size) {
                if (i != j && !pairs.contains(Pair(points[j], points[i]))) {
                    pairs.add(Pair(points[i], points[j]))
                }
            }
        }
        val sortedPairs = pairs.sortedWith { a,b -> ((a.first.dist(a.second) - b.first.dist(b.second))*1000).toInt() }.toMutableList()
        val circuits = mutableListOf<MutableSet<Vec3d>>()
        for (i in 0..10){
            val cur = sortedPairs.removeAt(0)

        }
//        points.println()
        return 0
    }

    val testInput = readInput("Day08_test")
        part1(testInput).println()
//        part2(testInput).println()

    val input = readInput("Day08")
//        part1(input).println()
//        part2(input).println()
}
