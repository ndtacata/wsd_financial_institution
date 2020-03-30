package ph.com.metrobank.fin.inst.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { FinancialInstitutionRequest.class })
public class FinancialInstitutionRequestEntityTest {

	private FinancialInstitutionRequest finInst;
		
	@Before
	public void testSetup() {
		finInst = new FinancialInstitutionRequest();
		finInst.setAddress("test address");
		finInst.setBancnetCode("123456");
		finInst.setBrstn("12345");
		finInst.setCreatedBy("TEST");
		finInst.setEnrollmentEnabled(0);
		finInst.setName("BANK");
		finInst.setPchcCode("12345");
		finInst.setSwiftCode("XXXXX");
		finInst.setTransferEnabled(0);
		finInst.setType("EXTERNAL");
		finInst.setUpdatedBy("TEST");
	}
	
	
	@Test
	public void testValues() {
		assertThat(finInst.getAddress()).isEqualTo("test address");
		assertThat(finInst.getBancnetCode()).isEqualTo("123456");
		assertThat(finInst.getBrstn()).isEqualTo("12345");
		assertThat(finInst.getCreatedBy()).isEqualTo("TEST");
		assertThat(finInst.getEnrollmentEnabled()).isEqualTo(0);
		assertThat(finInst.getName()).isEqualTo("BANK");
		assertThat(finInst.getPchcCode()).isEqualTo("12345");
		assertThat(finInst.getSwiftCode()).isEqualTo("XXXXX");
		assertThat(finInst.getTransferEnabled()).isEqualTo(0);
		assertThat(finInst.getType()).isEqualTo("EXTERNAL");
		assertThat(finInst.getUpdatedBy()).isEqualTo("TEST");
		
	}
}
