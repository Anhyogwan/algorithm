package com.ssafy.recur.BOJ.sol17143;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	// 칸에 상어는 최대 한마리 있을 수 있음
	// 시작지점은 1번열에서 한칸 <<
	// 낚시왕이 오른쪽 한 칸 이동
	// 땅(x축 0인듯?)과 제일 가까운 상어를잡음
	// 상어는 잡으면 사라짐
	// 상어 주어진 속도로 이동
	// 상어가 범위 밖으로 이동하려고하면 방향을 반대로 바꿔서 그쪽으로 이동
	// 이동 끝나고 만약 같은 칸에 상어가 두마리 이상 있으면 크기가 가장 큰 애만 살아남음
	// 같은 크기는 없고, 둘 이상의 상어가 있는 경우도 없음
	static class Shark {
		int i, r, c, s, d, z;

		public Shark(int i, int r, int c, int s, int d, int z) {
			this.i = i;
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}

	// 1에서 2로 즉,1-5
	static int r, c, m;
	static ArrayList<Shark> shark;
	static Shark[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		shark = new ArrayList<>();
		map = new Shark[r][c];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int sr = Integer.parseInt(st.nextToken()) - 1;
			int sc = Integer.parseInt(st.nextToken()) - 1;
			int ss = Integer.parseInt(st.nextToken());
			int sd = Integer.parseInt(st.nextToken());
			int sz = Integer.parseInt(st.nextToken());
			Shark s = new Shark(i, sr, sc, ss, sd, sz);
			shark.add(s);
			map[sr][sc] = s;
		}
		fishing(0);
	}

	// 낚시왕이 y +1 로 한칸 이동하고
	// 상어 잡고
	// 상어 이동
	// 4 칸인데 2칸이 늘음
	// 시작위치 + (입력 -2)+c%c or c
	// 이동하는 칸수는 %연산의 나머지
	// 바라보는 방향은 /연산의 몫
	// 연산의 몫의 홀수 짝수를 봐서 방향을 판단하고 그 방향만큼 %연산한 칸 수를 이동
	static int result = 0;

	private static void fishing(int FishKingy) {
//		for (int i = 0; i < shark.size(); i++) {
//			System.out.println(FishKingy + " " + (shark.get(i).r + 1) + " " + (shark.get(i).c + 1) + " " + (shark.get(i).d));
//		}
		if (FishKingy >= c) {
			System.out.println(result);
			return;
		}

		int nr = r;
		for (int i = 0; i < shark.size(); i++) {
			if (shark.get(i).c == FishKingy) {
				if (nr >= shark.get(i).r) {
					nr = shark.get(i).r;
				}
			}
		}
		for (int i = 0; i < shark.size(); i++) {
			if (shark.get(i).c == FishKingy && nr == shark.get(i).r) {
				// System.out.println(shark.get(i).z);
				result += shark.get(i).z;
				shark.remove(i);
				break;
			}
		}
//8 3 5 4 2
		moveShark();
		fishing(FishKingy + 1);
	}

	// 상어는 속력만큼 이동 , 튕김
	// 컬럼길이 * 2 -2 가 한번 왕복하는데 걸리는 길이?

	static int[] dx = { -1, 1 };
	static int[] dy = { 1, -1 };

	private static void moveShark() {
		map = new Shark[r][c];
		for (int i = 0; i < shark.size(); i++) {
//			System.out.println(shark.get(i).r + " " + shark.get(i).c);
			if (shark.get(i).d == 1 || shark.get(i).d == 2) {
//				System.out.println(shark.get(i).s);
//				System.out.println((r * 2 - 2));
				int move = shark.get(i).s % (r * 2 - 2);
//				System.out.println(move);
				for (int j = 0; j < move; j++) {
					if (shark.get(i).r == 0) {
						shark.get(i).d = 2;
					}
					if (shark.get(i).r == r - 1) {
						shark.get(i).d = 1;
					}
					shark.get(i).r += dx[shark.get(i).d - 1];
				}
			} else {
//				System.out.println(shark.get(i).s);
//				System.out.println(shark.get(i).d);
				int move = shark.get(i).s % (c * 2 - 2);
				for (int j = 0; j < move; j++) {
					if (shark.get(i).c == 0) {
						shark.get(i).d = 3;
					}
					if (shark.get(i).c == c - 1) {
						shark.get(i).d = 4;
					}
					shark.get(i).c += dy[shark.get(i).d - 3];
//					System.out.println("move " + move + " " + shark.get(i).d);
//					System.out.println(
//							"move " + move + " " + shark.get(i).d + " " + (shark.get(i).r+1) + " " + (shark.get(i).c+1));
				}
			}
			if (map[shark.get(i).r][shark.get(i).c] != null) {
				// System.out.println("걸림" + shark.get(i).z + " " +
				// map[shark.get(i).r][shark.get(i).c].z);
				if (shark.get(i).z > map[shark.get(i).r][shark.get(i).c].z) {
					Shark s=map[shark.get(i).r][shark.get(i).c];
					map[shark.get(i).r][shark.get(i).c] = shark.get(i);
					shark.remove(s);
				} else {
					shark.remove(shark.get(i));
				}
				i--;
			} else {
				map[shark.get(i).r][shark.get(i).c] = shark.get(i);
			}
		}

	}
}
