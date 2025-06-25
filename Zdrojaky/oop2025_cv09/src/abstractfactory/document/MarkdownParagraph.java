package abstractfactory.document;

class MarkdownParagraph implements Paragraph {
    private boolean bold;
    private String contents;

    public MarkdownParagraph(boolean bold, String contents) {
        this.bold = bold;
        this.contents = contents;
    }

    @Override
    public boolean isBold() {
        return bold;
    }

    @Override
    public String getFormattedContent() {
        String boldMark = bold ? "**" : "";
        return boldMark + contents + boldMark;
    }
}
