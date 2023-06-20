package id.co.indivara.finalproject.hotelrev.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cust")
    private Integer idCust;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;
}
