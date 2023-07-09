import java.util.Scanner;

class BOJ_1356 {
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		String s = sc.nextLine();
		int N = s.length();
		boolean flag = false;
		
		for(int i = 0; i < N - 1; i++) {
			int tot1 = 1, tot2 = 1;
			for(int j = 0; j <= i; j++) tot1 *= s.charAt(j) -'0';
			for(int j = i + 1; j < N; j++) tot2 *= s.charAt(j) - '0';
			
			
			
			if(tot1 == tot2) {
				flag = true;
				break;
			}
		}
		
		if(flag == true) {
			System.out.println("YES");
		}
		else {
			System.out.println("NO");
		}
		
		sc.close();
	}
}