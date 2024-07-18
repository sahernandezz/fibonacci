package com.proteccion.fibonacci.web.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.ToString;

@ToString
public class TimeRequest {

    @NotBlank(message = "Time is mandatory")
    private String time; // HH:MM:SS format

    public int getMinutes() {
        return Integer.parseInt(time.split(":")[1]);
    }

    public int getSeconds() {
        return Integer.parseInt(time.split(":")[2]);
    }

    public String getTime() {
        return time;
    }
}
