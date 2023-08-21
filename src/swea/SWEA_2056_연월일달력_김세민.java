package swea;

import java.util.Scanner;

/**
 * 2056_연월일달력
 * @author semin.kim
 * 1. 연월일로 구성된 8자리 날짜를 입력받는다.
 * 2. 연/월/일로 나눈다.
 * 3. 월이 1~12 사이의 값을 갖는지 확인한다.
 * 4. 일이 각 달에 해당하는 값(1월인 경우 31일)을 갖는지 확인한다.
 * 5. 유효한 날짜인 경우 YYYY/MM/DD 형식으로 출력하고
 * 6. 유효한 날짜가 아닌 경우 -1을 출력
 */

public class SWEA_2056_연월일달력_김세민 {
	public static int T, year, month, day;
	public static String dateString;
	
	/**
	 * isValid method 구현
	 * 1. 주어진 월과 일에 대하여 유효한 값인지 조건문을 통해 확인한다.
	 * 2. 유효하다면 true 반환, 유효하지 않다면 false 반환
	 * @param y
	 * @param m
	 * @param d
	 * @return
	 */
	public static boolean isValid(int y, int m, int d) {
		if(m > 12 || m < 1) { 
			return false;
		} else if(m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) {
			if(d > 31 || d < 1) {
				return false;
			}			
		} else if(m == 2) {
			if(d > 28 || d < 1) {
				return false;
			}
		} else if(m == 4 || m == 6 || m == 9 || m == 11){
			if(d > 30 || d < 1) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * printDate method 구현
	 * 1. YYYY/MM/DD 형식에 맞게 날짜를 출력한다.
	 * @param y
	 * @param m
	 * @param d
	 */
	public static void printDate(int y, int m, int d) {
		if(y == 0) {
			System.out.print("0000/");
		} else if(y < 10) {
			System.out.print("000"+y+"/");
		} else if(y < 100) {
			System.out.print("00"+y+"/");
		} else if(y < 1000) {
			System.out.print("0"+y+"/");
		} else {
			System.out.print(y+"/");
		}
		
		if(m < 10) {
			System.out.print("0"+m+"/");
		} else {
			System.out.print(m+"/");
		}
		
		if(d < 10) {
			System.out.print("0"+d);
		} else {
			System.out.print(d);
		}
		
		System.out.println();
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++) {
			dateString = sc.next();
			int tmp = Integer.parseInt(dateString);
			year = tmp / 10000; // 년을 구한다.
			month = (tmp % 10000) / 100; //월을 구한다.
			day = (tmp  % 10000) % 100; //일을 구한다.
			System.out.print("#" + test_case + " ");
			if(isValid(year, month, day)) { //유효한 날짜라면
				printDate(year, month, day); //형식에 맞게 출력
			} else { //유효한 날짜가 아니라면
				System.out.println(-1); // -1 출력
			}
		}
		
		sc.close();
	}

}
