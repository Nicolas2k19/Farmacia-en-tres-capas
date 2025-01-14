package farmacia.domain;

import java.math.BigDecimal;

import farmacia.enums.TipoMedicamento;

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
		if(obj==null) {
			return false;
		}	
		Medicamento medicamento = ((Medicamento) obj);
		boolean mismoTipo = this.tipo.equals(medicamento.getTipo());
		boolean mismaDosis = this.dosis == ((Medicamento) obj).getDosis();
		return super.equals(obj) && mismoTipo && mismaDosis;

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