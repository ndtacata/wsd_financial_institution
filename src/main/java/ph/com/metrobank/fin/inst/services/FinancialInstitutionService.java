package ph.com.metrobank.fin.inst.services;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ph.com.metrobank.fin.inst.exception.FinancialInstitutionDefinitionException;
import ph.com.metrobank.fin.inst.model.ErrorMessage;
import ph.com.metrobank.fin.inst.model.FinancialInstitution;
import ph.com.metrobank.fin.inst.model.FinancialInstitutionRequest;
import ph.com.metrobank.fin.inst.model.TraceLog;
import ph.com.metrobank.fin.inst.repository.FinancialInstitutionRepository;
import ph.com.metrobank.fin.inst.utils.Utils;

@Service
public class FinancialInstitutionService {
	
	@Autowired
	private FinancialInstitutionRepository financialInstRepo;
	
	@Autowired
	private FinancialInstitutionSpecifications finInstSpecification;
	
	@Autowired
	private Utils utils;
	
	@Autowired
	private LoggingService loggingService;
	
	private static final String PESONET_FINANCIAL_INSTITUTION = "pchcCode";
	private static final String INSTAPAY_FINANCIAL_INSTITUTION = "bancnetCode";
	private static final String SWIFT_CODE = "swiftCode";
	
	public List<FinancialInstitution> listAllFinancialInstitutions() throws FinancialInstitutionDefinitionException{

		List<FinancialInstitution> listOfAllFinInst = financialInstRepo.findAllByOrderByNameAsc();
		
		if(listOfAllFinInst.isEmpty()) {
			loggingService.log(this.getClass().toString(), "", "", ErrorMessage.ERROR_FINANCIAL_INSTITUTION_DETAILS_NOT_FOUND);
			throw new FinancialInstitutionDefinitionException(ErrorMessage.ERROR_FINANCIAL_INSTITUTION_DETAILS_NOT_FOUND);
		}else {
			return listOfAllFinInst;
		}
	}
	
	public List<FinancialInstitution> listAllPESONetFinInstitution() throws FinancialInstitutionDefinitionException{
		List<FinancialInstitution> listOfAllFinInst = financialInstRepo.findAll(finInstSpecification
				.findSpecificationsByFinInstTransferType(PESONET_FINANCIAL_INSTITUTION));
		
		if(listOfAllFinInst.isEmpty()) {
			loggingService.log(this.getClass().toString(), "", "", ErrorMessage.ERROR_FINANCIAL_INSTITUTION_DETAILS_NOT_FOUND);
			throw new FinancialInstitutionDefinitionException(ErrorMessage.ERROR_FINANCIAL_INSTITUTION_DETAILS_NOT_FOUND);
		}else {
			return listOfAllFinInst;
		}
	}

	public List<FinancialInstitution> listAllInstapayFinInstitution() throws FinancialInstitutionDefinitionException{
		List<FinancialInstitution> listOfAllFinInst = financialInstRepo.findAll(finInstSpecification
				.findSpecificationsByFinInstTransferType(INSTAPAY_FINANCIAL_INSTITUTION));
		
		if(listOfAllFinInst.isEmpty()) {
			loggingService.log(this.getClass().toString(), "", "", ErrorMessage.ERROR_FINANCIAL_INSTITUTION_DETAILS_NOT_FOUND);
			throw new FinancialInstitutionDefinitionException(ErrorMessage.ERROR_FINANCIAL_INSTITUTION_DETAILS_NOT_FOUND);
		}else {
			return listOfAllFinInst;
		}
	}
	
	@Cacheable(value = "findPesonetFinInst")
	public List<FinancialInstitution> inquireByPCHCCode(String bank) throws FinancialInstitutionDefinitionException {
		loggingService.log(this.getClass().toString(), "", "", TraceLog.INQUIRE_BY_PCHC_CODE);
		List<FinancialInstitution> listOfAllFinInst = financialInstRepo.findAll(finInstSpecification
				.findSpecificationsByBank(bank, PESONET_FINANCIAL_INSTITUTION));
		if(listOfAllFinInst.isEmpty()) {
			loggingService.log(this.getClass().toString(), "", "", ErrorMessage.ERROR_FINANCIAL_INSTITUTION_DETAILS_NOT_FOUND.concat(bank));
			throw new FinancialInstitutionDefinitionException(ErrorMessage.ERROR_FINANCIAL_INSTITUTION_DETAILS_NOT_FOUND.concat(bank));
		}else {
			return listOfAllFinInst;
		}		
	}
	
