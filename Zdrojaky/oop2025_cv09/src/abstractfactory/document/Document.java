package abstractfactory.document;

import java.io.IOException;

public interface Document {
    void add(Element element);
    void save(String fileName) throws IOException;
}
