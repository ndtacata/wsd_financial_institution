package ph.com.metrobank.fin.inst.model;

import static org.assertj.core.api.Assertions.assertThat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { ErrorDetails.class })
public class ErrorDetailsEntityTest {

private ErrorDetails errDetails;
	
	@Before
	public void setUp() throws ParseException {
		errDetails = new ErrorDetails();
		errDetails.setError(HttpStatus.BAD_REQUEST);
		errDetails.setMessage("error message");
		errDetails.setPath("path");
		errDetails.setStatus(404);
		errDetails.setTimestamp(new SimpleDateFormat("dd-MM-yyyy").parse("2018-05-07").toString());
		
	}
	
	@Test
	public void testErrorDetailsValue() throws ParseException {
		ErrorDetails errDetails2 = new ErrorDetails(new SimpleDateFormat("dd-MM-yyyy").parse("2018-05-07").toString(), "error message", "path", HttpStatus.BAD_REQUEST, 404);
		
		assertThat(errDetails.getError()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(errDetails.getMessage()).isEqualTo("error message");
		assertThat(errDetails.getPath()).isEqualTo("path");
		assertThat(errDetails.getStatus()).isEqualTo(404);
		assertThat(errDetails.getTimestamp()).isEqualTo(new SimpleDateFormat("dd-MM-yyyy").parse("2018-05-07").toString());
		
		assertThat(errDetails).isEqualToComparingFieldByFieldRecursively(errDetails2);
	}

}
