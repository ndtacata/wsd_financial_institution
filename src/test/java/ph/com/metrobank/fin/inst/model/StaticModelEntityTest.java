package ph.com.metrobank.fin.inst.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class StaticModelEntityTest {
	@Test
	public void testStaticValue() throws NoSuchFieldException, SecurityException {
		assertThat(ErrorMessage.ERROR_FINANCIAL_INSTITUTION_DETAILS_NOT_FOUND).isEqualTo("ERROR_FINANCIAL_INSTITUTION_DETAILS_NOT_FOUND ");
		assertThat(ErrorMessage.ERROR_UPDATING).isEqualTo("ERROR_UPDATING: ");
		assertThat(ErrorMessage.ERROR_PROCESSING_JSON_NULL).isEqualTo("ERROR_PROCESSING_JSON_NULL");
		assertThat(ErrorMessage.ERROR_MAPPING_TO_JSONSTRING).isEqualTo("ERROR_MAPPING_TO_JSONSTRING");
		assertThat(TraceLog.LIST_ALL_FINANCIAL_INSTITUTIONS).isEqualTo("LIST_ALL_FINANCIAL_INSTITUTIONS");
		assertThat(TraceLog.LIST_ALL_PESONET_FINANCIAL_INSTITUTIONS).isEqualTo("LIST_ALL_PESONET_FINANCIAL_INSTITUTIONS");
		assertThat(TraceLog.LIST_ALL_INSTAPAY_FINANCIAL_INSTITUTIONS).isEqualTo("LIST_ALL_INSTAPAY_FINANCIAL_INSTITUTIONS");
		assertThat(TraceLog.INQUIRE_BY_BANCNET_CODE).isEqualTo("INQUIRE_BY_BANCNET_CODE ");
		assertThat(TraceLog.INQUIRE_BY_PCHC_CODE).isEqualTo("INQUIRE_BY_PCHC_CODE ");
		assertThat(TraceLog.INQUIRE_BY_SWIFT_CODE).isEqualTo("INQUIRE_BY_SWIFT_CODE ");
		assertThat(TraceLog.ADD_FINANCIAL_INSTITUTION).isEqualTo("ADD_FINANCIAL_INSTITUTION");
		assertThat(TraceLog.UPDATE_FINANCIAL_INSTITUTION_DETAILS).isEqualTo("UPDATE_FINANCIAL_INSTITUTION_DETAILS");
		assertThat(TraceLog.HEALTHCHECK).isEqualTo("HEALTHCHECK");
	}

	
	@Test(expected=IllegalStateException.class)
	public void testErrorMessage() throws IllegalAccessException {
		new ErrorMessage();
	}
	
	@Test(expected=IllegalStateException.class)
	public void testTraceLog() throws IllegalAccessException {
		new TraceLog();
	}
}
