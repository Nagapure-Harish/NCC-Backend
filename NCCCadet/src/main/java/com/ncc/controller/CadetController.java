package com.ncc.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ncc.modal.Cadet;
import com.ncc.service.CadetService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@RestController
@CrossOrigin(value ="http://localhost:4200/" )
@RequestMapping("/ncc")
public class CadetController {
  @Autowired
  private CadetService cadetservice;
  @GetMapping("/get")
  public List<Cadet> getAll() {
      return cadetservice.getAllCadet();
  }

  @GetMapping("/get/{mobile}")
  public Optional<Cadet> getUserById(@PathVariable Long mobile) {
      return cadetservice.getCadetByMobile(mobile);
  }
  
  @Transactional
  @PutMapping("/update")
  public String updateCadet(@RequestBody Cadet cadet) {
  	return cadetservice.updateCadet(cadet);
  }
  
  @PostMapping("/create")
  public String saveCadet(@RequestBody Cadet cadet) {
      return cadetservice.createCadet(cadet);
  }
   
  @Transactional
  @DeleteMapping("/delete/{mobile}")
  public String deleteUser(@PathVariable Long cadet) {
//  	System.out.println(cadet);
  	return cadetservice.deleteCadet(cadet);
  }	
  
  @Transactional
  @GetMapping("/alldelete")
  public List<Cadet> alldeletedCadet() {
  	return cadetservice.getAllDeletedUser();
  }
  
  @Transactional
  @GetMapping("/notdelete")
  public List<Cadet> notAllDeltedUser() {
  	return cadetservice.getAllNotDeletedUser();
  }

  @GetMapping("/excel")
  public void generateExcelReport(HttpServletResponse response) throws Exception {
	  response.setContentType("application/octet-stream");
	  String headerkey = "Content-Disposition";
	  String headerValue = "attachment;Filename=Nominal Roll.xls";
	  
	  response.setHeader(headerkey, headerValue);
	  
	  cadetservice.generateExcel(response);
  }
  
  @PostMapping("/updateFromExcel/{mobile}")
  public ResponseEntity<String> updateFromExcel(@RequestParam("filePath") String filePath) {
      try {
          cadetservice.updateDatabaseFromExcel(filePath);
          return ResponseEntity.ok("Database updated successfully from Excel.");
      } catch (IOException e) {
          e.printStackTrace();
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating database from Excel.");
      }
  }
}
