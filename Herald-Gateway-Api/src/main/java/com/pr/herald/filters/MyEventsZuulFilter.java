package com.pr.herald.filters;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.pr.herald.utils.TokenUtils;
@Component
public class MyEventsZuulFilter extends ZuulFilter {



	@Autowired
	private TokenUtils tokenUtils;

	@Value("${javatab.token.header}")
	private String tokenHeader;

	private static Logger log = Logger.getLogger(MyEventsZuulFilter.class);

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() 
	{	
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String url = request.getRequestURL().toString();
		if(StringUtils.contains(url, "/userApp/event/"))
			return true;
		else
			return false;
	}

	@Override
	public Object run() {

		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String authToken = request.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(authToken);
		log.info("--- User/Email :"+username+" ---");
		log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
		ctx.addZuulRequestHeader("email", username);
		return null;
	}

}
