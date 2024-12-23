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

    fun draw(walls:Set<Vec2>, boxes:Set<Vec2>, boxesLeft:Set<Vec2>, robot:Vec2) {
        for (y in 0..<10) {
            var line = ""
            for (x in 0..<20) {
                val v = Vec2(y,x)
                if (v == robot) line += '@'
                else if (walls.contains(v)) line += '#'
                else if (boxesLeft.contains(v)) line+='['
                else if (boxes.contains(v)) line += ']'
                else line+= '.'
            }
            line.println()
        }
    }

    fun part1(input: List<String>): Any {
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

    fun part2(input: List<String>): Any {
        val splitIndex= input.indexOf("")
        val boardInput = input.subList(0,splitIndex)
        val movesInput = input.subList(splitIndex+1, input.size)

        val moves = movesInput.joinToString("")
        val walls:MutableSet<Vec2> = mutableSetOf()
        val boxes:MutableSet<Vec2> = mutableSetOf()
        val boxesLeft:MutableSet<Vec2> = mutableSetOf()

        var robot:Vec2? = null

        boardInput.withIndex().forEach { (y, row) ->
            row.withIndex().forEach { (x, c)->
                val p1 = Vec2(y,x*2)
                val p2 = Vec2(y,x*2 + 1)
                when (c) {
                    '#' -> walls.addAll(setOf(p1,p2))
                    'O' -> {
                        boxes.addAll(setOf(p1,p2))
                        boxesLeft.add(p1)
                    }
                    '@' -> robot = p1
                }
            }
        }
        for (m in moves) {
            val dyx = mapMove(m)
            val posToMove= mutableSetOf<Vec2>(robot!!)
            val posToCheck = mutableListOf(robot!! + dyx)
            var possible = true

            //check if robot can move
            while (posToCheck.isNotEmpty()) {
                val p = posToCheck.removeFirst()
                if (posToMove.contains(p)) continue
                if (walls.contains(p)) {
                    possible = false
                    break
                }
                if (!boxes.contains(p)) {
                    continue
                }
                posToMove.add(p)
                val p1 = if (boxesLeft.contains(p)) p + E else p + W
                posToCheck.add(p1)
                posToCheck.add(p1+dyx)
            }

            if (!possible) continue

            //move boxes in front
            val boxesLeftToAdd = mutableSetOf<Vec2>()
            val boxesToAdd = mutableSetOf<Vec2>()
            val nextRobot = robot!! + dyx
            for (p in posToMove) {
                if (boxesLeft.contains(p)){
                    boxesLeft.remove(p)
                    boxesLeftToAdd.add(p+dyx)
                }
                if (boxes.contains(p)){
                    boxes.remove(p)
                    boxesToAdd.add(p+dyx)
                }
            }
            robot = nextRobot
            boxesToAdd.forEach { boxes.add(it) }
            boxesLeftToAdd.forEach { boxesLeft.add(it)}
        }

        return boxesLeft.fold(0L) { acc, b ->
            acc + b.x + b.y*100
        }
    }

    val testInput = readInput("Day15_test")
//    part1(testInput).println()
//    part2(testInput).println()

    val input = readInput("Day15")
    part1(input).println()
    part2(input).println()

}
