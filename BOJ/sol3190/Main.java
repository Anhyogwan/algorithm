package com.ssafy.recur.BOJ.sol3190;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Commend {
	int sec;
	char direct;

	public Commend(int sec, char direct) {
		this.sec = sec;
		this.direct = direct;
	}
}

class Pre_Commend {
	int sec;
	int p_dx;
	int p_dy;

	public Pre_Commend(int sec, int dx, int dy) {
		this.sec = sec;
		this.p_dx = dx;
		this.p_dy = dy;
	}
}

public class Main {
	// 하나 먹을떄마다 다음칸 하나 늘려줌
	// 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
	// 이동한칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다.
	// L은 왼쪽
	// D는 오른쪽
	// 사과 -> 2로 표시
	// 뱀은 -> 1로 표시
	static int[][] map;
	static int[][] apple;
	static Commend[] C_list;
	static char status = 'D';
	static char pre_status = 'D';
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static int N;
	static int[] tail = { 0, 0 };
	static int cnt = 0;;
	static Queue<Pre_Commend> pre_commend = new LinkedList<>();

	// x 오른쪽은 두개를 바꾸기
	// 왼쪽은 두개를 바꿔서 *-1
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 2;
		}
		int L = Integer.parseInt(br.readLine());
		C_list = new Commend[L]; // 이동방향 명령 배열
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			C_list[i] = new Commend(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
		}
		map[0][0] = 1;
		pre_commend.offer(new Pre_Commend(C_list[0].sec, dx[0], dy[0]));
		game_start(0, 0, 0, 0, dx[0], dy[0]);
		System.out.println(cnt);
	}

	// 이동한 칸 만큼과 해당 명령을 queue에 집어넣고
	// 명령을 지속하면서 이동한 칸을 넘어가면 큐에서 값을 뺴서 tail을 그 위치까지 이동시켜줌
	// 이거 반복
	static void game_start(int idx, int x, int y, int d, int p_dx, int p_dy) {
//		for(int i = 0 ; i <map.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		cnt++;
		if (idx < C_list.length && cnt > C_list[idx].sec) {
			idx++;
			if (C_list[idx - 1].direct == 'D') {
				d=++d%4;
			} else {
				if(--d==-1) d=3;
			}
			if (idx < C_list.length) {
				pre_commend.offer(new Pre_Commend(C_list[idx].sec - C_list[idx - 1].sec-1, dx[d], dy[d]));
			}
		}
		if (!isInD(x + dx[d], y + dy[d])) {
			return;
		}
		if (!apple_check(x + dx[d], y + dy[d])) {
			if (!pre_commend.isEmpty()) {
				if (pre_commend.peek().sec-- == 0) {
					pre_commend.poll();
					//System.out.println(pre_commend);
					if (!pre_commend.isEmpty()) {
						p_dx = pre_commend.peek().p_dx;
						p_dy = pre_commend.peek().p_dy;
					}else {
						p_dx = dx[d];
						p_dy= dy[d];
					}
				}
			}
//			System.out.println(p_dx +" " + p_dy);
//			System.out.println(tail[0] +" " + tail[1]);
//			System.out.println();
			map[tail[0]][tail[1]] = 0;
			tail[0] += p_dx;
			tail[1] += p_dy;
		}
		map[x + dx[d]][y + dy[d]] = 1;
		game_start(idx, x + dx[d], y + dy[d], d, p_dx, p_dy);
	}

	// 0, 1 - > D의 명령을 입력 받았다면 -> 1,0 -> 거기서 L의 명령을 입력받았다면 - > 0 , 1
	// 1,0 -> D의 명령을 입력 받았다면 -> 0,-1
	static boolean isInD(int x, int y) { // 본인의 몸에 닿거나 , 맵의 벽 (0,0 or N,N같은 위치)에 닿으면 return;
		if (x < N && x >= 0 && y < N && y >= 0 && map[x][y] != 1) {
			return true;
		}
		return false;
	}

	static boolean apple_check(int x, int y) {
		return map[x][y] == 2 ? true : false;
	}
}
