import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun calcSecret(v:Long):Long {
    var res = v
    var n1 = res shl 6
    res = res xor n1
    res %= 16777216
    var n2 = res shr 5
    res = res xor n2
    res %= 16777216
    var n3 = res shl 11
    res = res xor n3
    res %= 16777216
    return res
}

fun main() {

    fun solve(input: List<String>): Any {
        val secretNums:MutableList<Long> = input.map { it.toLong() }.toMutableList()

//        var n = 123L
//        for (i in 0..5) {
//            n = calcSecret(n)
//            n.println()
//        }

        for (i in 0..<2000) {
            secretNums.withIndex().forEach { (k,v) ->
                secretNums[k] = calcSecret(v)
            }
        }
        return secretNums.sum()
    }



    val testInput = readInput("Day22_test")
//    solve(testInput).println()

    val input = readInput("Day22")
    solve(input).println()

}
