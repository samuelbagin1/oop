package abstractfactory.document;

public class HtmlFactory implements Factory {
    @Override
    public Document createDocument() {
        return new HtmlDocument();
    }

    @Override
    public Heading createHeading(HeadingLevel level, String content) {
        return new HtmlHeading(level, content);
    }

    @Override
    public Paragraph createParagraph(boolean bold, String content) {
        return new HtmlParagraph(bold, content);
    }

    @Override
    public List createList(String... elements) {
        return new HtmlList(elements);
    }
}
