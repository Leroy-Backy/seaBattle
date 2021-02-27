public enum Sym {
    CLEAR("⬜️"), SHIP("✡️"), CRUSHED("✳️"), PAINTED("\uD83D\uDFE7");

    private String sym;

    Sym(String sym){
        this.sym = sym;
    }

    public String toString(){
        return sym;
    }
}
