package gridgenerationms.game.grid

import gridgenerationms.game.kruskal.Kruskal

class Grid(height: Int, width: Int, seed: Long) {

    private var mst :Kruskal

    var n :Int = height
    var m :Int = width
    var matrix :Array<BooleanArray>
    var exit :Pair<Int,Int>


    init {
        mst = Kruskal(n, m, seed)
        matrix = mst.matrix
        exit = mst.exit
    }

}

/*
fun main(args: Array<String>) { //Used for testing

    val n = 9
    val m = 9

    for ( it in (0..20) ) {
        println("Seed")
        println(it)

        println("Next two should be equal")

        for( rep in (0..1) ) {
            val mat = Grid(n, m, it.toLong())
            for (r in 0 until n) {
                for (c in 0 until m) {
                    if (mat.matrix[r][c]) {
                        print("X")
                    } else {
                        print(" ")
                    }
                }
                println("")
            }
            println("")
        }
    }

}

 */