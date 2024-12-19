import javax.tools.Tool
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

fun main() {
    val cache = hashMapOf<Pair<List<String>,String>, Boolean>()

    fun canBeDone(towels:List<String>,used:Set<Int>, design:String):Boolean {
        if (design.isEmpty()) return true
        if (cache.containsKey(Pair(towels,design))){
            return cache[Pair(towels,design)]!!
        }

        for ((i,t) in towels.withIndex()) {
            if (used.contains(i)) continue
            if (design.startsWith(t)) {
                if (canBeDone(towels, used, design.removePrefix(t)))
//                    cache[Pair(towels,design)] = true
                    return true
            }
        }
        cache[Pair(towels,design)] = false
        return false
    }
    fun part1(input: List<String>): Any {
        val towels = input[0].split(", ")
        return input.subList(2,input.size).withIndex().fold(0) { acc, (i,design)->
            val res = canBeDone(towels, setOf(), design)
//            if (res) {
//                design.println()
//            }
            acc + if (res) 1 else 0
        }
    }

    val testInput = readInput("Day19_test")
    part1(testInput).println()

    val input = readInput("Day19")
    part1(input).println()

}
