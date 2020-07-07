package com.catalogue.Model;

import java.util.List;

public class ControllerInputOutput {
	
	private List<Catalogue> catalogueList;
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public List<Catalogue> getcatalogueList() {
		return catalogueList;
	}
	
	private ControllerInputOutput(ControllerBuilder controllerBuilder) {
		this.catalogueList = controllerBuilder.catalogueList;
		this.message = controllerBuilder.message;
	}
	
	public static class ControllerBuilder{
		
		private List<Catalogue> catalogueList;
		
		private String message;
		
		public ControllerBuilder() {
			
		}
		
		public ControllerBuilder catalogueList(List<Catalogue> catalogueList) {
			this.catalogueList = catalogueList;
			return this;
		}
		
		public ControllerBuilder message(String message) {
			this.message = message;
			return this;
		}
		
		public ControllerInputOutput build() {
			ControllerInputOutput user =  new ControllerInputOutput(this);
            return user;
        }
	}
}
