package validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Playmobil;
import validation.PlaymobilValidator;

public class PlaymobilValidatorTest {
	
	private Playmobil playmobil;
	
	@BeforeEach
	void setUp() {

	    playmobil = new Playmobil();

	    playmobil.setReferencia("PM001");
	    playmobil.setNombre("Castillo Medieval");
	    playmobil.setCategoria("Knights");
	    playmobil.setPrecioCompra(120);
	    playmobil.setValorActual(180);
	    playmobil.setObservaciones("Figura completa");
	}

	@Test
	void playmobilValido() {	   

	    assertNull(PlaymobilValidator.validar(playmobil));
	}
	@Test
	void referenciaVacia() {

	    playmobil.setReferencia("");

	    assertEquals("La referencia es obligatoria.",
	            PlaymobilValidator.validar(playmobil));
	}
	@Test
	void nombreVacio() {

	    playmobil.setNombre("");

	    assertEquals("El nombre es obligatorio.",
	            PlaymobilValidator.validar(playmobil));
	}
	@Test
	void precioNegativo() {

	    playmobil.setPrecioCompra(-10);

	    assertEquals("El precio de compra no puede ser negativo.",
	            PlaymobilValidator.validar(playmobil));
	}
	@Test
	void valorActualNegativo() {
		
	    playmobil.setValorActual(-50);

	    assertEquals("El valor actual no puede ser negativo.",
	            PlaymobilValidator.validar(playmobil));
	}
	@Test
	void referenciaDemasiadoLarga() {

	    playmobil.setReferencia("1234567890123456789012345678901");

	    assertEquals("La referencia no puede superar los 30 caracteres.",
	            PlaymobilValidator.validar(playmobil));
	}
	@Test
	void nombreDemasiadoLargo() {

	    playmobil.setNombre("A".repeat(101));

	    assertEquals("El nombre es demasiado largo.",
	            PlaymobilValidator.validar(playmobil));
	}
}
