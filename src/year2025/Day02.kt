package year2025

import println
import readInput
import readInputBlob

fun findInvalid(from:String, to:String): Long{
    val fromL = from.toLong(10)
    val toL = to.toLong(10)
    val res = mutableSetOf<Long>()
    var sum = 0L
    for (i in fromL..toL) {
        val istr = i.toString();
        if (istr.length % 2 == 0 && istr.take(istr.length / 2) == istr.substring(istr.length / 2)) {
            res.add(i)
            sum += i
//            i.println()
        }
    }
//    sum.println()
    return sum
}

fun findInvalidP2(from:String, to:String): Long{
    val fromL = from.toLong(10)
    val toL = to.toLong(10)
    val res = mutableSetOf<Long>()
    var sum = 0L
    for (i in fromL..toL) {
        val istr = i.toString();
        val istrlen = istr.length
        for (j in 1..istrlen/2){
            if (istrlen % j != 0) continue
            val chunks = istr.chunked(j)
            if (chunks.all { it == chunks[0] }) {
                i.println()
                sum += i
                break
            }
        }
//        if (istr.length % j == 0 && istr.take(istr.length / 2) == istr.substring(istr.length / 2)) {
//            res.add(i)
//            sum += i
//            i.println()
//        }
    }
//    sum.println()
    return sum
}

fun main() {

    fun part1(input: List<List<String>>): Long {
        val sum = input.fold(0L){ acc, (l,r) -> acc + findInvalid(l,r) }
        return sum
    }

    fun part2(input: List<List<String>>): Long {
        val sum = input.fold(0L){ acc, (l,r) -> acc + findInvalidP2(l,r) }
        return sum
    }
    val testInput = readInput("Day02_test")
        .first()
        .split(",")
        .map { str -> str.split("-") }

//    part1(testInput).println()
    part2(testInput).println()
//        check(part1(testInput) == 3)
//        check(part2(testInput) == 6)

    val input = readInput("Day02")
        .first()
        .split(",")
        .map { str -> str.split("-") }
//    part1(input).println()
        part2(input).println()
}
