package oas.rest;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("rest")
public class OasApplication extends ResourceConfig {
	public OasApplication() {
		packages("oas.rest");
	}
}
