package year2025

import println
import readInput


fun main() {

    fun part1(input: List<String>): Long {

        val grid = input.map { line ->
            line.trim().split("\\s+".toRegex())
        }
        val ops = grid.last()
        val nums = grid.dropLast(1)
//        ops.println()
//        nums.println()

        return ops.foldIndexed(0L) { i, sum,op ->
            var res = 0L
            val cols = nums.map{ line -> line[i] }
//            cols.println()
//            op.println()
            if (op == "*") {
                res = sum + cols.fold(1L) { acc, num -> acc * num.toLong() }
            }
            else if (op == "+") {
                res = sum + cols.fold(0L) { acc, num -> acc + num.toLong() }
            }
            else check(false)
            res
        }
    }

    fun calc(op: Char, nums: List<Long>): Long {
        if (op == '*') {
            return nums.fold(1L) { acc, num -> acc * num }
        }
        else if (op == '+') {
            return nums.fold(0L) { acc, num -> acc + num }
        }
        check(false)
        return 0L
    }

    fun part2(input: List<String>): Long {

        val columns = input[0].indices.map { col ->
            input.map { line ->
                var res = ' '
                if (col < line.length) res = line[col]
                res
            }
        }.map { it.joinToString("") }

        var sum = 0L
        var op = ' '
        var nums = mutableListOf<Long>()
        columns.forEach { s ->
            var num: Long
            if (s.trim().isNotEmpty()) {
                if (arrayOf('*','+').contains(s.last())) {
                    op = s.last()
                    num = s.dropLast(1).trim().toLong()
                } else {
                    num = s.trim().toLong()
                }
                nums.add(num)
            }
            else {
                sum += calc(op, nums)
                nums = mutableListOf()
                op = ' '
            }
        }
        sum += calc(op, nums)

        return sum
    }


    val testInput = readInput("Day06_test")
        part1(testInput).println()
        part2(testInput).println()

    val input = readInput("Day06")
        part1(input).println()
        part2(input).println()
}
