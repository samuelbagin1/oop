package abstractfactory.document;

public interface Factory {
    Document createDocument();
    Heading createHeading(HeadingLevel level,String content);
    Paragraph createParagraph(boolean bold, String content);
    List createList(String ... elements);
}
