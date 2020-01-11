package com.crud.tasks.domain;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor
//@RequiredArgsConstructor
public class TaskDto {
    private Long id;
    private String title;
    private String content;
}
