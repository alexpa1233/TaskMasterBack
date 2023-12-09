package com.Alejandro.TFG.model;

import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotificationMessage {
    public String recipientToken;
    public String title;
    public String body;
    
    private Map<String,String> data;
}
