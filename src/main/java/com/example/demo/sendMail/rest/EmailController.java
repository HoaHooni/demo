package com.example.demo.sendMail.rest;

import com.example.demo.exception.HandleException;
import com.example.demo.sendMail.entity.EmailDetails;
import com.example.demo.sendMail.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sendMail")
public class EmailController {
    @Autowired
    private IEmailService emailService;

    // Sending a simple Email
    @PostMapping
    public String sendMail(@RequestBody EmailDetails details) throws HandleException {
        String status
                = emailService.sendSimpleMail(details);

        return status;
    }

    // Sending email with attachment
    @PostMapping("/Attachment")
    public String sendMailWithAttachment(
            @RequestBody EmailDetails details) throws HandleException {
        String status
                = emailService.sendMailWithAttachment(details);

        return status;
    }
}
