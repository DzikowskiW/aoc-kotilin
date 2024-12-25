fun main() {

    fun solve(input: List<String>): Any {
        val locks = mutableListOf<List<Int>>()
        val keys = mutableListOf<List<Int>>()
        var curSchematic = mutableListOf<Int>()
        var curIsLock = false
        for (y in input.indices) {
            val row = input[y]
            if (row.isEmpty()) {
                continue
            }
            if (y == 0 || input[y-1].isEmpty()) {
                curIsLock = row.all { it == '#' }
                curSchematic.clear()
                row.forEach { curSchematic.add(0) }
                continue
            }
            if (y == input.size -1 || input[y+1].isEmpty()) {
                if (curIsLock) {
                    locks.add(curSchematic.toList())
                } else {
                    keys.add(curSchematic.toList())
                }
                continue
            }
            row.withIndex().forEach { (x,c) ->
                if (c == '#') curSchematic[x] +=1
            }
        }
        var res = 0
        for (l in locks){
            for (k in keys) {
                if (l.withIndex().all { (i,lc) -> lc + k[i] <= 5}){
                    res += 1
                }
            }
        }
        return res
    }

    val testInput = readInput("Day25_test")
    solve(testInput).println()

    val input = readInput("Day25")
    solve(input).println()
}
