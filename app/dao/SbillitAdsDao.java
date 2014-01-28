package dao;

import java.util.List;

import mapper.SbillitAdsMapper;
import mapper.SbillitOrderMapper;

import org.springframework.beans.factory.annotation.Autowired;

import entity.SbillitAds;

public class SbillitAdsDao {
	
	@Autowired
	private SbillitAdsMapper sbillitAdsMapper;
	
	public List<SbillitAds> findValidAds() {
		return sbillitAdsMapper.findValidAds();
	}

}
