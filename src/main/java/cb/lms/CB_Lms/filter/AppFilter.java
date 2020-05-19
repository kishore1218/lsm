package cb.lms.CB_Lms.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import cb.lms.CB_Lms.service.JWTService;
import cb.lms.CB_Lms.service.UserModuleService;




/**
 * 
 * @author 1595812
 *
 */
@Component
public class AppFilter implements Filter {
	
	
	@Autowired
	JWTService jwtService;
	
//	@Autowired
//	UserModuleService moduleService;

	/**
	 * 
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {

		try {
			if (isActionAllowed((HttpServletRequest) request, (HttpServletResponse) response)) {
				// logger.debug("Processing Request-->"+getRequestUrl());
				filterChain.doFilter(request, response);
//				 setResponseHeaders(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			((HttpServletResponse) response).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * 
	 * @param res
	 */
	private void setResponseHeaders(ServletResponse res) {

		HttpServletResponse response = (HttpServletResponse) res;

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
		 response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "X-requested-with, Content-Type,Accept");
		response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		response.setHeader("Pragma", "no-cache");
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public boolean isActionAllowed(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// String actionName = getPatternActionName(getRequestUrl(request), request);

		String url = getRequestUrl(request);

		System.out.println("request URL: " + url);

		if (url.contains("swagger-ui.html") || url.contains("authenticate") || url.contains("h2-console")|| url.contains("refreshtoken")) {
			return true;
		} else if (isUserAuthenticated(request)) {
			return true;
		} else {
			// redirect to login page
//			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//			response.setHeader("noAuth", "true");
			return false;
		}
		
//		return true;

	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	private String getRequestUrl(HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		return url;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	private boolean isUserAuthenticated(HttpServletRequest request) {
		
		final String requestTokenHeader = request.getHeader("authorization");

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

				String jwtToken = requestTokenHeader.substring(7);
				
				if(jwtService.validateToken(jwtToken)){
					
					return true;
				}
		}
		
		return false;
}

	/**
	 * 
	 * @param requestURI
	 * @param request
	 * @return
	 */
	private static String getPatternActionName(String requestURI, HttpServletRequest request) {
		if (requestURI == null) {
			return requestURI;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(".*");
		sb.append(request.getContextPath());
		sb.append("/(.*)");

		String pattern = sb.toString();

		// Create a Pattern object
		Pattern r = Pattern.compile(pattern);

		// Now create matcher object.
		Matcher m = r.matcher(requestURI);
		if (m.find()) {

			return m.group(1);

		}
		return null;
	}
	
	
	
}
