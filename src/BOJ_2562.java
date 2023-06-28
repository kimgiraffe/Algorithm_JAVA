import java.util.Scanner;

public class BOJ_2562 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		int A[] = new int[10];
		int MAX = 0;
		int tmp = 0;
		
		for(int i = 1; i < 10; i++) {
			A[i] = sc.nextInt();
			if(A[i] > MAX) {
				MAX = A[i];
				tmp = i;
			}
		}
		
		System.out.println(MAX);
		System.out.println(tmp);
		sc.close();

	}
}