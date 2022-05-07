import java.util.*;
import java.io.*;

public class PrimePath{ 

	public static void main(String[] args){
		HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();

		Scanner input = new Scanner(System.in);
		int start = input.nextInt();
		int end = input.nextInt();

		ArrayList<Integer> primes = new ArrayList<>();

		if (start < end){
			for (int i = start; i <= end; i++){
				if (isPrime(i) == true)
					primes.add(i);
			}
		}
		else{
			for (int i = end; i <= start; i++){
				if (isPrime(i) == true)
					primes.add(i);
			}
		}

		for (int i = 0; i < primes.size(); i++){
			for (int j = 0; j < primes.size(); j++){
				if (isOneAway(primes.get(i), primes.get(j))){
					if (graph.get(primes.get(i)) == null)
						graph.put(primes.get(i), new ArrayList<Integer>());
					graph.get(primes.get(i)).add(primes.get(j));
				}

			}
		}

		//bfs >:(
				Queue<Integer> toDo = new LinkedList<>();
				HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
				HashMap<Integer, Integer> distance = new HashMap<Integer, Integer>();

				toDo.offer(start);
				distance.put(start, 0);
				visited.put(start, true);
				boolean pathFound = false;
				while(toDo.isEmpty() == false){
					int current = toDo.poll();
					if (current == end){
						pathFound = true;
						System.out.println(distance.get(current));
						break;
					} else{
						for (int i : graph.get(current)) {
							if (!visited.containsKey(i)) {
								toDo.offer(i);
								distance.put(i, distance.get(current) + 1);
								visited.put(i, true);
							}
						}
					}
				}

				if (pathFound == false)
					System.out.println("-1");

	}
	private static boolean isPrime(int num){
		for (int i = 2; i < num/2; i++){
			if (num % i == 0)
				return false;
		}
		return true;
	}

	private static boolean isOneAway(int num1, int num2){
		boolean hasDiff = false;
		while (num1 != 0){
			if (num1 % 10 != num2 % 10){
				if (hasDiff == true)
					return false;
				hasDiff = true;
			}
			num1 /= 10;
			num2 /= 10;
		}
		return hasDiff;
	}

}
