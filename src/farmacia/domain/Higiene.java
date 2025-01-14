package farmacia.domain;

import java.math.BigDecimal;

import farmacia.enums.Aroma;

public class Higiene extends Producto {

	private Aroma aroma;
	private boolean hipoalergenico;

	public Higiene(Long id, String nombre, BigDecimal precio, int stock, Aroma aroma, boolean hipoalergenico) {
		super(id, nombre, precio, stock);
		this.aroma = aroma;
		this.hipoalergenico = hipoalergenico;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==null) {
			return false;
		}
		Higiene producto = ((Higiene) obj);
		boolean mismoAroma = this.aroma.equals(producto.getAroma());
		boolean ambosHipoalergenicos = this.hipoalergenico == (producto.isHipoalergenico());
		return super.equals(obj) && mismoAroma && ambosHipoalergenicos;
	}

	@Override
	public String toString() {
		return super.toString() + " aroma = " + aroma + " hipoalergenico = " + hipoalergenico;
	}

	public Aroma getAroma() {
		return aroma;
	}

	public void setAroma(Aroma aroma) {
		this.aroma = aroma;
	}

	public boolean isHipoalergenico() {
		return hipoalergenico;
	}

	public void setHipoalergenico(boolean hipoalergenico) {
		this.hipoalergenico = hipoalergenico;
	}

}
