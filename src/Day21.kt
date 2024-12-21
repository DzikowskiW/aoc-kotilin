import kotlin.math.min

fun findPaths(pad:List<List<Char?>>): HashMap<Pair<Char,Char>,MutableSet<String>> {
    val maxx = pad[0].size-1
    val maxy = pad.size-1

    // map positions of buttons
    val pos = hashMapOf<Char,Pair<Int,Int>>()
    pad.forEachIndexed { i, row ->
        row.forEachIndexed { j, c ->
            if (c != null) pos[c] = Pair(i,j)
        }
    }

    //find shortest paths between buttons
    val paths:HashMap<Pair<Char,Char>,MutableSet<String>> = hashMapOf()
    for (a in pos.keys) {
        for (b in pos.keys) {
            val ab = Pair(a,b)
            if (a == b)
                paths[ab] = mutableSetOf("A")
            else {
                // bfs to positon
                paths[ab] = mutableSetOf()
                val toCheck = mutableListOf(Pair(a, ""))
                var minPathLen = Int.MAX_VALUE
                while (toCheck.isNotEmpty()) {
                    val (cToCheck, pathSoFar) = toCheck.removeFirst()
                    val (y,x) = pos[cToCheck]!!
                    val moves = listOf(
                        Pair(Pair(y-1, x), "^"),
                        Pair(Pair(y+1, x), "v"),
                        Pair(Pair(y, x-1), "<"),
                        Pair(Pair(y, x+1), ">"),
                    )
                    for ((coords, pathStep) in moves) {
                        val (ny,nx) = coords
                        //check in pounds and not null
                        if ((ny !in 0..maxy) || (nx !in 0..maxx) || pad[ny][nx] == null) continue
                        if (pad[ny][nx] == b) {
                            if (minPathLen < pathSoFar.length + 1) {
                                toCheck.clear()
                                break
                            }
                            minPathLen = pathSoFar.length + 1
                            paths[ab]!!.add(pathSoFar + pathStep + "A")
                        } else {
                            toCheck.addLast(Pair(pad[ny][nx]!!, pathSoFar+pathStep))
                        }
                    }
                }
            }
        }
    }
    return paths
}


fun main() {

    fun part1(): Any {
        val numpad = listOf(
            listOf('7','8','9'),
            listOf('4','5','6'),
            listOf('1','2','3'),
            listOf(null,'0','A')
        )
        val dirpad = listOf(
            listOf(null, '^','A'),
            listOf('<','v','>')
        )

        val numpadPaths = findPaths(numpad)
        val dirpadPaths = findPaths(dirpad)

        val cache:HashMap<Pair<Pair<Char,String>,Int>, Long> = hashMapOf()

        fun solveDirpad(code:String, level:Int, maxLevel:Int = 2, startC:Char = 'A'):Long {
            if (cache.containsKey(Pair(Pair(startC, code),level))) {
                return cache[Pair(Pair(startC, code),level)]!!
            }
            //find code positions
            var seqs = mutableListOf<String>()
            for (j in 0..<code.length) {
                val c1 = if (j ==0) startC else code[j-1]
                val c2 = code[j]
                val newSeqs = mutableListOf<String>()
                for (p in dirpadPaths[Pair(c1, c2)]!!) {
                    if (j == 0) {
                        newSeqs.add(p)
                        continue
                    }
                    for (s in seqs) {
                        newSeqs.add(s+p)
                    }
                }
                seqs = newSeqs
            }
            if (level == maxLevel) {
                cache[Pair(Pair(startC,code),level)] = seqs[0].length.toLong()
                return seqs[0].length.toLong()
            }

            //assume that all shortest paths have the same length
            cache[Pair(Pair(startC, code),level)] = seqs.fold(Long.MAX_VALUE) { r, s ->
                val r1 = s.withIndex().sumOf {  (i,c) ->
                    val startCC = if (i == 0) 'A' else s[i-1]
                    solveDirpad(c.toString(), level + 1, maxLevel, startCC)
                }
                min(r, r1)
            }
            return cache[Pair(Pair(startC,code),level)]!!
        }

        fun solveNumpad(code:String):Long {
            //find code positions
            var seqs = mutableListOf<String>()
            for (j in 0..<code.length) {
                val c1 = if (j ==0) 'A' else code[j-1]
                val c2 = code[j]
                val newSeqs = mutableListOf<String>()
                for (p in numpadPaths[Pair(c1, c2)]!!) {
                    if (j == 0) {
                        newSeqs.add(p)
                        continue
                    }
                    for (s in seqs) {
                        newSeqs.add(s+p)
                    }
                }
                seqs = newSeqs
            }

            return seqs.fold(Long.MAX_VALUE) { r, s ->
                val r1 = s.withIndex().sumOf {  (i,c) ->
                    val startCC = if (i == 0) 'A' else s[i-1]
                    solveDirpad(c.toString(), 1, 25, startCC)
                }
                min(r, r1)
            }
        }

        val testNums = listOf(
            Pair("029A", 29),
            Pair("980A",980),
            Pair("179A",179),
            Pair("456A",456),
            Pair("379A",379),
        )
        val numsToEnter = listOf(
            Pair("789A",789),
            Pair("540A",540),
            Pair("285A",285),
            Pair("140A",140),
            Pair("189A",189),
        )
        var acc = 0L
        for (numToEnter in numsToEnter) {
            val res = solveNumpad(numToEnter.first)
            Pair(res, numToEnter.second).println()
            acc += res * numToEnter.second
        }
        return acc
    }
    part1().println()
}
