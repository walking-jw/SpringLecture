package com.springlec.s0705.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.springlec.s0705.dto.Board_Dto;
import com.springlec.s0705.util.Constant;

public class Board_Dao {

	////////////////////////
	//  Template Setting  //
	////////////////////////
	JdbcTemplate template;
	
	////////////////////////
	//  Constructor       //
	////////////////////////
	public Board_Dao() {
		this.template = Constant.template; // com.springlec.s0705.util
	}
	
	//////////////////////////////////////////////////////////
	//     LiST METHOD								        //
    //////////////////////////////////////////////////////////
	
	public ArrayList<Board_Dto> list(){
		System.out.println(" * * Dao : list() * * ");
	
		// Query  :  SELECT * FROM 으로 쓸 경우는, Bean과의 순서만 맞춰주면 된다.
		String query = "SELECT bId, bName, bTitle, bContent, bDate FROM mvc_board";
		
		// 한Row가 아닌 다수일 경우,template.query 사용
		return (ArrayList<Board_Dto>) template.query(query, new BeanPropertyRowMapper<Board_Dto>(Board_Dto.class)); // BDto 형식으로 사용할 것인데.. 그건 BDto.class 에 있는애야 
		
	} // list()
	

	//////////////////////////////////////////////////////////
	//     WRITE METHOD						  		        //
    //////////////////////////////////////////////////////////
	
	public void write(final String bName, final String bTitle, final String bContent) {
	// final 사용 이유 : (보안상의 이유) 개발자가 임의로 수정할 수 없게 하기 위함.

		System.out.println(" * * Dao : write() * * ");
		
		// setString , setInt 사용시 필요
		this.template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				
				// Query
				String query = "INSERT INTO mvc_board (bName, bTitle, bContent, bDate) values (?,?,?,now())";
				
				PreparedStatement preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, bName);
				preparedStatement.setString(2, bTitle);
				preparedStatement.setString(3, bContent);
				
				return preparedStatement;
			}
		});
	} // write
	
	//////////////////////////////////////////////////////////
	//     DELETE METHOD								    //
    //////////////////////////////////////////////////////////
	
	public void delete(final int bId) {
		
		// Query
		String query = "DELETE FROM mvc_board WHERE bId = ?";
		this.template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setInt(1, bId);
				
			}
		});
	}
	
	
}
