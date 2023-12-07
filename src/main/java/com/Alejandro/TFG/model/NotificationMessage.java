package com.Alejandro.TFG.model;

import java.util.Map;

import lombok.Data;

@Data
public class NotificationMessage {
    public String recipientToken;
    public String title;
    public String body;
    
    private Map<String,String> data;
}
