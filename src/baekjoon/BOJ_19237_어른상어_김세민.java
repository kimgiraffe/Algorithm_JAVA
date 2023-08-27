package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_19237_어른상어
 * 상어에는 1 이상 M 이하의 자연수 번호가 붙어 있고, 모든 번호는 서로 다르다. 
 * 상어들은 영역을 사수하기 위해 다른 상어들을 쫓아내려고 하는데, 1의 번호를 가진 어른 상어는 가장 강력해서 나머지 모두를 쫓아낼 수 있다.
 * N×N 크기의 격자 중 M개의 칸에 상어가 한 마리씩 들어 있다. 맨 처음에는 모든 상어가 자신의 위치에 자신의 냄새를 뿌린다. 
 * 그 후 1초마다 모든 상어가 동시에 상하좌우로 인접한 칸 중 하나로 이동하고, 자신의 냄새를 그 칸에 뿌린다. 
 * 냄새는 상어가 k번 이동하고 나면 사라진다.
 * 각 상어가 이동 방향을 결정할 때는, 먼저 인접한 칸 중 아무 냄새가 없는 칸의 방향으로 잡는다. 
 * 그런 칸이 없으면 자신의 냄새가 있는 칸의 방향으로 잡는다. 
 * 이때 가능한 칸이 여러 개일 수 있는데, 그 경우에는 특정한 우선순위를 따른다. 
 * 우선순위는 상어마다 다를 수 있고, 같은 상어라도 현재 상어가 보고 있는 방향에 따라 또 다를 수 있다. 
 * 상어가 맨 처음에 보고 있는 방향은 입력으로 주어지고, 그 후에는 방금 이동한 방향이 보고 있는 방향이 된다.
 * 모든 상어가 이동한 후 한 칸에 여러 마리의 상어가 남아 있으면, 가장 작은 번호를 가진 상어를 제외하고 모두 격자 밖으로 쫓겨난다.
 * 1번 상어만 격자에 남게 되기까지 몇 초가 걸리는지를 구하는 프로그램을 작성하시오.
 * 
 * Shark 클래스를 만들어 상어의 위치정보, 방향의 우선순위 정보를 저장한다.
 * 1. 모든 상어의 위치를 이동한다.
 * 2. 지도상의 모든 냄새를 1 감소한다.
 * 3. 자신보다 작은 상어와 겹치는 경우가 존재하는지 확인한다.
 * 4. 살아있는 모든 상어의 위치에 냄새를 남긴다.
 * 5. 1~4를 남아있는 상어가 1마리가 될 때까지 반복한다.
 * 6. 시간이 1000이 넘는 경우... -1을 출력하고 1000이 넘지 않는 경우 걸린 시간을 출력한다.
 * @author semin.kim
 */

class Smell {
    int num, time;
    Smell(int num, int time) {
        this.num = num;
        this.time = time;
    }
}

class Shark {
    int row, col, num, dir;
    Shark(int r, int c, int num) {
        this.row = r;
        this.col = c;
        this.num = num;
    }
}

public class BOJ_19237_어른상어_김세민 {
	static BufferedReader br;
	static StringTokenizer st;
    static int mapSize, sharkCount, k, sharkNum, TIME;
    static Shark[] sharkList;
    static Smell[][] smell;
    static int[][] map;
    static int[][][] priority;  // 각 상어의 방향에 대한 우선순위  [num][현재방향][우선순위] == 방향
    static final int[] DELTA_ROW = {0, -1, 1, 0, 0};  // 0 상하좌우
    static final int[] DELTA_COL = {0, 0, 0, -1, 1};

    public static void smelling() {
        for(Shark s : sharkList) {
            if(s == null) continue;
            int r = s.row, c = s.col, num = s.num;
            if(smell[r][c] == null) {  // 빈 칸으로 들어옴
                smell[r][c] = new Smell(num, k);
            }else {  // 내 냄새가 있는 칸으로 들어옴
                smell[r][c].time = k;
            }
        }
    }

    public static void runOutOfSmellTime() {
        for(int row = 0; row < mapSize; row++) {
            for(int col = 0; col < mapSize; col++) {
                if(smell[row][col] != null) {
                    if(smell[row][col].time == 1) {
                        smell[row][col] = null;
                    }else {
                        smell[row][col].time -= 1;
                    }
                }
            }
        }
    }

