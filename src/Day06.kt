
fun main() {
    val playerPositions = listOf(Pair(-1,0), Pair(0,1), Pair(1,0), Pair(0,-1))
    class Guard (var pos:Pair<Int,Int>, y:Int, x:Int)

    fun part1(input: List<String>):Int {
        var ybound = input.size - 1
        var xbound = input[0].length - 1
        var player = Pair(0,0)
        var playerPos = 0
        var walls = mutableSetOf<Pair<Int,Int>>()
        var path = mutableSetOf<Pair<Int,Int>>()
        input.withIndex().forEach{ (y, el) ->
            el.mapIndexedNotNull { x, c ->
                when (c) {
                    '#' -> x
                    '^' -> {
                        player = Pair(y,x)
                        null
                    }
                    else -> null
                }
            }.forEach { x -> walls.add(Pair(y,x)) }
        }

        var yy = player.first
        var xx = player.second
        while ((yy in 0..ybound) and (xx in 0..xbound)) {
            if (walls.contains(Pair(yy,xx))) {
                //hit the wall
                yy -= playerPositions[playerPos].first
                xx -= playerPositions[playerPos].second
                playerPos = (playerPos + 1) % playerPositions.size
            } else {
                //all good
                path.add(Pair(yy, xx))
                yy += playerPositions[playerPos].first
                xx += playerPositions[playerPos].second
            }
        }
        return path.size
    }

    val testInput = readInput("Day06_test")
//    part1(testInput).println()
//    check(bothParts(testInput) == Pair(143,123))

    val input = readInput("Day06")
    part1(input).println()
//    bothParts(input).println()
}
