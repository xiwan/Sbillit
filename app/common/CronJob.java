package common;

import java.util.List;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;

import play.Logger;
import services.SbillitOrderService;

public class CronJob extends TimerTask {
	@Autowired
	private SbillitOrderService sbillitOrderService;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		List<Long> closeIds = sbillitOrderService.closeExpiredOrder();
		Logger.info(closeIds.toString());
	}

}
