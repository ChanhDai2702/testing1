package com.example.demo.controller.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.DiemDTO;
import com.example.demo.dto.DiemTichLuyDTO;
import com.example.demo.model.BangDiem;

import com.example.demo.model.NienKhoa;
import com.example.demo.model.SinhVien;
import com.example.demo.service.BangDiemService;

import com.example.demo.service.NienKhoaService;
import com.example.demo.service.SinhVienService;

@Controller
public class BangDiemWebController {
	@Autowired
	private BangDiemService bangDiemService;



	
	@Autowired
	private NienKhoaService nienKhoaService;
	@Autowired 
	private SinhVienService sinhVienService;
	
	@RequestMapping(value = "/bangdiem")
	public String getBangDiem(Model model,Principal principal) {
		SinhVien sv=sinhVienService.getSVbyID(Long.valueOf(principal.getName()));
		model.addAttribute("sv",sv);
		List<BangDiem> dsbd = bangDiemService.getAllBangDiembyIDSV(sv.getMasv());
		
		List<DiemDTO> diemDTO = new ArrayList<DiemDTO>();
		List<NienKhoa> dsnk = nienKhoaService.getAllNienKhoabyIdSV(sv.getMasv());
		
		
		for(int t=0;t<dsnk.size();t++) {
			DiemDTO dto = new DiemDTO();
			
			dto.setHocky(dsnk.get(t).getHocKy().getLoaiHocKy());
			dto.setNienkhoa(dsnk.get(t).getKhoa());
			diemDTO.add(dto);
		}
		
		float dtl_10 = 0,dtl_4=0;
		for(int i = 0;i<diemDTO.size();i++) {
			List<BangDiem> bangDiems = new ArrayList<BangDiem>();
			Float dtb_10 = (float) 0;
			for(int j = 0;j<dsbd.size();j++) {
				if(diemDTO.get(i).getHocky()
						.equalsIgnoreCase(dsbd.get(j).getNienkhoa().getHocKy().getLoaiHocKy())) {
					if(diemDTO.get(i).getNienkhoa()
							.equalsIgnoreCase(dsbd.get(j).getNienkhoa().getKhoa())) {
						BangDiem bd = new BangDiem();
						bd = dsbd.get(j);
						bangDiems.add(bd);
						if(bd.getDtb_10()!=null) {
							dtb_10 = dtb_10 + bd.getDtb_10();
						}
						if(bd.getDtb_10()==null) {
							dtb_10=dtb_10;
						}
						
						System.out.println(dtb_10+"dtb100000000000000000");
					}
						
				}
			}
			diemDTO.get(i).setDtb_10(dtb_10/bangDiems.size());
			diemDTO.get(i).setDtb_4((diemDTO.get(i).getDtb_10()*4)/10);
			diemDTO.get(i).setDsBangDiems(bangDiems);	
			dtl_10 = dtl_10 + diemDTO.get(i).getDtb_10();
			dtl_4 = dtl_4 + diemDTO.get(i).getDtb_4();
		}
		
		DiemTichLuyDTO tichLuyDTO = new DiemTichLuyDTO();
		tichLuyDTO.setDtl_10(dtl_10/diemDTO.size());
		tichLuyDTO.setDtl_4(dtl_4/diemDTO.size());
		
	
		
	
		
		model.addAttribute("dsbd", diemDTO);
		model.addAttribute("tichluy", tichLuyDTO);
		return "ketqua";

	}

}
