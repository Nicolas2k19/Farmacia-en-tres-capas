package farmacia.domain;

import java.math.BigDecimal;

import farmacia.domain.enums.TipoMedicamento;
import jakarta.persistence.Entity;
@Entity
public class Medicamento extends Producto {

	private TipoMedicamento tipo;
	private double dosis;

	public Medicamento(Long id, String nombre, BigDecimal precio, int stock, TipoMedicamento tipo, double dosis) {
		super(id, nombre, precio, stock);
		this.tipo = tipo;
		this.dosis = dosis;
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

	public TipoMedicamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoMedicamento tipo) {
		this.tipo = tipo;
	}

	public double getDosis() {
		return dosis;
	}

	public void setDosis(double dosis) {
		this.dosis = dosis;
	}

}
