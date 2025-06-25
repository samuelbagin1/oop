package abstractfactory.document;

class HtmlList implements List {
    private String[] data;

    public HtmlList(String ... elements) {
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
        builder.append("<ul>\n");
        for (String text: data) {
            builder.append("    <li>" + text + "</li>" + "\n");
        }
        builder.append("</ul>\n");
        return builder.toString();
    }

}
