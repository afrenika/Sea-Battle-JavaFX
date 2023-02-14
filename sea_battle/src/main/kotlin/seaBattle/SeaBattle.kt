package seaBattle

import kotlin.random.Random

class SeaBattle {


    class Ship(var b1: Int, var b2: Int, var ij: Int, var n: Char)

    var fieldComputer = Array(10) { Array(10) { 0 } }

    var fieldUser = Array(10) { Array(10) { 0 } }

    val ships = arrayOf(4, 3, 3, 2, 2, 2, 1, 1, 1, 1)

    fun computer(field: Array<Array<Int>>){
        for(ship in ships){
            var put = false
            while (!put){
                val rand = Random
                val randomN = rand.nextInt(1, 5)
                val randomI = rand.nextInt(1, 10)
                val randomJ = rand.nextInt(1, 10)
                if(field[randomI][randomJ] == 0){
                    when(randomN){
                        1 -> {
                            if ((randomI - ship + 1 > 0) && check(randomI - ship + 1, randomI, randomJ, 'v', field)){
                                for (i in randomI - ship + 1..randomI) {
                                    field[i][randomJ] = ship }
                                put = true
                                bound(randomI - ship + 1, randomI, randomJ, 'v', field)
                            }
                        }

                        2 -> {
                            if ((randomI + ship - 1 < 10) && check(randomI, randomI + ship - 1, randomJ, 'v', field)){
                                for (i in randomI until randomI + ship) {
                                    field[i][randomJ] = ship }
                                put = true
                                bound(randomI, randomI + ship - 1, randomJ, 'v', field)
                            }
                        }

                        3 -> {
                            if ((randomJ - ship + 1 > 0) && check(randomJ - ship + 1, randomJ, randomI, 'h', field)){
                                for (j in randomJ - ship + 1..randomJ) {
                                    field[randomI][j] = ship }
                                put = true
                                bound(randomJ - ship + 1, randomJ, randomI, 'h', field)
                            }
                        }

                        4 -> {
                            if ((randomJ + ship - 1 < 10) && check(randomJ, randomJ + ship - 1, randomI, 'h', field)){
                                for (j in randomJ until randomJ + ship) {
                                    field[randomI][j] = ship }
                                put = true
                                bound(randomJ, randomJ + ship - 1, randomI, 'h', field)
                            }
                        }
                    }
                } }
//                field[6] = arrayOf(0, 0, 0, 0, 0, 8, 8, 8, 0, 0)
//                field[7] = arrayOf(0, 0, 0, 0, 0, 8, 2, 8, 0, 0)
//                field[8] = arrayOf(8, 8, 8, 8, 8, 8, 2, 8, 8, 8)
//                field[9] = arrayOf(1, 8, 3, 3, 3, 8, 8, 8, 2, 2)
//
//                for(i in 1.. 5){
//                    field[i] = Array(10) { 0 } }
    }}

    fun bound(b1: Int, b2: Int, ij:Int, n:Char, field: Array<Array<Int>>){

        val ijm = (ij - 1) >= 0
        val ijp = (ij + 1) <= 9


        if (n == 'v'){
            if ((b1 - 1) >= 0){
                if(ijm){
                    field[b1 - 1][(ij - 1)] = 8}
                field[b1 - 1][ij] = 8
                if(ijp){
                    field[b1 - 1][ij + 1] = 8
                }}
            for(i in b1..b2){
                if(ijm){
                    field[i][ij - 1] = 8
                }
                if(ijp){
                    field[i][ij + 1] = 8
                }
            }
            if ((b2 + 1) <= 9){
                if(ijm){
                    field[b2 + 1][(ij - 1)] = 8}
                field[b2 + 1][ij] = 8
                if(ijp){
                    field[b2 + 1][ij + 1] = 8
                }}}

        else if (n == 'h'){
            if (b1 - 1 >= 0){
                if(ijm){
                    field[(ij - 1)][b1 - 1] = 8}
                field[ij][b1 - 1] = 8
                if(ijp){
                    field[ij + 1][b1 - 1] = 8
                }}
            for(i in b1..b2){
                if(ijm){
                    field[ij - 1][i] = 8
                }
                if(ijp){
                    field[ij + 1][i] = 8
                }
            }
            if (b2 + 1 <= 9){
                if(ijm){
                    field[(ij - 1)][b2 + 1] = 8}
                field[ij][b2 + 1] = 8
                if(ijp){
                    field[ij + 1][b2 + 1] = 8
                }}}
    }

