package id.co.indivara.finalproject.hotelrev.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "room_number")
    private Integer roomNumber;

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "room_available")
    private Boolean roomAvailable;

    @Column(name = "room_description")
    private String roomDescription;

    @Column(name = "total_rent")
    private Integer totalRent;

    @Column(name = "id_cust")
    private Integer idCust;

}
