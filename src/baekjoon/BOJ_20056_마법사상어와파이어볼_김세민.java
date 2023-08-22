package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * BOJ_20056_마법사상어와파이어볼
 * 
 * 크기가 N*N(4이상 50이하)격자에 M(0이상 N^2이하)개의 파이어볼 발사
 * 마법사 상어가 모든 파이어볼에게 이동을 명령하면 다음이 일들이 일어난다.
 * 1.모든 파이어볼이 자신의 방향 di로 속력 si칸 만큼 이동한다.
 * 	1-1.이동하는 중에는 같은 칸에 여러 개의 파이어볼이 있을 수도 있다.
 * 2.이동이 모두 끝난 뒤, 2개 이상의 파이어볼이 있는 칸에서는 다음과 같은 일이 일어난다.
 * 2-1.같은 칸에 있는 파이어볼은 모두 하나로 합쳐진다.
 * 2-2.파이어볼은 4개의 파이어볼로 나누어진다.
 * 2-3.나누어진 파이어볼의 질량, 속력, 방향은 다음과 같다.
 * 	2-3-1.질량은 ⌊(합쳐진 파이어볼 질량의 합)/5⌋이다.
 *  2-3-2.속력은 ⌊(합쳐진 파이어볼 속력의 합)/(합쳐진 파이어볼의 개수)⌋이다.
 *  2-3-3.합쳐지는 파이어볼의 방향이 모두 홀수이거나 모두 짝수이면, 방향은 0, 2, 4, 6이 되고, 그렇지 않으면 1, 3, 5, 7이 된다.
 * 2-4.질량이 0인 파이어볼은 소멸되어 없어진다.
 * 마법사 상어가 이동을 K번 명령한 후, 남아있는 파이어볼 질량의 합을 구해보자.
 * 
 * 이동하는 중에 같은 칸에 파이어볼이 여러 개 있을 수 있기 때문에 
 * 2차원 파이어볼 arrayList를 이용하여 이동 시 해당 위치의 모든 파이어볼을 관리하자.
 * 파이어볼은 맵의 크기만큼 움직이면 제자리로 돌아온다.
 * 현재 속력만큼 움직이면, 파이어볼의 속력 / 맵의 크기만큼 자기 위치에 위치하고, 속력을 맵의 크기로 나눈 나머지만큼만 더 움직인다.
 * 
 * 
 * @author semin.kim
 */

