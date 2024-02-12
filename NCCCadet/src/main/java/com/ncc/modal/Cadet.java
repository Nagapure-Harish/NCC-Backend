package com.ncc.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Cadet {
	@Id 
  private String srNo;
  private String regNo;
  private String name;
//  private String fatherNameAndAddress;
 private String fatherName;
private String address;
  private String rank;
  private String adhar;
  private String accountNo;
  private String bankName;
  private String dateOfBirth;
  private String className;
  private String yearOfNcc;
  private String ifcCode;
  private Long   mobile;
  private String remark;
  private int    deleteStatus;
  
public int getDeleteStatus() {
	return deleteStatus;
}
public void setDeleteStatus(int deleteStatus) {
	this.deleteStatus = deleteStatus;
}
public String getSrNo() {
	return srNo;
}
public void setSrNo(String srNo) {
	this.srNo = srNo;
}
public String getName() {
	return name;
}
public String getRegNo() {
	return regNo;
}
public void setRegNo(String regNo) {
	this.regNo = regNo;
}
public void setName(String name) {
	this.name = name;
}
public String getFatherName() {
	return fatherName;
}
public void setFatherName(String fatherName) {
	this.fatherName = fatherName;
}
public String getRank() {
	return rank;
}
public void setRank(String rank) {
	this.rank = rank;
}
public String getAdhar() {
	return adhar;
}
public void setAdhar(String adhar) {
	this.adhar = adhar;
}
public String getAccountNo() {
	return accountNo;
}
public void setAccountNo(String accountNo) {
	this.accountNo = accountNo;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}

public String getBankName() {
	return bankName;
}
public void setBankName(String bankName) {
	this.bankName = bankName;
}
//public String getFatherNameAndAddress() {
//	return fatherNameAndAddress;
//}
//public void setFatherNameAndAddress(String fatherNameAndAddress) {
//	this.fatherNameAndAddress = fatherNameAndAddress;
//}

public String getDateOfBirth() {
	return dateOfBirth;
}
public void setDateOfBirth(String dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
}
public String getClassName() {
	return className;
}
public void setClassName(String class1) {
	className = class1;
}

public String getYearOfNcc() {
	return yearOfNcc;
}
public void setYearOfNcc(String yearOfNcc) {
	this.yearOfNcc = yearOfNcc;
}
public String getIfcCode() {
	return ifcCode;
}
public void setIfcCode(String ifcCode) {
	this.ifcCode = ifcCode;
}
public Long getMobile() {
	return mobile;
}
public void setMobile(Long mobile) {
	this.mobile = mobile;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
  
  
}
