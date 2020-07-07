package com.catalogue.Repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.catalogue.Model.Catalogue;
import com.catalogue.Repository.IRepository.IDAOImpl;
import com.catalogue.Repository.IRepository.IProductRepository;

@Repository
public class DAOImpl implements IDAOImpl {
	
	@Autowired
	private IProductRepository dataRepository;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	public List<Catalogue> getAllCatalogues() {
		return dataRepository.findAll();
	}
	
	public Catalogue getCatalogue(Long id) {
		Optional<Catalogue> optionalObject = dataRepository.findById(id);
		if(optionalObject.isPresent()) {
			return optionalObject.get();
		}
		return null;
	}
	
	public Catalogue getCatalogueBySerialNumber(String serialNumber) {
		
		Query q = entityManager.createNativeQuery("Select * from Catalogue l where l.serial_number = :serialNumber", Catalogue.class);
		q.setParameter("serialNumber", serialNumber);
		return q.getResultList() != null ? (Catalogue)q.getResultList().iterator().next() : null;
	}
	
	public void deleteCatalogue(Long id) {
		dataRepository.deleteById(id);
	}
	
	public Catalogue addCatalogue(Catalogue Catalogue) {
		return dataRepository.save(Catalogue);
	}

}
