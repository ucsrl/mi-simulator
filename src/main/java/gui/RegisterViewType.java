package gui;

public enum RegisterViewType {
    BINARY("BinĂ¤r"),
    DECIMAL("Dezimal"),
    HEX("Hexadezimal"),
    FLOAT("Float");

    private final String name;

    RegisterViewType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
