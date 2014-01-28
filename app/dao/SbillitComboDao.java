package dao;

import java.util.List;

import mapper.SbillitComboMapper;

import org.springframework.beans.factory.annotation.Autowired;

import entity.SbillitCombo;

public class SbillitComboDao {
	
	@Autowired
	private SbillitComboMapper sbillitComboMapper;
	
	public SbillitCombo findComboById(Long comboId) {
		return sbillitComboMapper.findComboById(comboId);
	}

}
