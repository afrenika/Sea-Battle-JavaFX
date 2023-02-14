package seaBattle

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.stage.Stage

class StartController {

    @FXML
    lateinit var button:Button

    @FXML
    private fun start() {
        (button.scene.window as Stage).close()

        val fxmlLoader = FXMLLoader()
        fxmlLoader.location = MainSB::class.java.getResource("createField.fxml")
        val stage = Stage()
        stage.title = "Морской бой"
        stage.scene = Scene(fxmlLoader.load())
        stage.icons.add(Image("file:sb.png"))
        stage.show()

    }

}