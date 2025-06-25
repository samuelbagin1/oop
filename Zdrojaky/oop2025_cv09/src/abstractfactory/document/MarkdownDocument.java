package abstractfactory.document;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

class MarkdownDocument implements Document {
    private java.util.List<Element> data;

    public MarkdownDocument() {
        data = new LinkedList<Element>();
    }

    @Override
    public void add(Element element) {
        data.add(element);
    }

    @Override
    public void save(String fileName) throws IOException {
        try (FileWriter fileWriter = new FileWriter(fileName + ".md");
             BufferedWriter writer = new BufferedWriter(fileWriter)
        ) {
            for (Element e: data) {
                writer.write(e.getFormattedContent() + "\n\n");
            }
        }
    }
}
