package farmacia.domain;

import farmacia.domain.enums.TipoMedicamento;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class Medicamento extends Producto {

	@Enumerated(EnumType.STRING)
	private TipoMedicamento tipo;
	private double dosis;

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
