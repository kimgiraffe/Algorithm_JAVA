import java.util.Scanner;

class BOJ_27324 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		if(N / 10 == N % 10) {
			System.out.println(1);
		}
		else {
			System.out.println(0);
		}
		
		sc.close();
	}
}