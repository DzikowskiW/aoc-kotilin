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
                    val s = cache.getOrPut(Pair(yy,xx)) { mutableSetOf() }
                    s.addAll(calcTrail(c + 1, yy-1, xx).orEmpty())
                    s.addAll(calcTrail(c + 1, yy+1, xx).orEmpty())
                    s.addAll(calcTrail(c + 1, yy, xx-1).orEmpty())
                    s.addAll(calcTrail(c + 1, yy, xx+1).orEmpty())
                }
            }
            return cache[Pair(yy,xx)]
        }
        return input.withIndex().fold(0) { res, (y, row) ->
            res + row.withIndex().fold(0) { acc, (x,c) ->
                acc + if (c == '0') calcTrail(c,y,x).orEmpty().size else 0
            }
        }
    }


    fun part2(input: List<String>): Int {
        val cache = hashMapOf<Pair<Int,Int>,Int>()
        fun calcTrail(toFind:Char, yy: Int, xx: Int):Int {
            if (yy !in input.indices || xx !in 0..<input[0].length) {
                return 0
            }
            if (input[yy][xx] != toFind) {
                return 0
            }
            if (Pair(yy,xx) !in cache) {
                val  c = input[yy][xx]
                if (c == '9') {
                    cache[Pair(yy, xx)] = 1
                } else {
                    cache[Pair(yy,xx)]= calcTrail(c + 1, yy-1, xx) +
                        calcTrail(c + 1, yy+1, xx) +
                        calcTrail(c + 1, yy, xx-1) +
                        calcTrail(c + 1, yy, xx+1)
                }
            }
            return cache[Pair(yy,xx)]!!
        }

        return input.withIndex().fold(0) { res, (y, row) ->
            res + row.withIndex().fold(0) { acc, (x,c) -> acc + if (c == '0') calcTrail(c,y,x) else 0 }
        }
    }

    val testInput = readInput("Day10_test")
//    part1(testInput).println()

    val input = readInput("Day10")
    part1(input).println()
    part2(input).println()
}
