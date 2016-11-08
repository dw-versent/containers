package net.tqxr.containers.client;

public class MessageBody {

    public String message;

    public String date;


    @Override
    public String toString() {
        return String.format("MESSAGE: '%s'\nDATE: %s",
                message, date);
    }
}
