package abstractfactory.document;

class MarkdownHeading implements Heading {
    private HeadingLevel level;
    private String content;

    public MarkdownHeading(HeadingLevel level, String content) {
        this.level = level;
        this.content = content;
    }

    @Override
    public HeadingLevel getLevel() {
        return level;
    }

    @Override
    public String getFormattedContent() {
        String start = switch (level) {
            case LEVEL1 -> "#";
            case LEVEL2 -> "##";
        };
        return start + " " + content;
    }
}
