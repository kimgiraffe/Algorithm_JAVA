package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준_17266_어두운굴다리
 *
 * @author semin.kim
 */
public class BOJ_17266_어두운굴다리_김세민 {
    static BufferedReader br;
    static StringTokenizer st;

    static int N, M; // 굴다리의 길이, 가로등의 위치
    static int[] light;

    public static boolean decide(int x){
        int prev = 0;
        for(int idx = 0; idx < M; idx++){
            if(light[idx] - x <= prev){
                prev = light[idx] + x;
            } else {
                return false;
            }
        }

        return N - prev <= 0;
    }

    public static int solve(){
        int low = 0;
        int high = N - 1;

        while(low <= high){
            int mid = low + (high - low) / 2;

            if(decide(mid)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine().trim());
        M = Integer.parseInt(br.readLine().trim());

        light = new int[M];

        st = new StringTokenizer(br.readLine().trim());

        for(int idx = 0; idx < M; idx++){
            light[idx] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve());
    }
}
