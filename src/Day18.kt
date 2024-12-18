import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

fun main() {

    fun readBoard(input:List<String>, limit:Int= Int.MAX_VALUE):Set<Pair<Int,Int>> {
        val points:MutableSet<Pair<Int,Int>> = mutableSetOf()
        input.withIndex().forEach{ (i, row) ->
            if (i < limit) {
                val (y, x) = row.split(",").map { it.toInt() }
                points.add(Pair(y, x))
            }
        }
        return points
    }

    fun bfs(walls:Set<Pair<Int,Int>>, maxy: Int, maxx: Int, from:Pair<Int,Int>, to:Pair<Int,Int>, diagonal:Boolean = false):Int {
        val checked:MutableSet<Pair<Int,Int>> = mutableSetOf(from)
        val dist: HashMap<Pair<Int,Int>,Int> = hashMapOf()
        val q:MutableList<Pair<Int,Int>> = mutableListOf(from)
        dist[from] = 0
        while (q.isNotEmpty()) {
            val p = q.removeFirst()
            val (py, px) = p
            for (y in max(0, py-1)..min(maxy, py+1))
                for (x in max(0, px-1)..min(maxx, px+1)) {
                    if (!diagonal) {
                        if (py != y && px != x) continue
                    }
                    val np = Pair(y, x)
                    if (!checked.contains(np) && !walls.contains(np)) {
                        dist[np] = dist[p]!! + 1
                        checked.add(np)
                        q.addLast(np)
                        if (np == to) {
                            return dist[np]!!
                        }
                    }
                }
        }
        return -1
    }

    fun part1(input: List<String>, dims:Int = 70, limit:Int = 1024): Any {
        val walls = readBoard(input, limit)
        return bfs(walls,dims, dims, Pair(0,0), Pair(dims,dims))
    }
    fun part2(input: List<String>, dims:Int = 70, from:Int): Any {
        var maxi = from
        var walls = readBoard(input, maxi)
        while (bfs(walls,dims, dims, Pair(0,0), Pair(dims,dims)) >= 0) {
            maxi +=1
            walls = readBoard(input, maxi)
        }
        return input[maxi-1]
    }

    val testInput = readInput("Day18_test")
//    part1(testInput, 6, 12).println()
//    part2(testInput, 6, 12).println()

    val input = readInput("Day18")
    part1(input, 70, 1024).println()
    part2(input, 70, 1024).println()

}
