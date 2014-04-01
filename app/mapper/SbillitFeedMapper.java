package mapper;

import java.util.List;
import java.util.Map;

import entity.SbillitFeed;

public interface SbillitFeedMapper {
	
	public List<SbillitFeed> findUserFeeds (Map map);

}
