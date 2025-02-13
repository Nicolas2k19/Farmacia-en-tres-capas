package farmacia.domain;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
@Entity
public class Vacuna extends Producto {

	private int cantidadDeAplicacionesRequeridas;

	public Vacuna(Long id, String nombre, BigDecimal precio, int stock, int cantidadDeAplicacionesRequeridas) {
		super(id, nombre, precio, stock);
		this.cantidadDeAplicacionesRequeridas = cantidadDeAplicacionesRequeridas;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==null) {
			return false;
		}
		return super.equals(obj) && this.cantidadDeAplicacionesRequeridas == (((Vacuna) obj))
				.getCantidadDeAplicacionesRequeridas();
	}

	@Override
	public String toString() {
		return super.toString() + " Cantidad de aplicaciones requeridas = " + cantidadDeAplicacionesRequeridas;
	}

	public int getCantidadDeAplicacionesRequeridas() {
		return cantidadDeAplicacionesRequeridas;
	}

	public void setCantidadDeAplicacionesRequeridas(int cantidadDeAplicacionesRequeridas) {
		this.cantidadDeAplicacionesRequeridas = cantidadDeAplicacionesRequeridas;
	}

}
