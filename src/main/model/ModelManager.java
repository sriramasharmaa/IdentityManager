package main.model;

import main.model.data.generic.DataModel;

/*
 * Model manager will consult the data model based on the category
 * 
 */
public class ModelManager {
	private String category ;
	public ModelManager(String category) {
		// TODO Auto-generated constructor stub
		this.category = category;
		
	}
	
	public void initDataModel(){
		if(this.category == null){
			DataModel obj= new DataModel();			
		}
	}



}
