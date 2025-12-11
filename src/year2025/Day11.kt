package year2025

import println
import readInput


fun main() {

    fun part12(lines: List<String>){
        val graph = hashMapOf<String, MutableSet<String>>()

        val cache = hashMapOf<Pair<String,String>, Long>()
        fun findPaths(from:String, to:String): Long {
            if (!cache.contains(Pair(from,to))) {
                if (from == to) return 1L
                cache[Pair(from,to)] = graph[from]?.sumOf { findPaths(it, to) } ?: 0L
            }
            return cache[Pair(from,to)]!!
        }

        lines.forEach { line ->
            val (k,edges) = line.split(": ")
            edges.trim().split(" ").forEach { e-> graph.getOrPut(e){ mutableSetOf() }.add(k) } //reversed graph
        }

        val p1 = findPaths("out","you")
        val p2 = findPaths("out","fft") * findPaths("fft","dac")* findPaths("dac","svr") + findPaths("out","dac") * findPaths("dac","fft") * findPaths("fft","svr")
        p1.println()
        p2.println()
    }

    val testInput = readInput("Day11_test")
    part12(testInput).println()

    val input = readInput("Day11")
    part12(input)
}
