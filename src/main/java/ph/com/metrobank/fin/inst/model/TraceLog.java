package ph.com.metrobank.fin.inst.model;

public class TraceLog {

	public static final String LIST_ALL_FINANCIAL_INSTITUTIONS = "LIST_ALL_FINANCIAL_INSTITUTIONS";
	public static final String LIST_ALL_PESONET_FINANCIAL_INSTITUTIONS = "LIST_ALL_PESONET_FINANCIAL_INSTITUTIONS";
	public static final String LIST_ALL_INSTAPAY_FINANCIAL_INSTITUTIONS = "LIST_ALL_INSTAPAY_FINANCIAL_INSTITUTIONS";
	public static final String INQUIRE_BY_PCHC_CODE = "INQUIRE_BY_PCHC_CODE ";
	public static final String INQUIRE_BY_BANCNET_CODE = "INQUIRE_BY_BANCNET_CODE ";
	public static final String INQUIRE_BY_SWIFT_CODE = "INQUIRE_BY_SWIFT_CODE ";
	public static final String ADD_FINANCIAL_INSTITUTION = "ADD_FINANCIAL_INSTITUTION";
	public static final String UPDATE_FINANCIAL_INSTITUTION_DETAILS = "UPDATE_FINANCIAL_INSTITUTION_DETAILS";
	public static final String HEALTHCHECK = "HEALTHCHECK";
	
	TraceLog() {
		throw new IllegalStateException("Utility class");
	}
}
