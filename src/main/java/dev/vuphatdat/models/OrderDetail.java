package dev.vuphatdat.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "oeder_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_deail_id")
    private Long id;

    private Double total;

    @Column(name = "create_at")
    private LocalDate createAt;

    @Column(name = "modified_at")
    private LocalDate modifiedAt;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;
}
