package ph.com.metrobank.fin.inst.model;

public class FinancialInstitutionRequest {

	private String name;
	private String type;
	private String brstn;
	private String swiftCode;
	private String address;
	private String bancnetCode;
	private String pchcCode;
	private int transferEnabled;
	private int enrollmentEnabled;
	private String createdBy;
	private String updatedBy;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrstn() {
		return brstn;
	}

	public void setBrstn(String brstn) {
		this.brstn = brstn;
	}

	public String getSwiftCode() {
		return swiftCode;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBancnetCode() {
		return bancnetCode;
	}

	public void setBancnetCode(String bancnetCode) {
		this.bancnetCode = bancnetCode;
	}

	public String getPchcCode() {
		return pchcCode;
	}

	public void setPchcCode(String pchcCode) {
		this.pchcCode = pchcCode;
	}

	public int getTransferEnabled() {
		return transferEnabled;
	}

	public void setTransferEnabled(int transferEnabled) {
		this.transferEnabled = transferEnabled;
	}

	public int getEnrollmentEnabled() {
		return enrollmentEnabled;
	}

	public void setEnrollmentEnabled(int enrollmentEnabled) {
		this.enrollmentEnabled = enrollmentEnabled;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
}
