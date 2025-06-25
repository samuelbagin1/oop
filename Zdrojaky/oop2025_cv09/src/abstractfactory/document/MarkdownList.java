package abstractfactory.document;

class MarkdownList implements List {
    private String[] data;

    public MarkdownList(String ... elements) {
        data = elements;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public String getElement(int index) {
        return data[index];
    }

    @Override
    public String getFormattedContent() {
        StringBuilder builder = new StringBuilder();
        for (String text: data) {
            builder.append("- " + text + "\n");
        }
        return builder.toString();
    }
}
