package mapper;

import java.util.List;

import entity.SbillitConfig;
import entity.SbillitDice;

public interface SbillitMasterMapper {
	
	public List<SbillitConfig> getConfig();
	
	public List<SbillitDice> getDice();

}
