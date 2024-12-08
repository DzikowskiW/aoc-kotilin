fun main() {


    fun bothParts(input: List<String>): Any {
        val antennas = hashMapOf<Char,MutableList<Pair<Int,Int>>>()
        val antinodes = mutableSetOf<Pair<Int, Int>>()
        input.withIndex().forEach { (y,row) ->
            row.withIndex().forEach { (x, ch) ->
                if (ch != '.') {
                    if (antennas[ch] != null) {
                        antennas[ch]!!.forEach {
                            val dy = it.first - y
                            val dx = it.second - x
                            val p1 = Pair(y - dy, x - dx)
                            val p2 = Pair(it.first + dy, it.second + dx)
                            if (p1.first in input.indices && p1.second in 0..<input[0].length)
                                antinodes.add(p1)
                            if (p2.first in input.indices && p2.second in 0..<input[0].length)
                                antinodes.add(p2)
                        }
                    }
                    antennas.getOrPut(ch) { mutableListOf() }.add(Pair(y,x))
                }
            }
        }
        return antinodes.size
    }

    val testInput = readInput("Day08_test")
    bothParts(testInput).println()

    val input = readInput("Day08")
    bothParts(input).println()
}
