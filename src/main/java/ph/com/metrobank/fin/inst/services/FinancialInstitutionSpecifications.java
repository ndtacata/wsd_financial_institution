package ph.com.metrobank.fin.inst.services;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ph.com.metrobank.fin.inst.model.FinancialInstitution;

@Component
public class FinancialInstitutionSpecifications {

	public Specification<FinancialInstitution> findSpecificationsByFinInstTransferType(String transferType) {
		
		return (final Root<FinancialInstitution> root, final CriteriaQuery<?> query, final CriteriaBuilder cb) -> {
			
			List<Predicate> predicates =  new ArrayList<>();
			predicates.add(cb.isNotNull(root.get(transferType)));
			query.orderBy(cb.asc(root.get("name")));
			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}
	
	public Specification<FinancialInstitution> findSpecificationsByBank(String bank, String bankType) {
		
		return (final Root<FinancialInstitution> root, final CriteriaQuery<?> query, final CriteriaBuilder cb) -> {
			
			List<Predicate> predicates =  new ArrayList<>();
			predicates.add(cb.isNotNull(root.get(bankType)));
			predicates.add(cb.and(cb.equal(root.get(bankType), bank)));
			query.orderBy(cb.asc(root.get("name")));
			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}
}
