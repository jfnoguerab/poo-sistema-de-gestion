package models;
/*
 * Class OrderType
 * Tipo de dato enum para ser usado
 * con los "orders" los cuales pueden
 * ser ascendente (ASC) o descendente (desc)
 */
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
