fun main() {

    fun part1(input: List<String>): Long {
        val disk = mutableListOf<String>()
        var digit = 0
        input[0].map { el -> el.toString().toInt()}.fold(false) { free, el ->
            val c = if (free) '.' else digit.toString()
            if (!free) digit = (digit + 1)
            for (j in 0..<el) {
                disk.add(c.toString())
            }
            !free
        }
        var start = 0
        var end = disk.size-1
        while (start<=end) {
            if (disk[start] == ".") {
                disk[start] = disk[end]
                disk[end] = "."
                do {
                    end -= 1
                } while (disk[end] == ".")
            }
            start += 1
        }
        var count = 0L
        for (i in disk.indices) {
            if (disk[i] == ".") break
            count +=  i * disk[i].toInt()
        }
//        disk.println()
        return count
    }

    val testInput = readInput("Day09_test")
    part1(testInput).println()

    val input = readInput("Day09")
    // 1351621719 to low
    // 5646589015
    part1(input).println()
}
