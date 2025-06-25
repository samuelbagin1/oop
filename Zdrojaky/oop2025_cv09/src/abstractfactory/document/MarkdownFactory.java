package abstractfactory.document;

public class MarkdownFactory implements Factory {
    @Override
    public Document createDocument() {
        return new MarkdownDocument();
    }

    @Override
    public Heading createHeading(HeadingLevel level, String content) {
        return new MarkdownHeading(level, content);
    }

    @Override
    public Paragraph createParagraph(boolean bold, String content) {
        return new MarkdownParagraph(bold, content);
    }

    @Override
    public List createList(String ... elements) {
        return new MarkdownList(elements);
    }
}
