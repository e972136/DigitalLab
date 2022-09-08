package com.gaspar.demo01;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Greeting {
    private Integer id;
    private String message;
}
