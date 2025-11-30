package year2024

import println
import readInput

fun main() {
    fun solve(input: List<String>): Any {
        val g: HashMap<String, MutableSet<String>> = hashMapOf()

        // PARSE INPUT
        input.forEach { row ->
            val a = row.split("-")
            if (!g.containsKey(a[0])) g[a[0]] = mutableSetOf()
            if (!g.containsKey(a[1])) g[a[1]] = mutableSetOf()
            g[a[0]]!!.add(a[1])
            g[a[1]]!!.add(a[0])
        }

        // PART 1
        val sets1: MutableSet<Set<String>> = mutableSetOf()
        g.keys.forEach { k ->
            if (k.startsWith("t")) {
                g[k]!!.forEach { k1 ->
                    g[k1]!!.forEach { k2 ->
                        g[k2]!!.forEach { k3 ->
                            if (k3 == k) {
                                sets1.add(setOf(k, k1, k2))
                            }
                        }
                    }
                }
            }
        }
        val part1 = sets1.size

        // PART 2
        val sets2: MutableSet<Set<String>> = mutableSetOf()

        fun search(cur: String, lan: Set<String>) {
            if (sets2.contains(lan)) return
            sets2.add(lan)
            for (c in g[cur]!!) {
                if (lan.contains(c)) continue
                if (!g[c]!!.containsAll(lan)) continue
                search(c, lan + c)
                sets2.add(lan)
            }
        }

        g.keys.forEach { k->
            search(k, setOf(k))
        }
        val part2 = sets2.maxBy { it.size }.sorted().joinToString(",")
        return Pair(part1,part2)
    }

    val testInput = readInput("Day23_test")
    solve(testInput).println()

    val input = readInput("Day23")
    solve(input).println()
}
