package com.lxn.code.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogQuery implements Serializable {
    private String title;
    private Long typeId;
    private Boolean recommend;

}
