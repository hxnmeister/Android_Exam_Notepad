package com.ua.project.android_exam_notepad.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Note implements Serializable {
    private Integer id;
    private String title;
    private String text;
}
