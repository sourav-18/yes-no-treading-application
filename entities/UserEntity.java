package com.ms.yes_no_treading_application.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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

    @ColumnDefault("0")
    @Column(nullable = false,name = "deposit_balance")
    private Double depositBalance= 0.0;

    @ColumnDefault("0")
    @Column(nullable = false,name = "win_balance")
    private Double winBalance=0.0;
}
