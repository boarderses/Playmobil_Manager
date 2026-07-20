package util;

import java.io.File;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class VisorImagen {

    public static void mostrar(String rutaImagen) {

        if (rutaImagen == null || rutaImagen.isBlank()) {
            return;
        }

        Image imagen = new Image(
                new File(rutaImagen)
                        .toURI()
                        .toString());

        ImageView imageView = new ImageView(imagen);

        imageView.setPreserveRatio(true);
        imageView.setFitWidth(700);
        imageView.setFitHeight(700);

        StackPane root = new StackPane(imageView);

        Scene scene = new Scene(root, 750, 750);

        Stage stage = new Stage();
        stage.setTitle("Imagen");
        stage.setScene(scene);
        stage.show();
    }
}
