package com.catalogue.Controller;

import java.util.List;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalogue.Exception.CustomRunTimeException;
import com.catalogue.Exception.NoContentException;
import com.catalogue.Model.Inventory;
import com.catalogue.Model.ControllerInputOutput;
import com.catalogue.Service.IService.ICatalogueService;

@RestController
@RequestMapping("/api/v1")
public class CatalogueController {

	@Autowired
	ICatalogueService catalogueService;

	@GetMapping("/catalogue")
	public ResponseEntity<ControllerInputOutput> getAllCatalogues() {

		List<Inventory> allCatalogues = catalogueService.getAllCatalogues();

		if (allCatalogues == null || allCatalogues.isEmpty()) {
			throw new CustomRunTimeException("No Resource found");
		}

		ControllerInputOutput output = new ControllerInputOutput.ControllerBuilder().catalogueList(allCatalogues)
				.build();

		return new ResponseEntity<ControllerInputOutput>(output, HttpStatus.OK);
	}

	@GetMapping("/catalogue/{id}")
	public ResponseEntity<Inventory> getCatalogue(@PathVariable Integer id) {

		if (id == null) {
			throw new NoContentException("Invalid Input");
		}

		Inventory Catalogue = catalogueService.getCatalogue(id);

		return new ResponseEntity<>(Catalogue, HttpStatus.OK);
	}

	@PostMapping("/catalogue")
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseEntity<String> addCatalogue(@RequestBody Inventory Catalogue) {

		Catalogue = catalogueService.saveCatalogue(Catalogue);

		if (Catalogue.getId() != null) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/catalogue/{id}")
	public ResponseEntity<String> addCatalogue(@PathVariable Integer id) {

		catalogueService.deleteCatalogue(id);

		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@PostConstruct
	public void addDummyCataloguesFromWeb() {
		
		Inventory cat = new Inventory();
		cat.setCategory("Ebook");
		cat.setCurrency("INR");
		cat.setLeftOver(3);
		cat.setPrice(100);
		cat.setProductName("Hadoop Book");
		cat.setSubCategory("Information Technology");
		
		Consumer<Inventory> c = catalogue -> this.addCatalogue(catalogue);
		c.accept(cat); 
	}
	
	@GetMapping("/dummy")
	public ResponseEntity<ControllerInputOutput> getDummyOutput() {

		List<Inventory> allCatalogues = catalogueService.getAllCatalogues();

		if (allCatalogues == null || allCatalogues.isEmpty()) {
			throw new CustomRunTimeException("No Resource found");
		}
		
		
		ControllerInputOutput output = new ControllerInputOutput.ControllerBuilder().message("Experiment Success")
				.build();

		return new ResponseEntity<ControllerInputOutput>(output, HttpStatus.OK);
	}

}
