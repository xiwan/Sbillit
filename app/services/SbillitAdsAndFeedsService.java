package services;

import java.util.List;

import entity.SbillitAds;
import entity.SbillitFeed;

public interface SbillitAdsAndFeedsService {
	
	public List<SbillitAds> findValidAds();
	
	public List<SbillitFeed> findUserFeeds(Long userId);

}
