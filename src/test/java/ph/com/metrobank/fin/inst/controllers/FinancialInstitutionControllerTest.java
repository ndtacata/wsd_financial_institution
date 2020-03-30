package ph.com.metrobank.fin.inst.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import ph.com.metrobank.fin.inst.WsdFinancialInstitutionApplication;
import ph.com.metrobank.fin.inst.model.FinancialInstitution;
import ph.com.metrobank.fin.inst.model.FinancialInstitutionRequest;
import ph.com.metrobank.fin.inst.services.FinancialInstitutionService;
import ph.com.metrobank.fin.inst.services.LoggingService;
import ph.com.metrobank.fin.inst.utils.Utils;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(properties = "spring.profiles.active=dev")
@ContextConfiguration(classes = { WsdFinancialInstitutionApplication.class })
public class FinancialInstitutionControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private Utils utils;
	
	@MockBean
	private FinancialInstitutionService finInstService;

	@MockBean
	private LoggingService loggingService;

	private FinancialInstitution finInst, finInst2;
	private FinancialInstitutionRequest finInstRequest;
	
	private List<FinancialInstitution> listFinInst = new ArrayList<>();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		finInst = new FinancialInstitution();
		finInst.setAddress("test address");
		finInst.setBancnetCode("123456");
		finInst.setBrstn("12345");
		finInst.setCreatedBy("TEST");
		finInst.setDateCreated(null);
		finInst.setDateModified(null);
		finInst.setEnrollmentEnabled(0);
		finInst.setId(1);
		finInst.setName("BANK");
		finInst.setPchcCode("12345");
		finInst.setSwiftCode("XXXXX");
		finInst.setTransferEnabled(0);
		finInst.setType("EXTERNAL");
		finInst.setUpdatedBy("TEST");
		
		finInst2 = new FinancialInstitution();
		finInst2.setAddress("test address");
		finInst2.setBancnetCode("123456");
		finInst2.setBrstn("12345");
		finInst2.setCreatedBy("TEST");
		finInst2.setDateCreated(null);
		finInst2.setDateModified(null);
		finInst2.setEnrollmentEnabled(0);
		finInst2.setId(1);
		finInst2.setName("2BANK");
		finInst2.setPchcCode("12345");
		finInst2.setSwiftCode("XXXXX");
		finInst2.setTransferEnabled(0);
		finInst2.setType("EXTERNAL");
		finInst2.setUpdatedBy("TEST");
		
		finInstRequest = new FinancialInstitutionRequest();
		finInstRequest.setAddress("test address");
		finInstRequest.setBancnetCode("123456");
		finInstRequest.setBrstn("12345");
		finInstRequest.setCreatedBy("TEST");
		finInstRequest.setEnrollmentEnabled(0);
		finInstRequest.setName("BANK");
		finInstRequest.setPchcCode("12345");
		finInstRequest.setSwiftCode("XXXXX");
		finInstRequest.setTransferEnabled(0);
		finInstRequest.setUpdatedBy("TEST");
		
		listFinInst.add(finInst);
		listFinInst.add(finInst2);
		
	}

	@Test
	public void testListAll() throws Exception {
/*		when(orchestrationService.processInstapaySending(Mockito.any())).thenReturn(instapaySendingModel);
		mockMvc.perform(post("/instapaySending/send").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(instapaySendingModel.toString())).andExpect(status().isOk());
					mockMvc.perform(get("/wsd/holiday/searchAll")).andExpect(status().isOk());
		*/
		when(finInstService.listAllFinancialInstitutions()).thenReturn(listFinInst);
		mockMvc.perform(get("/financial-institution/list-all")).andExpect(status().isOk());
	}
	
	@Test
	public void testListAllPESONet() throws Exception {
		when(finInstService.listAllFinancialInstitutions()).thenReturn(listFinInst);
		mockMvc.perform(get("/financial-institution/list-all-pesonet")).andExpect(status().isOk());
	}
	
	@Test
	public void testListAllInstapay() throws Exception {
		when(finInstService.listAllFinancialInstitutions()).thenReturn(listFinInst);
		mockMvc.perform(get("/financial-institution/list-all-instapay")).andExpect(status().isOk());
	}
	
	@Test
	public void testInquireByPCHCCode() throws Exception {
		when(finInstService.inquireByPCHCCode(Mockito.anyString())).thenReturn(listFinInst);
		mockMvc.perform(get("/financial-institution/inquire-by-pchc-code")
				.param("bank", "123456")).andExpect(status().isOk());
	}
	
	@Test
	public void testInquireByBancnetCode() throws Exception {
		when(finInstService.inquireByBancnetCode(Mockito.anyString())).thenReturn(listFinInst);
		mockMvc.perform(get("/financial-institution/inquire-by-bancnet-code")
				.param("bank", "123456")).andExpect(status().isOk());
	}
	
	@Test
	public void testInquireBySwiftCode() throws Exception {
		when(finInstService.inquireBySwiftCode(Mockito.anyString())).thenReturn(listFinInst);
		mockMvc.perform(get("/financial-institution/inquire-by-swift-code")
				.param("bank", "123456")).andExpect(status().isOk());
	}
	
	@Test
	public void testAdd() throws Exception {	
		String payload = "    {\r\n" + 
				"    	\"name\": \"AL-AMANAH ISLAMIC\",\r\n" + 
				"        \"brstn\": \"10360015\",\r\n" + 
				"        \"swiftCode\": \"AIIPPHM1XXX\",\r\n" + 
				"        \"address\": \"PHIDCO A. Building Veterans Avenue,Zamboanga City te\",\r\n" + 
				"        \"bancnetCode\": null,\r\n" + 
				"        \"pchcCode\": \"5996\",\r\n" + 
				"        \"transferEnabled\": 1\r\n" + 
				"    }";
		
		when(finInstService.addFinancialInstitution(finInstRequest)).thenReturn(finInst);
		mockMvc.perform(post("/financial-institution/add")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(payload))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testUpdate() throws Exception {	
		String payload = "    {\r\n" + 
				"    	\"name\": \"AL-AMANAH ISLAMIC\",\r\n" + 
				"        \"brstn\": \"10360015\",\r\n" + 
				"        \"swiftCode\": \"AIIPPHM1XXX\",\r\n" + 
				"        \"address\": \"PHIDCO A. Building Veterans Avenue,Zamboanga City te\",\r\n" + 
				"        \"bancnetCode\": null,\r\n" + 
				"        \"pchcCode\": \"5996\",\r\n" + 
				"        \"transferEnabled\": 1\r\n" + 
				"    }";
		
		when(finInstService.updateFinancialInstitution(finInstRequest)).thenReturn(1);
		mockMvc.perform(post("/financial-institution/update")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(payload))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testHealthCheck() throws Exception {
		mockMvc.perform(get("/financial-institution/healthCheck"))
				.andExpect(status().isOk())
			;
	}

}
