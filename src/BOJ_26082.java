import java.util.Scanner;

class BOJ_26082 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();
		
		System.out.println(B / A * C * 3);
		
		sc.close();
	}
}