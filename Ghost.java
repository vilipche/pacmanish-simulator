public class Ghost extends Person {
	
	private static int cpt = 1;
	private int number;
	
	public Ghost(int x, int y) {
		super(x, y, Direction.STAY);
		this.number = cpt++;
	}

	@Override
	public String toString() {
		return ""+this.number;
	}

	//Gets random Direction and checks if that next case is not a wall
	@Override
	public void findBestDirection(Terrain t) {
		
		boolean canIGo = false;
		
		while(true) {
			int dir = (int)(Math.random()*5);
			switch(dir) {
				case 0: canIGo = t.getCase(this.x - 1, this.y).isAccessible();
					break;
				case 1: canIGo = t.getCase(this.x + 1, this.y).isAccessible();
					break;
				case 2: canIGo = t.getCase(this.x, this.y - 1).isAccessible();
					break;
				case 3: canIGo = t.getCase(this.x, this.y + 1).isAccessible();
					break;
				default:
					break;					
			}
			
			if(canIGo) {
				this.direction = direction.randomDirection(dir);
				return;
			}
		}
		
	}

	@Override
	public void moveInDirection(Terrain t) {
		findBestDirection(t); 
		applyDirection();
		
	}
	
	public int getNumber() {
		return this.number;
	}
	

}