	@Cacheable(value = "findInstapayFinInst")
	public List<FinancialInstitution> inquireByBancnetCode(String bank) throws FinancialInstitutionDefinitionException {
		loggingService.log(this.getClass().toString(), "", "", TraceLog.INQUIRE_BY_BANCNET_CODE);
		List<FinancialInstitution> listOfAllFinInst = financialInstRepo.findAll(finInstSpecification
				.findSpecificationsByBank(bank, INSTAPAY_FINANCIAL_INSTITUTION));
		
		if(listOfAllFinInst.isEmpty()) {
			loggingService.log(this.getClass().toString(), "", "", ErrorMessage.ERROR_FINANCIAL_INSTITUTION_DETAILS_NOT_FOUND.concat(bank));
			throw new FinancialInstitutionDefinitionException(ErrorMessage.ERROR_FINANCIAL_INSTITUTION_DETAILS_NOT_FOUND.concat(bank));
		}else {
			return listOfAllFinInst;
		}	
	}
	
	@Cacheable(value = "findBySwiftCode")
	public List<FinancialInstitution> inquireBySwiftCode(String bank) throws FinancialInstitutionDefinitionException {		
		loggingService.log(this.getClass().toString(), "", "", TraceLog.INQUIRE_BY_SWIFT_CODE);
		List<FinancialInstitution> listOfAllFinInst = financialInstRepo.findAll(finInstSpecification
				.findSpecificationsByBank(bank, SWIFT_CODE));
		
		if(listOfAllFinInst.isEmpty()) {
			loggingService.log(this.getClass().toString(), "", "", ErrorMessage.ERROR_FINANCIAL_INSTITUTION_DETAILS_NOT_FOUND.concat(bank));
			throw new FinancialInstitutionDefinitionException(ErrorMessage.ERROR_FINANCIAL_INSTITUTION_DETAILS_NOT_FOUND.concat(bank));
		}else {
			return listOfAllFinInst;
		}	
	}
	
	public FinancialInstitution addFinancialInstitution(FinancialInstitutionRequest financialInstitution) throws FinancialInstitutionDefinitionException {
		FinancialInstitution finInst = new FinancialInstitution();
		try {
			BeanUtils.copyProperties(financialInstitution, finInst);
			return financialInstRepo.save(finInst);
		}catch (Exception e) {
			loggingService.log(this.getClass().toString(), "", ErrorMessage.ERROR_FINANCIAL_INSTITUTION_DETAILS_NOT_FOUND, e.getMessage());
			throw new FinancialInstitutionDefinitionException(ErrorMessage.ERROR_FINANCIAL_INSTITUTION_DETAILS_NOT_FOUND.concat(e.getMessage()));
		}
	}
	
	@Transactional
	@CacheEvict(value={"findPesonetFinInst", "findInstapayFinInst", "findBySwiftCode"}, allEntries = true)
	public int updateFinancialInstitution(FinancialInstitutionRequest newFinancialInst) {
		int updateResult = financialInstRepo.updateFinancialInstitution(newFinancialInst.getAddress(), 
				newFinancialInst.getBrstn(), newFinancialInst.getBancnetCode(), newFinancialInst.getPchcCode(),
				newFinancialInst.getSwiftCode(), newFinancialInst.getTransferEnabled(), newFinancialInst.getName());
		loggingService.log(this.getClass().toString(), "", TraceLog.UPDATE_FINANCIAL_INSTITUTION_DETAILS, Integer.toString(updateResult).concat(" ").concat(utils.mapToJsonString(newFinancialInst)));
		return updateResult;
	}

}
