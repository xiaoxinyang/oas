package org.oas.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.oas.jaxb.Token;

@Path("/v2.0")
public class IdentityResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response showVersion() {
		return Response.status(Response.Status.ACCEPTED.getStatusCode())
				.entity("{}")
				.type(MediaType.APPLICATION_JSON).build();
	}

	@Path("/tokens")
	public Tokens getTokens() {
		return new Tokens();
	}

	public class Tokens{
		public String a = "2341234";
		@POST
		@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
		public Token showVersion() {
			Token token = new Token();
			token.getAny().add(new Tokens());
			return token;
		}
	}
}
