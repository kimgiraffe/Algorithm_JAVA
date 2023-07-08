import java.util.*;

class SWEA_2007 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++) {
			String s = sc.next();
			for(int i = 1; i < s.length()/2; i++) {
				if(s.substring(i, 2 * i).equals(s.substring(0, i))) {
					System.out.println("#" + test_case + " " + i);
					break;
				}
			}
		}
		
		sc.close();
	}
}