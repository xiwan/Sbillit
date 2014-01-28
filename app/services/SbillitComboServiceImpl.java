package services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

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
	public Map findComboAndSeller(Long comboId) {
		// TODO Auto-generated method stub
		SbillitCombo combo = sbillitComboDao.findComboById(comboId);
		SbillitSeller seller = sbillitSellerDao.findSellerById(combo.getSellerId());
		
		Map returnMap = new HashMap<String, Object>();
		returnMap.put("combo", combo);
		returnMap.put("seller", seller);
		
		return returnMap;
	}
	


}
