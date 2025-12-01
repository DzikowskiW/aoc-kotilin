package year2025

import println
import readInput

fun mod(a: Int, b: Int): Int {
    return ((a % b) + b) % b
}

fun main() {

    fun part1(input: List<String>): Int {
        var res = 0
        val codes = input.map {
            Pair(it.first(), it.drop(1).toInt())
        }
        codes.fold(50) { acc, (dir, v) ->
//            println("acc: $acc, dir: $dir, v: $v")
            val delta = (if (dir == 'L') -1 else 1)
            val next = mod(acc + delta * v, 100)
//            println("next: $next")
            if (next == 0) res+=1
            next
        }
        return res
    }

    fun part2(input: List<String>): Int {
        var res = 0
        val codes = input.map {
            Pair(it.first(), it.drop(1).toInt())
        }
        codes.fold(50) { acc, (dir, v) ->
//            println("---------")
//            println("acc: $acc, dir: $dir, v: $v")
            val delta = (if (dir == 'L') -1 else 1)
            val next = mod(acc + delta * v, 100)
            if (dir == 'L') {
                res += (mod(100 - acc, 100) + v) / 100
            } else {
                res += (acc + v) / 100
            }
//            println("RES $res")
//            println("next: $next")
            mod(next, 100)
        }
        return res
    }
    val testInput = readInput("Day01_test")
        check(part1(testInput) == 3)
        check(part2(testInput) == 6)

    val input = readInput("Day01")
        part1(input).println()
        part2(input).println()
}
