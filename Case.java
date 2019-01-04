public abstract class Case {
	protected int x;
	protected int y;
	protected boolean accessible; //allows us to know if the person can step on the case
	
	
	public Case(int x, int y, boolean access) {
		this.x = x;
		this.y = y;
		this.accessible = access;
	}	
	
	public abstract String toString();
	
	public boolean isAccessible() {
		return this.accessible;
	}
	
}
