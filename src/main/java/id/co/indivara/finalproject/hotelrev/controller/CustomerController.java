package id.co.indivara.finalproject.hotelrev.controller;

import id.co.indivara.finalproject.hotelrev.entity.Customer;
import id.co.indivara.finalproject.hotelrev.entity.ResponseMessage;
import id.co.indivara.finalproject.hotelrev.entity.Room;
import id.co.indivara.finalproject.hotelrev.repo.CustomerRepo;
import id.co.indivara.finalproject.hotelrev.repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    RoomRepo roomRepo;
    ResponseMessage responseMessage;

    @PostMapping("/registerCustomer")
    public ResponseMessage registerCustomer(@RequestBody Customer customer){
        try {
            customerRepo.save(customer);
            return new ResponseMessage(100,"berhasil register data customer");
        }catch (Exception ex){
            return new ResponseMessage(001, "gagal register data customer "+ ex.getMessage());
        }
    }

    @GetMapping("/findAllCustomer")
    public List<Customer> findAllCustomer(){
        List<Customer> customerList = (List<Customer>) customerRepo.findAll();
        return customerList;
    }

    @GetMapping("/findById/{idCust}")
    public Customer findCustomerById(@PathVariable Integer idCust){
        Customer customer = customerRepo.findById(idCust).get();
        return customer;
    }

    @PostMapping("/reserve/{idCust}/{roomType}")
    public ResponseMessage reserveRoom(@PathVariable Integer idCust, @PathVariable String roomType){
        try {
            List<Room> roomList = roomRepo.findByRoomTypeAndRoomAvailable(roomType, true);
            if (!roomList.isEmpty()){
                Room room = roomList.stream().findAny().get();
                room.setRoomAvailable(false);
                room.setRoomDescription("telah di reserve");
                room.setIdCust(idCust);
                roomRepo.save(room);
                return new ResponseMessage(100,"reserve berhasil, nomer kamar " + room.getRoomNumber());
            }
            return new ResponseMessage();
        } catch (Exception ex){
            return new ResponseMessage(001,"Reserve gagal " +ex.getMessage());
        }
    }

    @PostMapping("/checkin/{idCust}/{roomNumber}")
    public  ResponseMessage checkin (@PathVariable Integer idCust, @PathVariable Integer roomNumber) {
        try {
            Customer customer = customerRepo.findById(idCust).get();
            if (customer != null) {
                Room rooms = roomRepo.findByRoomNumber(roomNumber);
                 if (!rooms.getRoomAvailable() && rooms.getIdCust() == customer.getIdCust()) {
                    rooms.setRoomDescription(customer.getName() + " telah check in"); //R
                    rooms.setIdCust(customer.getIdCust());
                    roomRepo.save(rooms);
                    return new ResponseMessage(100, "customer " + customer.getName() + " berhasil check in"); //R
                } else {
                    return new ResponseMessage(001, "Kamar telah di reserve");
                }
            }
            return new ResponseMessage(); //R
        } catch (Exception ex) {
            return new ResponseMessage(001, "Checkin Gagal " + ex.getMessage());
        }
    }

    @PostMapping("/checkout/{idCust}/{roomNumber}")
    public ResponseMessage checkout (@PathVariable Integer idCust, @PathVariable Integer roomNumber){
        try {
            Customer customer = customerRepo.findById(idCust).orElse(null);
            Room room =roomRepo.findByRoomNumber(roomNumber);
            if (room != null){
                room.setRoomAvailable(true);
                room.setTotalRent(room.getTotalRent()+1);
                room.setRoomDescription("Ready");
                room.setIdCust(null);
                roomRepo.save(room);
            }
            return new ResponseMessage(100,"checkout berhasil");
        } catch (Exception ex){
            return new ResponseMessage(001,"checkout gagal " + ex.getMessage());
        }
    }
}