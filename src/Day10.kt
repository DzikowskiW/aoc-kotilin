fun main() {
    val cache = hashMapOf<Pair<Long,Int>, Long>()
    fun solve(input: List<String>): Any{
        fun calc(n: Long, level:Int):Long {
            if (level == 0) return 1
            val i = Pair(n, level)
            if (!cache.containsKey(i)) {
                val nstr = n.toString()
                cache[i] = if (n == 0L) {
                        calc(1, level - 1)
                    } else if (nstr.length % 2 == 0) {
                        val mid = nstr.length / 2
                        calc(nstr.substring(0, mid).toLong(), level - 1) + calc(nstr.substring(mid).toLong(), level - 1)
                    } else {
                        calc(n * 2024, level - 1)
                    }
            }
            return cache[i]!!
        }
        val res1 =  input[0].split(" ").fold(0L) { acc, n ->  acc + calc(n.toLong(),25) }
        val res2 =  input[0].split(" ").fold(0L) { acc, n ->  acc + calc(n.toLong(),75) }
        return Pair(res1, res2)
    }

    val testInput = readInput("Day10_test")
    solve(testInput).println()

    val input = readInput("Day10")
    solve(input).println()
}
