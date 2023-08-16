package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1873_상호의배틀필드_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T; // 테스트 케이스의 개수
	static int map_height, map_width; // 맵의 높이, 너비
	static int user_input_cnt; // 사용자 입력의 개수
	static char[][] map;
	static String user_inputString;
	static int tank_colIdx, tank_rowIdx; // 전차의 세로, 가로 위치
	
	public static void updateMap() {
		for(int idx = 0; idx < user_input_cnt; idx++) {
			if(user_inputString.charAt(idx) == 'U') {
				
				// 전차가 바라보는 방향을 위쪽으로 변경
				map[tank_colIdx][tank_rowIdx] = '^';
				// 맵을 벗어나는 경우...이동하지 않는다.
				// 한 칸 위의 칸이 평지인 경우... 그 칸으로 이동
				if(tank_colIdx - 1 >= 0 && map[tank_colIdx - 1][tank_rowIdx] == '.') {
					map[tank_colIdx - 1][tank_rowIdx] = map[tank_colIdx][tank_rowIdx]; // 전차가 위로 이동
					map[tank_colIdx][tank_rowIdx] = '.'; // 전차가 이전에 있었던 칸은 평지
					tank_colIdx--;
				}
			} else if(user_inputString.charAt(idx) == 'D') {
				// 전차가 바라보는 방향을 아래로 변경
				map[tank_colIdx][tank_rowIdx] = 'v';
				// 맵을 벗어나는 경우... 이동하지 않는다.
				// 한 칸 아래의 칸이 평지인 경우... 그 칸으로 이동
				if(tank_colIdx + 1 < map_height && map[tank_colIdx + 1][tank_rowIdx] == '.') {
					map[tank_colIdx + 1][tank_rowIdx] = map[tank_colIdx][tank_rowIdx]; // 전차가 아래로 이동
					map[tank_colIdx][tank_rowIdx] = '.'; // 전차가 이전에 있었던 칸은 평지
					tank_colIdx++;
				}
			} else if(user_inputString.charAt(idx) == 'L') {
				// 전차가 바라보는 방향을 왼쪽으로 변경
				map[tank_colIdx][tank_rowIdx] = '<';
				// 맵을 벗어나는 경우... 이동하지 않는다.
				// 한 칸 왼쪽의 칸이 평지인 경우... 그 칸으로 이동
				if(tank_rowIdx - 1 >= 0 && map[tank_colIdx][tank_rowIdx - 1] == '.') {
					map[tank_colIdx][tank_rowIdx - 1] = map[tank_colIdx][tank_rowIdx]; // 전차가 왼쪽으로 이동
					map[tank_colIdx][tank_rowIdx] = '.'; // 전차가 이전에 있었던 칸은 평지
					tank_rowIdx--;
				}
			} else if(user_inputString.charAt(idx) == 'R') {
				// 전차가 바라보는 방향을 오른쪽으로 변경
				map[tank_colIdx][tank_rowIdx] = '>';
				// 맵을 벗어나는 경우... 이동하지 않는다.
				// 한 칸 왼쪽의 칸이 평지인 경우... 그 칸으로 이동
				if(tank_rowIdx + 1 < map_width && map[tank_colIdx][tank_rowIdx + 1] == '.') {
					map[tank_colIdx][tank_rowIdx + 1] = map[tank_colIdx][tank_rowIdx]; // 전차가 오른쪽으로 이동
					map[tank_colIdx][tank_rowIdx] = '.'; // 전차가 이전에 있었던 칸은 평지
					tank_rowIdx++;
				}
			} else if(user_inputString.charAt(idx) == 'S') {
				if(map[tank_colIdx][tank_rowIdx] == '^') { // 전차가 위쪽 방향을 보고 있는 경우...
					for(int col = tank_colIdx - 1; col >= 0; col--) {
						if(map[col][tank_rowIdx] == '*') { // 포탄이 벽돌로 만들어진 벽과 충돌하는 경우...
							map[col][tank_rowIdx] = '.'; // 평지가 된다.
							break;
						} else if(map[col][tank_rowIdx] == '#') { // 강철로 만들어진 벽과 충돌하는 경우...
							break;
						} else if(map[col][tank_rowIdx] == '.' || map[col][tank_rowIdx] == '-') { // 평지 또는 물을 만나는 경우...
							continue;
						}
					}
				} else if(map[tank_colIdx][tank_rowIdx] == 'v') { // 전차가 아래쪽 방향을 보고 있는 경우...
					for(int col = tank_colIdx + 1; col < map_height; col++) {
						if(map[col][tank_rowIdx] == '*') { // 포탄이 벽돌로 만들어진 벽과 충돌하는 경우...
							map[col][tank_rowIdx] = '.'; // 평지가 된다.
							break;
						} else if(map[col][tank_rowIdx] == '#') { // 강철로 만들어진 벽과 충돌하는 경우...
							break;
						} else if(map[col][tank_rowIdx] == '.' || map[col][tank_rowIdx] == '-') { // 평지 또는 물을 만나는 경우...
							continue;
						}
					}
				} else if(map[tank_colIdx][tank_rowIdx] == '<') { // 전차가 왼쪽 방향을 보고 있는 경우...
					for(int row = tank_rowIdx - 1; row >= 0; row--) {
						if(map[tank_colIdx][row] == '*') { // 포탄이 벽돌로 만들어진 벽과 충돌하는 경우...
							map[tank_colIdx][row] = '.'; // 평지가 된다.
							break;
						} else if(map[tank_colIdx][row] == '#') { // 강철로 만들어진 벽과 충돌하는 경우...
							break;
						} else if(map[tank_colIdx][row] == '.' || map[tank_colIdx][row] == '-') { // 평지 또는 물을 만나는 경우...
							continue;
						}
					}
				} else if(map[tank_colIdx][tank_rowIdx] == '>') { // 전차가 오른쪽 방향을 보고 있는 경우...
					for(int row = tank_rowIdx + 1; row < map_width; row++) {
						if(map[tank_colIdx][row] == '*') { // 포탄이 벽돌로 만들어진 벽과 충돌하는 경우...
							map[tank_colIdx][row] = '.'; // 평지가 된다.
							break;
						} else if(map[tank_colIdx][row] == '#') { // 강철로 만들어진 벽과 충돌하는 경우...
							break;
						} else if(map[tank_colIdx][row] == '.' || map[tank_colIdx][row] == '-') { // 평지 또는 물을 만나는 경우...
							continue;
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스의 개수 입력
		
		for(int test_case = 1; test_case <= T; ++test_case) {
			sb = new StringBuilder();
			st = new StringTokenizer(br.readLine().trim());
			map_height = Integer.parseInt(st.nextToken()); // 맵의 높이 입력
			map_width = Integer.parseInt(st.nextToken()); // 맵의 너비 입력
			
			map = new char[map_height][map_width];
			
			for(int colIdx = 0; colIdx < map_height; colIdx++) {
				String tmp = br.readLine().trim();
				for(int rowIdx = 0; rowIdx < map_width; rowIdx++) {
					map[colIdx][rowIdx] = tmp.charAt(rowIdx);
					if(map[colIdx][rowIdx] == '^' || map[colIdx][rowIdx] == 'v' || map[colIdx][rowIdx] == '<' || map[colIdx][rowIdx] == '>') {
						tank_colIdx = colIdx;
						tank_rowIdx = rowIdx;
					}
					
				}
			}
			
			user_input_cnt = Integer.parseInt(br.readLine().trim()); // 사용자가 넣을 입력의 개수 입력
			user_inputString = br.readLine().trim();
			
			updateMap();
			sb.append('#').append(test_case).append(' ');
			for(int colIdx = 0; colIdx < map_height; colIdx++) {
				for(int rowIdx = 0; rowIdx < map_width; rowIdx++) {
					sb.append(map[colIdx][rowIdx]);
				}
				sb.append('\n');
			}
			System.out.print(sb);
		}
		
	}
}
