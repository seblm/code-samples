package javafx;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class JavaFX extends Application {


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        ToggleButton button = new ToggleButton("Click");

        final StringProperty btnText = button.textProperty();
        button.setOnAction(actionEvent -> {
            ToggleButton source = (ToggleButton) actionEvent.getSource();
            if (source.isSelected()) {
                btnText.set("Clicked!");
            } else {
                btnText.set("Click!");
            }
        });

        root.setCenter(button);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(200);
        stage.setHeight(200);
        stage.show();
    }

}
