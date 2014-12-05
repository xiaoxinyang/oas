package org.oas.rest;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.oas.OasResourceConfig;

public class IdentityResourceTest extends JerseyTest {
    @Override
    protected Application configure() {
        return new OasResourceConfig();
    }

	@Test
	public void test() {
        String s = target().path("/v2.0").request().get(String.class);
        assertEquals("Hello World!", s);
	}
}
