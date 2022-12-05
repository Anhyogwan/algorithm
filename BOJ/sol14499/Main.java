package com.ssafy.recur.BOJ.sol14499;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Point {
	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	// r 이 높을수록 x축 + c가 높을수록 y축 +
	// 주사위는 위가 1 오른족이 3
	// 지도의 각 칸에는 정수가 하나씩 쓰여져 있고 주사위를 굴렸을 때 , 이동한 칸에 쓰여지 있는 수가 0이면 주사위의 바닥면에 쓰여있는 수가
	// 칸에 복사됨?
	// 0이 아니면 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사됨 그리고 칸은 0됨
	// 동쪽으로 갔을때 6 -> 3 , 1 -> 3 , 2 -> 3, 3 -> 3, 서쪽으로 갔을때 6 -> 4 , 북쪽으로 갔을때 6 -> 2
	// , 남쪽으로 갔을때 6- > 5
	// 동 서 북 남
	// 현재 위치에서 동쪽으로 갓을때는 밑에있던 숫자가 서쪽을 향하고 반대편에 있던 숫자가 동쪽을 감
	// 현재 위치에서 서쪽으로 갓을때는 밑에있던 숫자가 동쪽을 향하고 반대편에 있던 숫자가 동쪽을 감
	// 3425 -> 1625
	// 3425 -> 6125
	// 3425 -> 3416
	// 3425 -> 3461
	static int[][] map;
	static int N, M;
	static int[] oppo = { 0, 6, 5, 4, 3, 2, 1 }; // 각 주사위의 눈의 대칭점에 있는 주사위 눈
	static int[] move = { 0, 1, 0, 3, 2 }; // 동,서,북,남 으로 움직였을때 현재(now변수) 주사위 눈이 가게될 dice배열 내의 위치
	static int[] dice = { 3, 4, 2, 5 }; // 현재 주사위의 동,서,북,남 의 위치에 있는 값
	static int[] dice_stat = { 0, 0, 0, 0, 0, 0, 0 }; // 각 주사위눈에 들어가 있는 값
	static int now = 6; // 바닥과 접촉중인 주사위눈 
	static Point dice_p;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dice_p = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		int K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			game(Integer.parseInt(st.nextToken()));
		}
		System.out.println(sb);
	}

	static void game(int comd) {
		if (!isIn(dice_p.x, dice_p.y, comd))
			return;
		int temp = dice[move[comd]]; //현재 바닥과 접촉중인 주사위가 이동할 위치의 눈 임시 저장 -> 바닥접촉중인 주사위가 이동할 위치는 명령받은 방향의 반대방향 
		dice[move[comd]] = now; // 바닥과 접촉중인 주사위 눈 이동
		int temp2 = dice[comd-1]; // 이동할 방향을 보고 있던 주사위의 눈 을 temp2에 저장
		dice[comd - 1] = oppo[now]; // 바닥에 접촉중이였던 눈과 대칭점에 있는 눈을 이동할 방향을 보고있는 위치에 이동
		now = temp2; // 이동할 방향을 보고 있던 주사위의 눈을 바닥과 접촉중인 주사위로 바꿔줌
		if (map[dice_p.x][dice_p.y] == 0) {
			map[dice_p.x][dice_p.y] = dice_stat[now];
		} else {
			dice_stat[now] = map[dice_p.x][dice_p.y];
			map[dice_p.x][dice_p.y] = 0;
		}
		sb.append(dice_stat[oppo[now]] + "\n");
	}

	static boolean isIn(int x, int y, int comd) {
		if (comd == 1 && y + 1 < M) {
			dice_p.y++;
			return true;
		}
		if (comd == 2 && y - 1 >= 0) {
			dice_p.y--;
			return true;
		}
		if (comd == 3 && x - 1 >= 0) {
			dice_p.x--;
			return true;
		}
		if (comd == 4 && x + 1 < N) {
			dice_p.x++;
			return true;
		}
		return false;
	}
}
