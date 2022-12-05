package com.ssafy.recur.BOJ.sol3109;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Build {
	int x;
	int y;

	public Build(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	// 빵집이 있는곳은 R,C 좌표
	// 첫째열 -> 근처의 빵집 ,마지막 열 -> 원웅이 빵집
	// 모든 파이프라인은 첫째 열에서 시작해야하고, 마지막 열에서 끝나야 함
	// 각 칸의 중심기리 연결
	// . 빈칸 x는 건물
	// 한 칸 당 세방향으로 진행 가능
	// 경로는 겹칠 수 없고 접할 수 없다 ( 즉, 겹칠경우 백트래킹으로 제외할 케이스 메모이제이션 사용 할 필요 있어 보임)
	static char[][] map;
	static int C, R;
	static int[] dx = { -1, 0, 1 };
	static int max_value = 0;
	static boolean[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		dp = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String str1 = br.readLine();
			for (int j = 0; j < C; j++) {
				char ch = str1.charAt(j);
				if (ch == 'x') {
					dp[i][j] = true;
				}
			}
		}
		for (int i = 0; i < R; i++) {
			if (check(0, i)) {
				max_value++;
			}
				
		}
		System.out.println(max_value);
	}

	// 1번의 3개의 경우의 수를 구하고 그걸 기준으로 밑의 경우의 수를 구하고 ... x번까지 반복 하면서 조건에 맞지 않을거같은 애들을
	// 짤라주는
	// 경우의 수를 완성하자 마자 걔로 다시 반복을 들어가는걸 꼐속 반복해야함
	static boolean check(int y, int x) {
		if (y == C - 1) {
			return true;
		}
		// 파이프라인의 경우의 수를 나온 dp 맵을 토대로
		for (int i = 0; i < 3; i++) {
			if (x + dx[i] >= 0 && x + dx[i] < R && dp[x + dx[i]][y + 1] != true) {
				dp[x + dx[i]][y + 1] = true;
				if(check(y + 1, x + dx[i])) return true;
			}
		}
		return false;
	}
}
