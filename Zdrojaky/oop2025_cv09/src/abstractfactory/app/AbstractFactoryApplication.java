package abstractfactory.app;

import abstractfactory.document.Document;
import abstractfactory.document.Factory;
import abstractfactory.document.HeadingLevel;
import abstractfactory.document.HtmlFactory;
import abstractfactory.document.MarkdownFactory;

import java.io.IOException;

public class AbstractFactoryApplication {
    public static void main(String[] args) throws IOException {
//        Factory factory = new HtmlFactory();
        Factory factory = new MarkdownFactory();

        Document document = factory.createDocument();
        document.add(factory.createHeading(HeadingLevel.LEVEL1, "Objektovo orientovane programovanie"));
        document.add(factory.createHeading(HeadingLevel.LEVEL2, "1. cvicenie"));
        document.add(factory.createParagraph(false, "Na cviceni sme preberali kompilaciu kodu"));
        document.add(factory.createParagraph(true, "Nainstalujte si vyvojove prostredie a vytvorte jednoduchy program"));
        document.add(factory.createHeading(HeadingLevel.LEVEL2, "2. cvicenie"));
        document.add(factory.createParagraph(false, "Na cviceni sme preberali"));
        document.add(factory.createList("git", "primitivne a referencne typy", "literaly", "obalkove triedy"));
        document.save("vystup");
    }
}
