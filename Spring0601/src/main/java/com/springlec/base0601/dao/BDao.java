package com.springlec.base0601.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.springlec.base0601.dto.BDto;

public class BDao {

	DataSource dataSource;
	
	// Constructor
	public BDao() {
		try {
			
			// context.xml 과 연결해주기
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/mvc");
			
		}catch(Exception e){
			e.printStackTrace();
			
			
		}
		
	}
	
	
	
	// 초기화면 검색
	public ArrayList<BDto> list(){
		
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		
		try {
			//DB 와 연결
			connection = dataSource.getConnection();
			
			// Query
			String query = "SELECT bId, bName, bTitle, bContent, bDate FROM mvc_board";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			// DATA 가져오기
			while (resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate);
				dtos.add(dto);
			
			}// while
			
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}//finally
		
		return dtos;
	} // METHOD : list
	
	
	// 글 작성 Method
	public void write(String bName, String bTitle, String bContent) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		try {
			//DB 와 연결
			connection = dataSource.getConnection();
			
			// Query
			String query = "INSERT INTO mvc_board (bName, bTitle, bContent, bDate) values (?,?,?,now())";
			
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			
			preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}//finally
		
	}
	
	// 자세히보기
// 초기화면 검색
	public BDto content(int strbId){
		
		BDto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		
		try {
			//DB 와 연결
			connection = dataSource.getConnection();
			
			// Query
			String query = "SELECT bId, bName, bTitle, bContent, bDate FROM mvc_board WHERE bId = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, strbId);
			resultSet = preparedStatement.executeQuery();
			
			// DATA 가져오기
			if (resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				
				dto = new BDto(bId, bName, bTitle, bContent, bDate);
				
			
			}// if
			
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}//finally
		return dto;
	} // METHOD : content

	
	// 삭제하기
	public void delete(int bId) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		try {
			//DB 와 연결
			connection = dataSource.getConnection();
			
			// Query
			String query = "DELETE FROM mvc_board WHERE bId = ?";
			
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, bId);
			
			preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}//finally
		
	}
	
	
	// 수정하기
	public void update(int bId, String bName, String bTitle, String bContent) {
		
	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			//DB 와 연결
			connection = dataSource.getConnection();
			
			// Query
			String query = "UPDATE mvc_board SET ";
			String query2 = "bName = ?, bTitle =?, bContent = ? WHERE bId = ?";
			
			preparedStatement = connection.prepareStatement(query + query2);

			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setInt(4, bId);
			
			preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}//finally
		
	}
	
	
	
	
	
} // Main
