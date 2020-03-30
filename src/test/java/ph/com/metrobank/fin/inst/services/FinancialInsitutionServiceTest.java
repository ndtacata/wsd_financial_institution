package ph.com.metrobank.fin.inst.services;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ph.com.metrobank.fin.inst.exception.FinancialInstitutionDefinitionException;
import ph.com.metrobank.fin.inst.model.FinancialInstitution;
import ph.com.metrobank.fin.inst.model.FinancialInstitutionRequest;
import ph.com.metrobank.fin.inst.repository.FinancialInstitutionRepository;
import ph.com.metrobank.fin.inst.utils.Utils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { FinancialInstitutionService.class })
public class FinancialInsitutionServiceTest {
	
	@InjectMocks
	private FinancialInstitutionService financialInstitutionService;
	
	@MockBean
	private FinancialInstitutionRepository financialInstRepo;
	
	@MockBean
	private FinancialInstitutionSpecifications specifications;
	
	@MockBean
	private EntityManager entityManager;
	
	@MockBean
	private Utils utils;
	
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
	public void testListAllFinancialInstitution() throws FinancialInstitutionDefinitionException {
		Mockito.when(financialInstRepo.findAllByOrderByNameAsc()).thenReturn(listFinInst);
		financialInstitutionService.listAllFinancialInstitutions();
		assertThat(listFinInst.get(0).getAddress()).isEqualTo(finInst.getAddress());
	}
	
	@Test(expected = FinancialInstitutionDefinitionException.class)
	public void testEmptyListAllFinancialInstitution() throws FinancialInstitutionDefinitionException {
		Mockito.when(financialInstRepo.findAllByOrderByNameAsc()).thenReturn(new ArrayList<>());
		Mockito.when(loggingService.log(Mockito.anyString(), Mockito.anyString(), 
				Mockito.anyString(), Mockito.anyString())).thenReturn("");
		financialInstitutionService.listAllFinancialInstitutions();
		assertThat(true).isTrue();
	}
	
	@Test
	public void testListAllPesonetFinancialInstitution() throws FinancialInstitutionDefinitionException {
		Mockito.when(financialInstRepo.findAll(specifications
				.findSpecificationsByFinInstTransferType(Mockito.anyString()))).thenReturn(listFinInst);
		financialInstitutionService.listAllPESONetFinInstitution();
		assertThat(listFinInst.get(0).getAddress()).isEqualTo(finInst.getAddress());		
	}
	
	@Test (expected = FinancialInstitutionDefinitionException.class)
	public void testEmptyListPesonetFinancialInstitution() throws FinancialInstitutionDefinitionException {
		Mockito.when(financialInstRepo.findAll(specifications
				.findSpecificationsByFinInstTransferType(Mockito.anyString()))).thenReturn(Collections.emptyList());
		financialInstitutionService.listAllPESONetFinInstitution();
	}
	
	@Test
	public void testListAllInstapayFinancialInstitution() throws FinancialInstitutionDefinitionException {
		Mockito.when(financialInstRepo.findAll(specifications
				.findSpecificationsByFinInstTransferType(Mockito.anyString()))).thenReturn(listFinInst);
		financialInstitutionService.listAllInstapayFinInstitution();
		assertThat(listFinInst.get(0).getAddress()).isEqualTo(finInst.getAddress());		
	}
	
	@Test (expected = FinancialInstitutionDefinitionException.class)
	public void testEmptyListInstapayFinancialInstitution() throws FinancialInstitutionDefinitionException {
		Mockito.when(financialInstRepo.findAll(specifications
				.findSpecificationsByFinInstTransferType(Mockito.anyString()))).thenReturn(Collections.emptyList());
		financialInstitutionService.listAllInstapayFinInstitution();
	}
	
	@Test
	public void testInquireByPCHCCode() throws FinancialInstitutionDefinitionException {
		Mockito.when(financialInstRepo.findAll(specifications
				.findSpecificationsByBank(Mockito.anyString(), Mockito.anyString()))).thenReturn(listFinInst);
		financialInstitutionService.inquireByPCHCCode("1233");
		assertThat(listFinInst.get(0).getAddress()).isEqualTo(finInst.getAddress());		
	}
	
