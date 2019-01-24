package com.dearbinge.openapi;

import com.dearbinge.data.api.SecurityService;
import com.dearbinge.service.InfrastructDeal;
import com.dearbinge.utils.RandomUtil;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Map;

@Controller
@RequestMapping("/user/")
//@ImportResource({ "classpath:applicationContext.xml", "classpath:dubbo-services.xml" })
@EnableAutoConfiguration
public class UserSecurity {
	@Resource
	private Map<String, InfrastructDeal> posDataServiceMap;
	@Resource
	private SecurityService securityService;

	@RequestMapping(value = "/code", method = RequestMethod.GET)
	@ResponseBody
	public void getCode(HttpServletRequest request, HttpServletResponse response) {
		try {
			/* 得到指定的head头的值 */
			// String headValue = request.getHeader("host");
			// System.out.println(headValue);
			// System.out.println("-------------------------------------------------");
			// Enumeration<String> e = request.getHeaders("Accept-Encoding");
			// while (e.hasMoreElements()) {
			// headValue = (String) e.nextElement();
			// String value = request.getHeader(headValue);
			// System.out.println(headValue + "=" + value);
			// }
			// System.out.println("-------------------------------------------------");
			// e = request.getHeaderNames();
			// while (e.hasMoreElements()) {
			// String name = (String) e.nextElement();
			// String value = request.getHeader(name);
			// System.out.println(name + " = " + value);
			//
			// }
			HttpSession session = request.getSession();
			session.setAttribute("code", RandomUtil.generateString(24));
			response.getWriter().write(
					"this is node3, port:8082,address:" + InetAddress.getLocalHost() + ",sessionid:" + session.getId());
		} catch (IOException ex) {
		}
	}

//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new UserSecurityInterceptor()).addPathPatterns("/syncParkingBasicData");
//
//	}
}
