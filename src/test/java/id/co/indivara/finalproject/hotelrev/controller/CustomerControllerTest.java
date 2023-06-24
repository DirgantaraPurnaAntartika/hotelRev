package id.co.indivara.finalproject.hotelrev.controller;

import id.co.indivara.finalproject.hotelrev.entity.Customer;
import id.co.indivara.finalproject.hotelrev.repo.CustomerRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CustomerControllerTest {
    @Autowired
    CustomerRepo customerRepo;

    @Test
    public void testRegister(){
        Customer c = new Customer();
        c.setName("Dirgantara");
        c.setAge(19);
        customerRepo.save(c);
    }

    @Test
    public void testFindAll(){
        List<Customer> list = (List<Customer>) customerRepo.findAll();
    }

    @Test
    public void testFindById(){
        Customer customer = customerRepo.findById(14).get();
    }

    @Test
    public void testReserve(){

    }

    @Test
    public void testCheckIn(){

    }

    @Test
    public void testCheckOut(){

    }
}