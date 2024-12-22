import java.io.File

fun displayRobots(vals:List<List<Int>>,width:Int, height:Int, time:Int) {
    val out:MutableList<MutableList<Char>> = mutableListOf()
    for (y in 0..<height) {
        val chars = mutableListOf<Char>()
        for (x in 0..<width) {
            var ctoAdd = '.'
            for (v in vals) {
                if (v[0] == x && v[1] == y) {
                    ctoAdd = '*'
                    break
                }
            }
            chars.add(ctoAdd)
        }
        out.addLast(chars)
    }
    val file = File("zzz_$time.txt")
    file.printWriter().use { writer ->
        for (o in out) {
            writer.println(o.joinToString(""))
        }
    }
}

fun main() {

    fun solve(input: List<String>, width:Int, height:Int): Any {
        val vals = input.map { row ->
             "-?\\d+".toRegex().findAll(row).map { it.value.toInt() }.toMutableList()
        }

        var time = 15000
        var ctime = 0
        var timeToDisplay = 46
        var part1:MutableList<Int>? = null
        while (time > 0) {
            ctime += 1
            time -= 1
            for (i in vals.indices) {
                vals[i][0] += vals[i][2]
                vals[i][1] += vals[i][3]
                if (vals[i][0] < 0) vals[i][0]+=width
                if (vals[i][1] < 0) vals[i][1]+=height
                vals[i][0] %= width
                vals[i][1] %= height
            }
            if (ctime == timeToDisplay) {
                displayRobots(vals, width, height, ctime)
                timeToDisplay += width
            }
            if (ctime == 100) {
                part1 = vals.fold(mutableListOf(0,0,0,0)) { acc, v ->
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
        return part1!!.fold(1L) { acc, v -> acc*v }
    }

    val testInput = readInput("Day14_test")
//    solve(testInput, 11, 7).println()

    val input = readInput("Day14")
    solve(input, 101, 103).println()
}