    fun check(b1:Int, b2:Int, ij:Int, n:Char, field: Array<Array<Int>>):Boolean{
        val checking = fun(x:Int):Boolean{return x in arrayOf(0, 8)}
        val ijm = (ij - 1) >= 0
        val ijp = (ij + 1) <= 9
        if (n == 'v'){
            if ((b1 - 1) >= 0){
                if(ijm){
                    if (!checking(field[b1 - 1][(ij - 1)])){return false}}
                if (!checking(field[b1 - 1][ij])){return false}
                if(ijp){
                    if (!checking(field[b1 - 1][(ij + 1)])){return false}
                }}
            for(i in b1 .. b2){
                if(ijm){
                    if (!checking(field[i][(ij - 1)])){return false}
                }
                if (!checking(field[i][(ij)])){return false}
                if(ijp){
                    if (!checking(field[i][(ij + 1)])){return false}
                }
            }
            if ((b2 + 1) <= 9){
                if(ijm){
                    if (!checking(field[b2 + 1][(ij - 1)])){return false}}
                if (!checking(field[b2 + 1][ij])){return false}
                if(ijp){
                    if (!checking(field[b2 + 1][(ij + 1)])){return false}
                }}}
        else if (n == 'h'){
            if ((b1 - 1) >= 0){
                if(ijm){
                    if (!checking(field[(ij - 1)][b1 - 1])){return false}}
                if (!checking(field[ij][b1 - 1])){return false}
                if(ijp){
                    if (!checking(field[(ij + 1)][b1 - 1])){return false}
                }}
            for(i in b1 .. b2){
                if(ijm){
                    if (!checking(field[(ij - 1)][i])){return false}
                }
                if (!checking(field[(ij)][i])){return false}
                if(ijp){
                    if (!checking(field[(ij + 1)][i])){return false}
                }
            }
            if ((b2 + 1) <= 9){
                if(ijm){
                    if (!checking(field[(ij - 1)][b2 + 1])){return false}}
                if (!checking(field[ij][b2 + 1])){return false}
                if(ijp){
                    if (!checking(field[(ij + 1)][b2 + 1])){return false}
                }}}
        else{
            return false
        }
        return true
    }

    fun clearUser(){
        fieldUser = Array(10) { Array(10) { 0 } }
    }

    fun checkAttack(i:Int, j:Int, f: Int):Ship?{
        val field = if (f == 1){fieldComputer} else{fieldUser}
        val size = field[i][j]
        val checking = fun(x:Int):Boolean{return x in arrayOf(1, 2, 3, 4) }
        val subAdd = fun(add:Int):Int{return if(add > 0) add + 1 else add - 1}
        var add = 1; var addS = 0
        var flag = false; var flag1 = false; var flag2 = false
        var bIndex = 0; var eIndex = 0; var n = 'n'; var ij = 0

        while((!flag && !flag1)){
            addS++
            if (0 > i - add){
                bIndex = i - add + 1
                if (size == (addS)){
                    flag1 = true
                    eIndex = i}
                else{
                    add = 0
                    addS--
            }}
            else if (i - add > 9){
                if (size == (addS)){
                    flag1 = true
                    eIndex = i - add - 1}
                else {break}
            }
            else{

            if (field[i - add][j] == 8){
                if (add > 0){
                    bIndex = i - add + 1
                    if (size != (addS)){
                        add = 0
                        addS--}
                    else{flag1 = true
                         eIndex = i}}
                else {
                    if (size == (addS)){
                        flag1 = true
                        eIndex = i - add - 1}
                     else{break}
                }}
            else if (checking(field[i - add][j])){
                flag = true}}
            add = subAdd(add)
        if(flag1){
            ij = j
            n = 'v'}
        }

        add = 1; addS = 0
        while(!flag && !flag2 && !flag1){
            addS++
            if (0 > j - add){
                bIndex = j - add + 1
                if (size == (addS)){
                    flag2 = true
                    eIndex = j}
                else{
                    add = 0
                    addS--}}
            else if (j - add > 9){
                if (size == addS){
                    flag2 = true
                    eIndex = j - add - 1}
                else {break}
            }
            else{
            if (field[i][j - add] == 8){
                if (add > 0){
                    bIndex = j - add + 1
                    if (size != (addS)){
                        add = 0
                        addS--}
                    else{flag2 = true
                        eIndex = j}}
                else {
                    if (size == addS){
                        flag2 = true
                        eIndex = j - add - 1}
                    else{break}
                }}
            else if (checking(field[i][j - add])){
                flag = true}}
            add = subAdd(add)

            if(flag2){
                ij = i
                n = 'h'}}

       return if (!flag){
            explosion(bIndex, eIndex, ij, n, field)
            Ship(bIndex, eIndex, ij, n)
        } else null
    }

    private fun explosion(b1: Int, b2: Int, ij: Int, n: Char, field: Array<Array<Int>>){
        for(i in b1..b2){
            if(n == 'h'){
                field[ij][i] = 7}
            if(n == 'v'){
                field[i][ij] = 7}
        }
    }

}