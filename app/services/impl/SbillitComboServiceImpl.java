package services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import services.SbillitComboService;

import dao.SbillitComboDao;
import dao.SbillitSellerDao;

import entity.SbillitCombo;
import entity.SbillitSeller;

public class SbillitComboServiceImpl implements SbillitComboService {
	@Autowired
	private SbillitComboDao sbillitComboDao;
	@Autowired
	private SbillitSellerDao sbillitSellerDao;
	
	@Override
	public Map<String, Object> findComboAndSeller(Long comboId) {
		// TODO Auto-generated method stub
		SbillitCombo combo = sbillitComboDao.findComboById(comboId);
		SbillitSeller seller = sbillitSellerDao.findSellerById(combo.getSellerId());
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("combo", combo.genComboArray());
		returnMap.put("seller", seller);
		return returnMap;
	}
	


}
