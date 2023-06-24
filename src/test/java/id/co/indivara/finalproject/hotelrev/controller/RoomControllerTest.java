package id.co.indivara.finalproject.hotelrev.controller;

import id.co.indivara.finalproject.hotelrev.entity.Room;
import id.co.indivara.finalproject.hotelrev.repo.RoomRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoomControllerTest {

    @Autowired
    RoomRepo roomRepo;

    @Test
    public void createRoom() {
        Room r= new Room();
        r.setRoomNumber(101);
        r.setRoomType("Deluxe");
        r.setRoomAvailable(true);
        r.setRoomDescription("Ready");
        r.setTotalRent(0);
        r.setIdCust(0);
        roomRepo.save(r);
    }

    @Test
    public void findAllRoom() {
        List<Room> list = (List<Room>) roomRepo.findAll();
    }

    @Test
    public void findId() {
        Room room = roomRepo.findById(15).get();
    }

    @Test
    public void findNoKamar() {
        Room room = roomRepo.findByRoomNumber(101);
    }

    @Test
    public void updateRoom() {
        Room r = roomRepo.findById(15).get();
        r.setRoomDescription("KOSONGGGG");
        roomRepo.save(r);
    }


    @Test
    public void cekRoom() {
        List<Room> list = roomRepo.findByRoomAvailable(true);
    }

    @Test
    public void totalSewa() {
        List<Object[]> list = roomRepo.findTotalRentByRoomType();
    }

}