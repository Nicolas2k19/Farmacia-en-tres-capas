package farmacia.repository.interfaces;


import org.springframework.data.jpa.repository.JpaRepository;

import farmacia.domain.Farmacia;

import java.util.List;


public interface FarmaciaRepository extends JpaRepository<Farmacia,Long> {

    public List<Farmacia> findAllByOrderByNombreDesc();

    public List<Farmacia> findAllByOrderByNombreAsc();
}
