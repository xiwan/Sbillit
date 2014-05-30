package services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dao.SbillitMasterDao;
import entity.SbillitConfig;
import entity.SbillitDice;
import services.SbillitMasterService;

public class SbillitMasterServiceImpl implements SbillitMasterService {
	
	@Autowired
	private SbillitMasterDao sbillitMasterDao;
	
	private static String KEY_VERSION = "version";
	private static String KEY_VERSION_UPADTE_LINK= "VersionUpdateLink";
	private static String KEY_DONATE_LINK = "DonateLink";

	@Override
	public String[] getMasterVersion() {
		// TODO Auto-generated method stub
		//Map<String, Object> masterData = new HashMap<String, Object>();
		String version = "0";
		String versionUpdateLink = "";
		String DonateLink = "";
		
		List<SbillitConfig> configs = new ArrayList<SbillitConfig> ();
		configs = sbillitMasterDao.getConfig();
		for (SbillitConfig conf: configs) {
			if (conf.getKey().equalsIgnoreCase(KEY_VERSION)){
				version = conf.getValue();
			}
			if (conf.getKey().equalsIgnoreCase(KEY_VERSION_UPADTE_LINK)){
				versionUpdateLink = conf.getValue();
			}
			if (conf.getKey().equalsIgnoreCase(KEY_DONATE_LINK)){
				DonateLink = conf.getValue();
			}
		}
		String[] verArray = new String[3];
		verArray[0] = version;
		verArray[1] = versionUpdateLink;
		verArray[2] = DonateLink;
		return verArray;
	}
	
	@Override
	public List<SbillitDice> getDice() {
		List<SbillitDice> dinners = sbillitMasterDao.getDice();
		return dinners;
	}

}
