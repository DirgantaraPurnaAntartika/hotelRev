package id.co.indivara.finalproject.hotelrev.controller;

import id.co.indivara.finalproject.hotelrev.entity.ResponseMessage;
import id.co.indivara.finalproject.hotelrev.entity.Room;
import id.co.indivara.finalproject.hotelrev.repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomRepo roomRepo;

    ResponseMessage responseMessage;

    @PostMapping("/createRoom")
    public ResponseMessage createRoom(@RequestBody Room room){
        ResponseMessage responseMessage=new ResponseMessage();
        try {
            roomRepo.save(room);
            responseMessage.setCode(200);
            responseMessage.setMessage("BERHASIL MENAMBAHKAN ROOM");
        }catch (Exception ex){
            responseMessage.setCode(201);
            responseMessage.setMessage("GAGAL MENAMBAHKAN ROOM "+ex.getMessage());
        }
        return responseMessage;
    }

    @PatchMapping("/update")
    public ResponseMessage updateRoom(@RequestBody Room room){
        ResponseMessage responseMessage= new ResponseMessage();
        try {
            Room r=(Room) roomRepo.findByRoomNumber(room.getRoomNumber());
            r.setRoomNumber(room.getRoomNumber());
            r.setRoomType(room.getRoomType());
            r.setRoomAvailable(room.getRoomAvailable());
            r.setTotalRent(room.getTotalRent());
            r.setRoomDescription(room.getRoomDescription());
            r.setIdCust(room.getIdCust());
            roomRepo.save(r);
            responseMessage.setCode(100);
            responseMessage.setMessage("Update berhasil");
        } catch (Exception ex){
            responseMessage.setCode(001);
            responseMessage.setMessage("Update gagal" +ex.getMessage());
        }
        return responseMessage;
    }

    @GetMapping("/all")
    public ArrayList<Room> findAllRoom(){
        ArrayList<Room> rooms=(ArrayList<Room>) roomRepo.findAll();
        return rooms;
    }

    @GetMapping("/available")
    public List<Room> cekRoom(){
        List<Room> room =roomRepo.findByRoomAvailable(true);
        return room;
    }

    @GetMapping("/hotelReport")
    public List<Object[]> totalSewa(){
        List<Object[]> rooms =roomRepo.findTotalRentByRoomType();
        return rooms;
    }

    @GetMapping("/findById/{roomId}")
    public Room findId (@PathVariable Integer roomId){
        return roomRepo.findById(roomId).get();
    }

    @GetMapping("/findByRoomNumber/{roomNumber}")
    public Room findNoKamar (@PathVariable Integer roomNumber){
        Room room = (Room) roomRepo.findByRoomNumber(roomNumber);
        return room;
    }
}
