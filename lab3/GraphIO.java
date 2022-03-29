import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class GraphIO{
	static public void readFile(Graph g, String filename) {
		File file;
		Scanner scan = null;
		
		try {
			file = new File(filename);
			scan = new Scanner(file);
			int num = Integer.parseInt(scan.nextLine());
			for(int i = 0; i < num; i++) {
				g.addNode(Integer.parseInt(scan.next()), Integer.parseInt(scan.next()), Integer.parseInt(scan.next()));
			}
			while(scan.hasNextLine()){
				g.addEdge(Integer.parseInt(scan.next()), Integer.parseInt(scan.next()), Integer.parseInt(scan.next()));
			}
		}
		catch(IOException e) {
			System.out.println("Error");
		}
	}
	
}
