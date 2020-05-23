package com.lxn.code.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Type implements Serializable {
    private long id;
    private String name;
}