public class BOJ_20056_마법사상어와파이어볼_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static final int[] DR = {-1, -1, 0, 1, 1, 1, 0, -1};
	static final int[] DC = {0, 1, 1, 1, 0, -1, -1, -1};
	
	static class fireball {
		int row;
		int col;
		int mass; // 질량
		int speed; // 속도
		int dir; // 방향
		
		public fireball(int row, int col, int mass, int speed, int dir) {
			this.row = row;
			this.col = col;
			this.mass = mass;
			this.speed = speed;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "fireball [row=" + row + ", col=" + col + ", mass=" + mass + ", speed=" + speed + ", dir=" + dir
					+ "]";
		}
	}
	
	static ArrayList<fireball> [][] map; // 파이어볼의 정보를 담을 2차원 fireball arrayList
	static ArrayList<fireball> fireBalls; // 남을 파이어볼을 담을 arrayList
	
	static int map_size, fireball_cnt, order_cnt; // 격자의 크기, 파이어볼의 개수, 명령의 개수
	static int left_mass_sum; // 남아있는 파이어볼 질량의 합
	
	private static void move_fireball() {
		// 파이어볼이 이동하면 해당 위치의 파이어볼은 없어진다.
		for(int row = 1; row <= map_size; row++) {
			for(int col = 1; col <= map_size; col++) {
				map[row][col].clear();
			}
		}
		
		for(int idx = 0; idx < fireBalls.size(); idx++) {
			int row = fireBalls.get(idx).row;
			int col = fireBalls.get(idx).col;
			int mass = fireBalls.get(idx).mass;
			int speed = fireBalls.get(idx).speed;
			int dir = fireBalls.get(idx).dir;
			
			int next_row = row + DR[dir] * (speed % map_size);
			int next_col = col + DC[dir] * (speed % map_size);
			
			if(next_row > map_size) next_row -= map_size;
			if(next_col > map_size) next_col -= map_size;
			if(next_row <= 0) next_row += map_size;
			if(next_col <= 0) next_col += map_size;
			
			// 파이어볼 이동
			map[next_row][next_col].add(new fireball(next_row, next_col, mass, speed, dir));
			
			fireBalls.get(idx).row = next_row;
			fireBalls.get(idx).col = next_col;
		}
	}
	
	private static void merge_and_divide_fireballs() {
		ArrayList<fireball> temp = new ArrayList<>();
		
		for(int row = 1; row <= map_size; row++) {
			for(int col = 1; col <= map_size; col++) {
				if(map[row][col].size() == 0) continue; // 해당 위치의 파이어볼이 없는 경우...
				if(map[row][col].size() == 1) { // 해당 위치의 파이어볼이 없는 경우...
					temp.add(map[row][col].get(0)); //  바로 넣어줌
					continue;
				}
				// 2개 이상의 파이어볼이 존재하는 경우...
				int mass_sum = 0; // 파이어볼의 질량의 합
				int speed_sum = 0; // 파이어볼의 속력의 합
				int count = map[row][col].size(); // 해당 위치의 합쳐질 파이어볼의 개수
				
				boolean isOdd = true; // 파이어볼의 방향이 홀수?
				boolean isEven = true; // 파이어볼의 방향이 짝수?
				
				for(int idx = 0; idx < map[row][col].size(); idx++) {
					mass_sum += map[row][col].get(idx).mass;
					speed_sum += map[row][col].get(idx).speed;
					if((map[row][col].get(idx).dir & 1) == 0) { // 파이어보르이 방향이 짝수인 경우...
						isOdd = false;
					}
					else {
						isEven = false;
					}
				}
				
				int mass = mass_sum / 5; // 나누어진 질량
				int speed = speed_sum / count; // 나누어진 속력
				if(mass == 0) continue; // 질량이 0인 경우...
				if(isEven || isOdd) { // 방향이 모두 짝수이거나 홀수인 경우...
					for(int dir = 0; dir < 4; dir++) { // 4개로 나누어진 파이어볼을 담기
						temp.add(new fireball(row, col, mass, speed, dir * 2));
					}
				}
				else { // 그렇지 않은 경우...
					for(int dir = 0; dir < 4; dir++) { // 4개로 나누어진 파이어볼을 담기
						temp.add(new fireball(row, col, mass, speed, dir * 2 + 1));
					}
				}
			}
		}
		fireBalls = temp;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		
		map_size = Integer.parseInt(st.nextToken());
		fireball_cnt = Integer.parseInt(st.nextToken());
		order_cnt = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[map_size + 1][map_size + 1]; // (1, 1) 부터 사용
		fireBalls = new ArrayList<>();
		
		// arrayList 초기화
		for(int row = 1; row <= map_size; row++) {
			for(int col = 1; col <= map_size; col++) {
				map[row][col] = new ArrayList<>();
			}
		}
		
		// 파이어볼의 정보 입력
		for(int idx = 0; idx < fireball_cnt; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			int mass = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			
			fireBalls.add(new fireball(row, col, mass, speed, dir));
			map[row][col].add(new fireball(row, col, mass, speed, dir));
		}

//		for(int row = 1; row <= map_size; row++) {
//			for(int col = 1; col <= map_size; col++) {
//				System.out.print(map[row][col] + " ");
//			}
//			System.out.println();
//		}
		
		// 명령 시행
		for(int order = 1; order <= order_cnt; order++) {
			move_fireball(); // 모든 파이어볼을 이동
			merge_and_divide_fireballs(); // 파이어볼 합치고 나누기
		}
		
		// 남아있는 파이어볼들의 질량 합 구하기
		for(int idx = 0; idx < fireBalls.size(); idx++) {
			left_mass_sum += fireBalls.get(idx).mass;
		}
		// 남아있는 파이어볼 질량의 합 출력
		System.out.println(left_mass_sum);
	}
}
