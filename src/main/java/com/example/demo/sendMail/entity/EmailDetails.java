package com.example.demo.sendMail.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {
    // Class data members
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
