package farmacia.domain;

import farmacia.domain.enums.Aroma;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class Higiene extends Producto {

	@Enumerated(EnumType.STRING)
	private Aroma aroma;
	private boolean hipoalergenico;

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		return super.equals(obj) && this.aroma.equals(((Higiene) obj).getAroma())
				&& this.hipoalergenico == (((Higiene) obj).isHipoalergenico());
	}

	@Override
	public String toString() {
		return super.toString() + " aroma = " + aroma + " hipoalergenico = " + hipoalergenico;
	}

}
