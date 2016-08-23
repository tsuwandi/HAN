package module.dryout;

public enum DryOutType {
	COMPLETED("COMPLETED"), FINAL("FINAL");
	
	private final String text;

    /**
     * @param text
     */
    private DryOutType(final String text) {
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
