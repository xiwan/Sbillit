package services;

import java.util.List;
import java.util.Map;

import entity.SbillitDice;

public interface SbillitMasterService {
	
	public Map getMasterVersion();
	
	public List<SbillitDice> getDice();

}
