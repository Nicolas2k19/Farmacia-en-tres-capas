package farmacia.domain;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Vacuna extends Producto {

	private int cantidadDeAplicacionesRequeridas;

	public Vacuna(String nombre, BigDecimal precio, int cantidadDeAplicacionesRequeridas,Farmacia farmacia) {
		super(nombre, precio,farmacia);
		this.cantidadDeAplicacionesRequeridas = cantidadDeAplicacionesRequeridas;
	}

	public Vacuna(){
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


}
