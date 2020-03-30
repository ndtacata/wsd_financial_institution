package ph.com.metrobank.fin.inst.services;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import ph.com.metrobank.fin.inst.model.FinancialInstitution;
import ph.com.metrobank.fin.inst.model.FinancialInstitutionRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { FinancialInstitutionSpecifications.class })
public class FinancialInstitutionSpecificationsTest {

	@InjectMocks
	private FinancialInstitutionSpecifications specifications;
	
	@MockBean
	private EntityManager entityManager;
	
	@MockBean
	private Root<FinancialInstitution> root;
	
	@MockBean
    private CriteriaQuery<?> query;
	
	@MockBean
    private CriteriaBuilder criteriaBuilder;
	
	@MockBean
	private Predicate predicate;
	
	@MockBean
	private CriteriaUpdate<FinancialInstitution> criteriaUpdate;
	
	private FinancialInstitution finInst, finInst2;
	private FinancialInstitutionRequest finRequest;
	
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
		
		finRequest = new FinancialInstitutionRequest();
		finRequest.setAddress("test address");
		finRequest.setBancnetCode("123456");
		finRequest.setBrstn("12345");
		finRequest.setCreatedBy("TEST");
		finRequest.setEnrollmentEnabled(0);
		finRequest.setName("BANK");
		finRequest.setPchcCode("12345");
		finRequest.setSwiftCode("XXXXX");
		finRequest.setTransferEnabled(0);
		finRequest.setType("EXTERNAL");
		finRequest.setUpdatedBy("TEST");
		
		listFinInst.add(finInst);
		listFinInst.add(finInst2);
		
	}
	
	
	@Test
	public void testFindSpecificationsByFinInstTransferTypeNotNull() throws ParseException {
		
		Specification<FinancialInstitution> spec = specifications
				.findSpecificationsByFinInstTransferType("");
		spec.toPredicate(root, query, criteriaBuilder);
		verify(criteriaBuilder, times(1)).isNotNull(root.get("pchcCode"));
	}
	
	@Test
	public void testFindSpecificationsByBank() throws ParseException {
		Specification<FinancialInstitution> spec = specifications
				.findSpecificationsByBank("test", "test");
		spec.toPredicate(root, query, criteriaBuilder);
		verify(criteriaBuilder, times(1)).equal(root.get("name"), "test");
		verify(criteriaBuilder, times(1)).isNotNull(root.get("bank"));
	}
	
/*	@Test

	public void testUpdateFinancialInstitution() {
		Mockito.when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
		Mockito.when(criteriaBuilder.createCriteriaUpdate(FinancialInstitution.class)).thenReturn(criteriaUpdate);
		Mockito.when(criteriaUpdate.from(FinancialInstitution.class)).thenReturn(root);
		Mockito.when(criteriaBuilder.equal(root.get("name"), "")).thenReturn(predicate);
		criteriaUpdate.set("name", "test");
		Query q=null;
		Mockito.when(entityManager.createQuery(criteriaUpdate)).thenReturn(q);
		specifications.updateFinancialInstitution(entityManager, finRequest);
	}*/
}
