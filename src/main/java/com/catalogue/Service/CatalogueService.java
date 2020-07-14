package com.catalogue.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogue.Exception.CustomRunTimeException;
import com.catalogue.Model.Inventory;
import com.catalogue.Repository.DAOImpl;
import com.catalogue.Service.IService.ICatalogueService;

@Service
public class CatalogueService implements ICatalogueService{
	
	@Autowired
	DAOImpl daoImpl;

	public List<Inventory> getAllCatalogues() {
		return daoImpl.getAllCatalogues();
	}
	
	public Inventory getCatalogue(Integer id) {
		
		Inventory Catalogue = daoImpl.getCatalogue(Long.valueOf(id.toString()));
		
		if(Catalogue == null) {
			throw new CustomRunTimeException("No Catalogue found for Id " + id);
		}
		return Catalogue;
	}
	
	public Inventory getCatalogueBySerialNumber(String serialNumber) {
		
		Inventory Catalogue = daoImpl.getCatalogueBySerialNumber(serialNumber);
		
		if(Catalogue == null) {
			throw new CustomRunTimeException("No Catalogue found for serial number " + serialNumber);
		}
		return Catalogue;
	}
	
	public void deleteCatalogue(Integer id) {
		daoImpl.deleteCatalogue(Long.valueOf(id.toString()));
	}
	
	public Inventory saveCatalogue(Inventory Catalogue) {
		return daoImpl.addCatalogue(Catalogue);
	}
	
	public Inventory linkCatalogueWithStudent(Inventory Catalogue) {
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
	
	public Inventory deLinkCatalogueWithStudent(Inventory Catalogue) {
		return this.saveCatalogue(Catalogue);
	}
}
