package model;

public class Playmobil {
	
	private int id;
    private String referencia;
    private String nombre;
    private String categoria;
    private double precioCompra;
    private double valorActual;
    private String observaciones;
    private String rutaImagen;
    
    public Playmobil() {}
    
    public Playmobil(String referencia,
            String nombre,
            String categoria,
            double precioCompra,
            double valorActual,
            String observaciones,
            String rutaImagen) {

    	this.referencia = referencia;
    	this.nombre = nombre;
    	this.categoria = categoria;
    	this.precioCompra = precioCompra;
    	this.valorActual = valorActual;
    	this.observaciones = observaciones;
    	this.rutaImagen = rutaImagen;
    }


	public Playmobil(int id, String referencia, String nombre, String categoria, double precioCompra,
			double valorActual, String observaciones, String rutaImagen) {
		this.id = id;
		this.referencia = referencia;
		this.nombre = nombre;
		this.categoria = categoria;
		this.precioCompra = precioCompra;
		this.valorActual = valorActual;
		this.observaciones = observaciones;
		this.rutaImagen = rutaImagen;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public double getValorActual() {
		return valorActual;
	}

	public void setValorActual(double valorActual) {
		this.valorActual = valorActual;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}
   
	@Override
	public String toString() {
		return "Playmobil [id=" + id + ", referencia=" + referencia + ", nombre=" + nombre + ", categoria=" + categoria
				+ ", precioCompra=" + precioCompra + ", valorActual=" + valorActual + ", observaciones=" + observaciones
				+ ", rutaImagen=" + rutaImagen + "]";
	}
    

}
