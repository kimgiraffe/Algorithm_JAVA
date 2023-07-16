import java.util.Scanner;

/**
 * @author kimgiraffe
 */

public class BOJ_11945 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		String[] arr = new String[N];
		
		
		if(N != 0 && M != 0) {
			for(int col = 0; col < N; col++) {
				arr[col] = sc.next();
			}
		
			for(int col = 0; col < N; col++) {
				for(int row = M - 1; row > -1; row--) {
					System.out.print(arr[col].charAt(row));
				}
				System.out.println();
			}
		}
		
		sc.close();
	}

}
