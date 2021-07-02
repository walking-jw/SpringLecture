package com.springlec.base0802.dao;

import java.util.ArrayList;

import com.springlec.base0802.dto.Address_Dto;

public interface Address_Dao {
	
	// Address List
	public ArrayList<Address_Dto> addressListDao();
	
	// Insert
	public void newAddressDao(String name, String telno, String address, String email, String relation);
	
	// ContentView
	public Address_Dto contentViewDao(int seqno);
	
	// Modify
	public void modifyAddressDao(String name, String telno, String address, String email, String relation, int seqno);
	
	// Delete
	public void deleteAddressDao(int seqno);

	// ListQuery
	public ArrayList<Address_Dto> listQueryDao(String query, String content);
}
