package farmacia.domain;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;

@Getter
@Setter
@Entity
public abstract class Producto implements Comparable<Producto> {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private BigDecimal precio;
	@ManyToOne
	private Farmacia farmacia;

	public Producto(String nombre, BigDecimal precio,Farmacia farmacia) {
		this.nombre = nombre;
		this.precio = precio;
		this.farmacia = farmacia;
	}

	public Producto(){

	}

	@Override
	public boolean equals(Object obj) {
		Producto productoIngresado = ((Producto) obj);
		boolean mismoNombre = this.nombre.equals(productoIngresado.getNombre());
		boolean mismoPrecio = this.precio.compareTo(((Producto) obj).getPrecio()) == 0;
		return mismoNombre && mismoPrecio;
	}

	@Override
	public int compareTo(Producto o) {
		return this.nombre.compareTo(o.getNombre());
	}

	@Override
	public String toString() {
		return "id = " + id + " nombre = " + nombre + " precio = " + precio;
	}

	




}
