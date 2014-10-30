package org.oas.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.process.Inflector;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.model.Resource;
import org.glassfish.jersey.server.model.ResourceMethod;

@ApplicationPath("rest")
public class OasResourceConfig extends ResourceConfig {
	private static OasInflector in1 = new OasInflector("hello1");
	private static OasInflector in2 = new OasInflector("hello2");

	public OasResourceConfig() {
		packages("oas.rest");

        final Resource.Builder resourceBuilder = Resource.builder();
        resourceBuilder.path("helloworld");

        final ResourceMethod.Builder methodBuilder = resourceBuilder.addMethod("GET");
        methodBuilder.produces(MediaType.TEXT_PLAIN_TYPE)
                .handledBy(in1);

        final Resource resource = resourceBuilder.build();
        registerResources(resource);
	}

	private static class OasInflector implements Inflector<ContainerRequestContext, String> {
		private String hello;

		public OasInflector(String hello) {
			this.hello = hello;
		}

		public String apply(ContainerRequestContext containerRequestContext) {
            return hello;
		}
	}
}
