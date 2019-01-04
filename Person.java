public abstract class Person {
	protected int x;
	protected int y;
	protected Direction direction;
	
	
	public Person(int x, int y, Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	//moves the person to the direction
	public void applyDirection() {
		this.x += this.direction.getX();
		this.y += this.direction.getY();
		//this.direction = Direction.STAY;
	}
	
	
	public abstract String toString();
	public abstract void findBestDirection(Terrain t);
	public abstract void moveInDirection(Terrain t);
	

}
