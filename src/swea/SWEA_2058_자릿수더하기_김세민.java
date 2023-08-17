package swea;
import java.util.Scanner;

class SWEA_2058_자릿수더하기_김세민 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int sum = 0;
		while(N > 0) {
			sum += N % 10;
			N /= 10;
		}
		System.out.println(sum);
		sc.close();
	}
}