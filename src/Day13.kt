import kotlin.math.min

class Machine(val a:Pair<Long,Long>, val b:Pair<Long,Long>, val prize:Pair<Long,Long>) {
    override fun toString():String {
        return "[$a, $b | $prize]"
    }
    private val cache = hashMapOf<Pair<Long,Long>, Long>()
    fun calcTokens(curPrize:Pair<Long,Long>):Long {
        if (curPrize.first < 0L || curPrize.second < 0L ) {
            return Long.MAX_VALUE
        }
        if (curPrize.first == 0L && curPrize.second == 0L) {
            return 0
        }
        if (!cache.containsKey(curPrize)) {
            var patha = this.calcTokens(Pair(curPrize.first - a.first, curPrize.second - a.second))
            var pathb = this.calcTokens(Pair(curPrize.first - b.first, curPrize.second - b.second))
            if (patha != Long.MAX_VALUE) patha += 3
            if (pathb != Long.MAX_VALUE) pathb += 1
            cache[curPrize] =  min(patha, pathb)
        }
        return cache[curPrize]!!
    }

}

fun main() {

    fun solve(input: String, mul:Long = 1): Any{
        val machines = mutableListOf<Machine>()
        val regex=Regex("\\d+", RegexOption.MULTILINE)
        val matches = regex.findAll(input).map { it -> it.value.toLong() }.toList()
        var i = 0
        while (i < matches.size) {
            machines.add(Machine(
                Pair(matches[i], matches[i+1]),
                Pair(matches[i+2], matches[i+3]),
                Pair(matches[i+4]*mul, matches[i+5]*mul),
                ))
            i+=6
        }
        var sum = 0L
        for (m in machines) {
            val tokens = m.calcTokens(m.prize)
            if (tokens == Long.MAX_VALUE) "no path".println()
            else {
                sum += tokens
            }
        }
        return sum
    }

    val testInput = readInputBlob("Day13_test")
    solve(testInput).println()
    solve(testInput, 10000000000000).println()


    val input = readInputBlob("Day13")
//    solve(input).println()
}
