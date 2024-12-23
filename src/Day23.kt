

fun main() {
    fun solve(input: List<String>): Any {
        val g:HashMap<String,MutableSet<String>> = hashMapOf()
        val cycle3s:MutableSet<Set<String>> = mutableSetOf()

        fun dfs(cur:String, toFind:String, remainingDepth:Int):Set<Set<String>>? {
            if (remainingDepth == 0){
                return if (cur == toFind) setOf(setOf(cur)) else null
            }
            val a = g[cur]!!.fold(setOf<Set<String>>()) { acc, el ->
                val res = dfs(el, toFind, remainingDepth - 1)
                if (res != null) {
                    acc.union(res.map{ it.union(setOf(cur)) })
                } else {
                    acc
                }
            }
            return if (a.isNotEmpty()) a else null
        }

        input.forEach { row ->
            val a = row.split("-")
            if (!g.containsKey(a[0])) g[a[0]] = mutableSetOf()
            if (!g.containsKey(a[1])) g[a[1]] = mutableSetOf()
            g[a[0]]!!.add(a[1])
            g[a[1]]!!.add(a[0])
        }

        g.keys.forEach { k ->
            if (k.startsWith("t")) {
                val cycles = dfs(k, k, 3)
                cycles?.forEach { cycle3s.add(it) }
            }
        }
        return cycle3s.size
    }

    val testInput = readInput("Day23_test")
    solve(testInput).println()

    val input = readInput("Day23")
    solve(input).println() //2149 too high
}
