import java.util.Scanner;

class BOJ_27328 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int A = sc.nextInt();
		int B = sc.nextInt();
		if(A > B) {
			System.out.println(1);
		}
		else if(A < B) {
			System.out.println(-1);
		}
		else {
			System.out.println(0);
		}
		
		sc.close();
	}
}