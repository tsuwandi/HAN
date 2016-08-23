package module.dryin;

public enum DryInType {
	COMPLETED("COMPLETED"), FINAL("FINAL");
	
	private final String text;

    /**
     * @param text
     */
    private DryInType(final String text) {
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
