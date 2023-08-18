package baekjoon;
import java.util.Scanner;

public class BOJ_10809_알파벳찾기_김세민 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		String[] str = sc.next().split("");
		int A[] = new int[26];
		
		for(int i = 0; i < 26; i++) {
			A[i] = -1;
		}
		
		for(int i = 0; i < str.length; i++) {
			int j = (int)str[i].charAt(0) - 97;
			if(A[j] == -1) {
				A[j] = i; 
			}
		}
		
		for(int i = 0; i < 26; i++) {
			System.out.print(A[i] + " ");
			
		}
		sc.close();

	}
}