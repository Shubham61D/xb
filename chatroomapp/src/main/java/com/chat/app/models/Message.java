package com.chat.app.models;


public class Message {

    private String name;
    private String contents;


    public Message(String name, String contents) {
        this.name = name;
        this.contents = contents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "Message [name=" + name + ", contents=" + contents + "]";
    }
}
