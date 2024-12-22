import java.util.PriorityQueue

fun main() {
    val N = Vec2(-1,0)
    val E = Vec2( 0, 1)
    val S = Vec2(1, 0)
    val W = Vec2(0, -1)
    val DIRS = setOf(N,S,E,W)

    data class Edge(val to:Vec2, val weight:Int, val dir:Vec2)

    data class Node(val pos:Vec2, val edges:Set<Edge>, val distance: Int) : Comparable<Node> {
        override fun compareTo(other: Node) = distance.compareTo(other.distance)
    }

    fun findEdges(p:Vec2, dir:Vec2, graph:MutableSet<Vec2>):Set<Edge> {
        val edges = mutableSetOf<Edge>()
        for (d in DIRS) {
            val w = if (d == dir) 1 else 1001
            if (graph.contains(p + d)) edges.add(Edge(p + d, w, d))
        }
        return edges
    }

    fun dijsktra(road:MutableSet<Vec2>, start:Vec2, end: Vec2):Int {
        val dist:HashMap<Vec2, Int> = hashMapOf()
        dist[start] = 0
        val q = PriorityQueue<Node>()
        q.add(Node(start, findEdges(start, E, road), 0))

        while (q.isNotEmpty()) {
            val cur = q.poll()
            if (cur.distance > dist.getOrDefault(cur.pos, Int.MAX_VALUE)) continue
            for (edge in cur.edges) {
                val newDist = cur.distance + edge.weight
                if (newDist < dist.getOrDefault(edge.to, Int.MAX_VALUE)) {
                    dist[edge.to] = newDist
                    q.add(Node(edge.to, findEdges(edge.to, edge.dir, road), newDist))
                }
            }
        }
        return dist[end]!!
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

        return dijsktra(road, start, end)
    }

    val testInput = readInput("Day16_test")
    solve(testInput).println()

    val input = readInput("Day16")
    solve(input).println()

}