    public static void moveShark() {
        // 같은 칸에 여러 상어 오면 제거하는 로직도 여기서 수행
        for(Shark shark : sharkList) {
            if(shark == null) continue;

            boolean found = false;
            int row = shark.row, col = shark.col, num = shark.num, dir = shark.dir;

            // 빈 칸 체크
            for(int i = 1; i <= 4; i++) {
                int nextDir = priority[num][dir][i];  // 현 상태에서 i번째 우선운위를 갖는 방향
                int nextRow = row + DELTA_ROW[nextDir];
                int nextCol = col + DELTA_COL[nextDir];

                if(-1 < nextRow && nextRow < mapSize && -1 < nextCol && nextCol < mapSize) {
                    if(smell[nextRow][nextCol] == null) {  // 남의 냄새가 없는 빈 칸
                        // 이미 빈 칸에 다른 상어가 있으면 대결을 해야 함
                        if(map[nextRow][nextCol] == 0) {  // 아무 상어도 없음
                            map[row][col] = 0; map[nextRow][nextCol] = num;
                            shark.row = nextRow; shark.col = nextCol; shark.dir = nextDir;
                        }else {  // 다른 상어를 만남
                            if(map[nextRow][nextCol] < num) {   // 미리 와있던 상어가 더 쏌
                                sharkList[num] = null;  // 나 죽음
                                sharkNum -= 1;
                                map[row][col] = 0;
                            }else {
                                sharkList[map[nextRow][nextCol]] = null;  // 미리 와있던 상어 죽음
                                sharkNum -= 1;
                                map[row][col] = 0; map[nextRow][nextCol] = num;
                                shark.row = nextRow; shark.col = nextCol; shark.dir = nextDir;
                            }
                        }
                        found = true;
                        break;
                    }
                }
            }
            if(found) continue;

            // 빈 칸이 없는 경우 자기 냄새 있는 칸 체크
            for(int i = 1; i <= 4; i++) {
                int nextDir = priority[num][dir][i];
                int nextRow = row + DELTA_ROW[nextDir];
                int nextCol = col + DELTA_COL[nextDir];

                if(-1 < nextRow && nextRow < mapSize && -1 < nextCol && nextCol < mapSize) {
                    // 여기선 대결을 고려할 필요가 없다
                    // 다른 상어의 냄새가 있는 곳으로 가는 경우는 없기 때문
                    if(smell[nextRow][nextCol].num == num) {  // 내 냄새인 경우
                        map[row][col] = 0; map[nextRow][nextCol] = num;
                        shark.row = nextRow; shark.col = nextCol; shark.dir = nextDir;
                        break;
                    }
                }
            }
        }
    }

    public static void simulation() {
        while(sharkNum != 1) {
            smelling();  // 냄새 남긴다
            moveShark();  // 다음 칸으로 이동한다
            runOutOfSmellTime();  // smell time을 줄인다
            TIME++;
            if(TIME > 1000) return;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        
        mapSize = Integer.parseInt(st.nextToken());
        sharkCount = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        sharkNum = sharkCount;

        sharkList = new Shark[sharkCount+1];
        map = new int[mapSize][mapSize];
        smell = new Smell[mapSize][mapSize];
        
        for(int row = 0; row < mapSize; row++) {
            st = new StringTokenizer(br.readLine());
            for(int col = 0; col < mapSize; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
                if(map[row][col] != 0) {
                    sharkList[map[row][col]] = new Shark(row, col, map[row][col]);  // idx == 상어의 번호
                }
            }
        }

        // 상어들의 처음 방향 일괄 입력
        st = new StringTokenizer(br.readLine());
        for(int idx = 1; idx <= sharkCount; idx++) {
            sharkList[idx].dir = Integer.parseInt(st.nextToken());
        }

        // 상어들의 방향 우선 순위
        priority = new int[sharkCount+1][5][5];  // 행 번호를 상어 번호와 맞춰주기 위해 m+1행
        for(int num = 1; num <= sharkCount; num++) {
            for(int i = 1; i <= 4; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 1; j <= 4; j++) {
                    priority[num][i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }

        simulation();  // 1001초가 되면 강제 return 하여 -1 출력
        System.out.println(TIME > 1000 ? -1 : TIME);  // 1000초가 넘어도 다른 상어가 격자에 남아 있으면 -1을 출력한다.
    }
}