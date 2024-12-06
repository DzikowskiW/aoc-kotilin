
fun main() {
    val playerPositions = listOf(Pair(-1,0), Pair(0,1), Pair(1,0), Pair(0,-1))
    data class Triple<A, B, C>(val first: A, val second: B, val third: C) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Triple<*, *, *>) return false
            return first == other.first && second == other.second && third == other.third
        }

        override fun hashCode(): Int {
            var result = first?.hashCode() ?: 0
            result = 31 * result + (second?.hashCode() ?: 0)
            result = 31 * result + (third?.hashCode() ?: 0)
            return result
        }
    }


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

    fun part2(input: List<String>):Int {
        var ybound = input.size - 1
        var xbound = input[0].length - 1
        var player = Pair(0,0)
        val walls = mutableSetOf<Pair<Int,Int>>()

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

        val validPoints = mutableSetOf<Pair<Int,Int>>()

        for (j in 0..<input.size) {
            for (i in 0..<input[j].length) {
                if (input[j][i] != '.') continue
                walls.add(Pair(j,i))
                val path = mutableSetOf<Triple<Int,Int,Int>>()
                var yy = player.first
                var xx = player.second
                var playerPos = 0
                while ((yy in 0..ybound) and (xx in 0..xbound)) {
                    if (walls.contains(Pair(yy,xx))) {
                        //hit the wall
                        yy -= playerPositions[playerPos].first
                        xx -= playerPositions[playerPos].second
                        playerPos = (playerPos + 1) % playerPositions.size
                    } else {
                        //all good
                        if (path.contains(Triple(yy, xx, playerPos))) {
                            validPoints.add(Pair(j,i))
                            break
                        }
                        path.add(Triple(yy, xx, playerPos))
                        yy += playerPositions[playerPos].first
                        xx += playerPositions[playerPos].second
                    }
                }
                walls.remove(Pair(j,i))
            }
        }

        return validPoints.size
    }

    val testInput = readInput("Day06_test")
//    part1(testInput).println()
//    part2(testInput).println()

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}
