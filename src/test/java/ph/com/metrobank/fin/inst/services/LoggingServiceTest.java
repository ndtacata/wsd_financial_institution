package ph.com.metrobank.fin.inst.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { LoggingService.class })
public class LoggingServiceTest {
	
	@InjectMocks
	private LoggingService loggingService;
	
	private Map<String, String> map;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		map = new HashMap<String, String>();
		map.put("COMPONENT", "CMP");
		map.put("UUID", "1");
		map.put("PROCESS_DESCRIPTION", "WSD");
		map.put("TXN_DETAILS", "HOST DETAILS");
	}
	
	@Test
	public void testLoggingService() throws Exception {
		String log = loggingService.log("CMP", "1", "WSD", "HOST DETAILS");
		assertThat(map.toString()).isEqualTo(log);
		
	}
	
	@Test
	public void testLoggingServiceError() throws Exception {
		String errorLog = loggingService.logError("CMP", "1", "WSD", new Exception("HOST DETAILS"));
		assertThat(map.toString()).isEqualTo(errorLog);
	}
	
	@Test
	public void testEmptyParameters() throws Exception {
		String log = loggingService.log("", "", "", "");
		assertThat(log).isEqualTo(new HashMap<String, String>().toString());
		
	}
}
