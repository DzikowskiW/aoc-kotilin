import kotlin.math.min


fun main() {

    fun solve(input: List<String>, width:Int, height:Int): Any {
        val vals = input.map { row ->
             "-?\\d+".toRegex().findAll(row).map { it.value.toInt() }.toMutableList()
        }

        var time = 100
        while (time > 0) {
            time -= 1
            for (i in vals.indices) {
                vals[i][0] += vals[i][2]
                vals[i][1] += vals[i][3]
                if (vals[i][0] < 0) vals[i][0]+=width
                if (vals[i][1] < 0) vals[i][1]+=height
                vals[i][0] %= width
                vals[i][1] %= height
            }
        }

        val res = vals.fold(mutableListOf(0,0,0,0)) { acc, v ->
            if (v[0] < width/2 ) {
                if (v[1] < height/2)  acc[0] += 1
                if (v[1] > height/2)  acc[1] += 1
            }
            if (v[0] > width/2 ) {
                if (v[1] < height/2)  acc[2] += 1
                if (v[1] > height/2)  acc[3] += 1
            }
            acc
        }

        return res.fold(1L) { acc, v -> acc*v }
    }

    val testInput = readInput("Day14_test")
    solve(testInput, 11, 7).println()

    val input = readInput("Day14")
    solve(input, 101, 103).println()
}
