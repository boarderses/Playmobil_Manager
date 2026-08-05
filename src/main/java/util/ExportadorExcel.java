package util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import model.Playmobil;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportadorExcel {

    public static void exportar(List<Playmobil> lista,
                                File archivo)  
            throws Exception {
    	
    	//Crear workbook y la hoja
    	Workbook workbook = new XSSFWorkbook();

    	Sheet hoja = workbook.createSheet("Colección");
    	
    	//Estilo título
    	CellStyle estiloTitulo = workbook.createCellStyle();

    	Font fuenteTitulo = workbook.createFont();

    	fuenteTitulo.setBold(true);
    	fuenteTitulo.setFontHeightInPoints((short)16);

    	estiloTitulo.setFont(fuenteTitulo);
    	
    	//Estilo cabecera
    	CellStyle estiloCabecera = workbook.createCellStyle();

    	Font fuenteCabecera = workbook.createFont();
    	fuenteCabecera.setBold(true);
    	fuenteCabecera.setColor(IndexedColors.WHITE.getIndex());

    	estiloCabecera.setFont(fuenteCabecera);

    	estiloCabecera.setFillForegroundColor(
    	        IndexedColors.DARK_BLUE.getIndex());

    	estiloCabecera.setFillPattern(
    	        FillPatternType.SOLID_FOREGROUND);

    	estiloCabecera.setAlignment(
    	        HorizontalAlignment.CENTER);
    	
    	//Título
    	Row filaTitulo = hoja.createRow(0);

    	Cell titulo = filaTitulo.createCell(0);

    	titulo.setCellValue("PLAYMOBIL MANAGER");

    	titulo.setCellStyle(estiloTitulo);
    	
    	//Fecha
    	Row filaFecha = hoja.createRow(1);

    	Cell fecha = filaFecha.createCell(0);

    	fecha.setCellValue("Fecha de exportación: "
    	        + java.time.LocalDate.now());
    	
    	//Crea fila de cabecera
    	Row cabecera = hoja.createRow(2);
    	
    	//Crea columna
    	String[] columnas = {
    	        "Referencia",
    	        "Nombre",
    	        "Categoría",
    	        "Precio Compra",
    	        "Valor Actual",
    	        "Observaciones"
    	};
    	for (int i = 0; i < columnas.length; i++) {

    	    Cell celda = cabecera.createCell(i);

    	    celda.setCellValue(columnas[i]);

    	    celda.setCellStyle(estiloCabecera);
    	}
    	
    	//Estilo moneda
    	CellStyle estiloMoneda = workbook.createCellStyle();

    	DataFormat formato = workbook.createDataFormat();

    	estiloMoneda.setDataFormat(
    	        formato.getFormat("#,##0.00 €"));
    	
    	//Escribir playmobil
    	int fila = 3;

    	for (Playmobil p : lista) {

    	    Row row = hoja.createRow(fila++);

    	    row.createCell(0).setCellValue(p.getReferencia());
    	    row.createCell(1).setCellValue(p.getNombre());
    	    row.createCell(2).setCellValue(p.getCategoria());
    	    Cell compra = row.createCell(3);
    	    compra.setCellValue(p.getPrecioCompra());
    	    compra.setCellStyle(estiloMoneda);
    	    
    	    Cell valor = row.createCell(4);
    	    valor.setCellValue(p.getValorActual());
    	    valor.setCellStyle(estiloMoneda);
    	    
    	    row.createCell(5).setCellValue(p.getObservaciones());
    	}
    	for (int i = 0; i < columnas.length; i++) {
    	    hoja.autoSizeColumn(i);
    	}
    	hoja.createFreezePane(0, 3);
    	
    	//
    	fila += 2;

    	Row resumen = hoja.createRow(fila++);
    	resumen.createCell(0).setCellValue("Resumen");

    	double compraTotal = 0;
    	double valorTotal = 0;

    	for (Playmobil p : lista) {
    	    compraTotal += p.getPrecioCompra();
    	    valorTotal += p.getValorActual();
    	}

    	hoja.createRow(fila++)
    	        .createCell(0)
    	        .setCellValue("Total figuras: " + lista.size());

    	hoja.createRow(fila++)
    	        .createCell(0)
    	        .setCellValue("Compra: " + compraTotal + " €");

    	hoja.createRow(fila++)
    	        .createCell(0)
    	        .setCellValue("Valor actual: " + valorTotal + " €");

    	hoja.createRow(fila++)
    	        .createCell(0)
    	        .setCellValue("Beneficio: "
    	                + (valorTotal - compraTotal)
    	                + " €");
    	
    	hoja.setColumnWidth(0, 6000);
    	
    	//Guardar el archivo
    	FileOutputStream fos = new FileOutputStream(archivo);

    	workbook.write(fos);

    	fos.close();

    	workbook.close();

    }

}
