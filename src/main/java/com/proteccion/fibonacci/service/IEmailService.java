package com.proteccion.fibonacci.service;

public interface IEmailService {
    void sendEmail(String to, String subject, String text) throws Exception;
}
