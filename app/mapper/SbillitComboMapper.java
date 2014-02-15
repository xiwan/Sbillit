package mapper;

import java.util.List;

import entity.SbillitCombo;

public interface SbillitComboMapper {
	
	public SbillitCombo findComboById(Long comboId);
	
	public void insertCombo(SbillitCombo combo);
	
	public void updateSeq();

}
