package com.ncc.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncc.dao.CadetDao;
import com.ncc.modal.Cadet;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
@Service
public class CadetService {
   @Autowired
   private CadetDao cadetdao;
   

	public List<Cadet> getAllCadet() {
		// TODO Auto-generated method stub
		return cadetdao.findAll();
	}


	public Optional<Cadet> getCadetByMobile(Long mobile) {
		// TODO Auto-generated method stub
		return cadetdao.findByMobile(mobile);
	
	}


	public String updateCadet( Cadet cadet) {
		// TODO Auto-generated method stub
		Cadet c = cadetdao.findByMobile(cadet.getMobile()).get();
		if(c!=null) {
			
		
		c.setAccountNo(cadet.getAccountNo());
		c.setAddress(cadet.getAddress());
		c.setAdhar(cadet.getAdhar());
		c.setBankName(cadet.getBankName());
		c.setClassName(cadet.getClassName());
		c.setDateOfBirth(cadet.getDateOfBirth());
		c.setFatherName(cadet.getFatherName());
		c.setIfcCode(cadet.getIfcCode());
//		c.setMobile(cadet.getMobile());
//		c.setMotherName(cadet.getMotherName());
		c.setName(cadet.getName());
		c.setRank(cadet.getRank());
		c.setRegNo(cadet.getRegNo());
		c.setRemark(cadet.getRemark());
		c.setSrNo(cadet.getSrNo());
//		c.setFatherNameAndAddress(cadet.getFatherNameAndAddress());
//		c.setYearOfNcc(cadet.getYearOfNcc());
		c.setDeleteStatus(cadet.getDeleteStatus());
		c.setYearOfNcc(cadet.getYearOfNcc());
		cadetdao.save(c);
		return "Cadet Updated Successfully...!";
		}
		return "Cadet NOt Found";
	}


	public String createCadet(Cadet cadet) {
		// TODO Auto-generated method stub
		Cadet c = new Cadet();
		c.setAccountNo(cadet.getAccountNo());
		c.setAddress(cadet.getAddress());
		c.setAdhar(cadet.getAdhar());
		c.setBankName(cadet.getBankName());
		c.setClassName(cadet.getClassName());
		c.setDateOfBirth(cadet.getDateOfBirth());
		c.setFatherName(cadet.getFatherName());
		c.setIfcCode(cadet.getIfcCode());
		c.setMobile(cadet.getMobile());
//		c.setMotherName(cadet.getMotherName());
//		c.setFatherNameAndAddress(cadet.getFatherNameAndAddress());
		c.setName(cadet.getName());
		c.setRank(cadet.getRank());
		c.setRegNo(cadet.getRegNo());
		c.setRemark(cadet.getRemark());
		c.setSrNo(cadet.getSrNo());
//		c.setYearOfNcc(cadet.getYearOfNcc());
		c.setDeleteStatus(cadet.getDeleteStatus());
		c.setYearOfNcc(cadet.getYearOfNcc());
		cadetdao.save(c);
		return "Cadet Added Successfully...!";	}


	public String deleteCadet(Long mobile) {
		// TODO Auto-generated methd stub
		Optional<Cadet> cadet = cadetdao.findByMobile(mobile);
		if (cadet.isPresent()) {	
			Cadet c = cadet.get();
			c.setDeleteStatus(1);
			cadetdao.save(c);
		
		}
		return "Cadet Deleted Sucessfully..!";
		
	}
	public String deleteUser(Long mobile) {
		Optional<Cadet> cadet = cadetdao.findByMobile(mobile);
		if (cadet.isPresent()) {	
			Cadet c = cadet.get();
			c.setDeleteStatus(1);
			cadetdao.save(c);
		
		}
		return "Cadet Deleted Sucessfully..!";
	}

	public List<Cadet> getAllNotDeletedUser() {
		List<Cadet> Cadet1 = new ArrayList<>();
		List<Cadet> cadet = cadetdao.findByDeleteStatusNot(1);
//		listofuser.stream().forEach(x->System.out.println(x.getFirstName()));
//		if (listofuser.size() !=0) {
//			for (cadet user : listofuser) {
//				cadet emp = new cadet();
//				BeanUtils.copyProperties(user, emp);
//				listofuser1.add(emp);
//				System.out.print("Reached....!");
//			}
//		}else {
//			System.out.println("Else block..........!");
//			return null; 
//		}
//		System.out.println(listofuser1);
		return cadet;
	}

	public List<Cadet> getAllDeletedUser() {
		List<Cadet> Cadet = new ArrayList<>();
		List<Cadet> listofuser = cadetdao.findByDeleteStatus(1);
		if (listofuser.size() != 0) {
			for (Cadet cadet : listofuser) {
				Cadet c = new Cadet();
				BeanUtils.copyProperties(cadet, c);
				Cadet.add(c);
//				System.out.println("Reached.......!");
			}
		} else {
			System.out.println("Else block..........!");
			return null  ;
		}
//		System.out.println(Cadet);
		return Cadet;
	}

