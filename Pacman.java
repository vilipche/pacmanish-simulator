public class Pacman extends Person {
	private int lifes = 3;
	private int score = 0;

	
	
	public Pacman(int x, int y) {
		super(x, y, Direction.STAY);
	}
	

	public String toString() {
		if(direction == Direction.UP) 
			return "v";
		if(direction == Direction.DOWN)
			return "^";
		if(direction == Direction.RIGHT)
			return "<";
		if(direction == Direction.LEFT) 
			return ">";
		return "<";
		 
		
	}
	
	//check if that case where the pacman is over has food, if there is he eats it
	public void eatFood(Case current) {
		if(current instanceof Food) {
			if( ((Food)current).hasFood() ) {
				((Food)current).setHasFood(false);
				this.addScore();
				Food.decreaseCpt();
			}
		}
	}
	
	//Finds the best direction by avoiding ghosts and walls
	public void findBestDirection(Terrain t) {
		
		if( t.getCase(this.x, this.y + 1).isAccessible() && !t.ghostOnCase(this.x, this.y + 1)) {
			if( ((Food)t.getCase(this.x, this.y + 1)).hasFood() ) {
				this.direction = Direction.RIGHT;
				return;
			}			
		}
		
		if( t.getCase(this.x, this.y - 1).isAccessible() && !t.ghostOnCase(this.x, this.y - 1)) {
			if( ((Food)t.getCase(this.x, this.y - 1)).hasFood() ) {
				this.direction = Direction.LEFT;
				return;
			}			
		}
		
		if( t.getCase(this.x - 1, this.y).isAccessible() && !t.ghostOnCase(this.x - 1, this.y)) {
			if( ((Food)t.getCase(this.x - 1, this.y)).hasFood() ) {
				this.direction = Direction.UP;
				return;
			}			
		}
		
		if( t.getCase(this.x + 1, this.y).isAccessible() && !t.ghostOnCase(this.x + 1, this.y)) {
			if( ((Food)t.getCase(this.x + 1, this.y)).hasFood() ) {
				this.direction = Direction.DOWN;
				return;
			}			
		}
		
		//Si il n y a pas de nuriture autour pacman, il va choisir aleatoirement une direction
		//Parfois avec le do while, pacman ne peut pas etre mange.
		//si je mets cette piece de code en commentaire et en decomment l'autre, pacman peut mourir...
		
		Direction newDir;
		do {
			newDir = Direction.randomDirection();
		}
		while(!(t.getCase(this.x + newDir.getX(), this.y + newDir.getY()).isAccessible() && 
				!t.ghostOnCase(this.x + newDir.getX(), this.y + newDir.getY())));
			
			
			this.direction = newDir;
			
			return;

/*		
		
		Direction newDir = Direction.randomDirection();
		if( t.getCase(this.x + newDir.getX(), this.y + newDir.getY()).isAccessible() && 
				!t.ghostOnCase(this.x + newDir.getX(), this.y + newDir.getY())) {
			this.direction = newDir;
			return;
		} else {
			this.direction = Direction.STAY;
		 	return;
		}
*/		
		
	}
				
	
	//Makes the pacman move
	public void moveInDirection(Terrain t) {
		findBestDirection(t);
		applyDirection();
		eatFood(this.getPacman(t));
	}
	
	
	
	public void addScore() {
		score = score + 10;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public int getLifes() {
		return this.lifes;
	}
	
	public boolean isDead() {
		return this.lifes == 0;
	}
	
	public void lifeLost() {
		this.lifes -= 1;  
	}
	
	public void reborn() {
		this.x = 1;
		this.y = 1;
	}
	
	public void decreaseScore() {
		score -= 50;
	}

	public Case getPacman(Terrain t) {
		return t.getCase(this.x, this.y);
	}
	

}
