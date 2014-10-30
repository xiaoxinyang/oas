package org.oas.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OasFilter implements Filter {
	public void doFilter(
		ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		final ServletRequest requestWraper = wrapIfNecessary(servletRequest);
		filterChain.doFilter(requestWraper, servletResponse);

		if (servletResponse instanceof HttpServletResponse) {
			final HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
			final int status = ((HttpServletResponse)servletResponse).getStatus();

			if (status >= 400) {
				System.err.println("Error:" + status);
				System.err.println("Method: " + httpRequest.getMethod());
				System.err.println("PathInfo: " + httpRequest.getPathInfo());
				System.err.println("RequestURI: " + httpRequest.getRequestURI());
				byte[] inputByteArray = ((MultiReadHttpServletRequest)requestWraper).getInputByteArray();
				System.err.println(new String(inputByteArray));
			}
		}
	}

	private ServletRequest wrapIfNecessary(ServletRequest servletRequest) {
        if (servletRequest instanceof MultiReadHttpServletRequest) {
            return servletRequest;
        } else if (servletRequest instanceof HttpServletRequest) {
            return new MultiReadHttpServletRequest((HttpServletRequest) servletRequest);
        }

        return servletRequest;
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}
}