	  public void generateExcel(HttpServletResponse response) throws Exception {
		  
		 List<Cadet> c = cadetdao.findAll();
		 
		 HSSFWorkbook workbook =new HSSFWorkbook();	  
		 HSSFSheet sheet = workbook.createSheet("cadets Info");
		 HSSFRow row = sheet.createRow(0);
		 
		 row.createCell(0).setCellValue("Sr.No");
		 row.createCell(1).setCellValue("Reg.No");
		 row.createCell(2).setCellValue("Rank");
		 row.createCell(3).setCellValue("Name Of The Cadet");
		 row.createCell(4).setCellValue("Father Name");
		 row.createCell(5).setCellValue("Address");
		 row.createCell(6).setCellValue("Class");
		 row.createCell(7).setCellValue("Date Of Birth");
		 row.createCell(8).setCellValue("Year If The NCC");
		 row.createCell(9).setCellValue("Bank Name");
		 row.createCell(10).setCellValue("Bank A/C No");
		 row.createCell(11).setCellValue("IFSC Code");
		 row.createCell(12).setCellValue("Mobile No");
		 row.createCell(13).setCellValue("Aadhar Card No");
		 row.createCell(14).setCellValue("Remark");	
		 
		 int dataRowIndex =1;
		 for(Cadet cadet : c) {
			 HSSFRow dataRow = sheet.createRow(dataRowIndex);
			 dataRow.createCell(0).setCellValue(cadet.getSrNo());
			 dataRow.createCell(1).setCellValue(cadet.getRegNo());
			 dataRow.createCell(2).setCellValue(cadet.getRank());
			 dataRow.createCell(3).setCellValue(cadet.getName());;
			 dataRow.createCell(4).setCellValue(cadet.getFatherName());
			 dataRow.createCell(5).setCellValue(cadet.getAddress());
	         dataRow.createCell(6).setCellValue(cadet.getClassName());;
	         dataRow.createCell(7).setCellValue(cadet.getDateOfBirth());
	         dataRow.createCell(8).setCellValue(cadet.getYearOfNcc());
	         dataRow.createCell(9).setCellValue(cadet.getBankName());
	         dataRow.createCell(10).setCellValue(cadet.getAccountNo());
	         dataRow.createCell(11).setCellValue(cadet.getIfcCode());
	         dataRow.createCell(12).setCellValue(cadet.getMobile());
	         dataRow.createCell(13).setCellValue(cadet.getAdhar());
	         dataRow.createCell(14).setCellValue(cadet.getRemark());
	         dataRowIndex ++;
		 }
		 
		 ServletOutputStream ops = response.getOutputStream();
		 workbook.write(ops);
		 workbook.close();
		 ops.close();
	    }

	  @Transactional
	    public void updateDatabaseFromExcel(String filePath) throws IOException {
	        FileInputStream file = new FileInputStream(filePath);
	        Workbook workbook = new XSSFWorkbook(file);
	        org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);

	        Iterator<Row> rowIterator = sheet.iterator();
	        while (rowIterator.hasNext()) {
	            Row row = rowIterator.next();
//	            Long id = (long) row.getCell(0).getNumericCellValue();
	            String srno = row.getCell(0).getStringCellValue();
	            String regno = row.getCell(1).getStringCellValue();
	            String rank = row.getCell(2).getStringCellValue();
	            String name = row.getCell(3).getStringCellValue();
	            String father = row.getCell(4).getStringCellValue();
	            String address = row.getCell(5).getStringCellValue();
	            String class1 = row.getCell(6).getStringCellValue();
	            String dob = row.getCell(7).getStringCellValue();
	            String yon = row.getCell(8).getStringCellValue();
	            String bank = row.getCell(9).getStringCellValue();
	            String ac = row.getCell(10).getStringCellValue();
	            String ifsc = row.getCell(11).getStringCellValue();
	            Long mobile = (long) row.getCell(12).getNumericCellValue();
	            String adhar = row.getCell(13).getStringCellValue();
	            String remark = row.getCell(14).getStringCellValue();
	           

	            Cadet user = cadetdao.findByMobile(mobile).orElseThrow(() -> new IllegalArgumentException("Cadet not found"));
	            user.setSrNo(srno);
	            user.setRegNo(regno);
	            user.setRank(rank);
	            user.setName(name);
	            user.setName(father);
	            user.setAddress(address);
	            user.setClassName(class1);
	            user.setDateOfBirth(dob);
	            user.setYearOfNcc(yon);
	            user.setBankName(bank);
	            user.setAccountNo(ac);
	            user.setIfcCode(ifsc);
	            user.setMobile(mobile);
	            user.setAdhar(adhar);
	            user.setRemark(remark);
	            
	            cadetdao.save(user);
	        }
	        workbook.close();
	    }
}
