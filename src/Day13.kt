import kotlin.math.min

class Machine(val a:Pair<Long,Long>, val b:Pair<Long,Long>, val prize:Pair<Long,Long>) {
    override fun toString():String {
        return "[$a, $b | $prize]"
    }

    fun calcTokens():Long {
        val aSmash = (prize.first * b.second - prize.second * b.first) / (a.first * b.second - a.second * b.first)
        val bSmash = (prize.first - a.first * aSmash) / b.first
        if (a.first * aSmash + b.first*bSmash != prize.first) return -1
        if (a.second * aSmash + b.second*bSmash != prize.second) return -1
        return 3 * aSmash + bSmash
    }
}

fun main() {

    fun solve(input: String, mul:Long = 0): Any{
        val machines = mutableListOf<Machine>()
        val regex=Regex("\\d+", RegexOption.MULTILINE)
        val matches = regex.findAll(input).map { it -> it.value.toLong() }.toList()
        var i = 0
        while (i < matches.size) {
            machines.add(Machine(
                Pair(matches[i], matches[i+1]),
                Pair(matches[i+2], matches[i+3]),
                Pair(matches[i+4]+mul, matches[i+5]+mul),
                ))
            i+=6
        }
        var sum = 0L
        for (m in machines) {
            val tokens = m.calcTokens()
            if (tokens != -1L)  sum += tokens
        }
        return sum
    }

    val testInput = readInputBlob("Day13_test")
    solve(testInput).println()
    solve(testInput, 10000000000000).println()

    val input = readInputBlob("Day13")
    solve(input).println()
    solve(input, 10000000000000).println()
}
