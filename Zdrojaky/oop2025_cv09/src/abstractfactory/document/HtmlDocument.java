package abstractfactory.document;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class HtmlDocument implements Document {
    private java.util.List<Element> data;

    public HtmlDocument() {
        data = new LinkedList<Element>();
    }

    @Override
    public void add(Element element) {
        data.add(element);
    }

    @Override
    public void save(String fileName) throws IOException {
        String header = """
                <!DOCTYPE html>
                <html>
                    <head>
                        <title>Nazov html dokumentu</title>
                    </head>
                    <body>
                    
                """;
        String footer = """
                    </body>
                </html>    
                """;

        try (FileWriter fileWriter = new FileWriter(fileName + ".html");
             BufferedWriter writer = new BufferedWriter(fileWriter)
        ) {
            writer.write(header);
            for (Element e: data) {
                writer.write(e.getFormattedContent() + "\n");
            }
            writer.write(footer);
        }
    }
}
