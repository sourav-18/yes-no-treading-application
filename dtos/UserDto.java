package com.ms.yes_no_treading_application.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private Double depositBalance;
    private Double winBalance;
}
