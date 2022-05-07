import java.util.*;
import java.io.*;
public class Scooby{
	public static void main (String[] args){
		Scanner file = null;
		try{
			file = new Scanner(new File("scooby.dat"));
		}
		catch (Exception e){
			System.out.println("Cannot find file !");
		}
		int times = file.nextInt(); file.nextLine();
		for (int i = 0; i < times; i++){
			problemSolver(file);
		}
	}

	public static void problemSolver(Scanner file){
		HashMap<Character, HashSet<Character>> graph = new HashMap<>();
		String edges = file.nextLine();

		for (String edge : edges.split(" ")) {
			char room1 = edge.charAt(0);
			char room2 = edge.charAt(1);

			//put in map
			if (graph.containsKey(room1) == false)
				graph.put(room1, new HashSet<Character>());
			graph.get(room1).add(room2);
			if (graph.containsKey(room2) == false)            
				graph.put(room2, new HashSet<Character>());
			graph.get(room2).add(room1);
		}


		String goal = file.nextLine();
		char start = goal.charAt(0);
		char end = goal.charAt(1);

		if (graph.get(start) == null || graph.get(end) == null){
			System.out.println("no");
			return;
		}

		Stack<Character> check = new Stack<>();
		HashSet<Character> visited = new HashSet<>();
		check.push(start);
		visited.add(start);

		while (!check.isEmpty()){
			char temp = check.pop();
			if (temp == end){
				System.out.println("yes");
				return;
			}
			for (char spot : graph.get(temp)){
				if (! visited.contains(spot)) {
					check.push(spot);
					visited.add(spot);
				}
			}
		}
		System.out.println("no");
	}    
}


//build graph
//where now and where going
//start/goal arent in graph,, stop
//dfs