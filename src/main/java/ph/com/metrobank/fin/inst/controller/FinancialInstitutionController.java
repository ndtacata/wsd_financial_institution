package ph.com.metrobank.fin.inst.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import ph.com.metrobank.fin.inst.exception.FinancialInstitutionDefinitionException;
import ph.com.metrobank.fin.inst.model.FinancialInstitution;
import ph.com.metrobank.fin.inst.model.FinancialInstitutionRequest;
import ph.com.metrobank.fin.inst.model.TraceLog;
import ph.com.metrobank.fin.inst.services.FinancialInstitutionService;
import ph.com.metrobank.fin.inst.services.LoggingService;
import ph.com.metrobank.fin.inst.utils.Utils;

@RestController
@RequestMapping("${endpoint.url}")
public class FinancialInstitutionController {
	
	@Autowired
	private FinancialInstitutionService finInstService;
	
	@Autowired
	private LoggingService loggingService;
	
	@Autowired
	private Utils utils;
	
	@GetMapping(value = "list-all")
	@ApiOperation(value = "Get all Financial Institutions")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Resource not found") })
	public @ResponseBody ResponseEntity<List<FinancialInstitution>> listAllFinancialInstitutions() throws FinancialInstitutionDefinitionException{
		loggingService.log(this.getClass().toString(), "", TraceLog.LIST_ALL_FINANCIAL_INSTITUTIONS, "");
		List<FinancialInstitution> listFinancialInstitution = finInstService.listAllFinancialInstitutions();
		return ResponseEntity.ok(listFinancialInstitution);
	}
	
	@GetMapping(value = "list-all-pesonet")
	@ApiOperation(value = "Get all PESONet Financial Institutions")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Resource not found") })
	public @ResponseBody ResponseEntity<List<FinancialInstitution>> listAllPESONetFinInstitution() throws FinancialInstitutionDefinitionException{
		loggingService.log(this.getClass().toString(), "", TraceLog.LIST_ALL_PESONET_FINANCIAL_INSTITUTIONS, "");
		List<FinancialInstitution> listPESONetBanks = finInstService.listAllPESONetFinInstitution();
		return ResponseEntity.ok(listPESONetBanks);
	}
	
	@GetMapping(value = "list-all-instapay")
	@ApiOperation(value = "Get all Instapay Financial Institutions")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Resource not found") })
	public @ResponseBody ResponseEntity<List<FinancialInstitution>> listAllInstapayFinInstitution() throws FinancialInstitutionDefinitionException{
		loggingService.log(this.getClass().toString(), "", TraceLog.LIST_ALL_INSTAPAY_FINANCIAL_INSTITUTIONS, "");
		List<FinancialInstitution> listPESONetBanks = finInstService.listAllInstapayFinInstitution();
		return ResponseEntity.ok(listPESONetBanks);
	}
	
	@GetMapping(value = "inquire-by-pchc-code")
	@ApiOperation(value = "Financial Institution inquiry by PCHC Code")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Resource not found") })
	public @ResponseBody ResponseEntity<List<FinancialInstitution>> inquireByPCHCCode(@RequestParam(value = "bank") String bank) throws FinancialInstitutionDefinitionException{
		loggingService.log(this.getClass().toString(), "", TraceLog.INQUIRE_BY_PCHC_CODE, bank);
		List<FinancialInstitution> finInst = finInstService.inquireByPCHCCode(bank);
		return ResponseEntity.ok(finInst);
	}
	
	@GetMapping(value = "inquire-by-bancnet-code")
	@ApiOperation(value = "Financial Institution inquiry by Bancnet Code")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Resource not found") })
	public @ResponseBody ResponseEntity<List<FinancialInstitution>> inquireByBancnetCode(@RequestParam(value = "bank") String bank) throws FinancialInstitutionDefinitionException{
		loggingService.log(this.getClass().toString(), "", TraceLog.INQUIRE_BY_BANCNET_CODE, bank);
		List<FinancialInstitution> finInst = finInstService.inquireByBancnetCode(bank);
		return ResponseEntity.ok(finInst);
	}
	
	@GetMapping(value = "inquire-by-swift-code")
	@ApiOperation(value = "Financial Institution inquiry by Swift Code")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Resource not found") })
	public @ResponseBody ResponseEntity<List<FinancialInstitution>> inquireBySwiftCode(@RequestParam(value = "bank") String bank) throws FinancialInstitutionDefinitionException{
		loggingService.log(this.getClass().toString(), "", TraceLog.INQUIRE_BY_SWIFT_CODE, bank);
		List<FinancialInstitution> finInst = finInstService.inquireBySwiftCode(bank);
		return ResponseEntity.ok(finInst);
	}
	
	@PostMapping(value = "add")
	@ApiOperation(value = "Add financial institution")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Resource not found") })
	public @ResponseBody ResponseEntity<FinancialInstitution> addFinancialInstitution(
			@RequestBody FinancialInstitutionRequest finInst) throws FinancialInstitutionDefinitionException{
		loggingService.log(this.getClass().toString(), "", TraceLog.ADD_FINANCIAL_INSTITUTION, "");
		FinancialInstitution newFinancialInstitution = finInstService.addFinancialInstitution(finInst);
		loggingService.log(this.getClass().toString(), "", TraceLog.ADD_FINANCIAL_INSTITUTION, utils.mapToJsonString(newFinancialInstitution));
		return ResponseEntity.ok(newFinancialInstitution);
	}
	
	@PostMapping(value = "update")
	@ApiOperation(value = "Update financial institution details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Resource not found") })
	public @ResponseBody ResponseEntity<Integer> updateFinancialInstitution(
			@RequestBody FinancialInstitutionRequest newFinInst) {
		loggingService.log(this.getClass().toString(), "", TraceLog.UPDATE_FINANCIAL_INSTITUTION_DETAILS, "");
		return ResponseEntity.ok(finInstService.updateFinancialInstitution(newFinInst));
	}
	
	@GetMapping(value = "/healthCheck")
	@ApiOperation(value = "WSD Financial Institution Healthcheck")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Resource not found") })
	public @ResponseBody ResponseEntity<String> healthCheck() {
		loggingService.log(this.getClass().toString(), "", TraceLog.HEALTHCHECK, "");
		return ResponseEntity.ok("OK");
	}
	
}
