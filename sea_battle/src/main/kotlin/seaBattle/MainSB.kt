package seaBattle

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage

class MainSB : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader()
        fxmlLoader.location = Companion::class.java.getResource("start.fxml")
        stage.title = "Морской бой"
        stage.scene = Scene(fxmlLoader.load())
        stage.icons.add(Image("file:sb.png"))
        stage.show()

    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(MainSB::class.java)
        }
    }
}