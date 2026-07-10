package controller;

import java.io.File;

import dao.PlaymobilDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import model.Playmobil;
import util.Alertas;
import util.CategoriasPlaymobil;
import validation.PlaymobilValidator;

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
    @FXML private ComboBox<String> cmbCategoria;
    @FXML private TextField txtPrecioCompra;
    @FXML private TextField txtValorActual;
    @FXML private TextField txtBuscar;
    @FXML private TextArea txtObservaciones;
    @FXML private ImageView imgPlaymobil;
    
    @FXML private Button btnGuardar;
    @FXML private Button btnModificar;
    @FXML private Button btnEliminar;
    @FXML private Button btnSeleccionarImagen;
    
    
    private Playmobil playmobilSeleccionado;
    private PlaymobilDAO dao = new PlaymobilDAO();
    private String rutaImagenSeleccionada;

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
                cmbCategoria.setValue( seleccionado.getCategoria());               

                txtPrecioCompra.setText(
                        String.valueOf(seleccionado.getPrecioCompra()));

                txtValorActual.setText(
                        String.valueOf(seleccionado.getValorActual()));
                txtObservaciones.setText(
                		seleccionado.getObservaciones());
                
                if (seleccionado.getRutaImagen() != null &&
                	    !seleccionado.getRutaImagen().isEmpty()) {

                	    Image imagen = new Image(
                	            new File(seleccionado.getRutaImagen())
                	                    .toURI()
                	                    .toString());

                	    imgPlaymobil.setImage(imagen);
                	    rutaImagenSeleccionada = seleccionado.getRutaImagen();
                	} else {

                	    imgPlaymobil.setImage(null);
                	    rutaImagenSeleccionada = null;
                	}
            }
        });
        txtBuscar.textProperty().addListener(
                (obs, antiguo, nuevo) -> buscarPlaymobil());
        
        cmbCategoria.getItems().addAll(
                CategoriasPlaymobil.CATEGORIAS);
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
            p.setCategoria(cmbCategoria.getValue());

            p.setPrecioCompra(
                    Double.parseDouble(txtPrecioCompra.getText()));

            p.setValorActual(
                    Double.parseDouble(txtValorActual.getText()));

            p.setObservaciones(txtObservaciones.getText());
            p.setRutaImagen(rutaImagenSeleccionada);
            
            String error = PlaymobilValidator.validar(p);
            
            if (error != null) {
            	Alertas.error("Error de validación", error);
            	return;
            }
            if (dao.existeReferencia(p.getReferencia())) {

                Alertas.error(
                        "Referencia duplicada",
                        "Ya existe un Playmobil con esa referencia.");         
                return;
            }

            boolean insertado = dao.insertar(p);

            if (insertado) {

                Alertas.info("Guardado",
                        "Playmobil guardado correctamente");

                cargarTabla();
                limpiarFormularioPlaymobil();

            } else {

                Alertas.error("Error",
                        "No se pudo guardar el Playmobil");
            }            
        }
        catch (NumberFormatException e) {
            Alertas.error(
                    "Formato incorrecto",
                    "El precio de compra y el valor actual deben ser números.");
        }
        catch (Exception e) {
            Alertas.error(
                    "Error",
                    "Ha ocurrido un error inesperado.");
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
                cmbCategoria.getValue());

        playmobilSeleccionado.setPrecioCompra(
                Double.parseDouble(txtPrecioCompra.getText()));

        playmobilSeleccionado.setValorActual(
                Double.parseDouble(txtValorActual.getText()));
        
        playmobilSeleccionado.setObservaciones(
                txtObservaciones.getText());
        
        playmobilSeleccionado.setRutaImagen(rutaImagenSeleccionada);
       
        boolean actualizado =
                dao.actualizar(playmobilSeleccionado);

        if (actualizado) {

            Alertas.info("Modificado","Playmobil actualizado correctamente");

        cargarTabla();
        }
    }
    @FXML
    private void eliminarPlaymobil() {

        if (playmobilSeleccionado == null) {
        	
        	Alertas.error("Selección requerida", "Debes seleccionar un Playmobil");
            return;
        }
        
        boolean confirmar = Alertas.confirmar(
                "Eliminar",
                "¿Seguro que deseas eliminar este Playmobil?");

        if (!confirmar) {
            return;
        }
        
        dao.eliminar(playmobilSeleccionado.getId());

        limpiarFormularioPlaymobil();

        cargarTabla();
        Alertas.info("Eliminado", "Playmobil eliminado correctamente");
    }
    private void limpiarFormularioPlaymobil() {

        txtReferencia.clear();
        txtNombre.clear();
        cmbCategoria.getSelectionModel().clearSelection();
        txtPrecioCompra.clear();
        txtValorActual.clear();
        txtObservaciones.clear();
        imgPlaymobil.setImage(null);
        rutaImagenSeleccionada = null;

        playmobilSeleccionado = null;
    }
    
    @FXML
    private void seleccionarImagen() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar imagen");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Imágenes",
                        "*.png",
                        "*.jpg",
                        "*.jpeg"));

        File archivo =
                fileChooser.showOpenDialog(
                        imgPlaymobil.getScene().getWindow());

        if (archivo != null) {
            rutaImagenSeleccionada = archivo.getAbsolutePath();
            Image imagen = new Image(archivo.toURI().toString());
            imgPlaymobil.setImage(imagen);
        }
    }
    private void buscarPlaymobil() {

        String texto = txtBuscar.getText();

        tablaPlaymobil.getItems().setAll(dao.buscar(texto));
    }
}

