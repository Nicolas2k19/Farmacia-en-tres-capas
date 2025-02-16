package farmacia.domain;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public abstract class Producto implements Comparable<Producto> {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private BigDecimal precio;
	private int stock;

	public Producto(String nombre, BigDecimal precio, int stock) {
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



}
