package baekjoon;
import java.util.Scanner;

class BOJ_10822_더하기_김세민 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		String[] lines = sc.next().split(",");
		
		int total = 0;
		for(String Item : lines) {
			total += Integer.parseInt(Item);
		}
		System.out.println(total);
		
		sc.close();
	}
}