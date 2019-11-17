package com.panchaved.web;

import java.util.ArrayList;
import java.util.List;
import java.io.Reader;
import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.panchaved.enitity.Doctor;
import com.panchaved.enitity.Patient;
import com.panchaved.service.DoctorService;
import com.panchaved.service.PatientService;
import com.panchaved.util.AppSession;

@Controller
@RequestMapping("/admin")
public class AdminController {


	@Autowired
	PatientService pService;
	@Autowired DoctorService dService;


	AppSession session;

	@RequestMapping(method = RequestMethod.GET)
	public String dashboard() {
		//		System.out.println(s.getAttribute("user"));
		//HttpSession session = req.getSession(false);
		//System.out.println("session var:"+session.getAttribute("user"));
		return "adminDashboard.jsp";
	}

	@RequestMapping(value="/patient",method = RequestMethod.GET)
	public String patient(Model model) {
		showPatients(model, "1");
		return "table.jsp";
	}
	@RequestMapping(value="/ajaxPatient", method = RequestMethod.GET)
	public @ResponseBody JsonArray showPatients(Model model,@RequestParam("page") String p) {
		int page = Integer.parseInt(p);
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(pService.getAllRecords(page), new TypeToken<List<Patient>>() {}.getType());
		JsonArray json = element.getAsJsonArray();
		model.addAttribute("json",json);
		model.addAttribute("patient", pService.getAllRecords(page));
		return json;
	}

	//	@RequestMapping(value="/ajax", method = RequestMethod.GET)
	//	public String ajaxTest(Model model ) {
	//		return "ajaxTest.jsp";
	//		//		model.addAttribute("patient", pService.getAllRecords());
	//	}
	@RequestMapping(value="/patient/new", method = RequestMethod.GET)
	public String showNewPatientForm(Model model, HttpServletRequest req) {
		System.out.println("Get req");
		HttpSession s = req.getSession(false);
		model.addAttribute("user",s.getAttribute("user") );
		model.addAttribute("addpatient",new Patient());
		return "newpatient.jsp";
	}

	@RequestMapping(value="/patient/new", method = RequestMethod.POST)
	public String addPatient(Model model,@RequestParam("patientId") Integer id,@RequestParam("phoneNo") long contact,@RequestParam("remarks") String remarks,@RequestParam("patientName") String name,@RequestParam("dob") Date dob,@RequestParam("bloodgroup") String bloodgrp,@RequestParam("gender") String gender,@RequestParam("address") String address,@RequestParam("district") String district,@RequestParam("state") String state) {
		System.out.println("POST request");
		System.out.println(id+" "+bloodgrp+" "+name+" "+dob+" ");
		if(pService.insertPatient(id, name, gender, contact, bloodgrp, dob, address, district, state, remarks))
			model.addAttribute("success_msg", "New Patient added successfully with ID:"+id);
		else {
			model.addAttribute("success_msg", "Sorry Could Not Add Patient Please Retry");
		}

		return "newpatient.jsp";
	}

	@RequestMapping(value="/patient/update", method = RequestMethod.GET)
	public String showUpdatePatientForm(Model model,@RequestParam("patientId") Integer id) {
		System.out.println("inside update patient method : "+id);
		System.out.println("Done");
		model.addAttribute("patient",pService.getSelectedPatient(id));
		System.out.println(pService.getSelectedPatient(id).getRemarks());
		return "updatePatient.jsp";
	}

	@RequestMapping(value="/patient/update",method = RequestMethod.POST)
	public String updatePatient(Model model,@ModelAttribute("patient") Patient patient) {
		System.out.println("upadating doc!");

		if(pService.updatePatient(patient)) {
			model.addAttribute("success_msg","Patient updated with Id: "+patient.getPatientId());
		}else {
			model.addAttribute("success_msg","Sorry couldnt Update patient! Please Retry");
		}

		return "updatePatient.jsp";
	}

	@RequestMapping(value="/doctor")
	public String doctor(Model model) {
		showDoctors(model, "1"); 
		return "doctorTable.jsp";
	}

	@RequestMapping(value = "/ajaxDoctor", method = RequestMethod.GET)
	public @ResponseBody JsonArray showDoctors(Model model,@RequestParam("page") String p) {
		System.out.println("inside the showDoctors()");
		int page = Integer.parseInt(p);
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(dService.getAllRecords(page), new TypeToken<List<Doctor>>() {}.getType());
		JsonArray json = element.getAsJsonArray();

		model.addAttribute("doctor", dService.getAllRecords(page));
		return json;
	}

	@RequestMapping(value="/doctor/update", method = RequestMethod.GET)
	public String showUpdateForm(Model model,@RequestParam("doctorID") long id) {

		model.addAttribute("doctor",dService.getSelectedDoctor(id));
		model.addAttribute("doc",new Doctor());
		return "updateDoctor.jsp";
	}

	@RequestMapping(value="/doctor/update",method = RequestMethod.POST)
	public String updateDoctor(Model model,@ModelAttribute("doc") Doctor doc) {
		System.out.println("upadating doc!");
		if(dService.updateDoc(doc)) {
			model.addAttribute("success_msg","Doctor updated with Id: "+doc.getDoctorID());
		}else {
			model.addAttribute("success_msg","Sorry couldnt Update doctor! Please Retry");
		}
		return "updateDoctor.jsp";
	}

	@RequestMapping(value="/doctor/new",method = RequestMethod.GET)
	public String showNewDoctorForm(Model model) {
		model.addAttribute("doc",new Doctor());
		return "newDoctor.jsp";
	}

	@RequestMapping(value = "/doctor/new" , method = RequestMethod.POST)
	public String addDoctor(Model model, @ModelAttribute("doc") Doctor doc) {
		System.out.println("adding doc!");
		if(dService.insertDoctor(doc)) {
			model.addAttribute("success_msg", "New Doctor added successfully with ID:"+doc.getDoctorID());
		}else {
			model.addAttribute("success_msg", "Sorry Could Not Add Patient Please Retry ");
		}
		return "newDoctor.jsp";
	}

	@RequestMapping(value = "/bill" , method = RequestMethod.GET)
	public String showBill( Model model ) {
		System.out.println("Showing bill!");

		return "underConstruction.jsp";
	}
	@RequestMapping(value = "/getids" , method = RequestMethod.GET)
	public @ResponseBody ArrayList<Integer> loadIds()
	{	
		ArrayList<Integer> ids = new ArrayList<Integer>();
		System.out.println("INSIDE load IDs");
		try
		{
			ids = pService.getIds();
			for (Integer integer : ids) {
				System.out.println(integer);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	return ids;
	}
}