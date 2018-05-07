package com.fuzamei.demo.model;

import java.util.Date;

public class TransferWater {

	private Integer id;
	private String platformtoken;
	private String accountid;
	private String TRANDATE;
	private String  TRANTIME;
	private String CRE_TYP;
	private String  CRE_NO;
	private String MESSAGE;
	private String AMT;
	private String AMT1;
	private String FLAG1;
	private String ACCNO2;
	private String ACC_NAME1;
	private String DET;
	private String FLAG2;
	
	private String TRANFLOW;
	private String BFLOW;
	private String DET_NO;
	private String RLTV_ACCNO;
	private String CADBank_Nm;
	
	private Date tran_date;
	private Date create_date;
	private Integer start;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPlatformtoken() {
		return platformtoken;
	}
	public void setPlatformtoken(String platformtoken) {
		this.platformtoken = platformtoken;
	}
	public String getAccountid() {
		return accountid;
	}
	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	public String getTRANDATE() {
		return TRANDATE;
	}
	public void setTRANDATE(String tRANDATE) {
		TRANDATE = tRANDATE;
	}
	public String getTRANTIME() {
		return TRANTIME;
	}
	public void setTRANTIME(String tRANTIME) {
		TRANTIME = tRANTIME;
	}
	public String getCRE_TYP() {
		return CRE_TYP;
	}
	public void setCRE_TYP(String cRE_TYP) {
		CRE_TYP = cRE_TYP;
	}
	public String getCRE_NO() {
		return CRE_NO;
	}
	public void setCRE_NO(String cRE_NO) {
		CRE_NO = cRE_NO;
	}
	public String getMESSAGE() {
		return MESSAGE;
	}
	public void setMESSAGE(String mESSAGE) {
		MESSAGE = mESSAGE;
	}
	public String getAMT() {
		return AMT;
	}
	public void setAMT(String aMT) {
		AMT = aMT;
	}
	public String getAMT1() {
		return AMT1;
	}
	public void setAMT1(String aMT1) {
		AMT1 = aMT1;
	}
	public String getFLAG1() {
		return FLAG1;
	}
	public void setFLAG1(String fLAG1) {
		FLAG1 = fLAG1;
	}
	public String getACCNO2() {
		return ACCNO2;
	}
	public void setACCNO2(String aCCNO2) {
		ACCNO2 = aCCNO2;
	}
	public String getACC_NAME1() {
		return ACC_NAME1;
	}
	public void setACC_NAME1(String aCC_NAME1) {
		ACC_NAME1 = aCC_NAME1;
	}
	public String getFLAG2() {
		return FLAG2;
	}
	public void setFLAG2(String fLAG2) {
		FLAG2 = fLAG2;
	}
	public String getTRANFLOW() {
		return TRANFLOW;
	}
	public void setTRANFLOW(String tRANFLOW) {
		TRANFLOW = tRANFLOW;
	}
	public String getBFLOW() {
		return BFLOW;
	}
	public void setBFLOW(String bFLOW) {
		BFLOW = bFLOW;
	}
	public String getDET_NO() {
		return DET_NO;
	}
	public void setDET_NO(String dET_NO) {
		DET_NO = dET_NO;
	}
	public String getRLTV_ACCNO() {
		return RLTV_ACCNO;
	}
	public void setRLTV_ACCNO(String rLTV_ACCNO) {
		RLTV_ACCNO = rLTV_ACCNO;
	}
	public String getCADBank_Nm() {
		return CADBank_Nm;
	}
	public void setCADBank_Nm(String cADBank_Nm) {
		CADBank_Nm = cADBank_Nm;
	}
	public Date getTran_date() {
		return tran_date;
	}
	public void setTran_date(Date tran_date) {
		this.tran_date = tran_date;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getDET() {
		return DET;
	}
	public void setDET(String dET) {
		DET = dET;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}

	@Override
	public String toString() {
		return "TransferWater{" +
				"id=" + id +
				", platformtoken='" + platformtoken + '\'' +
				", accountid='" + accountid + '\'' +
				", TRANDATE='" + TRANDATE + '\'' +
				", TRANTIME='" + TRANTIME + '\'' +
				", CRE_TYP='" + CRE_TYP + '\'' +
				", CRE_NO='" + CRE_NO + '\'' +
				", MESSAGE='" + MESSAGE + '\'' +
				", AMT='" + AMT + '\'' +
				", AMT1='" + AMT1 + '\'' +
				", FLAG1='" + FLAG1 + '\'' +
				", ACCNO2='" + ACCNO2 + '\'' +
				", ACC_NAME1='" + ACC_NAME1 + '\'' +
				", DET='" + DET + '\'' +
				", FLAG2='" + FLAG2 + '\'' +
				", TRANFLOW='" + TRANFLOW + '\'' +
				", BFLOW='" + BFLOW + '\'' +
				", DET_NO='" + DET_NO + '\'' +
				", RLTV_ACCNO='" + RLTV_ACCNO + '\'' +
				", CADBank_Nm='" + CADBank_Nm + '\'' +
				", tran_date=" + tran_date +
				", create_date=" + create_date +
				", start=" + start +
				'}';
	}
}
