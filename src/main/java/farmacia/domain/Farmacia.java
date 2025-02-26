package farmacia.domain;

import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Farmacia implements Comparable<Farmacia> {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String nombre, localidad, cuil, calle;
	private Integer nroCalle;
	
	public Farmacia(String nombre, String localidad, String cuil, String calle, Integer nroCalle) {
		super();
		this.nombre = nombre;
		this.localidad = localidad;
		this.cuil = cuil;
		this.calle = calle;
		this.nroCalle = nroCalle;
	}

	public Farmacia(){

	}

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
