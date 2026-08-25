package controller;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import dao.PlaymobilDAO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Estadisticas;
import model.Playmobil;
import util.Alertas;
import util.CategoriasPlaymobil;
import util.Configuracion;
import util.ExportadorBackup;
import util.ExportadorCSV;
import util.ExportadorExcel;
import util.ExportadorPDF;
import util.ImportadorCSV;
import util.VisorImagen;
import validation.PlaymobilValidator;


public class MainController {

	@FXML
    private TableView<Playmobil> tablaPlaymobil;
	
	@FXML private TableColumn<Playmobil, String> colReferencia;
    @FXML private TableColumn<Playmobil, String> colNombre;
    @FXML private TableColumn<Playmobil, String> colCategoria;
    @FXML private TableColumn<Playmobil, Double> colPrecio;
    @FXML private TableColumn<Playmobil, Double> colValor;
    @FXML private TableColumn<Playmobil, String> colObservaciones;
    
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
    
    @FXML private Label lblTotalPlaymobil;
    @FXML private Label lblCompra;
    @FXML private Label lblValorActual;
    @FXML private Label lblBeneficio;
    @FXML private Label lblCategorias;
    
    @FXML private PieChart graficoCategorias;
    @FXML private TextArea txtResumenCategorias;
    
    
    private Playmobil playmobilSeleccionado;
    private final PlaymobilDAO dao = new PlaymobilDAO();
    private String rutaImagenSeleccionada;

