package farmacia.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Repository;

import farmacia.domain.Farmacia;
import farmacia.domain.Producto;
import farmacia.repository.interfaces.FarmaciaRepository;

@Repository
public class FarmaciaMock {
	/*
	private List<Farmacia> listaFarmacia;

	public FarmaciaMock(ArrayList<Farmacia> listaFarmacia) {
		this.listaFarmacia = listaFarmacia;
		listaFarmacia.add(new Farmacia(1l, "Farmacia 1", "San Miguel", "204237042402", "Zuviria", 2168));

		listaFarmacia.add(
				new Farmacia(2l, "Farmacia 2", "San Miguel", "204237042400", "Mitre", 2222));

		listaFarmacia.add(
				new Farmacia(3l, "Farmacia 3", "San Miguel", "204237042403", "Peron", 2232));
	}

	@Override
	public List<Farmacia> findAll() {
		return listaFarmacia;
	}

	@Override
	public void save(Farmacia farmacia) {
		
		if(!listaFarmacia.contains(farmacia)) {
			listaFarmacia.add(farmacia);
			return;
		}
		
		listaFarmacia.forEach(farmaciaIterada -> {
			if (farmaciaIterada.getId().equals(farmacia.getId())) {
				farmaciaIterada = farmacia;
			}

		});
		
		System.out.println(listaFarmacia);

		
	}

	@Override
	public void delete(Long id) {
		 this.listaFarmacia = this.listaFarmacia
		.stream()
		.filter(farmacia -> !farmacia.getId().equals(id))
		.collect(Collectors.toList());
	}

	@Override
	public Optional<Farmacia> findById(Long id) {
		 
		return this.listaFarmacia
				.stream()
				.filter(farmacia -> farmacia.getId()
				.equals(id))
				.findFirst();
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Farmacia> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Farmacia> List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllInBatch(Iterable<Farmacia> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Farmacia getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Farmacia getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Farmacia getReferenceById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Farmacia> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Farmacia> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Farmacia> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Farmacia> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Farmacia entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Farmacia> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Farmacia> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Farmacia> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Farmacia> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public <S extends Farmacia> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Farmacia> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Farmacia> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <S extends Farmacia, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		// TODO Auto-generated method stub
		return null;
	}
	
	*/

}
