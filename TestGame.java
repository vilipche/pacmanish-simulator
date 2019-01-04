import java.io.File;
import java.util.ArrayList;

public class TestGame {

	public static void main(String[] args) {
		Pacman pacman = new Pacman(1,1);
		Ghost g1 = new Ghost(7,7);
		Ghost g2 = new Ghost(11,1);
		Ghost g3 = new Ghost(18,18);
		Ghost g4 = new Ghost(17,17);
		Ghost g5 = new Ghost(10,10);
		
		//Safe positions for ghosts in import map: (18,18), (17,17), (10,10), (7,7), (11,1)

		ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
		ghosts.add(g1);
		ghosts.add(g2);
		ghosts.add(g3);
		ghosts.add(g4);
		ghosts.add(g5);

		
		//Comment / decomment map to use
//		Random map:
//		Terrain terrain = new Terrain(pacman, ghosts);
		
// 		Import map:
		File f = new File("newmap.txt");
		Terrain terrain = new Terrain(pacman, ghosts, f);
		
		System.out.println(terrain.toString());
		//Game loop
		while(true) {	

			if(pacman.isDead()) {
				System.out.println("Pacman looses");
				System.out.println("Final score: "+pacman.getScore());
				System.out.println(terrain.toString());
				return;
			}
			
			if(Food.getCpt() == 0) {
				System.out.println("Pacman won!");
				System.out.println("Final score: "+pacman.getScore());
				System.out.println(terrain.toString());
				return;
			}
			
			terrain.movePeople();
			System.out.println(terrain.toString());
			
			
			
			
			System.out.println("Position de pacman:\t"+pacman.getX() +"," +pacman.getY());
			for(Ghost g: ghosts) {
				System.out.println("Position de fantome:"+g.getNumber()+"\t"+g.getX() +"," +g.getY());
			}
			
			
			System.out.println(Terrain.getMapType());
			System.out.println("Food left:"+Food.getCpt());
			System.out.println("Pacman's score: "+pacman.getScore());
			System.out.println("Lives left:" +pacman.getLifes());
			System.out.println("*************************");
			
			
			
		
			try{
			     Thread.sleep(100);
			}catch(Exception e){
			     e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
	}

}
