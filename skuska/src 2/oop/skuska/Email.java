package oop.skuska;

public class Email {
    private String sender;
    private String receiver;
    private String subject; // nepovinny
    private String content;
    private byte[] attachment;  // nepovinny

    public Email(String sender, String receiver, String subject, String content, byte[] attachment) {
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.content = content;
        this.attachment = attachment;
//        EmailBuilder builder = new EmailBuilder();
//        builder.setSender(sender);
//        builder.setReceiver(receiver);
//        builder.setSubject(subject);
//        builder.setContent(content);
//        builder.setAttachment(attachment);
//
//        Email email = builder.build();
//        this.sender = email.getSender();
//        this.receiver = email.getReceiver();
//        this.subject = email.getSubject();
//        this.content = email.getContent();
//        this.attachment = email.getAttachment();
    }


    public Email() {}

    public byte[] getAttachment() {
        return attachment;
    }

    public String getContent() {
        return content;
    }

    public String getSubject() {
        return subject;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSender() {
        return sender;
    }

}
