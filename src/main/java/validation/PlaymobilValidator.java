package validation;

import model.Playmobil;

public class PlaymobilValidator {
	
	private static final int MAX_REFERENCIA = 20;
	private static final int MAX_NOMBRE = 100;
	private static final int MAX_OBSERVACIONES = 500;
	
	public static String validar(Playmobil p) {
		
		if (p == null) {
            return "El Playmobil no puede ser nulo.";
        }

        // Eliminar espacios al principio y al final
        if (p.getReferencia() != null) {
            p.setReferencia(p.getReferencia().trim());
        }

        if (p.getNombre() != null) {
            p.setNombre(p.getNombre().trim());
        }

		if (p.getObservaciones() != null) {
		    p.setObservaciones(p.getObservaciones().trim());
		}

        if (p.getReferencia() == null || p.getReferencia().isBlank()) {
            return "La referencia es obligatoria.";
        }
        
        if (p.getReferencia().length() > MAX_REFERENCIA) {
            return "La referencia no puede superar los 20 caracteres.";
        }

        if (p.getNombre() == null || p.getNombre().isBlank()) {
            return "El nombre es obligatorio.";
        }
        
        if (p.getNombre().length() > MAX_NOMBRE) {
            return "El nombre es demasiado largo.";
        }

        if (p.getCategoria() == null || p.getCategoria().isBlank()) {
            return "La categoría es obligatoria.";
        }
        
        if (!Double.isFinite(p.getPrecioCompra())) {
            return "El precio de compra no es válido.";
        }

        if (p.getPrecioCompra() < 0) {
            return "El precio de compra no puede ser negativo.";
        }
        
        if (!Double.isFinite(p.getValorActual())) {
            return "El valor actual no es válido.";
        }
        
        if (p.getValorActual() < 0) {
            return "El valor actual no puede ser negativo.";
        }
        
        if (p.getObservaciones() != null &&
                p.getObservaciones().length() > MAX_OBSERVACIONES) {
            return "Las observaciones no pueden superar los 500 caracteres.";
        }
        
        return null;
    }
}

