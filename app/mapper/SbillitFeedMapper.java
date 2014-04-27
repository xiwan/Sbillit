package mapper;

import java.util.List;
import java.util.Map;

import entity.SbillitFeed;

public interface SbillitFeedMapper {
	
	public List<SbillitFeed> findUserFeeds (Map map);
	
	public Integer insertUserFeeds(Map map);
	
	public Long selectSeqFeed();
	
	public void updateSeqFeed(Long diff);

}
