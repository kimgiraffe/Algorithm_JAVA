import java.util.*;

class SWEA_9997 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++) {
			int s = sc.nextInt();
			int h = s * 2 / 60;
			int m = s * 2 % 60;
			System.out.println("#" + test_case + " " + h + " " + m);
		}
		
		sc.close();
	}
}