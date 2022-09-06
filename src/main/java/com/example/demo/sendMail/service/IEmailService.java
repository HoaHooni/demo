package com.example.demo.sendMail.service;

import com.example.demo.exception.HandleException;
import com.example.demo.sendMail.entity.EmailDetails;

public interface IEmailService {
    String sendSimpleMail(EmailDetails details) throws HandleException;
    String sendMailWithAttachment(EmailDetails details) throws HandleException;
}
