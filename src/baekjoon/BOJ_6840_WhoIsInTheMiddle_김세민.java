package baekjoon;
import java.util.*;

class BOJ_6840_WhoIsInTheMiddle_김세민 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[3];
		for(int i = 0; i < 3; i++) {
			arr[i] = sc.nextInt();
		}
		
		Arrays.sort(arr);
		System.out.println(arr[1]);
	}
}