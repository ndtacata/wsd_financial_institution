/*
*  Copyright (c) 2019 Metropolitan Bank & Trust Company. All Rights Reserved. 
*  
*  @Author: Nerissa Monica DG. Tacata
*  @Date: 2019-06-25
*  @Email: nerissa.tacata@metrobank.com.ph
*  
*  
*  
*  
*/
package ph.com.metrobank.fin.inst.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FinancialInstitutionDefinitionException extends Exception{
	private static final long serialVersionUID = -6006344035160715792L;
	
	public FinancialInstitutionDefinitionException(String errorMessage) {
        super(errorMessage);
    }
}
