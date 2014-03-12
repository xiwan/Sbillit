package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mapper.SbillitOrderMapper;

import org.springframework.beans.factory.annotation.Autowired;

import common.Constant;


import entity.SbillitOrder;
import entity.SbillitOrderComment;
import entity.SbillitOrderItem;
import entity.SbillitOrderShare;
import entity.SbillitOrderThumbup;

public class SbillitOrderDao {
	
	@Autowired
	private SbillitOrderMapper sbillitOrderMapper;

	public List<SbillitOrder> findAllOrders() {
		return sbillitOrderMapper.findAllOrders();
	}

	public SbillitOrder findOrderbyId(long id) {
		return sbillitOrderMapper.findOrderbyId(id);
	}

	public List<SbillitOrder> findOrderHistoryByUserId(long userId) {
		return sbillitOrderMapper.findOrderHistoryByUserId(userId);
	}

	public void createOrder(SbillitOrder order) {
		this.sbillitOrderMapper.updateSeq();
		this.sbillitOrderMapper.insertOrder(order);
	}

	public void createOrderShare(Long orderId, String phone, Long userId, Integer status){
		SbillitOrderShare orderShare = new SbillitOrderShare();
		orderShare.setOrderId(orderId);
		orderShare.setStatus(status);
		orderShare.setPhone(phone);
		orderShare.setUserId(userId);
		this.sbillitOrderMapper.insertOrderShare(orderShare);
	}

	public void updateOrderShare(Long orderId, String phone, Integer status){
		Map paraMap = new HashMap<String, Object>();
		paraMap.put("orderId", orderId);
		paraMap.put("phone", phone);
		paraMap.put("status", status);
		this.sbillitOrderMapper.updateOrderShare(paraMap);	
	}

	public void updateOrder(Long orderId, Integer status, String picture1, String picture2, String picture3) {
		Map paraMap = new HashMap<String, Object>();
		paraMap.put("orderId", orderId);
		paraMap.put("status", status);
		paraMap.put("picture1", picture1);
		paraMap.put("picture2", picture2);
		paraMap.put("picture3", picture3);
		this.sbillitOrderMapper.updateOrder(paraMap);	
	}
	
	public void createOrderItem(Long orderId, Long userId, Long itemNum, Double itemPrice, String itemName) {
		SbillitOrderItem orderItem = new SbillitOrderItem();
		orderItem.setOrderId(orderId);
		orderItem.setUserId(userId);
		orderItem.setItemNum(itemNum);
		orderItem.setItemPrice(itemPrice);
		orderItem.setItemName(itemName);
		this.sbillitOrderMapper.insertOrderItem(orderItem);	
	}
	
	public void createOrderThumbup(Long orderId, Long userId, String title){
		SbillitOrderThumbup orderThumbup = new SbillitOrderThumbup();
		orderThumbup.setOrderId(orderId);
		orderThumbup.setUserId(userId);
		orderThumbup.setTitle(title);
		this.sbillitOrderMapper.insertOrderThumbup(orderThumbup);
	}
	
	public List<SbillitOrderThumbup> findOrderThumbupByUserId (Long userId) {
		return sbillitOrderMapper.findOrderThumbupByUserId(userId);
	}
	
	public List<SbillitOrder> findOrderInIds(List<Long> ids) {
		Map paraMap = new HashMap<String, Object>();
		paraMap.put("ids", ids);
		return this.sbillitOrderMapper.findOrderInIds(paraMap);
	}
	
	public List<SbillitOrderItem> findOrderItemByOrderId(Long orderId) {
		return this.sbillitOrderMapper.findOrderItemByOrderId(orderId);
	}
	
	
	public long createOrderComment(Long orderId, Long userId, Long atUserId, String message, Long status) {
		SbillitOrderComment orderComment = new SbillitOrderComment();
		orderComment.setOrderId(orderId);
		orderComment.setUserId(userId);
		orderComment.setAtUserId(atUserId);
		orderComment.setMessage(message);
		orderComment.setStatus(status);
		this.sbillitOrderMapper.updateCommentSeq();
		this.sbillitOrderMapper.insertOrderComment(orderComment);
		return orderComment.getId();
	}
	
	public List<SbillitOrderShare> findOrderShareByUserIdAndOrderId (Long userId, Long orderId) {
		Map paraMap = new HashMap<String, Object>();
		paraMap.put("userId", userId);
		paraMap.put("orderId", orderId);
		return this.sbillitOrderMapper.findOrderShareByUserIdAndOrderId(paraMap);
	}
	
	public List<SbillitOrderItem> findOrderItemByUserIdAndOrderId (Long userId, Long orderId) {
		Map paraMap = new HashMap<String, Object>();
		paraMap.put("userId", userId);
		paraMap.put("orderId", orderId);
		return this.sbillitOrderMapper.findOrderItemByUserIdAndOrderId(paraMap);
	}

	public List<SbillitOrderThumbup> findOrderThumbupByUserIdAndOrderId (Long userId, Long orderId) {
		Map paraMap = new HashMap<String, Object>();
		paraMap.put("userId", userId);
		paraMap.put("orderId", orderId);
		return this.sbillitOrderMapper.findOrderThumbupByUserIdAndOrderId(paraMap);
	}

	public List<SbillitOrderComment> findOrderCommentByUserIdAndOrderId (Long userId, Long orderId) {
		Map paraMap = new HashMap<String, Object>();
		paraMap.put("userId", userId);
		paraMap.put("orderId", orderId);
		return this.sbillitOrderMapper.findOrderCommentByUserIdAndOrderId(paraMap);
	}
	

}
