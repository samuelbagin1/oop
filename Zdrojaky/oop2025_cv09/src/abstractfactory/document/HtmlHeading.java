package abstractfactory.document;

class HtmlHeading implements Heading {
    private HeadingLevel level;
    private String content;

    public HtmlHeading(HeadingLevel level, String content) {
        this.level = level;
        this.content = content;
    }

    @Override
    public HeadingLevel getLevel() {
        return level;
    }

    @Override
    public String getFormattedContent() {
        return switch (level) {
            case LEVEL1 -> "<h1>" + content + "</h1>";
            case LEVEL2 -> "<h2>" + content + "</h2>";
        };
    }
}
