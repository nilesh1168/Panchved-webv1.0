package com.panchaved.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import org.springframework.stereotype.Component;



public class DoctorQuery {

	static Statement stm;
	static PreparedStatement pstm;
	private static Connection con = DbConnect.Connect();
	static ResultSet rs;

	
	public static ResultSet selectQueryDoctor(String offset) {
		try {
			String sql ="select * from doctor limit "+offset+",20";
			pstm = con.prepareStatement(sql);
			return pstm.executeQuery();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static PreparedStatement insertQueryDoctor() {
		try {
			String sql ="insert into doctor values(?,?,?,?,?,?)";
			pstm = con.prepareStatement(sql);
			
		}catch (SQLException e) {
		
			e.printStackTrace();
		}
		return pstm;
	}
	
	public static PreparedStatement updateQueryDoctor() {
		try {
			String sql ="UPDATE doctor SET  doctorName = ?, doctorDOB = ?, doctorQualification = ?, doctorAddress = ?, doctorCity = ? "
					+ "WHERE (doctorId = ?)";
			pstm = con.prepareStatement(sql);
			
		}catch (SQLException e) {
		
			e.printStackTrace();
		}
		return pstm;
	}


	public static ResultSet selectWQueryDoctor(long id) {
		try {
			System.out.println("id :"+id);
			String sql ="select * from doctor where doctorID=?";
			
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, id);
			return pstm.executeQuery();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
