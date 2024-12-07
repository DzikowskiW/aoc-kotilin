
fun main() {

    fun findEvals(nums:List<Long>,result:Long, acc:Long, i:Int):Int{
        if (acc > result ) return 0
        if (i >= nums.size) {
            return if (result == acc) 1 else 0
        }
        return findEvals(nums, result, acc*nums[i], i + 1) +
                findEvals(nums, result, acc+nums[i], i + 1)
    }

    fun findEvals2(nums:List<Long>,result:Long, acc:Long, i:Int):Int{
        if (acc > result ) return 0
        if (i >= nums.size) {
            return if (result == acc) 1 else 0
        }
        return findEvals2(nums, result, acc*nums[i], i + 1) +
                findEvals2(nums, result, acc+nums[i], i + 1) +
                findEvals2(nums, result, (acc.toString()+nums[i].toString()).toLong(), i + 1)
    }

    fun bothParts(input: List<String>):Pair<Long, Long> = input.fold(Pair(0L,0L)) { acc, row ->
        val (resStr, numsStr) = row.split(": ")
        val res = resStr.toLong()
        val nums = numsStr.split(" ").map { it.toLong() }
        val part1 = findEvals(nums, res, nums[0], 1)
        val part2 = findEvals2(nums, res, nums[0], 1)
        Pair(
            if (part1 > 0) acc.first + res else acc.first,
            if (part2 > 0) acc.second + res else acc.second
        )
    }

    val testInput = readInput("Day07_test")
    bothParts(testInput).println()

    val input = readInput("Day07")
    bothParts(input).println()
}
