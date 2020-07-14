package com.catalogue.Repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.catalogue.Model.Inventory;
import com.catalogue.Repository.IRepository.IDAOImpl;
import com.catalogue.Repository.IRepository.IProductRepository;

@Repository
public class DAOImpl implements IDAOImpl {
	
	@Autowired
	private IProductRepository dataRepository;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	public List<Inventory> getAllCatalogues() {
		return dataRepository.findAll();
	}
	
	public Inventory getCatalogue(Long id) {
		Optional<Inventory> optionalObject = dataRepository.findById(id);
		if(optionalObject.isPresent()) {
			return optionalObject.get();
		}
		return null;
	}
	
	public Inventory getCatalogueBySerialNumber(String serialNumber) {
		
		Query q = entityManager.createNativeQuery("Select * from Catalogue l where l.serial_number = :serialNumber", Inventory.class);
		q.setParameter("serialNumber", serialNumber);
		return q.getResultList() != null ? (Inventory)q.getResultList().iterator().next() : null;
	}
	
	public void deleteCatalogue(Long id) {
		dataRepository.deleteById(id);
	}
	
	public Inventory addCatalogue(Inventory Catalogue) {
		return dataRepository.save(Catalogue);
	}

}
