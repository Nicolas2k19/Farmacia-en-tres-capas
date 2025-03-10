package farmacia.domain;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Producto implements Comparable<Producto> {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Farmacia farmacia;
	private String nombre;
	private BigDecimal precio;


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
