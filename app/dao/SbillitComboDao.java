package dao;

import java.util.List;

import mapper.SbillitComboMapper;

import org.springframework.beans.factory.annotation.Autowired;

import entity.SbillitCombo;
import entity.SbillitOrder;

public class SbillitComboDao {
	
	@Autowired
	private SbillitComboMapper sbillitComboMapper;
	
	public SbillitCombo findComboById(Long comboId) {
		return sbillitComboMapper.findComboById(comboId);
	}
	
	public void createCombo(SbillitCombo combo) {
		this.sbillitComboMapper.updateSeq();
		this.sbillitComboMapper.insertCombo(combo);
	}

}
