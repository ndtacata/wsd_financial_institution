package ph.com.metrobank.fin.inst.model;

public class ErrorMessage {
	
	public static final String ERROR_PROCESSING_JSON_NULL = "ERROR_PROCESSING_JSON_NULL";
	public static final String ERROR_MAPPING_TO_JSONSTRING = "ERROR_MAPPING_TO_JSONSTRING";
	public static final String ERROR_FINANCIAL_INSTITUTION_DETAILS_NOT_FOUND = "ERROR_FINANCIAL_INSTITUTION_DETAILS_NOT_FOUND ";
	public static final String ERROR_UPDATING = "ERROR_UPDATING: ";
	
	ErrorMessage() {
		throw new IllegalStateException("Utility class");
	}
}
