package es.mariomateos.proyectocsv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Operaciones {
    
    //Variables
    
    String paisActual = "";
    String annoActual = "";
    String seleccionPais = "";
    String seleccionAnno = "";
    String atentadosObtenidos = "";
    
    int atentadosTotales = 0;
    int numeroSumasMedia = 0;
    float media = 0;
    
    boolean annadirAnno = true;
    boolean mostrarRegistro = false;
    boolean pulsacionbuscar = false;
    
    short contadorPaises = 0;
    short contadorAnnos = 0;
    
    Exportar exportar = new Exportar();
    
    public void leerFichero(HBox hBoxComboBox, HBox hBoxDatos, HBox hBoxMediaAtentados, VBox root, boolean depurar){

        //Creación de los arrays de pais y año
        ArrayList<String> listaPais = new ArrayList();
        ArrayList<String> listaAnno = new ArrayList();

        String nombreFichero = "atentados.csv";
        
        //Creación del BufferedReader
        BufferedReader br = null;
        try {
            // Crear un objeto BufferedReader al que se le pasa 
            //   un objeto FileReader con el nombre del fichero
            br = new BufferedReader(new FileReader(nombreFichero));
            // Leer la primera línea, guardando en un String
            String texto = br.readLine();
            // Repetir mientras no se llegue al final del fichero
            while(texto != null) {
                String[] valores = texto.split(",");
                Datos datosAtentados = new Datos();
                datosAtentados.setAtentados(valores[3]);
                datosAtentados.setAnno(valores[2]);
                datosAtentados.setPais(valores[0]);
                if(!paisActual.equals(datosAtentados.getPais()) && !datosAtentados.getPais().equals("Entity") && !datosAtentados.getPais().isEmpty()){
                    paisActual = datosAtentados.getPais();
                    listaPais.add(paisActual);
                    contadorPaises++;
                }
                
                for(int i=0;i<listaAnno.size();i++){
                    if(datosAtentados.getAnno().equals(listaAnno.get(i))){
                        annadirAnno = false;
                    }
                }
                if(annadirAnno==true && !datosAtentados.getAnno().equals("Year")){
                    listaAnno.add(datosAtentados.getAnno());
                    contadorAnnos++;
                }
                // Leer la siguiente línea
                texto = br.readLine();
            }
            if(depurar == true){
                System.out.println("Se han añadido "+contadorPaises+" entradas al comboBox de paises");
                System.out.println("Se han añadido "+contadorAnnos+" entradas al comboBox de años");
            }
        }
        // Captura de excepción por fichero no encontrado
        catch (FileNotFoundException ex) {
            System.out.println("Error: Fichero no encontrado");
            ex.printStackTrace();
        }
        // Captura de cualquier otra excepción
        catch(Exception ex) {
            System.out.println("Error de lectura del fichero");
            ex.printStackTrace();
        }
        // Asegurar el cierre del fichero en cualquier caso
        finally {
            try {
                // Cerrar el fichero si se ha podido abrir
                if(br != null) {
                    br.close();
                }
            }
            catch (Exception ex) {
                System.out.println("Error al cerrar el fichero");
                ex.printStackTrace();
            }
        }
        //Creación del combobox pais 
        ComboBox<String> comboBoxPais = new ComboBox(FXCollections.observableList(listaPais));
        hBoxComboBox.getChildren().add(comboBoxPais);
        comboBoxPais.setPromptText("Seleccionar país");

        Label paisSeleccionado = new Label();
        hBoxDatos.getChildren().add(paisSeleccionado);

        // Cuando seleccione algo en el ComboBox, se mostrará en el Label
        comboBoxPais.setOnAction((t) -> {
            seleccionPais = comboBoxPais.getValue();
            if(depurar == true){
                System.out.println("País seleccionado: "+seleccionPais);
            }
        });

        //Ordena el array por orden alfabético
        Collections.sort(listaAnno); 

        //Creación del combobox año 
        ComboBox<String> comboBoxAnno = new ComboBox(FXCollections.observableList(listaAnno));
        hBoxComboBox.getChildren().add(comboBoxAnno);
        comboBoxAnno.setPromptText("Seleccionar año");
       
        // Añadir un label en el que se mostrará el elemento seleccionado
        Label annoSeleccionado = new Label();
        hBoxDatos.getChildren().add(annoSeleccionado);

        // Cuando seleccione algo en el ComboBox, se mostrará en el Label
        comboBoxAnno.setOnAction((t) -> {
            seleccionAnno = comboBoxAnno.getValue();
            if(depurar == true){
                System.out.println("Año seleccionado: "+seleccionAnno);
            }
            
        });
        
        //Creación label numero atentados
        Label numeroAtentadosLabel = new Label();
        root.getChildren().add(numeroAtentadosLabel);
        
        //Creación label media atentados
        Label mediaAtentadosLabel = new Label();
        root.getChildren().add(mediaAtentadosLabel);
        
        //Creación boton buscar
        Button buttonBuscar = new Button("Buscar");
        hBoxMediaAtentados.getChildren().add(buttonBuscar);
        
        //Creación boton exportar
        Button buttonExportar = new Button("Exportar Media");
        hBoxMediaAtentados.getChildren().add(buttonExportar);
             
        buttonBuscar.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent t) {
                if(depurar == true){
                    System.out.println("Se ha pulsado el botón Buscar");
                }
                //////////////////////////////////////
                //String nombreFichero = "fatalities-from-terrorism.csv";
                // Declarar una variable BufferedReader
                BufferedReader br = null;
                try {
                    // Crear un objeto BufferedReader al que se le pasa
                    //   un objeto FileReader con el nombre del fichero
                    br = new BufferedReader(new FileReader(nombreFichero));
                    // Leer la primera línea, guardando en un String
                    String texto = br.readLine();
                    // Repetir mientras no se llegue al final del fichero
                    while(texto != null) {
                        String[] valores = texto.split(",");
                        Datos datosAtentados = new Datos();
                        datosAtentados.setAtentados(valores[3]);
                        datosAtentados.setAnno(valores[2]);
                        datosAtentados.setPais(valores[0]);
                        
                        if(datosAtentados.getPais().equals(seleccionPais) && datosAtentados.getAnno().equals(seleccionAnno)){
                            atentadosObtenidos = datosAtentados.getAtentados();
                            mostrarRegistro = true;
                        }
                        if(datosAtentados.getPais().equals(seleccionPais)){
                            atentadosTotales += Integer.parseInt(datosAtentados.getAtentados());
                            numeroSumasMedia++;
                        }
                        // Leer la siguiente línea
                        texto = br.readLine();
                    }
                    if(mostrarRegistro==true){
                        numeroAtentadosLabel.setText("Atentados en "+seleccionPais+" en "+seleccionAnno+": "+atentadosObtenidos);
                        mostrarRegistro = false;
                    }else{
                        if(depurar == true){
                            System.out.println("Llamando Alert de 'Error de búsqueda'");
                        }
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error en la búsqueda");
                        alert.setHeaderText("No se ha encontrado ningún registro");
                        alert.setContentText("Vaya, parece que no se ha encontrado ningún registro ya que no has seleccionado ningún país");

                        alert.showAndWait();
                    }
                    if(depurar == true){
                        System.out.println("Obteniendo media de valores...");
                    }
                    media = 0;
                    media = atentadosTotales/numeroSumasMedia;
                    mediaAtentadosLabel.setText("Media de atentados en "+seleccionPais+" a lo largo de toda la historia: "+media);
                    numeroSumasMedia = 0;
                    atentadosTotales = 0;
                    pulsacionbuscar = true;
                    if(depurar == true){
                        System.out.println("Media de valores obtenida");
                    }
                }
                // Captura de excepción por fichero no encontrado
                catch (FileNotFoundException ex) {
                    System.out.println("Error: Fichero no encontrado");
                    ex.printStackTrace();
                }
                // Captura de cualquier otra excepción
                catch(Exception ex) {
                    System.out.println("Error de lectura del fichero");
                    ex.printStackTrace();
                }
                // Asegurar el cierre del fichero en cualquier caso
                finally {
                    try {
                        // Cerrar el fichero si se ha podido abrir
                        if(br != null) {
                            br.close();
                        }
                    }
                    catch (Exception ex) {
                        System.out.println("Error al cerrar el fichero");
                        ex.printStackTrace();
                    }
                }
            }
        });
        
        buttonExportar.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent t) {
                if(pulsacionbuscar == true){
                    if(depurar == true){
                        System.out.println("Se ha pulsado el botón Exportar");
                    }
                    exportar.exportarContenido(media, seleccionPais);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Exportación");
                    alert.setHeaderText("La exportación se ha realizado con éxito");
                    alert.setContentText("Se ha exportado la media de atentados que se han producido en toda la historia en "+seleccionPais);
                    alert.showAndWait();
                    if(depurar == true){
                        System.out.println("Se ha realizado la exportación de datos");
                    }
                }else{
                    if(depurar == true){
                        System.out.println("Llamando Alert de 'Exportación fallida'");
                    }
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Exportación");
                    alert.setHeaderText("La exportación ha fallado");
                    alert.setContentText("Debes realizar una búsqueda primero para realizar la exportación de datos");
                    alert.showAndWait();
                }
                
            }
        });
        
    }
}
