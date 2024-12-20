import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {

    fun part1(input: List<String>): Any {
        var s:Pair<Int, Int>? = null
        var e:Pair<Int, Int>? = null
        val raceTrack:MutableSet<Pair<Int, Int>> = mutableSetOf()
        val maxx = input[0].length - 1
        val maxy = input.size - 1
        val dist:HashMap<Pair<Int,Int>, Int> = hashMapOf()

        input.withIndex().forEach { (y, row) ->
            row.withIndex().forEach { (x, c) ->
                when (c) {
                    '.' -> raceTrack.add(Pair(y,x))
                    'S' -> {
                        s = Pair(y,x)
                        raceTrack.add(s!!)
                    }
                    'E' -> {
                        e = Pair(y, x)
                        raceTrack.add(e!!)
                    }
                }
            }
        }
        if (s == null || e == null) throw Error("start and end not enabled")

        val adj = setOf(Pair(-1,0), Pair(1,0), Pair(0,1), Pair(0,-1))
        var h = s!!
        dist[s!!] = 0
        while (h != e) {
            for ((dy,dx) in adj) {
                val n = Pair(h.first + dy, h.second + dx)
                if (raceTrack.contains(n) && !dist.containsKey(n)) {
                    dist[n] = dist[h]!! + 1
                    h = n
                }
            }
        }

        //check shortcuts
        val shortcuts:HashMap<Set<Pair<Int,Int>>, Int> = hashMapOf()
        for (p in raceTrack) {
            val (y,x) = p
            for ((dy,dx) in adj) {
                val n = Pair(y + dy*2, x + dx*2)
                if (raceTrack.contains(n) && !raceTrack.contains(Pair(y + dy, x + dx))) {
                    if (!shortcuts.containsKey(setOf(n,p))) {
                        shortcuts[setOf(n,p)] = abs(dist[n]!! - dist[p]!!) - 2
                    }
                }
            }
        }

        var acc = 0
        for (v in shortcuts.values) {
            if (v >= 100) acc += 1
        }
        return acc
    }

    val testInput = readInput("Day20_test")
//    part1(testInput).println()

    val input = readInput("Day20")
    part1(input).println()

}
