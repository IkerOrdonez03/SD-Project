package es.deusto.sd.eurostyletuning.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.deusto.sd.eurostyletuning.entity.Purchase;

public interface PurchaseRepository extends JpaRepository <String, Purchase>{
	
	List<Purchase> findByPurchaseId(long purchaseId);

}
