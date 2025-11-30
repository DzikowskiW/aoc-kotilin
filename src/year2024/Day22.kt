package year2024

import println
import readInput

fun main() {
    fun calcSecret(v:Long):Long {
        var res = v
        var n1 = res shl 6
        res = res xor n1
        res %= 16777216
        var n2 = res shr 5
        res = res xor n2
        res %= 16777216
        var n3 = res shl 11
        res = res xor n3
        res %= 16777216
        return res
    }

    fun solve(input: List<String>): Any {
        val secretNums:MutableList<Long> = input.map { it.toLong() }.toMutableList()
        val seqSum:HashMap<List<Int>, Int> = hashMapOf()
        secretNums.withIndex().forEach { (k,v) ->
            val changes:MutableList<Int> = mutableListOf()
            val maxBananas:HashMap<List<Int>, Int> = hashMapOf()
            var prevNum = 0L
            for (i in 0..<2000) {
                prevNum = secretNums[k]
                secretNums[k] = calcSecret(secretNums[k])
                changes.addLast((secretNums[k] % 10 - prevNum % 10).toInt())
                if (changes.size == 4) {
                    val c = changes.toList()
                    if (!maxBananas.containsKey(c))
                        maxBananas[c] = (secretNums[k] % 10).toInt()
                    changes.removeFirst()
                }
            }
            maxBananas.forEach{ (k,v) ->
                seqSum[k] = seqSum.getOrDefault(k,0) + v
            }
        }

        return Pair(secretNums.sum(), seqSum.maxBy { (k,v) -> v }.value)
    }

    val testInput = readInput("Day22_test")
    solve(testInput).println()

    val input = readInput("Day22")
    solve(input).println()
}
