package gridgenerationms.game.kruskal

import java.util.*

class DSU(nodes: Int, seed: Long) {
    private var parent : IntArray
    private var rand : Random

     init {
        parent = IntArray(nodes) { i -> i }
        rand = Random(seed)
    }

     fun find (x : Int ): Int {
        if ( parent[x] != x ) {
            parent[x] = find( parent[x] )
        }
        return parent[x]
    }

    fun merge( x :Int, y :Int ) {
        val parX = find(x)
        val parY = find(y)

        if( parX == parY )
            return

        val option = (0..1).shuffled(rand).first()
        if ( option == 0 ) {
            parent[ parX ] = parY
        } else {
            parent[ parY ] = parX
        }
    }
}