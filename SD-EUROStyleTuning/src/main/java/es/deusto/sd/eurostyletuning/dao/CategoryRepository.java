package es.deusto.sd.eurostyletuning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import es.deusto.sd.eurostyletuning.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
