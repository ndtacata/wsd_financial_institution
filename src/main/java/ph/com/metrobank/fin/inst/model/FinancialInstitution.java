package ph.com.metrobank.fin.inst.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

@Table (name = "FINANCIAL_INSTITUTION")
@Entity
public class FinancialInstitution {

	@ApiModelProperty(notes = "Incremental ID")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ApiModelProperty(notes = "Financial Institution Name")
	@NotNull
	private String name;
	@ApiModelProperty(notes = "Financial Institution Type")
	@NotNull
	private String type;
	@ApiModelProperty(notes = "BRSTN")
	private String brstn;
	@ApiModelProperty(notes = "Swift Code")
	private String swiftCode;
	@ApiModelProperty(notes = "Address")
	private String address;
	@ApiModelProperty(notes = "Bancnet Code")
	private String bancnetCode;
	@ApiModelProperty(notes = "PCHC Code")
	private String pchcCode;
	@ApiModelProperty(notes = "Transfer Flag")
	@NotNull
	private int transferEnabled;
	@ApiModelProperty(notes = "Enrollment Flag")
	@NotNull
	private int enrollmentEnabled;
	@ApiModelProperty(notes = "Date Created")
	private Date dateCreated;
	@ApiModelProperty(notes = "Date Modified")
	private Date dateModified;
	@ApiModelProperty(notes = "Created By")
	private String createdBy;
	@ApiModelProperty(notes = "Updated By")
	private String updatedBy;
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public String getBrstn() {
		return brstn;
	}
	public String getSwiftCode() {
		return swiftCode;
	}
	public String getAddress() {
		return address;
	}
	public String getBancnetCode() {
		return bancnetCode;
	}
	public String getPchcCode() {
		return pchcCode;
	}
	public int getTransferEnabled() {
		return transferEnabled;
	}
	public int getEnrollmentEnabled() {
		return enrollmentEnabled;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public Date getDateModified() {
		return dateModified;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setBrstn(String brstn) {
		this.brstn = brstn;
	}
	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setBancnetCode(String bancnetCode) {
		this.bancnetCode = bancnetCode;
	}
	public void setPchcCode(String pchcCode) {
		this.pchcCode = pchcCode;
	}
	public void setTransferEnabled(int transferEnabled) {
		this.transferEnabled = transferEnabled;
	}
	public void setEnrollmentEnabled(int enrollmentEnabled) {
		this.enrollmentEnabled = enrollmentEnabled;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
}