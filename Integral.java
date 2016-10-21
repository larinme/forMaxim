public enum Integral {
    FIRST("1. ∫ -(\u221A(x\u00B2 + 3x - 1)) / x dx"),
    SECOND("2. \u222Bx\u00B3 dx"),
    THIRD("3. ∫(8 + 2x - x\u00B2) dx");

    private String textIntegral;

    private Integral(String textIntegral) { this.textIntegral = textIntegral; }

    public String getTextIntegral() {
        return textIntegral;
    }
    public static void printIntegral() {
        for(Integral integral: Integral.values()) {
            System.out.println(integral.getTextIntegral());
        }
    }
}
