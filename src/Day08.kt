fun main() {

    fun bothParts(input: List<String>): Any {
        val antennas = hashMapOf<Char,MutableList<Pair<Int,Int>>>()
        val antinodes = mutableSetOf<Pair<Int, Int>>()
        val antinodes2 = mutableSetOf<Pair<Int, Int>>()
        input.withIndex().forEach { (y,row) ->
            row.withIndex().forEach { (x, ch) ->
                if (ch != '.') {
                    if (antennas[ch] != null) {
                        antennas[ch]!!.forEach {
                            // part 1
                            val dy = it.first - y
                            val dx = it.second - x
                            var p1 = Pair(y - dy, x - dx)
                            var p2 = Pair(it.first + dy, it.second + dx)
                            if (p1.first in input.indices && p1.second in 0..<input[0].length)
                                antinodes.add(p1)
                            if (p2.first in input.indices && p2.second in 0..<input[0].length)
                                antinodes.add(p2)

                            //part 2
                            val dy2 = dy / gcd(dy,dx)
                            val dx2 = dx / gcd(dy,dx)
                            p1 = Pair(y - dy2, x - dx2)
                            p2 = Pair(it.first + dy2, it.second + dx2)
                            antinodes2.add(Pair(y,x))
                            antinodes2.add(it)
                            while (p1.first in input.indices && p1.second in 0..<input[0].length) {
                                antinodes2.add(p1)
                                p1 = Pair(p1.first - dy2, p1.second - dx2)
                            }
                            while (p2.first in input.indices && p2.second in 0..<input[0].length) {
                                antinodes2.add(p2)
                                p2 = Pair(p2.first + dy2, p2.second + dx2)
                            }
                        }
                    }
                    antennas.getOrPut(ch) { mutableListOf() }.add(Pair(y,x))
                }
            }
        }
        return Pair(antinodes.size, antinodes2.size)
    }

    val testInput = readInput("Day08_test")
    bothParts(testInput).println()

    val input = readInput("Day08")
    bothParts(input).println()
}
