/*
 *  Copyright (c) 2019 Metropolitan Bank & Trust Company
 *
 *  @author 33460
 *  @date 2019-08-16
 *  @email mark.to@metrobank.com.ph
 */
package ph.com.metrobank.fin.inst.model;

import org.springframework.http.HttpStatus;

public class ErrorDetails {

	private String timestamp;
	private String message;
	private String path;
	private HttpStatus error;
	private int status;

	public ErrorDetails(String timestamp, String message, String path, HttpStatus error, int status) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.path = path;
		this.error = error;
		this.status = status;
	}

	public ErrorDetails() {
		super();
	}

	public HttpStatus getError() {
		return error;
	}

	public void setError(HttpStatus error) {
		this.error = error;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
