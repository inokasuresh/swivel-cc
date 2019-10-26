package org.isg.cc.model;

public class SearchableFactory {

	public SearchableTypes getSearchableObj(String type) {
		if(type == null){
	         return null;
	      }		
	      if(type.equalsIgnoreCase("user")){
	         return new User();
	         
	      } else if(type.equalsIgnoreCase("ticket")){
	         return new Ticket();
	         
	      } else if(type.equalsIgnoreCase("org")){
	         return new Organization();
	      }
	      
	      return null;
	}
}
