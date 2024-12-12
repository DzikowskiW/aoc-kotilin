fun main() {
    fun solve(input: List<String>): Any{
        var nxt = 0L
        var area = hashMapOf<Long, Long>()
        var perimeter = hashMapOf<Long, Long>()
        var cs = mutableSetOf<Long>()
        var sums = hashMapOf<Long, Long>()
        var areas = hashMapOf<Pair<Int,Int>, Long>()
       input.withIndex().forEach { (y,row) ->
           row.withIndex().forEach { (x, c) ->
               var a = nxt
               var p = 4
               if (y > 0 && input[y-1][x] == c) {
                   p -= 2
                   a = areas.get(Pair(y-1,x))!!
               }
               if (x > 0 && input[y][x-1] == c){
                   p -= 2
                   if (p ==0) {
                       assert(areas.get(Pair(y-1,x))!! == areas.get(Pair(y,x-1))!!)
                   }
                   a = areas.get(Pair(y,x-1))!!

               }
               if (a == nxt) nxt += 1
               areas[Pair(y,x)] = a
               cs.add(a)
               area[a] = area.getOrDefault(a,0) + 1
               perimeter[a] = perimeter.getOrDefault(a, 0) + p
           }
       }
        val res =  cs.fold(0L) { acc, c ->
            sums[c] = perimeter[c]!! * area[c]!!
            acc + sums[c]!!
        }
        area.println()
        perimeter.println()
        sums.println()
        return res
    }

    val testInput = readInput("Day12_test")
    solve(testInput).println()

    val input = readInput("Day12")
//    solve(input).println()
}
