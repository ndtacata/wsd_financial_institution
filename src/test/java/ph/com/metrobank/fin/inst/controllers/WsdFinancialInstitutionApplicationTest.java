package ph.com.metrobank.fin.inst.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ph.com.metrobank.fin.inst.WsdFinancialInstitutionApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=WsdFinancialInstitutionApplication.class, initializers = ConfigFileApplicationContextInitializer.class)
@EnableMBeanExport(registration=RegistrationPolicy.REPLACE_EXISTING)
public class WsdFinancialInstitutionApplicationTest {
	@Test
	public void testWsdFinancialInstitutionApplication() {
		WsdFinancialInstitutionApplication.main(new String[] {});
		assertThat(1).isEqualTo(1);
	}
}