import kotlin.math.pow

fun main() {

    fun runProgram(aa:Int, bb:Int, cc: Int, program:List<Int>):Any {
        var a = aa
        var b = bb
        var c = cc

        fun getComboVal(o:Int) = when (o) {
            in 0..3 -> o
            4 -> a
            5 -> b
            6-> c
            else -> throw Error("Invalid op val")
        }

        var h = 0
        var output = ""

        while (true) {
            if (h !in program.indices)
                break
            val opcode = program[h]
            val operand = program[h+1]
            when (opcode) {
                0 ->
                    a /= 2.0.pow(getComboVal(operand)).toInt()
                1 ->
                    b = b xor operand
                2 ->
                    b= getComboVal(operand) % 8
                3 ->
                    if (a != 0) {
                        h = operand
                        continue
                    }
                4 ->
                    b = b xor c
                5->
                    output += (if (output.isNotEmpty()) "," else "") +(getComboVal(operand) % 8)
                6->
                    b = a / 2.0.pow(getComboVal(operand)).toInt()
                7->
                    c = a / 2.0.pow(getComboVal(operand)).toInt()
                else -> throw Error("Invalid opcode")
            }
            h += 2
        }

        return output
    }

    fun part1(input: List<String>): Any {
        var a = Regex("\\d+").find(input[0])!!.value.toInt()
        var b = Regex("\\d+").find(input[1])!!.value.toInt()
        var c = Regex("\\d+").find(input[2])!!.value.toInt()

        var program = input[4].substring(9).split(",").map { it.toInt() }


        return runProgram(a,b,c, program)
    }

    val testInput = readInput("Day17_test")
//    solve(testInput).println()

    val input = readInput("Day17")
    part1(input).println()
}
