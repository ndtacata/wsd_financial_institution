package ph.com.metrobank.fin.inst.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import ph.com.metrobank.fin.inst.controller.CustomResponseEntityExceptionHandler;
import ph.com.metrobank.fin.inst.model.ErrorDetails;

@RunWith(MockitoJUnitRunner.class)
public class CustomResponseEntityExceptionHandlerTest {

	@InjectMocks
	private CustomResponseEntityExceptionHandler subject;

	@Mock
	private WebRequest webRequest;

	@Test
	public void testHandleJSONParsingException() {

		when(webRequest.getDescription(false)).thenReturn("test_path");
		Exception ex = new Exception("test error message ");
		ResponseEntity<ErrorDetails> retVal = subject.handleJSONParsingException(ex, webRequest);

		assertThat(retVal.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

	}

}
