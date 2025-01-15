package farmacia.domain;

import java.math.BigDecimal;

public abstract class Producto implements Comparable<Producto> {

	private Long id;
	private String nombre;
	private BigDecimal precio;
	private int stock;

	public Producto(Long id, String nombre, BigDecimal precio, int stock) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
	}

	@Override
	public boolean equals(Object obj) {
		Producto productoIngresado = ((Producto) obj);
		boolean mismoNombre = this.nombre.equals(productoIngresado.getNombre());
		boolean mismoPrecio = this.precio.equals(((Producto) obj).getPrecio());
		return mismoNombre && mismoPrecio;
	}

	@Override
	public int compareTo(Producto o) {
		return this.nombre.compareTo(o.getNombre());
	}

	@Override
	public String toString() {
		return "id = " + id + " nombre = " + nombre + " precio = " + precio + " stock = " + stock;
	}
	
	public void sumarStock(int stock) {
		stockValido(stock);
		
		this.stock+=stock;

	}

	private void stockValido(int stock) {
		if(stock<=0) {
			throw new IllegalArgumentException("El stock ingresado es inferior o igual a 0, está cantidad es invalida.");
		}
	}
	
	public void quitarStock(int stock) {
		this.stock-=stock;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

}
