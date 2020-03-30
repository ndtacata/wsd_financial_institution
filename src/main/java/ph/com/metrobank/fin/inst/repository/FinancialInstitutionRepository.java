package ph.com.metrobank.fin.inst.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ph.com.metrobank.fin.inst.model.FinancialInstitution;

public interface FinancialInstitutionRepository extends CrudRepository<FinancialInstitution, String>, JpaSpecificationExecutor<FinancialInstitution>{
	
	public List<FinancialInstitution> findAllByOrderByNameAsc();

	public List<FinancialInstitution> findByPchcCode(String bank);
	
	public List<FinancialInstitution> findByBancnetCode(String bank);
	
	public List<FinancialInstitution> findBySwiftCode(String bank);
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Modifying
	@Query("UPDATE FinancialInstitution f SET f.address = :address, f.brstn = :brstn, f.bancnetCode = :bancnetCode, "
			+ "f.pchcCode = :pchcCode, f.swiftCode = :swiftCode, f.transferEnabled = :transferEnabled "
			+ "WHERE f.name = :name")
	int updateFinancialInstitution(@Param("address") String address, @Param("brstn") String brstn,
			@Param("bancnetCode") String bancnetCode, @Param("pchcCode") String pchcCode,
			@Param("swiftCode") String swiftCode, @Param("transferEnabled") int transferEnabled,
			@Param("name") String name);
}
