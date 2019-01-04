import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Terrain {
	
	private static final int WIDTH = 20;
	private static final int HEIGHT = 20;
	private Case[][] tile;	
	private Pacman pacman;
	private ArrayList<Ghost> ghosts;
	private static String mapType;	
	
	//Constructor for creating a random Game
	public Terrain(Pacman pacman, ArrayList<Ghost> ghosts) {
		tile = new Case[WIDTH][HEIGHT];
		this.pacman = pacman;
		this.ghosts = ghosts;
		randomTerrain();
		Terrain.setMapType("Map: Random "+" "+WIDTH+"x"+HEIGHT);
	}
	
	//Constructor for creating a Game from a imported map
	public Terrain(Pacman pacman, ArrayList<Ghost> ghosts, File f) {
		tile = new Case[WIDTH][HEIGHT];
		this.pacman = pacman;
		this.ghosts = ghosts;
		importedTerrain(f);
		Terrain.setMapType("Map: Imported "+" "+WIDTH+"x"+HEIGHT);
	}
	
	//Function for reading from file and creating instances for Walls and Food
	public void importedTerrain(File f) {
		String[][] matrix = new String[WIDTH][HEIGHT];
		Scanner reader = null;
		
	try {
            reader = new Scanner(f);
        } catch (Exception e) {
            System.out.println(e);
            return; // we exit the method
        }
		
		int i=0;
		
        while (reader.hasNextLine()) {
            String[] array = reader.nextLine().split("");
            matrix[i] = array.clone();
            i++;
            
        }
        
        for(int k=0;k<WIDTH;k++) {
        	for(int j=0;j<HEIGHT;j++) {
        		
        		if(matrix[k][j].contains("#")) {
        			this.tile[k][j] = new Wall(k,j);
        		}
        		if(matrix[k][j].contains("~")) {
        			this.tile[k][j] = new Food(k,j);
        		}
        	}
        }
        Food pacmansFirstPosition = (Food) this.tile[pacman.getX()][pacman.getY()];
        pacmansFirstPosition.setHasFood(false);
        Food.decreaseCpt();
        
        reader.close();
        
	}
	
	//Creating a random terrain
	public void randomTerrain() {
		for(int i=0;i<WIDTH;i++) {
			for(int j=0;j<HEIGHT;j++) {
				//creating walls around
				if(i==0 || j==0 || j==WIDTH-1 || i == HEIGHT-1) {
					tile[i][j] = new Wall(i,j);
				}
				else if(Math.random()<0.1){
					tile[i][j] = new Wall(i,j);
				}
				else {
					
					tile[i][j] = new Food(i,j);
				}
			}
		}
		
		Food pacmansFirstPosition = (Food) this.tile[pacman.getX()][pacman.getY()];
        pacmansFirstPosition.setHasFood(false);
        Food.decreaseCpt();
	}
	
	//Function that makes the movement possible for all People (Ghosts and Pacman)
	public void movePeople() {
		pacman.moveInDirection(this);
		this.checkPacmanEaten();
		for(Ghost g : ghosts) {
			g.moveInDirection(this);
		}
		this.checkPacmanEaten();
	}
	

	
	public Case getCase(int x, int y) {
		return this.tile[x][y];
	}
	
	//Function that checks if Pacman is on the same position as one of the ghosts
	public void checkPacmanEaten() {
		for(Ghost g : ghosts) {
			if(g.getX() == pacman.getX() && g.getY() == pacman.getY()) {
				pacman.lifeLost();
				pacman.decreaseScore();
				pacman.reborn();
			}
		}
	}
	
	//Function that looks if there is a ghost on the (i,j) case 
	public boolean ghostOnCase(int i, int j) {
		for(Ghost g: ghosts) {
			if(g.getX() == i && g.getY() == j) {
				return true;
			}
		}
		
		return false;
	}
	
	//Printing the terrain on screen with the map, pacman and ghosts
	public String toString() {
		String[][] map = new String[WIDTH][HEIGHT];
		String s = "";
		
		//storing the map
		for(int i=0;i<WIDTH;i++) {
            for(int j=0;j<HEIGHT;j++) {
            	map[i][j] = tile[i][j].toString();   
            }
        }
		
		//adding pacman 
		map[pacman.getX()][pacman.getY()] = pacman.toString();
		
		//adding ghosts
		for(Ghost g : ghosts) {	  
			  map[g.getX()][g.getY()] = g.toString();
		}
		
		//printing everything
		for(int i=0;i<WIDTH;i++) {
            for(int j=0;j<HEIGHT;j++) {
            	s = s+ map[i][j];
            }
            s=s+'\n';
		}
		
		return s;
	}


	public static String getMapType() {
		return mapType;
	}


	public static void setMapType(String mapType) {
		Terrain.mapType = mapType;
	}

	
	
}
