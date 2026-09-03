package com.ms.yes_no_treading_application.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity extends BaseEntity{
    @Column(nullable = false)
    private String name;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;


    @Column(name = "deposit_balance",nullable = false, columnDefinition="Decimal(10,2)")
    private Double depositBalance;


    @Column(name = "win_balance",nullable = false, columnDefinition="Decimal(10,2)")
    private Double winBalance;

    @OneToMany(mappedBy = "userEntity")
    List<BidEntity> bids;
}
