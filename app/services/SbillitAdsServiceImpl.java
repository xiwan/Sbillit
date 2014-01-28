package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dao.SbillitAdsDao;

import entity.SbillitAds;

public class SbillitAdsServiceImpl implements SbillitAdsService {
	
	@Autowired
	private SbillitAdsDao sbillitAdsDao;

	@Override
	public List<SbillitAds> findValidAds() {
		// TODO Auto-generated method stub
		return sbillitAdsDao.findValidAds();
	}

}
