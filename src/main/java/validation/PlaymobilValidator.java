package validation;

import model.Playmobil;

public class PlaymobilValidator {
	
	public static String validar(Playmobil p) {

        if (p.getReferencia() == null || p.getReferencia().isBlank()) {
            return "La referencia es obligatoria.";
        }

        if (p.getNombre() == null || p.getNombre().isBlank()) {
            return "El nombre es obligatorio.";
        }

        if (p.getCategoria() == null || p.getCategoria().isBlank()) {
            return "La categoría es obligatoria.";
        }

        if (p.getPrecioCompra() < 0) {
            return "El precio de compra no puede ser negativo.";
        }

        if (p.getValorActual() < 0) {
            return "El valor actual no puede ser negativo.";
        }
        return null;
    }
}

