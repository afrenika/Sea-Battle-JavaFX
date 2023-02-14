package seaBattle

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.GridPane
import javafx.stage.Modality
import javafx.stage.Stage
import java.io.IOException
import java.net.URL
import java.util.*


class CreateFieldController:Initializable{

    private var  shipCountNow = 0
    private lateinit var seaBattle: SeaBattle
    //зеркальность
    private var direction = true;
    //направление
    private var direction2 = true;

    @FXML
    lateinit var buttonRotate:Button
    @FXML
    lateinit var st:Button


    @FXML
    lateinit var grPanePicture:GridPane

    @FXML
    lateinit var gridPaneUserCr: GridPane

    var y = 1

    @FXML
    override fun initialize(p0: URL?, p1: ResourceBundle?) {
        seaBattle = SeaBattle()
    }


    @FXML
    private fun addShip(actionEvent: ActionEvent){
        val button = actionEvent.source as Button
        var iPress: Int = 0
        var jPress: Int = 0
        for(i in 1..10){
            for(j in 1..10){
                if (button == gridPaneUserCr.children[i*11 + j]){
                      iPress = i
                      jPress = j
                      break}
            }
            if (iPress != 0){break} }

        val ship = seaBattle.ships[shipCountNow]
        var put = false
        if(direction2){
            if(direction){
                if((jPress + ship - 1 <= 10) && seaBattle.check(jPress - 1, jPress + ship - 2, iPress - 1, 'h', seaBattle.fieldUser)){
                    for (j in jPress until jPress + ship) {
                        seaBattle.fieldUser[iPress - 1][j - 1] = ship}
                    put = true
                    seaBattle.bound(jPress - 1, jPress + ship - 2, iPress - 1, 'h', seaBattle.fieldUser)
                    showShip(jPress, jPress + ship - 1, iPress, 'h', seaBattle.fieldUser)
                }
            }
            else{
                if((jPress - ship + 1 > 0) && seaBattle.check(jPress - ship, jPress - 1, iPress - 1, 'h', seaBattle.fieldUser)){
                    for (j in (jPress - ship + 1) .. jPress) {
                        seaBattle.fieldUser[iPress - 1][j - 1] = ship }
                    put = true
                    seaBattle.bound(jPress - ship, jPress -  1, iPress - 1, 'h', seaBattle.fieldUser)
                    showShip(jPress - ship + 1, jPress, iPress, 'h', seaBattle.fieldUser)
                }
            }
            }
        else{
            if(direction){
                if((iPress + ship - 1 <= 10) && seaBattle.check(iPress - 1, iPress + ship - 2, jPress - 1, 'v', seaBattle.fieldUser)){
                    for (j in iPress until iPress + ship) {
                        seaBattle.fieldUser[j - 1][jPress - 1] = ship}
                    put = true
                    seaBattle.bound(iPress - 1, iPress + ship - 2, jPress - 1, 'v', seaBattle.fieldUser)
                    showShip(iPress, iPress + ship - 1, jPress, 'v', seaBattle.fieldUser)
                }
            }
            else{
                if((iPress - ship + 1 > 0) && seaBattle.check(iPress - ship, iPress - 1, jPress - 1, 'v', seaBattle.fieldUser)){
                    for (j in iPress - ship + 1 .. iPress) {
                        seaBattle.fieldUser[j - 1][jPress - 1] = ship }
                    put = true
                    seaBattle.bound(iPress - ship, iPress -  1, jPress - 1, 'v', seaBattle.fieldUser)
                    showShip(iPress - ship + 1, iPress, jPress, 'v', seaBattle.fieldUser)
                }
            }
        }
            if(!put){
                val alert = Alert(Alert.AlertType.ERROR)
                alert.title = "Ошибка"
                alert.headerText = "Сообщение об ошибке"
                alert.contentText = "Неправильно выбранная позиция"
                alert.showAndWait()
            }
            else{shipCountNow ++}
        if (shipCountNow == 10){
            disableButtons()
            st.isDisable = false
            shPicture()
        }
        else if (seaBattle.ships[shipCountNow] < ship){
            shPicture()
            if(seaBattle.ships[shipCountNow] == 1){
                buttonRotate.isVisible = false
            }
        }


    }

    private fun show(){
        for(i in 1..10){
            for(j in 1..10){
                val button: Button = gridPaneUserCr.children[i*11+j] as Button
                if (seaBattle.fieldUser[i - 1][j - 1] != 0){
                    if (seaBattle.fieldUser[i - 1][j - 1] == 8) {
                        button.style = "-fx-background-color: #7ba4cc"}
                    else{
                        button.style = "-fx-background-color: #F7C815"} }
                else{
                    button.style = "-fx-background-color: #2F70AF"
                }
            }

        }
    }

