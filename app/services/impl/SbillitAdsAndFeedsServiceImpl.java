package services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import services.SbillitAdsAndFeedsService;
import dao.SbillitAdsDao;
import dao.SbillitFeedDao;
import entity.SbillitAds;
import entity.SbillitFeed;

public class SbillitAdsAndFeedsServiceImpl implements SbillitAdsAndFeedsService {
	
	@Autowired
	private SbillitAdsDao sbillitAdsDao;
	
	@Autowired
	private SbillitFeedDao sbillitFeedDao;

	@Override
	public List<SbillitAds> findValidAds() {
		// TODO Auto-generated method stub
		return sbillitAdsDao.findValidAds();
	}

	@Override
	public List<SbillitFeed> findUserFeeds(Long userId) {
		// TODO Auto-generated method stub
		return sbillitFeedDao.findUserFeeds(userId);
	}

}
