package es.deusto.sd.eurostyletuning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import es.deusto.sd.eurostyletuning.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer>{
	
	Brand findByBrandName(int brandId);

}
