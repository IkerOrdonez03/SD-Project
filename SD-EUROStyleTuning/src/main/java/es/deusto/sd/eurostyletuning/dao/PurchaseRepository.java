package es.deusto.sd.eurostyletuning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import es.deusto.sd.eurostyletuning.entity.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}
