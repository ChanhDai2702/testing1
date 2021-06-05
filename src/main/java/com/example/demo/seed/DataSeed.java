package com.example.demo.seed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.model.TaiKhoan;
import com.example.demo.repository.TaiKhoanRepository;
import com.example.demo.service.KhoaService;
import com.example.demo.service.TaiKhoanService;

@Component
public class DataSeed implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private TaiKhoanRepository khoanService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		System.out.println("chay1111111111111111111111111111111111111111111111111111111111");
		if (!khoanService.findByTaiKhoan("123").isPresent()) {
			TaiKhoan admin = new TaiKhoan();
			
			admin.setTentaikhoan(123L);
			admin.setMatkhau(passwordEncoder.encode("123456"));
			admin.setPhanloai("ROLE_GV");
			khoanService.save(admin);
		}
	}
	
}
