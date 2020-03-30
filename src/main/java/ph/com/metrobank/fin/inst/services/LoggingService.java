/*
*  Copyright (c) 2019 Metropolitan Bank & Trust Company. All Rights Reserved. 
*  
*  @Author: Emmanuel Ombrosa, Nerissa Tacata
*  @Date: 2019-03-05
*  @Email: emmanuel.ombrosa@metrobank.com.ph; nerissa.tacata@metrobank.com.ph
*/
package ph.com.metrobank.fin.inst.services;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggingService {

	private static final Logger logger = LoggerFactory.getLogger(LoggingService.class);

	public String log(String component, String uuid, String processDescription, String txnDetails) {
		Map<String, String> map = new HashMap<>();
		updateLogMap(map, component, uuid, processDescription, txnDetails);
		String logMsg = map.toString();
		logger.info(logMsg);
		return logMsg;
	}

	public String logError(String component, String uuid, String processDescription, Exception e) {
		Map<String, String> map = new HashMap<>();
		updateLogMap(map, component, uuid, processDescription, e.getMessage());
		String logMsg = map.toString();
		logger.info(logMsg);
		return logMsg;
	}

	private Map<String, String> updateLogMap(Map<String, String> map, 
			String component, String uuid, String processDescription, String txnDetails) {
		if (!"".equals(component)) {
			map.put("COMPONENT", component);
		}
		if (!"".equals(uuid)) {
			map.put("UUID", uuid);
		}
		if (!"".equals(processDescription)) {
			map.put("PROCESS_DESCRIPTION", processDescription);
		}
		if (!"".equals(txnDetails)) {
			map.put("TXN_DETAILS", txnDetails);
		}
		return map;
	}
}
