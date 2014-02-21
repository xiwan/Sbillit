package controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import play.mvc.Result;
import play.mvc.With;
import services.SbillitComboService;
import utils.Constant;
import utils.JsonUtil;

import com.fasterxml.jackson.databind.JsonNode;

public class ModuleCombo extends Filter {
	
	@Autowired
	private SbillitComboService sbillitComboService;
	
	@With(Interceptor.class)
	public Result combo(Long comboId) {
		JsonNode js = null;
		Map returnMap = sbillitComboService.findComboAndSeller(comboId);
		if (returnMap == null){
			js = JsonUtil.toJson(Constant.ERROR_FREE, "");
		}else {
			js = JsonUtil.toJson(Constant.ERROR_FREE, returnMap);
		}
		return ok(js);
	}
}
