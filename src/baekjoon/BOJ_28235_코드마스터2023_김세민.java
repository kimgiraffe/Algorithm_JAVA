package baekjoon;
import java.util.Scanner;

class BOJ_28235_코드마스터2023_김세민 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		String s = sc.next();
		if(s.equals("SONGDO")) {
			System.out.println("HIGHSCHOOL");
		}
		else if(s.equals("CODE")) {
			System.out.println("MASTER");
		}
		else if(s.equals("2023")) {
			System.out.println("0611");
		}
		else if(s.equals("ALGORITHM")) {
			System.out.println("CONTEST");
		}
		
		sc.close();
	}
}