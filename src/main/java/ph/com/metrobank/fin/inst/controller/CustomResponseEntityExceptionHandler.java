/*
 *  Copyright (c) 2019 Metropolitan Bank & Trust Company
 *
 *  @author 33460
 *  @date 2019-08-16
 *  @email mark.to@metrobank.com.ph
 */
package ph.com.metrobank.fin.inst.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ph.com.metrobank.fin.inst.model.ErrorDetails;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleJSONParsingException(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));
		errorDetails.setMessage(ex.getMessage());
		errorDetails.setPath(request.getDescription(false));
		errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());
		errorDetails.setError(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}
