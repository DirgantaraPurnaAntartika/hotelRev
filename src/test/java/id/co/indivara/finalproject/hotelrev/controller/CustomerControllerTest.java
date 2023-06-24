package id.co.indivara.finalproject.hotelrev.controller;

import id.co.indivara.finalproject.hotelrev.entity.Customer;
import id.co.indivara.finalproject.hotelrev.entity.Room;
import id.co.indivara.finalproject.hotelrev.repo.CustomerRepo;
import id.co.indivara.finalproject.hotelrev.repo.RoomRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CustomerControllerTest {
    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    RoomRepo roomRepo;

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
    public void testReserve() {
        List<Room> roomList = roomRepo.findByRoomTypeAndRoomAvailable("Deluxe", true);
        if (!roomList.isEmpty()) {
            Room room = roomList.stream().findAny().get();
            room.setRoomAvailable(false);
            room.setRoomDescription("telah di reserve");
            room.setIdCust(14);
            roomRepo.save(room);
        }
    }

    @Test
    public void testCheckIn() {
        Customer customer = customerRepo.findById(14).get();
        if (customer != null) {
            Room rooms = roomRepo.findByRoomNumber(101);
            if (!rooms.getRoomAvailable() && rooms.getIdCust() == customer.getIdCust()) {
                rooms.setRoomDescription(customer.getName() + " telah check in"); //R
                rooms.setIdCust(customer.getIdCust());
                roomRepo.save(rooms);
            }
        }
    }

    @Test
    public void testCheckOut(){
        Customer customer = customerRepo.findById(14).orElse(null);
        Room room =roomRepo.findByRoomNumber(101);
        if (room != null){
            room.setRoomAvailable(true);
            room.setTotalRent(room.getTotalRent()+1);
            room.setRoomDescription("Ready");
            room.setIdCust(null);
            roomRepo.save(room);
        }
    }
}