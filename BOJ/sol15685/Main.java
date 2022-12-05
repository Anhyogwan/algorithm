package com.ssafy.recur.BOJ.sol15685;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class DraCurve {
	int x;
	int y;
	int direc;
	int gen;

	public DraCurve(int y, int x, int direc, int gen) {
		this.x = x;
		this.y = y;
		this.direc = direc;
		this.gen = gen;
	}
}

public class Main {
	// x,y 평소 쓰던거랑 반대임
	// 세대가 늘어나면 마지막 세대 기준 시계방향 90도 회전시키고 이전 세대의 끝 점에 붙인것
	// 입력된 드래곤 커브를 만들어서 맵에 기록을 하고
	// 기록이 끝나면 2중for문으로 dx,dy 로 각 꼭짓점 검사하면서 카운팅 후 출력
	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int N;
	static DraCurve[] dracurve;
	static int[][] map;
	static int cnt=0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[101][101];
		N = Integer.parseInt(br.readLine());
		dracurve = new DraCurve[N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			dracurve[i] = new DraCurve(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		for (int i = 0; i < N; i++) {
			//System.out.println(dracurve[i].x + " " + dracurve[i].y );
			map[dracurve[i].x][dracurve[i].y] = 1;
			map[dracurve[i].x + dx[dracurve[i].direc]][dracurve[i].y + dy[dracurve[i].direc]] = 1;
			ArrayList<Integer> drcu = new ArrayList<>();
			drcu.add((dracurve[i].direc + 1) % 4);
			//System.out.println(dracurve[i].x + "    " + dracurve[i].y);
			//System.out.println((dracurve[i].x+dx[dracurve[i].direc]) + "    " + (dracurve[i].y+dy[dracurve[i].direc]));
			curving(dracurve[i].x+dx[dracurve[i].direc], dracurve[i].y+ dy[dracurve[i].direc], dracurve[i].gen, 1, drcu);
		}
		
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				check(i, j);
			}
			//System.out.println(Arrays.toString(map[i]));
		}
		
		System.out.println(cnt);
		// 2차원 배열 완탐 돌리면서 정사각형 갯수 확인
	}

	private static void check(int i, int j) {
		if (map[i][j]==1 && map[i+1][j]==1 && map[i][j+1]==1 && map[i+1][j+1]==1) cnt++;
	}

	// 처음 시작 0 하고 -> 0에서 1 추가 -> 0,1 에서 2,1 로 변경 -> 2,1에서 2,3추가
	// 기존에 있던것들에서 +1 하고 뒤집은 리스트를 다음 으로 넘겨라
	// x와 y를 바꾸고 2번째 카운트마다 *
	// 드래곤 커브 맵에 입력
	// 끝점을 기록해두고
	// 일단 각 꼭짓점의 좌표값이 필요할듯
	// 좌표값을 90도를 돌리는 계산식으로 각 좌표마다의 위치를 계산해서 추가해주는식으로 해야할듯
	// 0세대 x+1,1세대 y-1,2세대 x-1,y-1,3세대 x-1,y+1,x-1,y-1
	// 우측방향으로 시작했을때 기준
	// 방향을 기준으로 반대로 읽어서 +1을 해준다?
	// 1부터 기존에 있던거를 뒤에서 탐색하면서 +1을 해줘서 앞에 추가
	// 입력된 direc 값은 0 -> 1 -> 2 1-> 2 3 2 1, 2 3 0 3 2 3 2 1
	
	// 90도 돌리는 방법
	// 상단에 있는 dx,dy 방향변수를 담은 리스트를 생성 (drcu)
	// 우선 리스트를 반복돌리면서 방향대로 x,y좌표를 체크 하면서 이동
	// 리스트를 뒤에서 부터 탐색하며 +1%4 해준 값 + 기존 리스트값 을 새 리스트에 저장 그렇게 리스트를 계속 늘려가면서 지정한 세대까지 재귀
	private static void curving(int x, int y, int generate, int gen, ArrayList<Integer> drcu) {
		if (gen > generate) {
			return;
		}
		for (int i = 0; i < drcu.size(); i++) {
			x = x + dx[drcu.get(i)];
			y = y + dy[drcu.get(i)];
			//System.out.println(x+ " " + y);
			map[x][y] = 1;
		}
		ArrayList<Integer> drcu_2 = new ArrayList<>();
		for (int i = drcu.size()-1; i >= 0; i--) {
			drcu_2.add((drcu.get(i) + 1) % 4);
		}
		drcu_2.addAll(drcu);
		curving(x, y, generate, gen + 1, drcu_2);
	}
}