    private fun showShip(b1:Int, b2:Int, ij:Int, n:Char, field: Array<Array<Int>>){
            val setStyleField = { x:Button -> x.style = "-fx-background-color: #7ba4cc"}
            val setStyleShip = { x:Button -> x.style = "-fx-background-color: #F7C815"}
            val ijm = (ij - 1) > 0
            val ijp = (ij + 1) <= 10
            if (n == 'v'){
                if ((b1 - 1) > 0){
                    if(ijm){
                        setStyleField(gridPaneUserCr.children[(b1 - 1)*11+ij - 1] as Button)}
                    setStyleField(gridPaneUserCr.children[(b1 - 1)*11+ij] as Button)
                    if(ijp){
                        setStyleField(gridPaneUserCr.children[(b1 - 1)*11+ij + 1] as Button)}}
                for(i in b1 .. b2){
                    if(ijm){
                        setStyleField(gridPaneUserCr.children[(i)*11+ij - 1] as Button)
                    }
                    setStyleShip(gridPaneUserCr.children[(i)*11+ij] as Button)
                    if(ijp){
                        setStyleField(gridPaneUserCr.children[(i)*11+ij + 1] as Button)
                    }
                }
                if ((b2 + 1) <= 10){
                    if(ijm){
                        setStyleField(gridPaneUserCr.children[(b2 + 1)*11+ij - 1] as Button)}
                    setStyleField(gridPaneUserCr.children[(b2 + 1)*11+ij] as Button)
                    if(ijp){
                        setStyleField(gridPaneUserCr.children[(b2 + 1)*11+ij + 1] as Button)}}}

            else if (n == 'h'){
                if ((b1 - 1) >= 1){
                    if(ijm){
                        setStyleField(gridPaneUserCr.children[(ij - 1)*11+b1 - 1] as Button)
                       }
                    setStyleField(gridPaneUserCr.children[(ij)*11+b1 - 1] as Button)
                    if(ijp){
                        setStyleField(gridPaneUserCr.children[(ij + 1)*11+b1 - 1] as Button)}}
                for(i in b1 .. b2){
                    if(ijm){
                        setStyleField(gridPaneUserCr.children[(ij - 1)*11+i] as Button)
                    }
                    setStyleShip(gridPaneUserCr.children[(ij)*11+i] as Button)
                    if(ijp){
                        setStyleField(gridPaneUserCr.children[(ij + 1)*11+i] as Button)
                    }
                }
                if ((b2 + 1) <= 10){
                    if(ijm){
                        setStyleField(gridPaneUserCr.children[(ij - 1)*11+b2 + 1] as Button)}
                    setStyleField(gridPaneUserCr.children[(ij)*11+b2 + 1] as Button)
                    if(ijp){
                        setStyleField(gridPaneUserCr.children[(ij + 1)*11+b2 + 1] as Button)}}}
        }

    private fun disableButtons(){
        for(i in 1..10){
            for(j in 1..10){
                (gridPaneUserCr.children[i*11+j] as Button).isDisable = true
    }}}

    @FXML
    private fun randomField(){
        seaBattle.clearUser()
        seaBattle.computer(seaBattle.fieldUser)
        show()
        shipCountNow = 10
        shPicture()
        buttonRotate.isVisible = false
        st.isDisable = false
    }


    @FXML
    private fun rotate(){
        y += 1
        when(y){
            1->{direction2 = true
                direction = true}
            2->direction2 = false
            3->{direction2 = true
                direction = false}
            4->{direction2 = false}
        }
        if (y == 4){ y = 0}
        shPicture()}


fun shPicture(){
    for (i in 0 until 8) {
        (grPanePicture.children[i] as Label).isVisible = false
        (grPanePicture.children[i] as Label).text = ""
    }
    if (shipCountNow < 10){
    if(direction2) {
        if(direction){
            for(i in 0 until seaBattle.ships[shipCountNow]){
                (grPanePicture.children[i] as Label).isVisible = true}
            (grPanePicture.children[0] as Label).text = "O"}
        else{
            for(i in 3 downTo  (4 - seaBattle.ships[shipCountNow])){
                (grPanePicture.children[i] as Label).isVisible = true}
            (grPanePicture.children[3] as Label).text = "O" }}

    else {
        if(direction){
            for(i in 4..seaBattle.ships[shipCountNow] + 3){
                (grPanePicture.children[i] as Label).isVisible = true}
            (grPanePicture.children[4] as Label).text = "O" }
        else{
            for(i in 7 downTo  (8 - seaBattle.ships[shipCountNow])){
                (grPanePicture.children[i] as Label).isVisible = true}
            (grPanePicture.children[7] as Label).text = "O" }}
        }}

    @FXML
    fun clear(){
        for(i in 1..10){
            for(j in 1..10){
                (gridPaneUserCr.children[i*11+j] as Button).style = "-fx-background-color: #2F70AF" } }
        seaBattle.clearUser()
        buttonRotate.isVisible = true
        shipCountNow = 0
        shPicture()
        st.isDisable = true
    }

    @FXML
    fun start(){
        try {
            (buttonRotate.scene.window as Stage).close()
            val loader = FXMLLoader()
            loader.location = SeaBattleController::class.java.getResource("sea-battle.fxml")
            val page = loader.load<AnchorPane>()
            val dialogScene = Stage()
            dialogScene.title = "Морской бой"
            dialogScene.icons.add(Image("file:sb.png"))
            dialogScene.initModality(Modality.WINDOW_MODAL)
            dialogScene.initOwner(null)
            val scene = Scene(page)
            dialogScene.scene = scene
            val controller: SeaBattleController = loader.getController()
            controller.seaBattle = seaBattle
            dialogScene.show()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


}