package com.accenture.tigital.libraries.typings;

public class NoteInput {
    private String subject;
    private String content;

    public NoteInput(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
