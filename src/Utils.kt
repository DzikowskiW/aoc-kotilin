import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * Reads lines from the given input txt file.
 */
const val CURRENT_YEAR = "2025"
fun readInput(name: String) = Path("src/year$CURRENT_YEAR/$name.txt").readText().trim().lines()
fun readInputBlob(name: String) = Path("src/year$CURRENT_YEAR/$name.txt").readText().trim()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

data class Vec2(val y:Int,val x:Int) {
    operator fun plus(other: Vec2): Vec2 {
        return Vec2(this.y + other.y, this.x + other.x)
    }
}

fun printVec2d(points:Set<Vec2>, maxy:Int, maxx:Int) {
    for (y in 0..maxy) {
        var line = ""
        for (x in 0..maxx) {
            line += if (Vec2(y,x) in points) "o" else "."
        }
        line.println()
    }
}

val N = Vec2(-1,0)
val E = Vec2( 0, 1)
val S = Vec2(1, 0)
val W = Vec2(0, -1)
val DIRS = setOf(N,S,E,W)

fun gcd(a: Int, b: Int): Int {
    var num1 = a
    var num2 = b
    while (num2 != 0) {
        val temp = num2
        num2 = num1 % num2
        num1 = temp
    }
    return num1
}