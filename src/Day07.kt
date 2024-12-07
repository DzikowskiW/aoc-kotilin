
fun main() {

    fun findEvals(nums:List<Long>,result:Long, acc:Long, i:Int):Int{
        if (acc > result ) return 0
        if (i >= nums.size) {
//            println(result)
            return if (result == acc) 1 else 0
        }
        return findEvals(nums, result, acc*nums[i], i + 1) + findEvals(nums, result, acc+nums[i], i + 1)
    }

    fun part1(input: List<String>):Long = input.fold(0L) { acc, row ->
        val (resStr, numsStr) = row.split(": ")
        val res = resStr.toLong()
        val nums = numsStr.split(" ").map { it.toLong() }
        val a = findEvals(nums, res, nums[0], 1)
//        println("$res | $a | $nums")
        if (a > 0) acc + res else acc
    }

    fun part2(input: List<String>):Int {
        return 0
    }

    val testInput = readInput("Day07_test")
    part1(testInput).println()
//    part2(testInput).println()

    val input = readInput("Day07")
    part1(input).println()
//    part2(input).println()
}
