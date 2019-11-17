package com.panchaved.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.catalina.tribes.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.panchaved.enitity.Doctor;
import com.panchaved.enitity.Patient;
import com.panchaved.util.DoctorQuery;
import com.panchaved.util.PatientQuery;

@Component
public class DoctorService {
	
	public ArrayList<Doctor> doctors;

	public DoctorService() {
		super();
		this.doctors = new ArrayList<Doctor>();
	}
	
	
	public boolean insertDoctor(Doctor doc)
	{
		try {
			PreparedStatement pstm = DoctorQuery.insertQueryDoctor();
			
			pstm.setLong(1, doc.getDoctorID());
			pstm.setString(2, doc.getDoctorName());
			pstm.setDate(3, doc.getDoctorDOB());
			pstm.setString(4, doc.getDoctorQualification());
			pstm.setString(5, doc.getDoctorAddress());
			pstm.setString(6, doc.getDoctorCity());
			
			int count = pstm.executeUpdate();
			if(count!=0) {
				return true; 
			}	
		} catch (SQLException e) {

			e.printStackTrace();
		}  
		return false;
	}
	
	public List getAllRecords(int page) { //Doctor's Service Mettod
		int o = (page-1) * 20;
		System.out.println(Integer.toString(o));
		ResultSet rs = DoctorQuery.selectQueryDoctor(Integer.toString(o));
		doctors.clear();
		System.out.println(Arrays.toString(doctors.toArray()));
		try {
			while(rs.next()) {
				
				long doctorId = rs.getLong(1);
				String doctorName = rs.getString(2);
				java.util.Date doctorDOB = rs.getDate(3);
				String  doctorQualification = rs.getString(4); 
				String doctorAddress =rs.getString(5);
				String doctorCity =rs.getString(6);
				Doctor doctor = new Doctor(doctorId, doctorName, doctorDOB, doctorQualification,
						doctorAddress, doctorCity);
				doctors.add(doctor);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		 
		return doctors;
	}


	public boolean updateDoc(Doctor doc) {
		
		
		try {
			System.out.println("doc"+doc);
			PreparedStatement pstm = DoctorQuery.updateQueryDoctor();
			
			pstm.setString(1, doc.getDoctorName());
			pstm.setDate(2, doc.getDoctorDOB());
			pstm.setString(3, doc.getDoctorQualification());
			pstm.setString(4, doc.getDoctorAddress());
			pstm.setString(5, doc.getDoctorCity());
			pstm.setLong(6, doc.getDoctorID());
			int count = pstm.executeUpdate();
			if(count!=0) {
				return true; 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return false;
		
	}


	public Doctor getSelectedDoctor(long id) {
		// TODO Auto-generated method stub
		ResultSet rs = DoctorQuery.selectWQueryDoctor(id);
		doctors.clear();
		Doctor doctor =null;
		
		try {
			while(rs.next()) {
				
				long doctorID = rs.getLong(1);
				String doctorName = rs.getString(2);
				java.util.Date doctorDOB = rs.getDate(3);
				String  doctorQualification = rs.getString(4); 
				String doctorAddress =rs.getString(5);
				String doctorCity =rs.getString(6);
				doctor = new Doctor(doctorID, doctorName, doctorDOB, doctorQualification,
						doctorAddress, doctorCity);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return doctor;
	}
	
	
}
