package com.example.demo.controller.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.ChuyenNganh;
import com.example.demo.model.GiangVien;
import com.example.demo.service.GiangVienService;

@Controller
public class GiangVienHomeController {
	@Autowired
private GiangVienService giangVienService;

	@RequestMapping(value = {"/giangvien/dashboard"})
	private String redirectHomeGV(Model model,HttpServletRequest request,Principal principal) {
		GiangVien gv=giangVienService.getGVbyID(Long.valueOf(principal.getName()));
		ChuyenNganh cn=gv.getChuyenNganh();
		model.addAttribute("cn",cn);
		model.addAttribute("gv",gv);
		return "homeGV";
	}
}
