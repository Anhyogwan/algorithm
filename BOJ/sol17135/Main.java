package com.ssafy.recur.BOJ.sol17135;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Enemy {
	int x;
	int y;

	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Archer {
	int x;
	int y;

	public Archer(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	// 2시간 반쯤 걸린듯
	// 격자판의 N번행 바로 아래(N+1번행)의 모든 칸에는 성이 있다
	// 궁수는 성이 있는 칸에 배치할 수 있음 -> 하나의 칸에는 하나의 궁수만 존재 가능
	// 턴마다 궁수는 적 하나 공격가능 또한 모든 궁수가 동시 공격 (같은 공격이 겹칠 수 있음 -> 맞는 사람 좀 불쌍한듯)
	// 궁수가 공격하는 사람은 거리가 D이하인 적중 가장 가까운 적 -> 8방탐색?
	// 여럿일 경우엔 가장 왼쪽에 있는 적 공격 --??
	// 공격받은 적은 게임에서 제외
	// 궁수 턴 끝나면 적 아레로 한 칸 이동 , 성이 있는 칸으로 갔을 경우 사망처리
	// 모든 적이 맵에서 사망하면 게임 끝
	// 격자판이 주어졋을때, 궁수의 공격으로 제거 할 수 있는 적의 최대 수 계산
	// 궁수가 '동시'에 공격한다는것을 보니 아마 bfs 문제가 아닐까 생각함 (dfs를 사용할 경우 중복이 발생할 가능성이 있음)
	// 문제 요약 ->턴 마다 적을 죽이는 시스템을 구현하고 그것을 조합 돌려서 최상의 결과를 뽑아라
	static int[][] map;
	static int N, M, D;
	static ArrayList<Enemy> eCount_3;
	static int max_kill = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		eCount_3 = new ArrayList<>();
		map = new int[N + 1][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num == 1) {
					eCount_3.add(new Enemy(i, j));
				}
				map[i][j] = num;
			}
		}
		comb(0, 0, 0);
		System.out.println(max_kill);
	}

	static void comb(int idx, int start, int flag) {
		if (idx == 3) {
			ArrayList<Archer> archer = new ArrayList<>();
			int[][] map2 = new int[N + 1][M];
			for (int i = 0; i < M; i++) {
				if ((flag & 1 << i) != 0) {
					archer.add(new Archer(N, i));
				}
			}

			for (int i = 0; i < N + 1; i++) {
				map2[i] = map[i].clone();
			}
			ArrayList<Enemy> eCount_2 = new ArrayList<>();
			eCount_2.addAll(eCount_3);
			game(archer, map2, eCount_2, 0);
			return;
		}

		for (int i = start; i < M; i++) {
			comb(idx + 1, i + 1, flag | 1 << i);
		}
	}

	static void game(ArrayList<Archer> archer, int[][] map2, ArrayList<Enemy> eCount, int enemy) {
		if (eCount.size() <= 0) {
			if (max_kill < enemy) {
				max_kill = enemy;
			}
			return;
		}
		ArrayList<Enemy> shoot = new ArrayList<Enemy>();
		for (int i = 0; i < archer.size(); i++) {
			int max_value = Integer.MAX_VALUE;
			Enemy shooting = null;
			for (int j = 0; j < eCount.size(); j++) {
				int r = Math.abs(archer.get(i).x - eCount.get(j).x);
				int c = Math.abs(archer.get(i).y - eCount.get(j).y);
				if (r + c <= D) {
					if (max_value == r + c && shooting.y > eCount.get(j).y) {
						shooting = eCount.get(j);
					}
					if (max_value > r + c) {
						shooting = eCount.get(j);
						max_value = r + c;
					}
				}
			}
			if (shooting != null) {
				shoot.add(shooting);
			}
		}

		for (int i = 0; i < shoot.size(); i++) {
			map2[shoot.get(i).x][shoot.get(i).y] = 0;
			if (eCount.contains(shoot.get(i))) {
				eCount.remove(shoot.get(i));
				enemy++;
			}
		}

		ArrayList<Enemy> eCount_4 = new ArrayList<>();
		for (int i = 0; i < eCount.size(); i++) {
			eCount_4.add(new Enemy(eCount.get(i).x, eCount.get(i).y));
		}
		for (int i = 0; i < eCount_4.size(); i++) {
			if (eCount_4.get(i).x + 1 >= N) {
				map2[eCount_4.get(i).x][eCount_4.get(i).y] = 0;
				eCount_4.remove(i);
				i--;
				continue;
			}
			map2[eCount_4.get(i).x][eCount_4.get(i).y] = 0;
			eCount_4.get(i).x += 1;
			map2[eCount_4.get(i).x][eCount_4.get(i).y] = 1;
		}
		game(archer, map2, eCount_4, enemy);
	}
}
