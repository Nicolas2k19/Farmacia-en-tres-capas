package farmacia.domain;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class Vacuna extends Producto {

	@Column(name = "cant_aplicaciones")
	private int cantidadDeAplicacionesRequeridas;

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
