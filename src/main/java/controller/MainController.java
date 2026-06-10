package controller;

import dao.PlaymobilDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    
    @FXML private TextField txtReferencia;
    @FXML private TextField txtNombre;
    @FXML private TextField txtCategoria;
    @FXML private TextField txtPrecioCompra;
    @FXML private TextField txtValorActual;
    
    @FXML private Playmobil playmobilSeleccionado;
    private PlaymobilDAO dao = new PlaymobilDAO();

    @FXML
    public void initialize() {
    	
    	colReferencia.setCellValueFactory(new PropertyValueFactory<>("referencia"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precioCompra"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valorActual"));

        cargarTabla();
        
        tablaPlaymobil.getSelectionModel()
        .selectedItemProperty()
        .addListener((obs, anterior, seleccionado) -> {

            if (seleccionado != null) {

                playmobilSeleccionado = seleccionado;

                txtReferencia.setText(seleccionado.getReferencia());
                txtNombre.setText(seleccionado.getNombre());
                txtCategoria.setText(seleccionado.getCategoria());

                txtPrecioCompra.setText(
                        String.valueOf(seleccionado.getPrecioCompra()));

                txtValorActual.setText(
                        String.valueOf(seleccionado.getValorActual()));
            }
        });
    }
    private void cargarTabla() {

        tablaPlaymobil.getItems().clear();
        tablaPlaymobil.getItems().addAll(dao.obtenerTodos());
    }
    @FXML
    private void guardarPlaymobil() {

        try {
            Playmobil p = new Playmobil();

            p.setReferencia(txtReferencia.getText());
            p.setNombre(txtNombre.getText());
            p.setCategoria(txtCategoria.getText());

            p.setPrecioCompra(
                    Double.parseDouble(txtPrecioCompra.getText()));

            p.setValorActual(
                    Double.parseDouble(txtValorActual.getText()));

            p.setObservaciones("");
            p.setRutaImagen("");

            boolean insertado = dao.insertar(p);

            if (insertado) {

                cargarTabla();

                txtReferencia.clear();
                txtNombre.clear();
                txtCategoria.clear();
                txtPrecioCompra.clear();
                txtValorActual.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void modificarPlaymobil() {

        if (playmobilSeleccionado == null) {
            return;
        }

        playmobilSeleccionado.setReferencia(
                txtReferencia.getText());

        playmobilSeleccionado.setNombre(
                txtNombre.getText());

        playmobilSeleccionado.setCategoria(
                txtCategoria.getText());

        playmobilSeleccionado.setPrecioCompra(
                Double.parseDouble(txtPrecioCompra.getText()));

        playmobilSeleccionado.setValorActual(
                Double.parseDouble(txtValorActual.getText()));

        dao.actualizar(playmobilSeleccionado);

        cargarTabla();
    }
    @FXML
    private void eliminarPlaymobil() {

        if (playmobilSeleccionado == null) {
            return;
        }

        dao.eliminar(playmobilSeleccionado.getId());

        playmobilSeleccionado = null;

        txtReferencia.clear();
        txtNombre.clear();
        txtCategoria.clear();
        txtPrecioCompra.clear();
        txtValorActual.clear();

        cargarTabla();
    } 
}

