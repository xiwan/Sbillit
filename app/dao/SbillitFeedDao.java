package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mapper.SbillitFeedMapper;

import org.springframework.beans.factory.annotation.Autowired;

import entity.SbillitFeed;

public class SbillitFeedDao {
	
	@Autowired
	private SbillitFeedMapper sbillitFeedMapper;
	
	public List<SbillitFeed> findUserFeeds(Long userId) {
		
		Map paraMap = new HashMap<String, Object>();
		paraMap.put("userId", "%"+userId+",%");
		List<SbillitFeed> feedList = sbillitFeedMapper.findUserFeeds(paraMap);
		return feedList;
	}

}
