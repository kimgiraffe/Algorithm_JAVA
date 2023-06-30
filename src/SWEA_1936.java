import java.util.Scanner;

class SWEA_1936 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		
		if(A == 1) {
			if(B == 2) {
				System.out.println("B");
			}
			else {
				System.out.println("A");
			}
		}
		else if(A == 2) {
			if(B == 1) {
				System.out.println("A");
			}
			else {
				System.out.println("B");
			}
		}
		else {
			if(B == 1) {
				System.out.println("B");
			}
			else {
				System.out.println("A");
			}
		}
		
		sc.close();
	}
}