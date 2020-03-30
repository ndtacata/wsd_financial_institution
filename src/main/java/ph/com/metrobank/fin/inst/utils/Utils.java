package ph.com.metrobank.fin.inst.utils;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ph.com.metrobank.fin.inst.model.ErrorMessage;
import ph.com.metrobank.fin.inst.services.LoggingService;

@Component
public class Utils {
	
	@Autowired
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private LoggingService logging;

	public <E> String mapToJsonString(E object) {
		try {
			if(object == null) {
				throw new IOException(ErrorMessage.ERROR_PROCESSING_JSON_NULL);
			}else {
				return mapper.enable(SerializationFeature.INDENT_OUTPUT).writeValueAsString(object);
			}
		} catch (IOException e) {
			logging.logError(this.getClass().toString(), "", ErrorMessage.ERROR_MAPPING_TO_JSONSTRING, e);
			return "";
		}
	}
}
