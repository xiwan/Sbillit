package dao;

import java.util.List;

import mapper.SbillitSellerMapper;

import org.springframework.beans.factory.annotation.Autowired;

import entity.SbillitSeller;

public class SbillitSellerDao {
	@Autowired
	private SbillitSellerMapper sbillitSellerMapper;
	
	public SbillitSeller findSellerById(Long sellerId) {
		return sbillitSellerMapper.findSellerById(sellerId);
	}
}
