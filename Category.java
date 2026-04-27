public enum Category {
    JAVA("Java", "☕"),
    PYTHON("Python", "🐍"),
    CPP("C++", "⚙️");
    DBMS("DBMS","📥");
 
    private final String displayName;
    private final String icon;

    Category(String displayName, String icon) {
        this.displayName = displayName;
        this.icon = icon;
    }

    public String getDisplayName() { return displayName; }
    public String getIcon()        { return icon; }

    @Override
    public String toString() { return icon + " " + displayName; }
}
