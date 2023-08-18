package baekjoon;
import java.util.Scanner;

class BOJ_1864_문어숫자_김세민 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			String s = sc.nextLine();
			if(s.equals("#")) break;
			int total = 0;
			for(int i = 0; i < s.length(); i++) {
				if(s.charAt(i) == '\\') {
					total += 1 * Math.pow(8, s.length() - i - 1);
				}
				else if(s.charAt(i) == '(') {
					total += 2 * Math.pow(8, s.length() - i - 1);
				}
				else if(s.charAt(i) == '@') {
					total += 3 * Math.pow(8, s.length() - i - 1);
				}
				else if(s.charAt(i) == '?') {
					total += 4 * Math.pow(8, s.length() - i - 1);
				}
				else if(s.charAt(i) == '>') {
					total += 5 * Math.pow(8, s.length() - i - 1);
				}
				else if(s.charAt(i) == '&') {
					total += 6 * Math.pow(8, s.length() - i - 1);
				}
				else if(s.charAt(i) == '%') {
					total += 7 * Math.pow(8, s.length() - i - 1);
				}
				else if(s.charAt(i) == '/') {
					total -= 1 * Math.pow(8, s.length() - i - 1);
				}
			}
			System.out.println(total);
		}
		sc.close();
	}
}