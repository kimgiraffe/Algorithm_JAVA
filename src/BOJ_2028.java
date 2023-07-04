import java.util.Scanner;

class BOJ_2028 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int i = 1; i <= T; i++) {
			int N = sc.nextInt();
			int cnt = 0, tmp = N;
			while(N > 0) {
				N /= 10;
				cnt++;
			}
			if(tmp * tmp % (Math.pow(10, cnt)) == tmp) {
				System.out.println("YES");
			}
			else {
				System.out.println("NO");
			}
		}
		
		sc.close();
	}
}