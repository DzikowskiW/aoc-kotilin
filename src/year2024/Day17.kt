package year2024

import println
import readInput
import kotlin.math.pow

fun main() {

    fun runProgram(aa:Long, bb:Long, cc: Long, program:List<Int>):List<Int> {
        var a = aa
        var b = bb
        var c = cc

        fun getComboVal(o:Int):Long = when (o) {
            in 0..3 -> o.toLong()
            4 -> a
            5 -> b
            6-> c
            else -> throw Error("Invalid op val")
        }

        var h = 0
        val output = mutableListOf<Int>()

        while (true) {
            if (h !in program.indices)
                break
            val opcode = program[h]
            val operand = program[h+1]
//            println("s:$h | $opcode,$operand | ${a.toString(8)}, ${b.toString(8)}, ${c.toString(8)}")
            when (opcode) {
                0 ->
                    a /= 2.0.pow(getComboVal(operand).toInt()).toLong()
                1 ->
                    b = b xor operand.toLong()
                2 ->
                    b= getComboVal(operand) % 8
                3 ->
                    if (a != 0L) {
                        h = operand
//                        println("!!!:$h | $opcode,$operand | ${a.toString(8)}, ${b.toString(8)}, ${c.toString(8)}")
                        continue
                    }
                4 ->
                    b = b xor c
                5-> {
                    output.addLast((getComboVal(operand) % 8).toInt())
//                    println("--------")
//                    output.println()
//                    println("--------")

                }
                6->
                    b = a / 2.0.pow(getComboVal(operand).toInt()).toLong()
                7->
                    c = a / 2.0.pow(getComboVal(operand).toInt()).toLong()
                else -> throw Error("Invalid opcode")
            }
//            println("e:$h | $opcode,$operand | ${a.toString(8)}, ${b.toString(8)}, ${c.toString(8)}")
            h += 2
        }

        return output
    }

    fun part1(input: List<String>): Any {
        val a = Regex("\\d+").find(input[0])!!.value.toLong()
        val b = Regex("\\d+").find(input[1])!!.value.toLong()
        val c = Regex("\\d+").find(input[2])!!.value.toLong()

        val program = input[4].substring(9).split(",").map { it.toInt() }

        return runProgram(a,b,c, program)
    }

    fun part2(input: List<String>): Any {

        fun solve(p:List<Int>, res:Long):Long? {
            if (p.isEmpty()) return res
            for (n in 0..<8) {
                val a = (res shl 3) + n
                var b = a % 8
                b = b xor 2
                val c = a shr b.toInt()
                b = b xor c
                b = b xor 3
                if ((b % 8).toInt() == p.last()) {
                    val right = solve(p.subList(0,p.size-1), a) ?: continue
                    return right
                }
            }
            return null
        }

        val programStr = input[4].substring(9)
        val program = programStr.split(",").map { it.toInt() }

        return solve(program, 0L) ?: -1L
    }

    val testInput = readInput("Day17_test")
//    part1(testInput).println()
//    part2(testInput).println()

    val input = readInput("Day17")
    part1(input).println()
    part2(testInput).println()

}
