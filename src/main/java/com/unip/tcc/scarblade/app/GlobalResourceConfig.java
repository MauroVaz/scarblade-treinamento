package com.unip.tcc.scarblade.app;


	

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api/v1/*")
public class GlobalResourceConfig extends ResourceConfig{	
	    
	    public GlobalResourceConfig() {
	    	packages("br.com.yaman.mandela.api.resources","io.swagger.jaxrs.listing");	    	
	    	
	    }
	    
}


