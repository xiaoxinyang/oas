package org.oas.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;

public class MultiReadHttpServletRequest extends HttpServletRequestWrapper {
	private ByteArrayOutputStream cachedBytes;

    public MultiReadHttpServletRequest(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }

    /**
     * Either this method or getReader() may be called to read the body, not both.
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
    	if (cachedBytes == null) {

    		cachedBytes = new ByteArrayOutputStream();
    		IOUtils.copy(super.getInputStream(), cachedBytes);
    	}

    	return new ServletInputStream(){
        	private ByteArrayInputStream input
        		= new ByteArrayInputStream(cachedBytes.toByteArray());

			@Override
			public int read() throws IOException {
				return input.read();
			}
    	};
    }

    /**
     * Either this method or getInputStream() may be called to read the body, not both.
     */
    @Override
    public BufferedReader getReader() throws IOException {
    	return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    public byte[] getInputByteArray() {
    	if (cachedBytes == null)
    		return null;

    	return cachedBytes.toByteArray();
    }
}