package com.catalogue.Service.IService;

import java.util.List;

import com.catalogue.Model.Catalogue;

public interface ICatalogueService {

	public List<Catalogue> getAllCatalogues();
	
	public Catalogue getCatalogue(Integer id);
	
	public void deleteCatalogue(Integer id);
	
	public Catalogue saveCatalogue(Catalogue Catalogue);
	
	public Catalogue linkCatalogueWithStudent(Catalogue Catalogue);
	
	public Catalogue deLinkCatalogueWithStudent(Catalogue Catalogue);
	
	public Catalogue getCatalogueBySerialNumber(String serialNumber);
}