	@Test (expected = FinancialInstitutionDefinitionException.class)
	public void testEmptyInquireByPCHCCode() throws FinancialInstitutionDefinitionException {
		Mockito.when(financialInstRepo.findAll(specifications
				.findSpecificationsByBank(Mockito.anyString(), Mockito.anyString()))).thenReturn(Collections.emptyList());
		Mockito.when(loggingService.log(Mockito.anyString(), Mockito.anyString(), 
				Mockito.anyString(), Mockito.anyString())).thenReturn("error");
		financialInstitutionService.inquireByPCHCCode("1233");
		assertThat(listFinInst.get(0).getAddress()).isEqualTo(finInst.getAddress());	
	}
	
	@Test
	public void testInquireByBancnetCode() throws FinancialInstitutionDefinitionException {
		Mockito.when(financialInstRepo.findAll(specifications
				.findSpecificationsByBank(Mockito.anyString(), Mockito.anyString()))).thenReturn(listFinInst);
		financialInstitutionService.inquireByBancnetCode("1233");
		assertThat(listFinInst.get(0).getAddress()).isEqualTo(finInst.getAddress());		
	}
	
	@Test (expected = FinancialInstitutionDefinitionException.class)
	public void testEmptyInquireByBancnetCode() throws FinancialInstitutionDefinitionException {
		Mockito.when(financialInstRepo.findAll(specifications
				.findSpecificationsByBank(Mockito.anyString(), Mockito.anyString()))).thenReturn(Collections.emptyList());
		Mockito.when(loggingService.log(Mockito.anyString(), Mockito.anyString(), 
				Mockito.anyString(), Mockito.anyString())).thenReturn("error");
		financialInstitutionService.inquireByBancnetCode("1233");
		assertThat(listFinInst.get(0).getAddress()).isEqualTo(finInst.getAddress());	
	}
	
	@Test
	public void testInquireBySwiftCode() throws FinancialInstitutionDefinitionException {
		Mockito.when(financialInstRepo.findAll(specifications
				.findSpecificationsByBank(Mockito.anyString(), Mockito.anyString()))).thenReturn(listFinInst);
		financialInstitutionService.inquireBySwiftCode("1233");
		assertThat(listFinInst.get(0).getAddress()).isEqualTo(finInst.getAddress());		
	}
	
	@Test (expected = FinancialInstitutionDefinitionException.class)
	public void testEmptyInquireBySwiftCode() throws FinancialInstitutionDefinitionException {
		Mockito.when(financialInstRepo.findAll(specifications
				.findSpecificationsByBank(Mockito.anyString(), Mockito.anyString()))).thenReturn(Collections.emptyList());
		Mockito.when(loggingService.log(Mockito.anyString(), Mockito.anyString(), 
				Mockito.anyString(), Mockito.anyString())).thenReturn("error");
		financialInstitutionService.inquireBySwiftCode("1233");
		assertThat(listFinInst.get(0).getAddress()).isEqualTo(finInst.getAddress());	
	}
	
	@Test
	public void testAddFinancialInstitution() throws FinancialInstitutionDefinitionException {
		Mockito.when(financialInstRepo.save(finInst)).thenReturn(finInst);
		financialInstitutionService.addFinancialInstitution(finInstRequest);
		assertThat(true).isTrue();
	}
	
	@Test(expected = FinancialInstitutionDefinitionException.class)
	public void testAddFinancialInstitutionError() throws FinancialInstitutionDefinitionException {
		finInstRequest = null;
		Mockito.when(loggingService.log(Mockito.anyString(), Mockito.anyString(), 
				Mockito.anyString(), Mockito.anyString())).thenReturn("error");
		financialInstitutionService.addFinancialInstitution(finInstRequest);
	}
	
	@Test
	public void testUpdateFinancialInstitutionDetails() {
		Mockito.when(financialInstRepo.updateFinancialInstitution(Mockito.anyString(), Mockito.anyString(), 
				Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), 
				Mockito.anyInt(), Mockito.anyString())).thenReturn(1);
		Mockito.when(loggingService.log(Mockito.anyString(), Mockito.anyString(), 
				Mockito.anyString(), Mockito.anyString())).thenReturn("test");
		Mockito.when(utils.mapToJsonString(finInstRequest)).thenReturn("test");
		financialInstitutionService.updateFinancialInstitution(finInstRequest);
	}
}
