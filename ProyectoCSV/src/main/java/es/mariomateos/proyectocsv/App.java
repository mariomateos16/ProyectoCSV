package es.mariomateos.proyectocsv;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class App extends Application {

    boolean depurar = true;
 
    @Override
    public void start(Stage stage) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #3B83BD;");
        var scene = new Scene(root, 640, 480);    
        stage.setTitle("PROYECTO CSV");
        stage.setScene(scene);
        stage.show();
        
        HBox hBoxComboBox = new HBox(20);
        hBoxComboBox.setAlignment(Pos.CENTER);        
        root.getChildren().add(hBoxComboBox);

        HBox hBoxMediaAtentados = new HBox(20);
        hBoxMediaAtentados.setAlignment(Pos.BOTTOM_CENTER);
        root.getChildren().add(hBoxMediaAtentados);
        
        HBox hBoxDatos = new HBox(20);
        hBoxDatos.setAlignment(Pos.CENTER);
        root.getChildren().add(hBoxDatos);
        
        Operaciones operaciones = new Operaciones();
        operaciones.leerFichero(hBoxComboBox, hBoxDatos, hBoxMediaAtentados, root, depurar);
        
    }
    
    public static void main(String[] args) {
        launch();
    }

}