
public class Test {
	public static void main(String[] args) {
		int max = 1000;
		for (long a = 1; a < max; a++) {
			for (long b = 1; b < max; b++) {
				for (long c = 1; c < max; c++) {
					if (a*(a+c)*(a+b) + b*(b+c)*(a+b) + c*(a+c)*(b+c) == 4 * (a+b) * (b+c) * (a+c)) {
						System.out.println("(a,b,c) >>> (" + a + "," + b + "," + c + ")");
						break;
					}
				}
			}
		}
	}
}
