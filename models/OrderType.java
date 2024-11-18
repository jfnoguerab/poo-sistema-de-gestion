package models;

public enum OrderType {
    ASC("asc"), DESC("desc");
    
    private String name;

    OrderType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
