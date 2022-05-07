import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WelcomeBack {
	public WelcomeBack() {
		
	}
	
	public String getMiddle(String str) {
		return str.length()%2 == 0 ? str.substring(str.length()/2 - 1, str.length()/2+1) : str.charAt(str.length()/2) + "";
	}
	
	public int[] sumNumbers(int n) {
		int[] result = new int[Math.abs(n) + 1];
		for (int i = 1; i <= n; i++) {
			result[i] = result[i-1] + i;
		}
		return result;
	}
	
	public int sumDigits(int num) {
		int sum = 0;
		while(num != 0) {
			sum += num % 10;
			num/=10;
		}
		return sum;
	}
	
	public int keepSummingDigits(int num) {
		while(Math.abs(num) > 9) {
			num = sumDigits(num);
		}
		return num;
	}
	
	public String getIntersection(int[] a, int[] b) {
		String result = "";
		Arrays.sort(a);
		Arrays.sort(b);
		
		for (int i = 0; i < a.length; i++) {
			if (result.contains(a[i] + "")) {
				continue;
			}
			if (contains(a[i],b)) {
				result = result + a[i];
			}
		}		
		return result;
	}
	
	public boolean contains(int target, int[] array) {
		int min = 0;
		int max = array.length-1;
		while(min <= max) {
			int middle = (min + max) / 2;
			if (array[middle] == target) {
				return true;
			}
			if (array[middle] > target) {
				max = middle - 1;
			} else {
				min = middle + 1;
			}
		}
		return false;
	}
	
	public Map<Integer, Integer> mapLengths(String[] words) {
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		for (String s : words) {
			map.put(s.length(), map.getOrDefault(s.length(), 0) + 1);
		}
		return map;
	}
	
	public int sumDigitsRecur(int num) {
		if (num == 0) {
			return 0;
		}
		return num%10 + sumDigitsRecur(num/10);
	}
	
	public int sumWithoutCarry(int a, int b) {
		String sum = "";
		for (int index = 0; index < (a + "").length(); index++) {
			sum = sum + ((Integer.parseInt("" + (a + "").charAt(index)) + Integer.parseInt("" + (b + "").charAt(index)))%10);
		}
		return Integer.parseInt(sum);
	}
	
	public int buySell1(int[] stock) {
		if (stock.length <= 2) {
			return 0;
		}
		int max = 0;
		for (int i = 0; i < stock.length; i++) {
			for (int j = i + 1; j < stock.length; j++) {
				if (max < stock[j] - stock[i]) {
					max = stock[j] - stock[i];
				}
			}
		}
		return max;
	}
	
	public void zeck(String fileName) {
		File f = new File(fileName);
		if (f.exists()) {
			try {
				Scanner reader = new Scanner(f);
				int N = reader.nextInt();
				for (int i = 0; i < N; i++) {
					int num = reader.nextInt();
					System.out.print(num + " = ");
					int max = 1;
					for (int nnn = 1; ;nnn++) {
						int test = getNthFibonacciNumber(nnn);
						if (test > num) {
							break;
						}
						max = nnn;
					}
					while (num != 0) {
						int test = getNthFibonacciNumber(max);
						if (test > num) {
							max--;
						} else if (test == num) {
							System.out.print(test);
							num -= test;
						} else {
							System.out.print(test + " + ");
							num -= test;
							max -= 2;
						}
					}
					System.out.println();
				}
				reader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public int getNthFibonacciNumber(int n) {
		double phi = (1 + Math.sqrt(5)) / 2;
		return (int) Math.round(Math.pow(phi, n) / Math.sqrt(5));
	}
}
