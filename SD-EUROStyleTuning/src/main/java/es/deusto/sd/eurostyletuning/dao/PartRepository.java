package es.deusto.sd.eurostyletuning.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.deusto.sd.eurostyletuning.entity.Part;

public interface PartRepository extends JpaRepository <String, Part> {

	List<Part> findByCategory(String categoryId);
	List<Part> findByBrand(String brandName);
}
