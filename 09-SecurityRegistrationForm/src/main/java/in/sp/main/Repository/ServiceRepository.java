package in.sp.main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.main.Entity.Customer;

public interface ServiceRepository extends JpaRepository<Customer,Long> {

	Customer findByEmail(String email);

	
}
