package dao;

import java.util.List;

import mapper.SbillitMasterMapper;

import org.springframework.beans.factory.annotation.Autowired;

import entity.SbillitConfig;
import entity.SbillitDice;

public class SbillitMasterDao {
	
	@Autowired
	private SbillitMasterMapper  sbillitMasterMapper;
	
	public List<SbillitConfig> getConfig() {				
		return sbillitMasterMapper.getConfig();
	}

	public List<SbillitDice> getDice() {				
		return sbillitMasterMapper.getDice();
	}

}
