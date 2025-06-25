package abstractfactory.document;

class HtmlParagraph implements Paragraph {
    private boolean bold;
    private String content;

    public HtmlParagraph(boolean bold, String content) {
        this.bold = bold;
        this.content = content;
    }

    @Override
    public boolean isBold() {
        return bold;
    }

    @Override
    public String getFormattedContent() {
        return "<p>" + (bold ? "<b>" : "") + content + (bold ? "</b>" : "") + "</p>";
    }
}
