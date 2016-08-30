package module.pembelian;

public enum ReceivedType {
	COMPLETED("COMPLETED"), FINAL("FINAL");
	
	private final String text;

    /**
     * @param text
     */
    private ReceivedType(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
