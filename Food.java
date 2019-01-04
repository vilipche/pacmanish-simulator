public class Food extends Case {
	private boolean hasFood;
	private static int cpt = 0;

	public Food(int x, int y) {
		super(x, y, true);
		this.hasFood = true;
		cpt++;
	}
	
	public String toString() {
		return this.hasFood()?"~":" ";
	}

	public boolean hasFood() {
		return hasFood;
	}

	public void setHasFood(boolean hasFood) {
		this.hasFood = hasFood;
	}

	public static int getCpt() {
		return cpt;
	}
	
	public static void decreaseCpt() {
		cpt = cpt - 1;
	}
	
	

	
}
