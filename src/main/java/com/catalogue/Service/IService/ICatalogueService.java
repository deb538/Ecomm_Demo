package com.catalogue.Service.IService;

import java.util.List;

import com.catalogue.Model.Inventory;

public interface ICatalogueService {

	public List<Inventory> getAllCatalogues();
	
	public Inventory getCatalogue(Integer id);
	
	public void deleteCatalogue(Integer id);
	
	public Inventory saveCatalogue(Inventory Catalogue);
	
	public Inventory linkCatalogueWithStudent(Inventory Catalogue);
	
	public Inventory deLinkCatalogueWithStudent(Inventory Catalogue);
	
	public Inventory getCatalogueBySerialNumber(String serialNumber);
}
