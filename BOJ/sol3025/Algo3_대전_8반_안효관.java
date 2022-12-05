package com.ssafy.recur.BOJ.sol3025;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Wall {
	char some;
	int x;
	int y;

	public Wall(char some, int x, int y) {
		this.some = some;
		this.x = x;
		this.y = y;
	}
}

public class Algo3_대전_8반_안효관 {
	// 비어 있거나 장애물로 막혀있음
	// 화산탄이 날아올 열을 입력하면 거기 첫행부터 굴러떨어짐
	// 더이상 움직이지 못 할 때까지 떨어짐
	// 화산탄 아래칸이 장애물 or 제일밑이라면 거기서 멈춤
	// 비어있으면 떨어짐
	// 떨어지는 화산탄 아래에 다른 화산탄이 있을 경우 -> 왼쪽+왼쪽 아래가 비어있다면 왼쪽아래로
	// 왼족이 불가능 할 경우 오른쪽 + 오른족 아래를 비어있는지 확인한 후 그쪽으로
	// 그게 아니라면 거기서 멈춤
	// X 좌표를 전부 저장하고
	// 화산탄이 멈춘 좌표도 계속 추가해주면서
	static int R, C, N;
	static char[][] map;
	static ArrayList<Wall> wall;
	static ArrayList<Wall>[] some;
	static Wall[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		String temp = "";
		wall = new ArrayList<>();
		// 제일 위에 있는것이 무엇인지 확인해야함d
		some = new ArrayList[C];
		for (int i = 0; i < C; i++) {
			some[i] = new ArrayList<>();
		}
		for (int i = 0; i < R; i++) {
			temp = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = temp.charAt(j);
				if (map[i][j] == 'X') {
					some[j].add(new Wall('X', i, j));
				}
			}
		}
		visited = new Wall[C];
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int col = Integer.parseInt(br.readLine()) - 1;
			int r = 0;
//			for (int a = 0; a < C; a++) {
//				if (visited[a] != null)
//					System.out.println(a + " " + visited[a].x + " " + visited[a].y);
//			}
			if (visited[col] != null) {
				if (isIn(visited[col].x - 1, visited[col].y - 1) && visited[col].y > col) {
					//System.out.println(visited[col].x + " " + visited[col].y);
					rolling(visited[col].y - 1, visited[col].x - 1, col);
				} else if (isIn(visited[col].x - 1, visited[col].y + 1) && visited[col].y < col) {
					rolling(visited[col].y + 1, visited[col].x - 1, col);
				} else {
					rolling(visited[col].y, visited[col].x - 1, col);
				}
			} else {
				rolling(col, 0, col);
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				bw.write(map[i][j]);
			}
			bw.write("\n");
		}
		bw.flush();
	}

	private static void rolling(int col, int r, int col_2) {
		// 시작 후 제일 처음 만나는게 벽이나 바닥일 경우는 걔 좌표 x-1 에다가 돌 올려놓고 최상단 돌로 해놓고 탈출
		// 시작 후 제일 처음 만나는게 돌일 경우 해당 라인에 있는 장애물중 돌 밑에 있는 애들중에 최상단을 찾아서 재귀
//		// 시작하는 지점을 계속 변경 해줘야함
//		for (int i = 0; i < R; i++) {
//			System.out.println(map[i]);
//		}
//		System.out.println();
		if (some[col].size() != 0) {
			for (int i = 0; i < some[col].size(); i++) {
				if (some[col].get(i).x > r) {
					if (some[col].get(i).some == 'X') {
						some[col].add(i, new Wall('O', some[col].get(i).x - 1, some[col].get(i).y));
						map[some[col].get(i).x][some[col].get(i).y] = 'O';
						visited[col_2] = new Wall('O', some[col].get(i).x, some[col].get(i).y);
						return;
					}
					if (some[col].get(i).some == 'O') {
						// 왼쪽 검사, 오른쪽 검사 후 이동방향 or 멈출지 결정
						if (isIn(some[col].get(i).x, some[col].get(i).y - 1)
								&& isIn(some[col].get(i).x - 1, some[col].get(i).y - 1)) {
							rolling(col - 1, some[col].get(i).x - 1, col_2);
							return;
						} else if (isIn(some[col].get(i).x, some[col].get(i).y + 1)
								&& isIn(some[col].get(i).x - 1, some[col].get(i).y + 1)) {
							rolling(col + 1, some[col].get(i).x - 1, col_2);
							return;
						} else {
							some[col].add(i, new Wall('O', some[col].get(i).x - 1, some[col].get(i).y));
							map[some[col].get(i).x][some[col].get(i).y] = 'O';
							visited[col_2] = new Wall('O', some[col].get(i).x, some[col].get(i).y);
							return;
						}
					}
				}
			}
		}
		some[col].add(new Wall('O', R - 1, col));
		map[R - 1][col] = 'O';
		visited[col_2] = new Wall('O', some[col].get(0).x, some[col].get(0).y);
	}

	private static boolean isIn(int x, int y) {
		if (x < R && x >= 0 && y < C && y >= 0 && map[x][y] == '.')
			return true;
		return false;
	}
}
