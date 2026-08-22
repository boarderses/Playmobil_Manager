package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.Estadisticas;
import model.Playmobil;

public class ExportadorPDF {

    public static void exportar(List<Playmobil> lista, File archivo)
            throws IOException {
    	

    	Estadisticas estadisticas = calcularEstadisticas(lista);
    	
    	DecimalFormat formato =
                new DecimalFormat("#,##0.00 €");
    	
        Document documento = new Document();

        try {
        PdfWriter.getInstance(documento,
                new FileOutputStream(archivo));

        documento.open();
        
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        String fecha = LocalDateTime.now().format(formatoFecha);

        // Título
        Font titulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD,22);
        Paragraph pTitulo =
                new Paragraph("PLAYMOBIL MANAGER", titulo);

        pTitulo.setAlignment(Paragraph.ALIGN_CENTER);

        documento.add(pTitulo);
        documento.add(new Paragraph(" "));
        
        // Información del informe
        documento.add(new Paragraph("Informe de la colección"));
        documento.add(new Paragraph(" "));
        
        documento.add(new Paragraph("Fecha de generación: " + fecha));
        documento.add(new Paragraph(""));
        
        documento.add(new Paragraph("Total de figuras: " + estadisticas.getTotalPlaymobil()));

        documento.add(new Paragraph(String.format("Compra total: %.2f €",estadisticas.getTotalCompra())));

        documento.add(new Paragraph(String.format("Valor actual: %.2f €",estadisticas.getTotalValorActual())));

        documento.add(new Paragraph(String.format("Beneficio: %.2f €",estadisticas.getBeneficio())));

        documento.add(new Paragraph(" "));
        
        documento.add(new Paragraph(
                "Número de figuras: " + lista.size()));
        documento.add(new Paragraph(" "));
        
        // Tabla
        PdfPTable tabla = new PdfPTable(5);
        tabla.setWidthPercentage(100);
        tabla.addCell(crearCabecera("Referencia"));
        tabla.addCell(crearCabecera("Nombre"));
        tabla.addCell(crearCabecera("Categoría"));
        tabla.addCell(crearCabecera("Compra"));
        tabla.addCell(crearCabecera("Valor"));
        for (Playmobil p : lista) {

            tabla.addCell(p.getReferencia());
            tabla.addCell(p.getNombre());
            tabla.addCell(p.getCategoria());
            tabla.addCell(formato.format(p.getPrecioCompra()));
            tabla.addCell(formato.format(p.getValorActual()));
        }
        documento.add(tabla); 
        
        }finally {		

        documento.close();
        }
    }
    private static Estadisticas calcularEstadisticas(
            List<Playmobil> lista) {

        Estadisticas estadisticas =
                new Estadisticas();

        estadisticas.setTotalPlaymobil(
                lista.size());

        double totalCompra = 0;
        double totalValorActual = 0;

        for (Playmobil p : lista) {

            totalCompra += p.getPrecioCompra();
            totalValorActual += p.getValorActual();
        }

        estadisticas.setTotalCompra(
                totalCompra);

        estadisticas.setTotalValorActual(
                totalValorActual);

        return estadisticas;
    }

    private static PdfPCell crearCabecera(
            String texto) {

        Font fuente =
                FontFactory.getFont(
                        FontFactory.HELVETICA_BOLD);

        PdfPCell celda =
                new PdfPCell(
                        new Phrase(
                                texto,
                                fuente));

        celda.setHorizontalAlignment(
                PdfPCell.ALIGN_CENTER);

        return celda;
    }
}