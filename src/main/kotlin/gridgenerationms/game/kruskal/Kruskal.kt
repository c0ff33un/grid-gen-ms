package gridgenerationms.game.kruskal

import java.util.*
/*
 Kruskal randomized minimum spanning tree over a n * m matrix; maze generation
 */


class Kruskal(height: Int, width: Int, seed: Long ) {

    /*
        deltas used to explore neighbours on matrix( down, right, up, left )
     */
    private val deltaX = intArrayOf(1,0,-1,0)
    private val deltaY = intArrayOf(0,1,0,-1)

    private var n :Int = height
    private var m :Int = width
    private var dsu :DSU
    private var reachable :Int = 0
    private var coords :MutableList<Pair<Int,Int>>
    private var rand :Random

    var exit :Pair<Int,Int> = Pair(0,0)
    var matrix :Array<BooleanArray>


    init {
        matrix = Array(n) { BooleanArray(m) {true} }
        coords = MutableList<Pair<Int,Int>>(n*m) { i -> getPos(i) }
        rand = Random(seed)
        var iterations: Int = 0 //Ensure finishi on small cases

        do {
            dsu = DSU(n*m, 0)
            coords.shuffle(rand)

            for ( pos in coords )
                if( !matrix[pos.first][pos.second] )
                    assert(addToTree(pos)) //add to dsu data from previous iterations

            for ( pos in coords )
                if( !inBorder(pos) && addToTree(pos) )
                    matrix[pos.first][pos.second] = false

            if ( iterations == 0 )
                assignExit()

            for ( pos in coords )
                if (!matrix[pos.first][pos.second] && dsu.find(getId(pos)) != dsu.find(getId(exit)) )
                    matrix[pos.first][pos.second] = true //update non-reachable coordinates

            reachable = 0
            for ( pos in coords )
                if( !matrix[pos.first][pos.second] )
                    ++reachable

            ++iterations
        } while ( 2 * reachable < n * m && iterations < 20 )
    }

    private fun assignExit () {
        for ( pos in coords ) {
            if (!inBorder(pos) || isCorner(pos))
                continue

            val neighbors: MutableList<Pair<Int, Int>> = getNeighbours(pos)

            var valid: Boolean = false
            for (it in neighbors)
                if (!matrix[it.first][it.second]) {
                    exit = pos
                    valid = true
                    break
                }

            if (valid) break
        }
        assert(addToTree(exit))
        matrix[exit.first][exit.second] = false
    }

    private fun addToTree ( pos :Pair<Int,Int> ) : Boolean {

        val neighbours : MutableList< Pair<Int,Int> > = getNeighbours( pos )
        val neighborsParents : MutableSet< Int > = mutableSetOf<Int>()

        for ( it in neighbours ) {
            neighborsParents.add( dsu.find( getId(it ) ) )
        }

        if( neighbours.size != neighborsParents.size )
            return  false

        for ( parent in neighborsParents )
            dsu.merge( getId(pos), parent )

        return true
    }

    private fun getNeighbours ( pos :Pair<Int,Int> ) : MutableList< Pair<Int,Int> > {
        val arr: MutableList<Pair<Int, Int>> = mutableListOf<Pair<Int, Int>>()

        for (d in (0..3)) {
            val neighbour: Pair<Int, Int> = Pair(pos.first + deltaX[d], pos.second + deltaY[d])
            if (validCoordinate(neighbour) && !matrix[neighbour.first][neighbour.second]) {
                arr.add(neighbour)
            }
        }
        return arr
    }

    private fun getPos ( id : Int ) : Pair<Int,Int> {
        return Pair(id/m,id%m)
    }

    private fun getId ( pos :Pair<Int,Int> ) : Int {
        return pos.first * m + pos.second
    }

    private fun validCoordinate ( pos :Pair<Int,Int> ) : Boolean {
        if ( pos.first < 0 || pos.first >= n )
            return false
        if ( pos.second < 0 || pos.second >= m )
            return false
        return true
    }

    private fun isCorner ( pos :Pair<Int,Int> ) : Boolean {
        if( pos.first == 0 || pos.first == n - 1 )
            return ( pos.second == 0 || pos.second == m - 1 )
        return false
    }
    private fun inBorder ( pos :Pair<Int,Int> ) : Boolean {
        if ( pos.first == 0 || pos.first == n - 1 )
            return  true
        if ( pos.second == 0 || pos.second == m - 1 )
            return  true
        return false
    }
}
