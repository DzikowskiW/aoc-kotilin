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
        val dists:HashMap<Pair<Int,Int>, Int> = hashMapOf()

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
                    }
                }
            }
        }
        if (s == null || e == null) throw Error("start and end not enabled")
        for (n in raceTrack) {
            dists[n] = bfs(raceTrack, maxy, maxx, e!!, n)
        }

        //check shortcuts
        val shortcuts:HashMap<Set<Pair<Int,Int>>, Int> = hashMapOf()
        val adj = setOf(Pair(-1,0), Pair(1,0), Pair(0,1), Pair(0,-1))
        for (p in raceTrack) {
            val (y,x) = p
            for ((dy,dx) in adj) {
                val n = Pair(y + dy*2, x + dx*2)
                if (raceTrack.contains(n) && !raceTrack.contains(Pair(y + dy, x + dx))) {
                    if (!shortcuts.containsKey(setOf(n,p))) {
                        shortcuts[setOf(n,p)] = abs(dists[n]!! - dists[p]!!) - 2
                    }
                }
            }
        }

//        dists.println()
//        val tmp = hashMapOf<Int,Int>()
//        shortcuts.values.forEach{ v ->
//            tmp[v] = tmp.getOrDefault(v, 0).inc()
//        }
        var acc = 0
        for (v in shortcuts.values) {
            if (v >= 100) acc += 1
        }
        return acc
    }

    val testInput = readInput("Day20_test")
//    part1(testInput).println()

    val input = readInput("Day20")
    part1(input).println() // 1287 too low 1298 too high

}
