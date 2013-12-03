package mapper;

import java.util.List;

import entity.SbillitOrder;

public interface SbillitOrderMapper {

	public List<SbillitOrder> findAllOrders();
	
	public SbillitOrder findOrderbyId(int id);	
	
}
