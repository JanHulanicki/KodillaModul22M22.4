package com.crud.tasks.domain;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Long id;
    private String title;
    private String content;
}
