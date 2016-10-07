package org.raj.learn.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class InjectdemoResource {

	@GET
	@Path("anotations")
	public String getInputs(@MatrixParam("matrix") String matrix,
							@HeaderParam("Content-Type") String header,
							@CookieParam("name") String name) {			
		return "Matrix param:"+matrix+" Header name:"+header+" Cookie value:"+name;
	}
	
	@GET
	@Path("context")
	public String getParametersUsingContext(@Context UriInfo uriInfo,@Context HttpHeaders headers){		
		String path = uriInfo.getAbsolutePath().toString();
		String baseUri = uriInfo.getBaseUri().toString();
		String pathParams = uriInfo.getPathParameters().toString();
		String queryParams = uriInfo.getQueryParameters().toString();
		
		//return "path:"+path+" baseUri:"+baseUri+" pathParams:"+pathParams+" queryParams:"+queryParams;
		
		String cookies = headers.getCookies().toString();
		String requestHeaders = headers.getRequestHeaders().toString();
		
		return "cookies"+cookies+" requestHeaders"+requestHeaders;
	}

}
