fun main() {



    fun part1(input: List<String>): Int {
        val cache = hashMapOf<Pair<Int,Int>,MutableSet<Pair<Int,Int>>>()
        fun calcTrail(toFind:Char, yy: Int, xx: Int):MutableSet<Pair<Int,Int>>? {
            if (yy !in input.indices || xx !in 0..<input[0].length) {
                return null
            }
            if (input[yy][xx] != toFind) {
                return null
            }
            if (Pair(yy,xx) !in cache) {
                val  c = input[yy][xx]
                if (c == '9') {
                    cache[Pair(yy, xx)] = mutableSetOf(Pair(yy,xx))
                } else {
                    cache.getOrPut(Pair(yy,xx)) { mutableSetOf() }.addAll(calcTrail(c + 1, yy-1, xx).orEmpty())
                    cache.getOrPut(Pair(yy,xx)) { mutableSetOf() }.addAll(calcTrail(c + 1, yy+1, xx).orEmpty())
                    cache.getOrPut(Pair(yy,xx)) { mutableSetOf() }.addAll(calcTrail(c + 1, yy, xx-1).orEmpty())
                    cache.getOrPut(Pair(yy,xx)) { mutableSetOf() }.addAll(calcTrail(c + 1, yy, xx+1).orEmpty())
                }
            }
            return cache[Pair(yy,xx)]
        }
        val summ =  input.withIndex().fold(0) { res, (y, row) ->
            res + row.withIndex().fold(0) { acc, (x,c) ->
                acc + if (c == '0') {
                    val tmp = calcTrail(c,y,x).orEmpty()
//                    Pair(y,x).println()
//                    tmp.size.println()
                    tmp.size
                } else 0
            }
        }
//        cache.println()
        return summ
    }

    val testInput = readInput("Day10_test")
//    part1(testInput).println()

    val input = readInput("Day10")
    part1(input).println()

}
