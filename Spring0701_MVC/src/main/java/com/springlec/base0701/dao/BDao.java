package com.springlec.base0701.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.springlec.base0701.dto.BDto;
import com.springlec.base0701.util.Constant;

public class BDao {

	JdbcTemplate template;
	
	// Constructor
	public BDao() {
		this.template = Constant.template; // 이렇게 사용하기 위해 util package 를 만든 것임.
	}
	
	
	//////////////////////////////////////////////////////////
	//     LiST METHOD								        //
    //////////////////////////////////////////////////////////
	
	public ArrayList<BDto> list(){
		System.out.println(" * * Dao : list() * * ");
	
		// 기존까지 쓰던 방식 이것으로 끝남!
		// 여기서 SELECT * FROM 으로 써도 되지만 
		
		String query = "SELECT bId, bName, bTitle, bContent, bDate FROM mvc_board";
		
		// 여러개를 가져올때는 query로 쓴다.
		return (ArrayList<BDto>) template.query(query, new BeanPropertyRowMapper<BDto>(BDto.class)); // BDto 형식으로 사용할 것인데.. 그건 BDto.class 에 있는애야 
		
	} // METHOD : list
	
	

	//////////////////////////////////////////////////////////
	//     WRITE METHOD						  		        //
    //////////////////////////////////////////////////////////
	
	public void write(final String bName, final String bTitle, final String bContent) {
		// 여기서 final 쓰는 이유는 정보를 받아서 개발자가 임의로 변경할 수도 있기때문에 final 을 필수로 넣어버림.
		
		this.template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				
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
	//     Content View : For Update, delete			    //
    //////////////////////////////////////////////////////////
	
	public BDto content(int strbId){
		String query = "SELECT bId, bName, bTitle, bContent, bDate FROM mvc_board WHERE bId = " + strbId;
		
		// 한줄만 가져오는 건 queryForObject!!
		return template.queryForObject(query, new BeanPropertyRowMapper<BDto>(BDto.class));
	} // content 
	
	

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
	
	
	//////////////////////////////////////////////////////////
	//     UPDATE METHOD								    //
    //////////////////////////////////////////////////////////
	
	public void update(final int bId, final String bName, final String bTitle, final String bContent) {
		
		// Query
		String query = "UPDATE mvc_board SET ";
		String query2 = "bName = ?, bTitle =?, bContent = ? WHERE bId = ?";
		
		this.template.update(query+query2, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setString(1, bName);
				ps.setString(2, bTitle);
				ps.setString(3, bContent);
				ps.setInt(4, bId);
				
			}
		});		
	}

	
} // End of BDao * * 
