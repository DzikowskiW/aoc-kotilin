fun main() {
    fun mapMove(m:Char):Vec2 {
        return when (m) {
            '^' -> N
            '>' -> E
            'v' -> S
            '<' -> W
            else -> throw Error("Invalid move: $m")
        }
    }

    fun reverseDir(dir:Vec2):Vec2 {
        return when (dir) {
            S -> N
            W -> E
            N -> S
            E -> W
            else -> throw Error("Invalid dir: $dir")
        }
    }

    fun solve(input: List<String>): Any {
        val splitIndex= input.indexOf("")
        val boardInput = input.subList(0,splitIndex)
        val movesInput = input.subList(splitIndex+1, input.size)

        val moves = movesInput.joinToString("")
        val walls:MutableSet<Vec2> = mutableSetOf()
        val boxes:MutableSet<Vec2> = mutableSetOf()
        var robot:Vec2? = null

        boardInput.withIndex().forEach { (y, row) ->
            row.withIndex().forEach { (x, c)->
                val p = Vec2(y,x)
                when (c) {
                    '#' -> walls.add(p)
                    'O' -> boxes.add(p)
                    '@' -> robot = p
                }
            }
        }

        for (m in moves) {
            val dyx = mapMove(m)
            var pos = robot!! + dyx

            //check if robot can move
            while (boxes.contains(pos)) {
                pos += dyx
            }
            if (walls.contains(pos)){
                continue
            }

            //move boxes in front
            val rdyx = reverseDir(dyx)
            var npos = pos + rdyx
            while (boxes.contains(npos)) {
                boxes.add(pos)
                boxes.remove(npos)
                pos = npos
                npos = pos + rdyx
            }

            //move itself
            if (robot == npos) {
                robot = robot!! + dyx
            } else {
                throw Error("Invalid robot state $robot <> $pos | $dyx")
            }
        }

        return boxes.fold(0L) { acc, b ->
            acc + b.x + b.y*100
        }
    }

    val testInput = readInput("Day15_test")
    solve(testInput).println()

    val input = readInput("Day15")
    solve(input).println()
}
