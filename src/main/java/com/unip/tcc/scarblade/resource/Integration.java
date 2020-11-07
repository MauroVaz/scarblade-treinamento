package com.unip.tcc.scarblade.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.unip.tcc.scarblade.treinamento.Treinamento;

@Path("/treinamento")
public class Integration {

	Treinamento trein = new Treinamento();
	
	@GET
	@Path("/ping")
	@Produces(value = "application/json")
	public Response getStatus() {
		return Response.ok().status(Response.Status.OK).entity("{\"retorno\":\"pong\"}").build();
	}
	
	@GET
	@Path("/treinar/{ID}")
	@Produces(value = "application/json")
	public Response scheduler(@PathParam("ID") String id) {
		try {

			trein.treinamentoFlux(id);
			System.out.println(id);
			
			return Response.ok().status(Response.Status.OK).build();
			

		} catch (Exception e) {
			e.printStackTrace();
			return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getCause().toString()).build();
		}
	}


}
