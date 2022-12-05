package com.ssafy.recur.swea.sol5644;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;





public class SWEA_5644_무선충전 {
	static class BC {
		int x;
		int y;
		int c;
		int p;

		public BC(int x, int y, int c, int p) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}
	}
	static class User {
		int x;
		int y;

		public User(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	// 접속한 사용자의 수만큼 충전 양을 균등하게 분배 -> 여러명 들어와도 무조건 한명처리가 된다.
	// 겹치는 구간에 접속한 사람은 접속이 적게 된 망에 접속하는게 좋다.
	// A,B 겹치기 가능
	// 각 BC의 범위 좌표들을 배열로 저장
	// 시작하자마자 한번 검사 한 후 한 번 반복 할 때 마다 사용자들을 한번 이동시키면서BC들의 범위 좌표와 일치하는지 체크
	// 들어가 있다면 BC 변수 증가 (어차피 최대 2명)
	// 이 문제에 존재 가능한 예외케이스 -> 사용자가 겹쳐있는데 BC의 범위도 겹쳐있을때?
	// 구현적으로 문제가 있을만한 것 -> 각 BC의 범위 좌표 구하기
	// 사용자가 BC와의 거리가 D 이하일 경우 접근 가능
	// D = |XA – XB| + |YA – YB|
	// 사용자가 한번 씩 움직일 때 마다
	static int[] dx = { 0, -1, 0, 1, 0 };
	static int[] dy = { 0, 0, 1, 0, -1 };
	static User User_A;
	static User User_B;
	static int[] Move_A;
	static int[] Move_B;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			int[][] map = new int[10][10];
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			User_A = new User(0, 0);
			User_B = new User(9, 9);
			Move_A = new int[M + 1];
			Move_B = new int[M + 1];
			Move_A[0] = 0;
			Move_B[0] = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < M + 1; i++) {
				Move_A[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < M + 1; i++) {
				Move_B[i] = Integer.parseInt(st.nextToken());
			}
			ArrayList<BC> bc = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < A; i++) {
				bc.add(new BC(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			for (int i = 0; i < M; i++) {
				User_A.x+=dx[Move_A[i]];
				User_A.y+=dy[Move_A[i]];
				User_B.x+=dx[Move_B[i]];
				User_B.y+=dy[Move_B[i]];
				charge();
			}
		}
	}

	//BC를 반복돌려서 충전 범위 내의 BC를 체크후 비교
	//리스트로 생성하면 될듯?
	static void charge() {
	}
}
