package controller;

import dao.PlaymobilDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Playmobil;

public class MainController {

	@FXML
    private TableView<Playmobil> tablaPlaymobil;
	
	@FXML private TableColumn<Playmobil, String> colReferencia;
    @FXML private TableColumn<Playmobil, String> colNombre;
    @FXML private TableColumn<Playmobil, String> colCategoria;
    @FXML private TableColumn<Playmobil, Double> colPrecio;
    @FXML private TableColumn<Playmobil, Double> colValor;

    private PlaymobilDAO dao = new PlaymobilDAO();

    @FXML
    public void initialize() {
    	
    	colReferencia.setCellValueFactory(new PropertyValueFactory<>("referencia"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precioCompra"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valorActual"));

        tablaPlaymobil.getItems().addAll(dao.obtenerTodos());
    }
}

