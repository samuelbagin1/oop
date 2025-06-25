package oop.skuska;

public class EmailBuilder implements EmailBuilderInterface {
    private String sender;
    private String receiver;
    private String subject; // nepovinny
    private String content;
    private byte[] attachment;  // nepovinny

    public EmailBuilder() {
        sender = null;
        receiver = null;
        subject = null;
        content = null;
        attachment = null;
    }

    @Override
    public Email build() throws EmailNotBuildableException {
        if (sender == null || sender.isEmpty()) {
            throw new EmailNotBuildableException("Sender is required");
        }
        if (receiver == null || receiver.isEmpty()) {
            throw new EmailNotBuildableException("Receiver is required");
        }
        if (content == null || content.isEmpty()) {
            throw new EmailNotBuildableException("Content is required");
        }

        Email email = new Email(sender, receiver, subject, content, attachment);

        reset();

        return email;
    }

    @Override
    public void setSender(String sender)  {
        // if (sender == null || sender.isEmpty()) throw new EmailNotBuildableException("Sender cannot be null or empty.");
        this.sender = sender;
    }

    @Override
    public void setReceiver(String receiver) {
        if (receiver == null || receiver.isEmpty()) {
            //throw new EmailNotBuildableException("Receiver cannot be null or empty.");
        }
        this.receiver = receiver;
    }

    @Override
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public void setContent(String content) {
        if (content == null || content.isEmpty()) {
            //throw new EmailNotBuildableException("Content cannot be null or empty.");
        }
        this.content = content;
    }

    @Override
    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    @Override
    public void reset() {
        this.sender = null;
        this.receiver = null;
        this.subject = null;
        this.content = null;
        this.attachment = null;
    }


    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSubject() {
        return subject;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public String getContent() {
        return content;
    }
}
