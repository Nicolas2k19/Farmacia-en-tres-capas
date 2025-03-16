package farmacia.domain;

import java.util.Objects;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Farmacia implements Comparable<Farmacia> {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String localidad;
	private String cuil;
	private String calle;
	@Column(name="nro_calle")
	private Integer nroCalle;

	@Override
	public String toString() {
		return "Farmacia [id=" + id + ", nombre=" + nombre + ", localidad=" + localidad + ", cuil=" + cuil + ", calle="
				+ calle + ", nroCalle=" + nroCalle+"]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Farmacia other = (Farmacia) obj;
		return Objects.equals(calle, other.calle) && Objects.equals(cuil, other.cuil)
				&& Objects.equals(localidad, other.localidad) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(nroCalle, other.nroCalle);
	}

	@Override
	public int compareTo(Farmacia farmaciaAComparar) {
		return this.nombre.compareTo(farmaciaAComparar.getNombre());
	}

	
	
	

}
