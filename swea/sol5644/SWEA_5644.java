package com.ssafy.recur.swea.sol5644;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
 
class BC {
    int x;
    int y;
    int c;
    int p;
 
    public BC(int x, int y, int c, int p) {
        this.x = y;
        this.y = x;
        this.c = c;
        this.p = p;
    }
}
 
class User {
    int x;
    int y;
 
    public User(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
 
public class SWEA_5644 {
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
    static ArrayList<BC> bc;
    static int max_charge;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int tc = 1; tc <= T; tc++) {
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
            bc = new ArrayList<>();
            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                bc.add(new BC(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
                        Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }
 
            Collections.sort(bc, (o1, o2) -> o2.p - o1.p);
            max_charge = 0;
            for (int i = 0; i < M + 1; i++) {
                User_A.x += dx[Move_A[i]];
                User_A.y += dy[Move_A[i]];
                User_B.x += dx[Move_B[i]];
                User_B.y += dy[Move_B[i]];
                charge();
            }
            System.out.println("#" + tc + " " + max_charge);
        }
    }
 
    // BC를 반복돌려서 충전 범위 내의 BC를 체크후 비교
    // 만약 사용자 두명이 같은 범위의 bc에 들어와 있다면 양쪽 사용자가 다른 범위의 BC에도 들어와 있는지 비교
    // 다른 범위의 BC에도 있다면 해당 사용자가 들어가 있는 BC값과 겹쳐있는 BC의 1/2 값을 비교해서 더 높으면 충전 아니면 BC를 +
    // 해줌
    static void charge() {
        ArrayList<BC> A_Possible = new ArrayList<>();
        ArrayList<BC> B_Possible = new ArrayList<>();
        for (int i = 0; i < bc.size(); i++) {
            if (Math.abs(User_A.x - bc.get(i).x) + Math.abs(User_A.y - bc.get(i).y) <= bc.get(i).c) {
                A_Possible.add(bc.get(i));
            }
            if (Math.abs(User_B.x - bc.get(i).x) + Math.abs(User_B.y - bc.get(i).y) <= bc.get(i).c) {
                B_Possible.add(bc.get(i));
            }
        }
         
        if (A_Possible.size() == 0 && B_Possible.size() == 0) {
            return;
        }
        if (A_Possible.size() == 0 && B_Possible.size() != 0) {
            max_charge += B_Possible.get(0).p;
            return;
        }
        if (A_Possible.size() != 0 && B_Possible.size() == 0) {
            max_charge += A_Possible.get(0).p;
            return;
        }
        if (B_Possible.get(0).equals(A_Possible.get(0))) {
            if (B_Possible.size() == 1 && A_Possible.size() == 1) {
                max_charge += A_Possible.get(0).p;
                return;
            }
            if (B_Possible.size() > 1 && A_Possible.size() == 1) {
                max_charge += (B_Possible.get(1).p + A_Possible.get(0).p);
                return;
            } else if (B_Possible.size() > 1 && A_Possible.size() == 1) {
                max_charge += B_Possible.get(0).p;
                return;
            }
            if (A_Possible.size() > 1 && B_Possible.size() == 1) {
                max_charge += (A_Possible.get(1).p + B_Possible.get(0).p);
                return;
            } else if (A_Possible.size() > 1 && B_Possible.size() == 1) {
                max_charge += A_Possible.get(0).p;
                return;
            }
            if (A_Possible.size() > 1 && B_Possible.size() > 1) {
                max_charge += (A_Possible.get(0).p + Math.max(A_Possible.get(1).p, B_Possible.get(1).p));
            } else {
                max_charge += A_Possible.get(0).p;
            }
        } else {
            max_charge += (A_Possible.get(0).p + B_Possible.get(0).p);
        }
    }
}