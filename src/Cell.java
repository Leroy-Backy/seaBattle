public class Cell {
    private String symbol;
    private int shipId;
    private boolean filled;

    public Cell(){
        this.symbol = Sym.CLEAR.toString();
        this.filled = false;
    }

    public boolean getFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public String toString(){
        return symbol;
    }
}
