package id.co.indivara.finalproject.hotelrev.repo;

import id.co.indivara.finalproject.hotelrev.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface CustomerRepo extends CrudRepository<Customer, Integer> {
}
