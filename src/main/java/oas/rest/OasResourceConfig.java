package oas.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.process.Inflector;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.model.Resource;
import org.glassfish.jersey.server.model.ResourceMethod;

@ApplicationPath("rest")
public class OasResourceConfig extends ResourceConfig {
	public OasResourceConfig() {
		packages("oas.rest");

        final Resource.Builder resourceBuilder = Resource.builder();
        resourceBuilder.path("helloworld");

        final ResourceMethod.Builder methodBuilder = resourceBuilder.addMethod("GET");
        methodBuilder.produces(MediaType.TEXT_PLAIN_TYPE)
                .handledBy(new Inflector<ContainerRequestContext, String>() {

            @Override
            public String apply(ContainerRequestContext containerRequestContext) {
                return "Hello World!";
            }
        });

        final Resource resource = resourceBuilder.build();
        registerResources(resource);
	}
}
