package com.catalogue.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogue.Exception.CustomRunTimeException;
import com.catalogue.Model.Catalogue;
import com.catalogue.Repository.DAOImpl;
import com.catalogue.Service.IService.ICatalogueService;

@Service
public class CatalogueService implements ICatalogueService{
	
	@Autowired
	DAOImpl daoImpl;

	public List<Catalogue> getAllCatalogues() {
		return daoImpl.getAllCatalogues();
	}
	
	public Catalogue getCatalogue(Integer id) {
		
		Catalogue Catalogue = daoImpl.getCatalogue(Long.valueOf(id.toString()));
		
		if(Catalogue == null) {
			throw new CustomRunTimeException("No Catalogue found for Id " + id);
		}
		return Catalogue;
	}
	
	public Catalogue getCatalogueBySerialNumber(String serialNumber) {
		
		Catalogue Catalogue = daoImpl.getCatalogueBySerialNumber(serialNumber);
		
		if(Catalogue == null) {
			throw new CustomRunTimeException("No Catalogue found for serial number " + serialNumber);
		}
		return Catalogue;
	}
	
	public void deleteCatalogue(Integer id) {
		daoImpl.deleteCatalogue(Long.valueOf(id.toString()));
	}
	
	public Catalogue saveCatalogue(Catalogue Catalogue) {
		return daoImpl.addCatalogue(Catalogue);
	}
	
	public Catalogue linkCatalogueWithStudent(Catalogue Catalogue) {
		Catalogue =  this.saveCatalogue(Catalogue);
		/*if(Catalogue.getCustomerNumber() != null) {
			
			try {
				String jsonStr = new ObjectMapper().writeValueAsString(Catalogue);
				this.kafkaTemplate.send(TOPIC, jsonStr);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} 
		}*/
		return Catalogue;
	}
	
	public Catalogue deLinkCatalogueWithStudent(Catalogue Catalogue) {
		return this.saveCatalogue(Catalogue);
	}
}
