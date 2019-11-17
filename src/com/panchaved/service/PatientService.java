package com.panchaved.service;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.panchaved.enitity.Doctor;
import com.panchaved.enitity.Patient;
import com.panchaved.util.CaseTaking;
import com.panchaved.util.DbConnect;
import com.panchaved.util.DoctorQuery;
import com.panchaved.util.PatientQuery;


public class PatientService {
	

	public ArrayList<Patient> patients;
	public PatientService() {

		this.patients = new ArrayList<Patient>();
	}
	
	public void registerPatient() {
		Connection con = DbConnect.Connect();
		try {
			Statement stmt = con.createStatement();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	public boolean insertPatient(Integer id, String patname, String gender, long contact, String bloodgrp,Date dob,String address,String district,String state,String remarks)
	{
		try {
			
			String sql ="insert into patient values(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstm = PatientQuery.insertQueryPatient();
			pstm.setInt(1, id);
			pstm.setString(2, patname);
			pstm.setString(3, gender);
			pstm.setLong(4, contact);
			pstm.setString(5, bloodgrp);
			pstm.setDate(6, (java.sql.Date) dob);
			pstm.setString(7, address);
			pstm.setString(8, district);
			pstm.setString(9, state);
			pstm.setString(10, remarks);
			pstm.setString(11, "");
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
	
	public ArrayList<Patient> getAllRecords(int page) {
		int o = (page-1) * 20;
		System.out.println(Integer.toString(o));
		ResultSet rs = PatientQuery.selectQueryPatient(Integer.toString(o));
		patients.clear();
		try {
			while(rs.next()) {
				int patientId = rs.getInt(1);
				String patientName = rs.getString("patientName");
				String gender = rs.getString(3);
				long phoneNo = rs.getLong(4);
				String bloodGroup = rs.getString(5); 
				java.util.Date dob = rs.getDate(6);
				String address =rs.getString(7);
				String district =rs.getString(8);
				String state = rs.getString(9);
				Patient patient = new Patient(patientId, patientName, gender, phoneNo, bloodGroup, (java.sql.Date) dob, address, district, state);
				patients.add(patient);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return (ArrayList<Patient>) patients;
	}

	public Patient getSelectedPatient(Integer id) {
		ResultSet rs = PatientQuery.selectWQueryPatient(id);
		patients.clear();
		Patient patient = null;
		
		try {
			while(rs.next()) {
				int patientId = rs.getInt(1);
				String patientName = rs.getString("patientName");
				String gender = rs.getString(3);
				long phoneNo = rs.getLong(4);
				String bloodGroup = rs.getString(5); 
				java.util.Date dob = rs.getDate(6);
				String address =rs.getString(7);
				String district =rs.getString(8);
				String state = rs.getString(9);
				String remarks = rs.getString(10);
				patient = new Patient(patientId, patientName, gender, phoneNo, bloodGroup, (java.sql.Date) dob, address, district, state,remarks);
				patients.add(patient);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return patient;
	}
	
	public boolean updatePatient(Patient pat) {
		
		
		try {
			
			PreparedStatement pstm = PatientQuery.updateQueryPatient();
			
			pstm.setString(1, pat.getPatientName());
			pstm.setString(2, pat.getGender());
			pstm.setLong(3, pat.getPhoneNo());
			pstm.setString(4, pat.getBloodGroup());
			pstm.setDate(5, pat.getDob());
			pstm.setString(6, pat.getAddress());
			pstm.setString(7, pat.getDistrict());
			pstm.setString(8, pat.getState());
			pstm.setString(9, pat.getRemarks());
			pstm.setInt(10, pat.getPatientId());
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

	public void saveCasetaking(Patient pat,CaseTaking casetake) {
		Date dateobj = new Date();
		casetake.setDate(dateobj);
		String fname = casetake.getPatientID()+"_"+pat.getPatientName();
		File yourFile = new File("/media/nilesh/Misc/Pancha-Karma/"+fname+"/"+fname+".txt");
		try {
		if(!yourFile.exists()){
			System.out.println("Creating new FIle!!!");
			  yourFile.createNewFile();
			  ObjectOutputStream os1 = new ObjectOutputStream(new FileOutputStream(yourFile,true));
			  os1.writeObject(casetake);
			  os1.close();
		}else{
			System.out.println("OPening exixsting file");
			ObjectOutputStream os2 = new ObjectOutputStream(new FileOutputStream("/media/nilesh/Misc/Pancha-Karma/"+fname+"/"+fname+".txt", true)) {
				protected void writeStreamHeader() throws IOException {
	                reset();
	            }
			};
			os2.writeObject(casetake);
	        os2.close();					
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
//		CaseTaking c;
//		String fname = casetake.getPatientID()+"_"+pat.getPatientName();
//		ObjectOutputStream os1;
//		
//			//os1 = new ObjectOutputStream(new FileOutputStream("/media/nilesh/Misc/Pancha-Karma/"+fname+"/"+fname+".txt"));
//			//os1.close();
//			
//			ObjectOutputStream os2 = new ObjectOutputStream(new FileOutputStream("/media/nilesh/Misc/Pancha-Karma/"+fname+"/"+fname+".txt", true)) {
//	            protected void writeStreamHeader() throws IOException {
//	                reset();
//	            }
//	        };
//			
//	        os2.writeObject(casetake);
//	        os2.close();
//	        
//	        ObjectInputStream is = new ObjectInputStream(new FileInputStream("/media/nilesh/Misc/Pancha-Karma/"+fname+"/"+fname+".txt"));
//	        System.out.println(is.readObject());
//	        System.out.println(is.readObject());
//	        
//		
//		
//		

	}
	
	
	public ArrayList<Integer> getIds(){
	ArrayList<Integer> id = new ArrayList<Integer>();
	try {
		ResultSet rs = PatientQuery.selectIds(); 
		while(rs.next())
			id.add(rs.getInt("patientId"));
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return id;	
	}
	
	public ArrayList<Object> getCaseTaking(Patient p) throws Exception {
		String fname = p.getPatientId()+"_"+p.getPatientName();
	    ArrayList<Object> objects = new ArrayList<Object>(); 
	    FileInputStream fis = new FileInputStream("/media/nilesh/Misc/Pancha-Karma/"+fname+"/"+fname+".txt");
	    ObjectInputStream ois = new ObjectInputStream(fis);

	    Object obj =null;

	    boolean isExist = true;

	    while(isExist){
	        if(fis.available() != 0){
	         obj = ois.readObject();    
	         objects.add(obj);
	        }
	        else{
	        isExist =false;
	        }
	    }
	    System.out.println(objects.size());
	    for (Object object : objects) {
			System.out.println(object);
		} 
	    return objects;     
	}
	
}
