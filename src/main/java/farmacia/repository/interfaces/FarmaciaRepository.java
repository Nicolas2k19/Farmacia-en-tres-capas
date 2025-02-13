package farmacia.repository.interfaces;


import org.springframework.data.jpa.repository.JpaRepository;

import farmacia.domain.Farmacia;


public interface FarmaciaRepository extends JpaRepository<Farmacia,Long> {

	
}
