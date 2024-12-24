

fun main() {
    data class Device(val l:String, val r:String, val op:String, var lval:Int? = null, var rval:Int? = null ) {
        fun calc():Int? {
            if (lval == null || rval == null) return null
            return when (op) {
                "AND" -> if (lval == 1 && rval ==1) 1 else 0
                "OR" -> if (lval == 1 || rval ==1) 1 else 0
                "XOR" -> if ((lval == 1 || rval == 1) && lval != rval) 1 else 0
                else -> throw Error("Invalid operation")
            }
        }
    }

    fun solve(input: List<String>): Any {
        val splitIndex= input.indexOf("")
        val initValsInput = input.subList(0,splitIndex)
        val devicesInput = input.subList(splitIndex+1, input.size)

        val initVals = hashMapOf<String,Int>()
        val devices = hashMapOf<String,Device>()

        for (r in initValsInput) {
           val rsplit = r.split(": ")
            initVals[rsplit[0]] = rsplit[1].toInt()
        }
        for (r in devicesInput) {
            val rsplit = r.split(" ")
            val res = rsplit[4]
            val left = rsplit[0]
            val right = rsplit[2]
            val op = rsplit[1]
            devices[res] = Device(left , right ,op)
        }

        val edge = devices.keys.filter { it.startsWith("z") }.sorted()
        fun checkDevice(label:String):Int? {
            if (initVals.containsKey(label)) {
                return initVals[label]
            }
            if (!devices.containsKey(label)){
                throw Exception("Invalid device label $label")
            }
            val d = devices[label]!!
            if (d.lval == null) {
                d.lval = checkDevice(d.l)
                initVals[d.l] = d.lval!!
            }
            if (d.rval == null) {
                d.rval = checkDevice(d.r)
                initVals[d.r] = d.rval!!
            }
            return d.calc()
        }

        return edge.map { checkDevice(it) }.joinToString("").reversed().toLong(2)
    }

    val testInput = readInput("Day24_test")
//    solve(testInput).println()

    val input = readInput("Day24")
    solve(input).println()
}
