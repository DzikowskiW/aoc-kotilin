import java.util.PriorityQueue

fun main() {


    data class Edge(val to:Vec2, val dir:Vec2, val weight:Int)

    data class Node(val pos:Vec2, val dir:Vec2, val edges:Set<Edge>, val distance: Int) : Comparable<Node> {
        override fun compareTo(other: Node) = distance.compareTo(other.distance)
    }

    fun findEdges(p:Vec2, dir:Vec2, graph:MutableSet<Vec2>):Set<Edge> {
        val edges = mutableSetOf<Edge>()
        for (d in DIRS) {
            val w = if (d == dir) 1 else 1001
            if (graph.contains(p + d)) edges.add(Edge(p + d, d, w))
        }
        return edges
    }

    fun getBestPlaceSum(backtrack:HashMap<Pair<Vec2,Vec2>,MutableSet<Pair<Vec2,Vec2>>>, endState:Pair<Vec2,Vec2>):Int {
        val seen:MutableSet<Pair<Vec2,Vec2>> = mutableSetOf(endState)
        val stateBacktrack:MutableList<Pair<Vec2,Vec2>> = mutableListOf(endState)
        while (stateBacktrack.isNotEmpty()) {
            val state = stateBacktrack.removeFirst()
            for (n in backtrack.getOrDefault(state, mutableSetOf())) {
                if (seen.contains(n)) continue
                seen.add(n)
                stateBacktrack.addLast(n)
            }
        }
        return seen.map { it.first }.toSet().size
    }

    fun modifiedDijsktra(road:MutableSet<Vec2>, start:Vec2, end: Vec2):Pair<Int,Int> {
        val dist:HashMap<Pair<Vec2,Vec2>, Int> = hashMapOf()
        val backtrack:HashMap<Pair<Vec2,Vec2>,MutableSet<Pair<Vec2,Vec2>>> = hashMapOf()
        val q = PriorityQueue<Node>()
        var minDist = Int.MAX_VALUE
        var endState:Pair<Vec2,Vec2>? = null

        dist[Pair(start, E)] = 0
        q.add(Node(start, E, findEdges(start, E, road), 0))

        while (q.isNotEmpty()) {
            val cur = q.poll()
            if (cur.distance > dist.getOrDefault(Pair(cur.pos, cur.dir), Int.MAX_VALUE)) continue
            if (cur.pos == end && cur.distance <=  minDist) {
                minDist = cur.distance
                endState = Pair(cur.pos,cur.dir)
            }
            for (edge in cur.edges) {
                val newDist = cur.distance + edge.weight
                val lowestDist = dist.getOrDefault(Pair(edge.to, edge.dir), Int.MAX_VALUE)
                if (newDist > lowestDist) continue
                if (newDist < lowestDist){
                    backtrack[Pair(edge.to, edge.dir)] = mutableSetOf()
                    dist[Pair(edge.to, edge.dir)] = newDist
                }
                backtrack[Pair(edge.to, edge.dir)]!!.add(Pair(cur.pos, cur.dir))
                q.add(Node(edge.to, edge.dir, findEdges(edge.to, edge.dir, road), newDist))
            }
        }

        if (endState == null) throw Exception("No end state for Dijsktra (Impossible challenge)")
        return Pair(minDist, getBestPlaceSum(backtrack, endState))
    }

    fun solve(input:List<String>): Any {
        val road:MutableSet<Vec2> = mutableSetOf()
        var start = Vec2(-1,-1)
        var end = Vec2(-1,-1)
        input.withIndex().forEach { (y, row) ->
            row.withIndex().forEach { (x, c) ->
                when (c) {
                    '.' -> road.add(Vec2(y,x))
                    'S' -> {
                        road.add(Vec2(y,x))
                        start = Vec2(y,x)
                    }
                    'E' -> {
                        road.add(Vec2(y,x))
                        end = Vec2(y,x)
                    }
                }
            }
        }
        return modifiedDijsktra(road, start, end)
    }

    val testInput = readInput("Day16_test")
    solve(testInput).println()

    val input = readInput("Day16")
    solve(input).println() //491 too high

}
