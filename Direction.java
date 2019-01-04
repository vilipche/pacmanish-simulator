public class Direction {
	private int x;
	private int y;
	
	public Direction(int xDir, int yDir) {
		x = xDir;
		y = yDir;
	}
	
	//TODO random constructor
	
	public static Direction UP = new Direction(-1,0);
	public static Direction DOWN = new Direction(1,0);
	public static Direction LEFT = new Direction(0,-1);
	public static Direction RIGHT = new Direction(0,1);
	public static Direction STAY = new Direction(0,0);
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	//returns the oposite direction 
	public Direction opositeDirection() {
		return new Direction(this.x * (-1), this.y * (-1));
	}
	
	//generates random direction from a random number from [0,3]
	public Direction randomDirection(int rand) {
		switch(rand) {
		case 0: 
			return Direction.UP;
		case 1:
			return Direction.DOWN;
		case 2:
			return Direction.LEFT;
		case 3:
			return Direction.RIGHT;
		default:
			return Direction.STAY;
		}
	}
	
	
	//generates random direction, checks if it goes only horizontaly and verticaly
	public static Direction randomDirection() {
		int x = (int)(Math.random()*3)-1;
		int y = (int)(Math.random()*3)-1;
		while(x!=0 && y!=0) {
			x = (int)(Math.random()*3)-1;
			y = (int)(Math.random()*3)-1;
		}
		return new Direction(x,y);
	}

	
}
