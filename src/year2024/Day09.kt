package year2024

import println
import readInput

fun main() {

    fun part1(input: List<String>): Long {
        val disk = mutableListOf<String>()
        var digit = 0
        input[0].map { el -> el.toString().toInt()}.fold(false) { free, el ->
            val c = if (free) '.' else digit.toString()
            if (!free) digit = (digit + 1)
            for (j in 0..<el) {
                disk.add(c.toString())
            }
            !free
        }
        var start = 0
        var end = disk.size-1
        while (start<=end) {
            if (disk[start] == ".") {
                disk[start] = disk[end]
                disk[end] = "."
                do {
                    end -= 1
                } while (disk[end] == ".")
            }
            start += 1
        }
        var count = 0L
        for (i in disk.indices) {
            if (disk[i] == ".") break
            count +=  i * disk[i].toInt()
        }
        return count
    }

    fun part2(input: List<String>): Long {
        val disk = mutableListOf<Int>()
        var digit = 0
        val diskSizes= hashMapOf<Int,Int>()
        val freeSpaces = mutableListOf<Pair<Int, Int>>()
        input[0].map { el -> el.toString().toInt()}.fold(false) { free, el ->
            val c = if (free) -1 else digit
            if (!free) {
                diskSizes[c] = el
                digit += 1
            } else {
                freeSpaces.add(Pair(disk.size, el))
            }
            for (j in 0..<el) {
                disk.add(c)
            }
            !free
        }
        for (n in digit-1 downTo 0) {
            val nSize = diskSizes[n]!!
            var curFreeSpace = 0
            for (j in disk.indices) {
                if (disk[j] == n) break
                if (disk[j] == -1) curFreeSpace += 1
                if (disk[j] != -1 ) curFreeSpace = 0
                if (curFreeSpace >= nSize) {
                    val neww = j - curFreeSpace + 1
                    val old = disk.indexOf(n)
                    for (k in 0..<nSize) {
                        disk[neww+k] = n
                        disk[old+k] = -1
                    }
                    break
                }
            }
        }

        var count = 0L
        for (i in disk.indices) {
            if (disk[i] != -1)
            count +=  i * disk[i]
        }
        return count
    }

    val testInput = readInput("Day09_test")
    part1(testInput).println()
    part2(testInput).println()

    val input = readInput("Day09")
     part1(input).println()
     part2(input).println()
}
