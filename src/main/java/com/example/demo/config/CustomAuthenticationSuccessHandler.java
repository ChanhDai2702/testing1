	package com.example.demo.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.demo.model.TaiKhoan;
import com.example.demo.service.TaiKhoanService;



@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler 
										implements AuthenticationSuccessHandler{
	static String RETURN_HOME = "/sinhvien/dashboard";
	static String RETURN_DASHBOARD="/giangvien/dashboard";
	static String ROLE_GV = "ROLE_GV";
	@Autowired
	private TaiKhoanService taiKhoanService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println(request.getParameter("tentaikhoan"));
		TaiKhoan user = taiKhoanService.findByTaiKhoan(request.getParameter("tentaikhoan"));
		System.out.println("zoooooooooooooooooooooooooo");
        String redirectURL = RETURN_HOME;
        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority(ROLE_GV))) {
        	redirectURL = RETURN_DASHBOARD;
        }
		super.setDefaultTargetUrl(redirectURL);
        super.onAuthenticationSuccess(request, response, authentication);
	}

}
