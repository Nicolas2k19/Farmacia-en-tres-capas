package farmacia.domain;

import java.math.BigDecimal;

import farmacia.domain.enums.TipoMedicamento;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Medicamento extends Producto {

	private TipoMedicamento tipo;
	private double dosis;

	public Medicamento(String nombre, BigDecimal precio, TipoMedicamento tipo, double dosis,Farmacia farmacia) {
		super(nombre, precio,farmacia);
		this.tipo = tipo;
		this.dosis = dosis;
	}

	public Medicamento(){

	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		return super.equals(obj) && this.tipo.equals(((Medicamento) obj).getTipo())
				&& this.dosis == ((Medicamento) obj).getDosis();

	}

	@Override
	public String toString() {
		return super.toString() + " tipo = " + tipo + ", dosis = " + dosis;
	}


}
