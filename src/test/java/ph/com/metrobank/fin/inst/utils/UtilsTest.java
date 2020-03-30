package ph.com.metrobank.fin.inst.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ph.com.metrobank.fin.inst.model.FinancialInstitution;
import ph.com.metrobank.fin.inst.services.LoggingService;

public class UtilsTest {

	@InjectMocks
	private Utils utils;
	
	@Mock
	private LoggingService logging;
	
	private FinancialInstitution finInst;
	
	@Before
	public void testSetup() {
		MockitoAnnotations.initMocks(this);
		finInst = new FinancialInstitution();
		finInst.setAddress("test address");
		finInst.setBancnetCode("123456");
		finInst.setBrstn("12345");
		finInst.setCreatedBy("TEST");
		finInst.setDateCreated(new Date());
		finInst.setDateModified(new Date());
		finInst.setEnrollmentEnabled(0);
		finInst.setId(1);
		finInst.setName("BANK");
		finInst.setPchcCode("12345");
		finInst.setSwiftCode("XXXXX");
		finInst.setTransferEnabled(0);
		finInst.setType("EXTERNAL");
		finInst.setUpdatedBy("TEST");
	}
	
	@Test
	public void testObjectToJson() {
		String output = utils.mapToJsonString(finInst);
		assertThat(output).isEqualTo(utils.mapToJsonString(finInst));
	}
	
	@Test
	public void testFailedMapToJsonString() {
		finInst = null;
		String jsonString = utils.mapToJsonString(finInst);
		when(logging.log(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn("error");
		assertThat(jsonString).isEmpty();
	}
	
}
