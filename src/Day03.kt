import java.math.BigDecimal
import java.math.BigInteger

fun main() {

    fun part1(input: List<String>): BigDecimal {
        var row = input.joinToString(separator = "")
        input.println()
        val muls = Regex("mul\\((\\d\\d?\\d?),(\\d\\d?\\d?)\\)").findAll(row)
        val res = muls.fold(BigDecimal.ZERO) { sum, el ->
            sum + el.groupValues[1].toBigDecimal() * el.groupValues[2].toBigDecimal()
        }
        return res
    }

//    fun part2(input: List<String>): Int {
//        return 2
//    }

    val testInput = readInput("Day03_test")
        check(part1(testInput) == BigDecimal(161))
//      check(part2(testInput) == 4)

    val input = readInput("Day03")
        part1(input).println()
//      part2(input).println()
}
