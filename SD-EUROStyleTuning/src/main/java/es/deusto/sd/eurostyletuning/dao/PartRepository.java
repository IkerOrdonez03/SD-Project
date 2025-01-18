package es.deusto.sd.eurostyletuning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import es.deusto.sd.eurostyletuning.entity.Part;

public interface PartRepository extends JpaRepository<Part, Integer> {

}
