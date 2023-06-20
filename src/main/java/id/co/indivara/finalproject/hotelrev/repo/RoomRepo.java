package id.co.indivara.finalproject.hotelrev.repo;

import id.co.indivara.finalproject.hotelrev.entity.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface RoomRepo extends CrudRepository<Room, Integer> {
    List<Room> findByRoomAvailable(Boolean b);

    List<Room> findByRoomTypeAndRoomAvailable(String roomType, Boolean b);

    Room findByRoomNumber(Integer a);

    @Query("SELECT roomType , SUM(totalRent) FROM Room GROUP BY roomType")
    List<Object[]> findTotalRentByRoomType();
}