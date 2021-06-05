package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BangDiem;
import com.example.demo.model.SinhVien;
import com.example.demo.service.BangDiemService;

@RestController
public class BangDiemAPI {
	@Autowired
	private BangDiemService bangdiemService;
	@RequestMapping(value = "/dsbangdiem/{id}",method = RequestMethod.GET)
	public List<BangDiem> getAllBangDiembyIDSV(@PathVariable("id")Long sv) {
		return bangdiemService.getAllBangDiembyIDSV(sv);
	}
	@RequestMapping(value = "/bangdiem/add",method = RequestMethod.POST)
	public boolean addBangDiem(@RequestBody BangDiem b) {
		return bangdiemService.addBangDiem(b);
	}
	@RequestMapping(value = "/bangdiem/edit",method = RequestMethod.PUT)
	public boolean  editBangDiem(@RequestBody BangDiem bd) {
		return bangdiemService.editBangDiem(bd);
	}
	@RequestMapping(value = "/dsbangdiem/getByidSVandidLHP/{idSV}/{idLHP}")
	public BangDiem getBangDiemSVBySVIDandMH(@PathVariable("idSV")Long idSV, @PathVariable("idLHP")Long idLHP) {
		// TODO Auto-generated method stub
		return bangdiemService.getBangDiemSVBySVIDandMH(idSV, idLHP);
	}
	@RequestMapping(value = "/dsbangdiem/getAllByidLHP/{id}")
	public List<BangDiem> getAllBangDiemByLHPID(@PathVariable("id")Long id) {
		// TODO Auto-generated method stub
		return bangdiemService.getAllBangDiemByLHPID(id);
	}
	@RequestMapping(value = "/dsbangdiem/getByIDDKHP/{id}")
	public BangDiem getBangDiemByIDDKHP(@PathVariable("id") Long id) {
		// TODO Auto-generated method stub
		return bangdiemService.getBangDiemByIDDKHP(id);
	}
	@RequestMapping(value = "bangdiem/delete/{idLHP}/{idSV}/{idNK}")
	public boolean deleteBangDiemwhereLHPandSV(@PathVariable("idLHP") Long idLHP,@PathVariable("idSV") Long idSV,@PathVariable("idNK") Long idNK) {
		// TODO Auto-generated method stub
		return bangdiemService.deleteBangDiemwhereLHPandSV(idLHP, idSV, idNK);
	}
}
