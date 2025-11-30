package year2024

import println
import readInput

fun main() {

    fun checkVector(input:List<String>, y:Int, x:Int, toFind:String, vector:Pair<Int,Int>):Int {
        // bounds
        if (toFind.length == 0 || y !in input.indices || x !in 0..<input[y].length) {
            return 0
        }
        //correct value
        if (toFind[0] != input[y][x]) {
            return 0
        }
        //if it is the last value to find, return
        if (toFind == "S") {
            return 1
        }
        return checkVector(input,y + vector.first, x + vector.second, toFind.drop(1), vector)
    }


    fun part1(input: List<String>):Int  {
        var counter = 0
        for (i in 0..<input.size) {
            for (j in 0..<input[i].length) {
                counter += checkVector(input, i, j, "XMAS", Pair(-1,-1))
                counter += checkVector(input, i, j, "XMAS", Pair(-1, 0))
                counter += checkVector(input, i, j, "XMAS", Pair(-1, 1))
                counter += checkVector(input, i, j, "XMAS", Pair( 0,-1))
                counter += checkVector(input, i, j, "XMAS", Pair( 0, 1))
                counter += checkVector(input, i, j, "XMAS", Pair( 1,-1))
                counter += checkVector(input, i, j, "XMAS", Pair( 1, 0))
                counter += checkVector(input, i, j, "XMAS", Pair( 1, 1))
            }
        }
        return counter
    }

    fun part2(input: List<String>):Int {
       var  sum = 0
        for (i in 1..<input.size - 1) {
            for (j in 1..<input[i].length -1) {
                if (input[i][j] == 'A') {
                    if ((input[i-1][j-1] == 'M' &&
                        input[i+1][j-1] == 'M' &&
                        input[i-1][j+1] == 'S' &&
                        input[i+1][j+1] == 'S') ||

                        (input[i-1][j-1] == 'M' &&
                        input[i-1][j+1] == 'M' &&
                        input[i+1][j-1] == 'S' &&
                        input[i+1][j+1] == 'S') ||

                        (input[i-1][j+1] == 'M' &&
                        input[i+1][j+1] == 'M' &&
                        input[i-1][j-1] == 'S' &&
                        input[i+1][j-1] == 'S') ||

                        (input[i+1][j+1] == 'M' &&
                        input[i+1][j-1] == 'M' &&
                        input[i-1][j+1] == 'S' &&
                        input[i-1][j-1] == 'S')) {
                            sum += 1
                    }
                }
            }
        }
        return sum
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 18)
//    part2(testInput).println()
    check(part2(testInput) == 9)

    val input = readInput("Day04")
        part1(input).println()
        part2(input).println()
}
