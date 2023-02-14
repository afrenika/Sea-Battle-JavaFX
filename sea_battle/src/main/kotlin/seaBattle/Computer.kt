package seaBattle

import kotlin.random.Random

class Computer(private val seaBattleController:SeaBattleController) {

    var field = Array(10){Array(10){0}}

    //i, j - точка, в которую первой в корабле попал компьютер, iN jN текущее положение,
    // dI dJ - вертикальное или горизонтальное смещение, size = размер корабля,
    // count - подсчет изменения направления, check - фиксирование попадания
    inner class Point(var i:Int, var j:Int) {
        var iN = i
        var jN = j
        var size = 0
        var dI = 2
        var dJ = 2
        var count = 0
        var check = false


        fun hod(seaBattleController:SeaBattleController):Int{
            return if (iN + dI in 0..9 && jN + dJ in 0..9){
                iN += dI
                jN += dJ
                if (field[iN][jN] == 1){
                    return -1 }
                field[iN][jN] = 1
                seaBattleController.attack(iN + 1, jN + 1, 2)}
                    else{-1}
            }
    }

    val rand = Random
    var point:Point? = null
    var p:Int = 0


    fun attack(){
        var dop = -1
        if (point == null){
            var t = true
            while (t){
                val randomI = rand.nextInt(0, 10)
                val randomJ = rand.nextInt(0, 10)

                if(field[randomI][randomJ] == 0){
                    val dop = seaBattleController.attack(randomI + 1, randomJ + 1, 2)
                    t = dop == -1
                    field[randomI][randomJ] = 1
                    if (dop in 1..4){
                        point = Point(randomI, randomJ)
                        point!!.size = dop
                        if (dop == 7){
                            bound(point!!.iN, 1, point!!.jN, 'v')
                            point = null}
                    }}}}
        else{
            while(true){
            if (dop == 7){
                if (point!!.dI == 0){
                    bound(if(point!!.dJ > 0) point!!.jN - point!!.size + 1 else point!!.jN, point!!.size, point!!.iN, 'h')
                }
                else{
                    bound(if(point!!.dI > 0) point!!.iN - point!!.size + 1 else point!!.iN, point!!.size, point!!.jN, 'v')
                }
                point = null}
            if (dop != - 1){
                break}
            if (point!!.dI == 2){
                val z = if(rand.nextInt(0, 2) == 0) 1 else -1
                point!!.dI = (if(rand.nextInt(0, 2) == 0) 1 else 0) * z
                point!!.dJ = (if (point!!.dI == 0) 1 else 0) * z
                dop = point!!.hod(seaBattleController)
                p = dop
                point!!.check = dop in arrayOf(1, 2, 3, 4) }
            else if (point!!.check  || point!!.count < 1){
                if (p !in arrayOf(1, 2, 3, 4)){
                    point!!.dI *= -1
                    point!!.dJ *= -1
                    point!!.count++
                    point!!.iN = point!!.i
                    point!!.jN = point!!.j}
                dop = point!!.hod(seaBattleController)
                p = dop}
            else if(!point!!.check){
                dop = point!!.dI
                point!!.dI = point!!.dJ
                point!!.dJ = dop
                point!!.iN = point!!.i
                point!!.jN = point!!.j
                point!!.count = 0
                dop = point!!.hod(seaBattleController)
                p = dop
                point!!.check = dop in arrayOf(1, 2, 3, 4)}}}
    }

    //на поле компьютера вокруг корабля рисуется рамка, куда бить не надо
    fun bound(b1: Int, size: Int, ij:Int, n:Char){

        val ijm = (ij - 1) >= 0
        val ijp = (ij + 1) <= 9


        if (n == 'v'){
            if ((b1 - 1) >= 0){
                if(ijm){
                    field[b1 - 1][(ij - 1)] = 1}
                field[b1 - 1][ij] = 1
                if(ijp){
                    field[b1 - 1][ij + 1] = 1
                }}
            for(i in b1 until b1 + size){
                if(ijm){
                    field[i][ij - 1] = 1
                }
                field[i][ij] = 1
                if(ijp){
                    field[i][ij + 1] = 1
                }
            }
            if ((b1 + size) <= 9){
                if(ijm){
                    field[b1 + size][(ij - 1)] = 1}
                field[b1 + size][ij] = 1
                if(ijp){
                    field[b1 + size][ij + 1] = 1
                }}}

        else if (n == 'h'){
            if (b1 - 1 >= 0){
                if(ijm){
                    field[(ij - 1)][b1 - 1] = 1}
                field[ij][b1 - 1] = 1
                if(ijp){
                    field[ij + 1][b1 - 1] = 1
                }}
            for(i in b1 until b1 + size){
                if(ijm){
                    field[ij - 1][i] = 1
                }
                field[ij][i] = 1
                if(ijp){
                    field[ij + 1][i] = 1
                }
            }
            if (b1 + size <= 9){
                if(ijm){
                    field[(ij - 1)][b1 + size] = 1}
                field[ij][b1 + size] = 1
                if(ijp){
                    field[ij + 1][b1 + size] = 1
                }}}
    }
}