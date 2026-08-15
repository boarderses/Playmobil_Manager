package controller;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import util.Alertas;
import util.Configuracion;

public class ConfiguracionController {

    @FXML
    private TextField txtCarpetaBackups;

    @FXML
    private CheckBox chkConfirmarEliminar;

    @FXML
    private CheckBox chkConfirmarRestaurar;


    @FXML
    public void initialize() {

        txtCarpetaBackups.setText(
                Configuracion.getCarpetaBackups());

        chkConfirmarEliminar.setSelected(
                Configuracion.confirmarEliminar());

        chkConfirmarRestaurar.setSelected(
                Configuracion.confirmarRestaurar());
    }


    @FXML
    private void seleccionarCarpeta() {

        DirectoryChooser chooser =
                new DirectoryChooser();

        chooser.setTitle(
                "Seleccionar carpeta de copias de seguridad");

        File carpeta = chooser.showDialog(
                txtCarpetaBackups.getScene().getWindow());

        if (carpeta != null) {

            txtCarpetaBackups.setText(
                    carpeta.getAbsolutePath());
        }
    }


    @FXML
    private void guardar() {

        String carpeta =
                txtCarpetaBackups.getText().trim();

        if (carpeta.isBlank()) {

            Alertas.error(
                    "Configuración",
                    "Debes indicar una carpeta para las copias de seguridad.");

            return;
        }

        try {

            Configuracion.guardar(
                    carpeta,
                    chkConfirmarEliminar.isSelected(),
                    chkConfirmarRestaurar.isSelected());

            Alertas.info(
                    "Configuración",
                    "Configuración guardada correctamente.");

            cerrar();

        } catch (IOException e) {

            e.printStackTrace();

            Alertas.error(
                    "Error",
                    "No se pudo guardar la configuración.");
        }
    }


    @FXML
    private void cerrar() {

        Stage ventana =
                (Stage) txtCarpetaBackups
                        .getScene()
                        .getWindow();

        ventana.close();
    }
}
