package com.jms;

public class MessageProtocol 
{
    public String handleProtocolMessage(String messageText) {
        String responseText;
        if (messageText == null || !messageText.startsWith("MyProtocolMessage"))
        	responseText = "Unknown protocol message: " + messageText;
        else
            responseText = "I recognize your protocol message";
         
        return responseText;
    }
}