package seaBattle

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.GridPane
import javafx.stage.Modality
import javafx.stage.Stage
import java.io.IOException


class SeaBattleController{

    @FXML
    lateinit var gridPaneUser: GridPane
    @FXML
    lateinit var gridPaneComputer: GridPane

    val computer = Computer(this)

    private var _seaBattle = SeaBattle()
    var seaBattle: SeaBattle
        set(value){
            _seaBattle = value
            _seaBattle.computer(seaBattle.fieldComputer)
            show(gridPaneUser, _seaBattle.fieldUser)
            disableButtons(2)
        }
        get()=  _seaBattle
    

    var t1 = 0; var t2 = 0

    


    @FXML
    private fun seeShip(){
        show(gridPaneComputer, seaBattle.fieldComputer)
        }

    private fun show(gridPane: GridPane, field: Array<Array<Int>>){
        for(i in 1..10){
            for(j in 1..10){
                val button: Button = gridPane.children[i*11+j] as Button
                if (field[i - 1][j - 1] != 0){
                    if (field[i - 1][j - 1] == 8) {
                        button.style = "-fx-background-color: #7ba4cc"}
                    else{
                        button.style = "-fx-background-color: #F7C815}" }}
                else{
                    button.style = "-fx-background-color: #2F70AF"
                }
            }
        }

    }

    fun disableButtons(f:Int){
        val gr:GridPane = if (f == 1) gridPaneComputer else gridPaneUser
        for(i in 1..10){
            for(j in 1..10){
                (gr.children[i*11+j] as Button).isDisable = true
            }}}

    fun NdisableButtons(f:Int){
        val gr:GridPane = if (f == 1) gridPaneComputer else gridPaneUser
        for(i in 1..10){
            for(j in 1..10){
                (gr.children[i*11+j] as Button).isDisable = false
            }}}


    @FXML
    private fun attack(actionEvent: ActionEvent){
        val button = actionEvent.source as Button
        var iPress: Int = 0
        var jPress: Int = 0

        for(i in 1..10){
            for(j in 1..10){
                if (button == gridPaneComputer.children[i*11 + j]){
                    iPress = i
                    jPress = j
                    break} }
            if (iPress != 0){break} }
        attack(iPress, jPress, 1)
        button.isDisable = true
        computer.attack()

        if (t1 == seaBattle.ships.size || t2 == seaBattle.ships.size){
            disableButtons(1)
            var s = ""
            if (t2 == seaBattle.ships.size){
                s = "Победил компьютер!"}
            else{
                s = "Победил игрок!"}

            val alert = Alert(Alert.AlertType.INFORMATION)
            alert.title = "Морской бой"
            alert.headerText = "Конец игры"
            alert.contentText = s
            alert.showAndWait()}}

    private fun explosion(ship: SeaBattle.Ship, gridPane: GridPane){
        var button:Button
        for(i in ship.b1 + 1..ship.b2 + 1){
            button = if(ship.n == 'h'){
                gridPane.children[(ship.ij + 1)*11 + i ] as Button
            } else{
                gridPane.children[i*11 + ship.ij + 1] as Button
            }
            button.style = "-fx-background-color: #ac8c0e; -fx-graphic: url(\"expl.png\")"}
        }

    
    fun attack(iPress:Int, jPress:Int, f:Int):Int{
        val gr:GridPane = if (f == 1) gridPaneComputer else gridPaneUser
        val field = if (f == 1) seaBattle.fieldComputer else seaBattle.fieldUser
        
        val button = (gr.children[iPress*11+jPress] as Button)
        var size = field[iPress - 1][jPress - 1]
        if (field[iPress - 1][jPress - 1] !in arrayOf(0, 8)){
            val ship = seaBattle.checkAttack(iPress - 1, jPress - 1, f)
            button.style = "-fx-background-color: #F7C815; -fx-graphic: url(\"chr.png\")"

            if (ship != null ) {
                if (f == 1){t1++}
                else{t2++}
                explosion(ship, gr)}
            else{
                field[iPress - 1][jPress - 1] = 5}}
        else{
            button.style = "-fx-background-color: #01274B"}

       return if (field[iPress - 1][jPress - 1] == 7) 7 else size
    }

    @FXML
    private fun newGame(){
        try {
            (gridPaneComputer.scene.window as Stage).close()
            val loader = FXMLLoader()
            loader.location = SeaBattleController::class.java.getResource("start.fxml")
            val page = loader.load<AnchorPane>()
            val dialogScene = Stage()
            dialogScene.title = "Морской бой"
            dialogScene.icons.add(Image("file:sb.png"))
            dialogScene.initModality(Modality.WINDOW_MODAL)
            dialogScene.initOwner(null)
            val scene = Scene(page)
            dialogScene.scene = scene
            dialogScene.show()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


}
