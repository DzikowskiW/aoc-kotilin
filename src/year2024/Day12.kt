package year2024

import println
import readInput

fun main() {
    fun solve(input: List<String>): Any{
        val filled = mutableSetOf<Pair<Int,Int>>()
        fun fill(sY:Int, sX:Int): Int {
            val checked = mutableSetOf<Pair<Int, Int>>()
            val toCheck = mutableListOf<Pair<Int, Int>>(Pair(sY, sX))
            var area = 0
            var sides = 0
            var perimeter = 0
            val sval = input[sY][sX]
            val maxy = input.size
            val maxx = input[0].length
            while (toCheck.isNotEmpty()) {
                val cur = toCheck.removeFirst()
                val (y, x) = cur
                if (checked.contains(cur)) continue
                checked.add(cur)
                filled.add(cur)

                area += 1
//                println("($y,$x) $maxy")
                // check outside corners
                if ((y-1 < 0 || (input[y-1][x] != sval)) && (x-1<0 || input[y][x-1] != sval)) sides +=1
                if ((y-1 < 0 || (input[y-1][x] != sval)) && (x+1 >= maxx || input[y][x+1] != sval)) sides +=1
                if ((y+1 >= maxy || (input[y+1][x] != sval)) && (x-1<0 || input[y][x-1] != sval)) sides +=1
                if ((y+1 >= maxy || (input[y+1][x] != sval)) && (x+1 >= maxx || input[y][x+1] != sval)) sides +=1
                // check inside corners
                if ((y-1 >= 0 && (input[y-1][x] == sval)) && (x-1 >= 0 && input[y][x-1] == sval) && input[y-1][x-1] != sval) sides +=1
                if ((y-1 >= 0 && (input[y-1][x] == sval)) && (x+1 < maxx && input[y][x+1] == sval) && input[y-1][x+1] != sval) sides +=1
                if ((y+1 < maxy && (input[y+1][x] == sval)) && (x-1 >= 0 && input[y][x-1] == sval) && input[y+1][x-1] != sval) sides +=1
                if ((y+1 < maxy && (input[y+1][x] == sval)) && (x+1 < maxx && input[y][x+1] == sval) && input[y+1][x+1] != sval) sides +=1
//                println("($y,$x) $sides")

                for (n in listOf(Pair(y - 1, x), Pair(y + 1, x), Pair(y, x - 1), Pair(y, x + 1))) {
                    val (ny, nx) = n
                    if (ny !in 0..<input.size) {
                        perimeter += 1
                        continue
                    }
                    if (nx !in 0..<input[0].length) {
                        perimeter += 1
                        continue
                    }
                    if (input[ny][nx] != sval) {
                        perimeter += 1
                        continue
                    }
                    if (checked.contains(n) || toCheck.contains(n)) continue
                    toCheck.addLast(n)
                }
            }
//            println("($sY,$sX): $area, $perimeter, $sides")
//            return area * perimeter // uncomment for part 1
            return area * sides //part 2
        }

        val res = input.withIndex().fold(0) { acc, (y, row) ->
            acc + row.withIndex().fold(0) { acc1, (x, c) ->
                if (!filled.contains(Pair(y,x)) ) acc1 + fill(y,x) else acc1
            }
        }
        return res
    }

    val testInput = readInput("Day12_test")
    solve(testInput).println()


    val input = readInput("Day12")
    solve(input).println()
}
