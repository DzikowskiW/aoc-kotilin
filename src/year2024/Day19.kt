package year2024

import println
import readInput

fun main() {
    val cache = hashMapOf<Pair<List<String>,String>, Long>()

    fun canBeDone(towels:List<String>, design:String):Long {
        if (design.isEmpty()) return 1L
        if (cache.containsKey(Pair(towels,design))){
            return cache[Pair(towels,design)]!!
        }
        var acc = 0L

        for (t in towels) {
            if (design.startsWith(t)) {
                val n = canBeDone(towels, design.removePrefix(t))
                acc += n
            }
        }
        cache[Pair(towels,design)] = acc
        return acc
    }
    fun bothParts(input: List<String>): Any {
        val towels = input[0].split(", ")
        return input.subList(2,input.size).fold(Pair(0,0L)) { acc, design->
            val res = canBeDone(towels, design)
            Pair(acc.first + if (res != 0L) 1 else 0, acc.second + res)
        }
    }

    val testInput = readInput("Day19_test")
    bothParts(testInput).println()

    val input = readInput("Day19")
    bothParts(input).println()

}