    @FXML
    public void initialize() {
    	
    	configurarTabla();
        cargarTabla();
        actualizarEstadisticas();
        actualizarGraficoCategorias();
        
        tablaPlaymobil.getSelectionModel()
        .selectedItemProperty()
        .addListener((obs, anterior, seleccionado) -> {

            if (seleccionado != null) {
            	
            	btnModificar.setDisable(false);
            	btnEliminar.setDisable(false);
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
              
        actualizarEstadoBotones();
        enfocarPrimerCampo();
    }  
    
    private void configurarTabla() {
		
    	colReferencia.setCellValueFactory(new PropertyValueFactory<>("referencia"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precioCompra"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valorActual"));
        colObservaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
	}
    private void cargarTabla() {

        tablaPlaymobil.getItems().clear();
        tablaPlaymobil.getItems().addAll(dao.obtenerTodos());
    }
    private void actualizarEstadisticas() {

        Estadisticas estadisticas = dao.obtenerEstadisticas();

        lblTotalPlaymobil.setText(
                String.valueOf(estadisticas.getTotalPlaymobil()));

        lblCompra.setText(
                String.format("%.2f €", estadisticas.getTotalCompra()));

        lblValorActual.setText(
                String.format("%.2f €", estadisticas.getTotalValorActual()));
        
        double beneficio = estadisticas.getBeneficio();

        lblBeneficio.setText(
                String.format("%.2f €", beneficio));
        
        if (beneficio > 0) {

            lblBeneficio.setStyle(
                    "-fx-text-fill: green; -fx-font-weight: bold;");

        } else if (beneficio < 0) {

            lblBeneficio.setStyle(
                    "-fx-text-fill: red; -fx-font-weight: bold;");

        } else {

            lblBeneficio.setStyle(
                    "-fx-text-fill: black; -fx-font-weight: bold;");
        }
        lblCategorias.setText(String.valueOf(dao.obtenerNumeroCategorias()));
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

                actualizarInterfaz();

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
        }
    }
    @FXML
    private void modificarPlaymobil() {

        if (playmobilSeleccionado == null) {
            return;
        }

        try {

            playmobilSeleccionado.setReferencia(txtReferencia.getText());
            playmobilSeleccionado.setNombre(txtNombre.getText());
            playmobilSeleccionado.setCategoria(cmbCategoria.getValue());
            playmobilSeleccionado.setPrecioCompra(Double.parseDouble(txtPrecioCompra.getText()));
            playmobilSeleccionado.setValorActual(Double.parseDouble(txtValorActual.getText()));
            playmobilSeleccionado.setObservaciones(txtObservaciones.getText());
            playmobilSeleccionado.setRutaImagen(rutaImagenSeleccionada);

            String error = PlaymobilValidator.validar(
                    playmobilSeleccionado);

            if (error != null) {

                Alertas.error(
                        "Error de validación",
                        error);
                return;
            }
            
            if (dao.existeReferenciaExceptoId(
                    playmobilSeleccionado.getReferencia(),
                    playmobilSeleccionado.getId())) {

                Alertas.error(
                        "Referencia duplicada",
                        "Ya existe otro Playmobil con esa referencia.");

                return;
            }

            boolean actualizado =
                    dao.actualizar(playmobilSeleccionado);

            if (actualizado) {

                Alertas.info(
                        "Modificado",
                        "Playmobil actualizado correctamente");

                actualizarInterfaz();

            } else {

                Alertas.error(
                        "Error",
                        "No se pudo actualizar el Playmobil.");
            }

        } catch (NumberFormatException e) {

            Alertas.error(
                    "Formato incorrecto",
                    "El precio de compra y el valor actual deben ser números.");

        } catch (Exception e) {

            Alertas.error(
                    "Error",
                    "Ha ocurrido un error inesperado.");
        }
    }
    @FXML
    private void eliminarPlaymobil() {

        if (playmobilSeleccionado == null) {
        	
        	Alertas.error("Selección requerida", "Debes seleccionar un Playmobil");
            return;
        }
        if (Configuracion.confirmarEliminar()) {
        	
        boolean confirmar = Alertas.confirmar(
                "Eliminar",
                "¿Seguro que deseas eliminar este Playmobil?");

        if (!confirmar) {
            return;
        }
    }
        boolean eliminado = dao.eliminar(playmobilSeleccionado.getId());

        if (eliminado) {
            actualizarInterfaz();

            Alertas.info(
                    "Eliminado",
                    "Playmobil eliminado correctamente.");
        } else {
            Alertas.error(
                    "Error",
                    "No se pudo eliminar el Playmobil.");
        }
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
        actualizarEstadoBotones();
        enfocarPrimerCampo();
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
    private void enfocarPrimerCampo() {

        txtReferencia.requestFocus();
    }
    private void actualizarEstadoBotones() {

        boolean seleccionado = playmobilSeleccionado != null;

        btnModificar.setDisable(!seleccionado);
        btnEliminar.setDisable(!seleccionado);
    }
    @FXML
    private void ampliarImagen(MouseEvent event) {

        if (event.getClickCount() == 2) {
            VisorImagen.mostrar(rutaImagenSeleccionada);
        }
    }
    
    private void actualizarGraficoCategorias() {

        ObservableList<PieChart.Data> datos =
                FXCollections.observableArrayList();
        
        var categorias = dao.obtenerPlaymobilPorCategoria();

        int total = categorias.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
        
        if (total == 0) {
            graficoCategorias.setData(datos);
            txtResumenCategorias.setText("No hay Playmobil registrados.");
            return;
        }

        StringBuilder resumen = new StringBuilder();

        categorias.forEach((categoria, cantidad) -> {

            double porcentaje = (cantidad * 100.0) / total;

            datos.add(new PieChart.Data(
                    categoria + " (" +
                    String.format("%.1f", porcentaje) +
                    "%)",
                    cantidad));
            
            resumen.append(categoria)
            .append(": ")
            .append(cantidad)
            .append(cantidad == 1 ? " unidad" : " unidades")
            .append("\n");
        });

        graficoCategorias.setData(datos);
        
        graficoCategorias.setTitle("Distribución por categorías");
        graficoCategorias.setLabelsVisible(true);
        graficoCategorias.setLegendVisible(true);
        graficoCategorias.setClockwise(true); 
        txtResumenCategorias.setText(resumen.toString());
        
        
    }
    @FXML
    private void exportarCSV() {
    	FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Exportar colección");

        fileChooser.setInitialFileName("coleccion_playmobil.csv");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Archivo CSV (*.csv)", "*.csv"));

        File archivo = fileChooser.showSaveDialog(btnGuardar.getScene().getWindow());

        if (archivo == null)
            return;

        try {

        	List<Playmobil> lista = dao.obtenerTodos();

        	ExportadorCSV.exportar(lista, archivo);

        	Alertas.info(
        	        "Exportación completada",
        	        "Se han exportado " + lista.size()
        	        + " Playmobil correctamente.");

        } catch (Exception e) {

            Alertas.error("Error","No se pudo exportar el archivo.");
        }
    }
    
    @FXML
    private void crearBackup() {
    	
        try {

            File carpeta = new File (Configuracion.getCarpetaBackups());
            

            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }
            
            DateTimeFormatter formato =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

            String nombreArchivo =
                    "backup_" +
                    LocalDateTime.now().format(formato) +
                    ".csv";

            File archivo = new File(carpeta, nombreArchivo);

            List<Playmobil> lista = dao.obtenerTodos();

            ExportadorBackup.crearBackup(lista, archivo);           

            Alertas.info(
                    "Copia de seguridad",
                    "Copia de seguridad creada correctamente.\n\n"
                    + archivo.getAbsolutePath());

        } catch (Exception e) {

            Alertas.error(
                    "Error",
                    "No se pudo crear la copia de seguridad.");
        }
    }
    @FXML
    private void restaurarBackup() {

    	if (Configuracion.confirmarRestaurar()) {
    		
        boolean confirmar = Alertas.confirmar(
                "Restaurar copia de seguridad",
                "La restauración reemplazará la colección actual.\n\n"
                + "¿Deseas continuar?");

        if (!confirmar) {
            return;
        }
    }

        FileChooser chooser = new FileChooser();

        chooser.setTitle("Seleccionar copia de seguridad");

        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Copias de seguridad (*.csv)", "*.csv"));

        File archivo = chooser.showOpenDialog(
                tablaPlaymobil.getScene().getWindow());

        if (archivo == null) {
            return;
        }

        try {

            List<Playmobil> lista =
                    ImportadorCSV.importar(archivo);

            if (lista.isEmpty()) {

                Alertas.error(
                        "Restauración",
                        "La copia de seguridad está vacía.");

                return;
            }

            dao.reemplazarColeccion(lista);

            actualizarInterfaz();

            Alertas.info(
                    "Restauración completada",
                    "La colección se ha restaurado correctamente.\n\n"
                    + "Registros restaurados: "
                    + lista.size());

        } catch (Exception e) {

            Alertas.error(
                    "Error",
                    "No se pudo restaurar la copia de seguridad.");
        }
    }
    
    @FXML
    private void exportarPDF() {

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Exportar PDF");

        fileChooser.setInitialFileName("coleccion_playmobil.pdf");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Documento PDF (*.pdf)", "*.pdf"));

        File archivo =
                fileChooser.showSaveDialog(btnGuardar.getScene().getWindow());

        if (archivo == null)
            return;

        try {

        	List<Playmobil> lista = dao.obtenerTodos();

        	ExportadorPDF.exportar(lista, archivo);
            
            Alertas.info("PDF generado", "El informe se ha creado correctamente.");

        } catch (Exception e) {

            Alertas.error("Error","No se pudo generar el PDF.");

        }
    }

    @FXML
    private void cerrarAplicacion() {

        Platform.exit();
    }
    @FXML
    private void importarCSV() {
    	FileChooser chooser = new FileChooser();

    	chooser.setTitle("Importar colección");

    	chooser.getExtensionFilters().add(
    	        new FileChooser.ExtensionFilter("CSV", "*.csv"));
    	
    	File archivo = chooser.showOpenDialog(
    	        tablaPlaymobil.getScene().getWindow());
    	
    	if (archivo == null) {
    	    return;
    	}
    	
    	try {

    	    List<Playmobil> lista = ImportadorCSV.importar(archivo);    	        	 
    	            
    	    int importados = dao.importar(lista);
    	    
    	    actualizarInterfaz();
    	    
    	    Alertas.info("Importación","Se han importado "
                    + importados
                    + " registros.");
    	    
    	}
    	catch (Exception e) {
    	    
    	    Alertas.error(
    	    	    "Error",
    	    	    "No se pudo importar el archivo CSV.");
    	}
    }
    @FXML
    private void exportarExcel() {

        FileChooser chooser = new FileChooser();

        chooser.setTitle("Exportar Excel");

        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Excel (*.xlsx)", "*.xlsx"));

        chooser.setInitialFileName("coleccion_playmobil.xlsx");

        File archivo = chooser.showSaveDialog(
                tablaPlaymobil.getScene().getWindow());

        if (archivo == null) {
            return;
        }

        try {

        	List<Playmobil> lista = dao.obtenerTodos();

        	ExportadorExcel.exportar(lista, archivo);
            
            Alertas.info("Exportación","Excel exportado correctamente.");


        } catch (Exception e) {

            Alertas.error(
                    "Error",
                    "No se pudo exportar el Excel.");
        }
    }
    @FXML
    private void abrirConfiguracion() {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/view/configuracion.fxml"));

            Parent root = loader.load();

            Stage ventana = new Stage();

            ventana.setTitle("Configuración");
            ventana.setScene(new Scene(root));

            ventana.initModality(Modality.APPLICATION_MODAL);

            ventana.showAndWait();

        } catch (Exception e) {

            Alertas.error(
                    "Error",
                    "No se pudo abrir la configuración.");
        }
    }
    private void actualizarInterfaz() {

        cargarTabla();
        actualizarEstadisticas();
        actualizarGraficoCategorias();
        limpiarFormularioPlaymobil();
    }
}

