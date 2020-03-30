package ph.com.metrobank.fin.inst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WsdFinancialInstitutionApplication {
	public static void main(String[] args) {
		SpringApplication.run(WsdFinancialInstitutionApplication.class, args);
	}
}
