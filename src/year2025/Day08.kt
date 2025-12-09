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
    fun part1(input: List<String>, startCons: Long): Long{
        val points = input.map { line -> line.split(',').map { it.toLong() } }.map { Vec3d(it[0], it[1], it[2]) }
        val pairs = mutableSetOf<Pair<Vec3d,Vec3d>>()
        for (i in 0..<points.size){
            for (j in 0..<points.size) {
                if (i != j && !pairs.contains(Pair(points[j], points[i]))) {
                    pairs.add(Pair(points[i], points[j]))
                }
            }
        }
        val sortedPairs = pairs.sortedWith { a,b -> ((a.first.dist(a.second) - b.first.dist(b.second))).toInt() }.toMutableList()

        val circuits = mutableListOf<MutableSet<Vec3d>>()
        var cons = startCons
        while (cons > 0){
            cons -= 1
            val cur = sortedPairs.removeAt(0)
            val c1 = circuits.find { it.contains(cur.first) }
            val c2 = circuits.find { it.contains(cur.second) }
            if (c1 != null && c2!= null) {
                if (c1 != c2) {
                    c1.addAll(c2)
                    circuits.remove(c2)
                }
            }
            else if (c1 != null) {
                c1.add(cur.second)
            } else if (c2 != null) {
                c2.add(cur.first)
            } else {
                circuits.add(mutableSetOf(cur.first, cur.second))
            }
        }
        val resc = circuits.map { it.size.toLong() }.sortedDescending()
        return resc[0] * resc[1] * resc[2]
    }

    fun part2(input: List<String>): Long{
        val points = input.map { line -> line.split(',').map { it.toLong() } }.map { Vec3d(it[0], it[1], it[2]) }
        val pairs = mutableSetOf<Pair<Vec3d,Vec3d>>()
        for (i in 0..<points.size){
            for (j in 0..<points.size) {
                if (i != j && !pairs.contains(Pair(points[j], points[i]))) {
                    pairs.add(Pair(points[i], points[j]))
                }
            }
        }
        val sortedPairs = pairs.sortedWith { a,b -> ((a.first.dist(a.second) - b.first.dist(b.second))).toInt() }.toMutableList()

        val circuits = mutableListOf<MutableSet<Vec3d>>()
        points.forEach { circuits.add(mutableSetOf(it)) }

        while (true){
            val cur = sortedPairs.removeAt(0)
            val c1 = circuits.find { it.contains(cur.first) }
            val c2 = circuits.find { it.contains(cur.second) }
            if (c1 != null && c2!= null) {
                if (c1 != c2) {
                    c1.addAll(c2)
                    circuits.remove(c2)
                    if (circuits.size == 1){
                        cur.first.x.println()
                        cur.second.x.println()
                        return cur.first.x * cur.second.x
                    }
                }
            }
            else {
                check(false)
            }
        }
    }

    val testInput = readInput("Day08_test")
        part1(testInput,10).println()
        part2(testInput).println()

    val input = readInput("Day08")
        part1(input, 1000).println()
        part2(input).println()
}
