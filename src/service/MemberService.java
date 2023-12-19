package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import controller.Controller;
import dao.MemberDAO;
import util.ScanUtil;
import util.ViewEnum;

public class MemberService {

   private static MemberService instance = null;

   private MemberService() {
   }

   public static MemberService getInstance() {
      if (instance == null)
         instance = new MemberService();
      return instance;
   } // 싱글톤 패턴

   MemberDAO memberdao = MemberDAO.getInstance();

   public int memberSignUp() { // 회원가입
      //System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
      System.out.println("──────────────────────   회원가입     ────────────────────────");
      String memNo;
      while (true) {
         System.out.print("아이디    : ");
         memNo = ScanUtil.nextLine();
         if (memNo.length() < 1) {
            System.out.println("-필수 입력 사항입니다.");
         } else if (memNo.length() > 5) {
            System.out.println("-5글자 이하로 작성해주세요. ");
         } else {
            break;
         }
      }

      String memPass;
      while (true) {
         System.out.print("비밀번호    : ");
         memPass = ScanUtil.nextLine();
         if (memPass.length() < 1) {
            System.out.println("-필수 입력 사항입니다.");
         } else if (memPass.length() > 16) {
            System.out.println("-15글자 이하로 작성해주세요. ex) java , 1234,  a01234\"");
         } else {
            break;
         }
      }

      String memName;
      while (true) {
         System.out.print("회원명    : ");
         memName = ScanUtil.nextLine();
         if (memName.length() < 1) {
            System.out.println("-필수 입력 사항입니다.");
         } else if (memName.length() > 15) {
            System.out.println("-15글자 이하로 작성해주세요.");
         } else {
            break;
         }
      }

      int memAge;
      while (true) {
         System.out.print("나이    : ");
         memAge = ScanUtil.nextInt();
         int length = (int) (Math.log10(memAge) + 1);
         if (length < 1) {
            System.out.println("-필수 입력 사항입니다.");
         } else if (length > 5) {
            System.out.println("-5글자 이하로 작성해주세요. ex) 24");
         } else {
            break;
         }
      }

      String memGender;
      while (true) {
         System.out.print("성별    : ");
         memGender = ScanUtil.nextLine();
         if (memGender.length() < 1) {
            System.out.println("-필수 입력 사항입니다.");
         } else if (memGender.length() > 5) {
            System.out.println("-5글자 이하로 작성해주세요. ex) 여");
         } else {
            break;
         }
      }

      String memJumin;
      while (true) {
         System.out.print("주민번호    : ");
         memJumin = ScanUtil.nextLine();
         if (memJumin.length() < 1) {
            System.out.println("-필수 입력 사항입니다.");
         } else if (memJumin.length() > 15) {
            System.out.println("-하이픈을 포함하여 15글자 이하로 작성해주세요 ex) 001215-4643565");
         } else {
            break;
         }
      }

      String memTelno;
      while (true) {
         System.out.print("연락처    : ");
         memTelno = ScanUtil.nextLine();
         if (memTelno.length() < 1) {
            System.out.println("-필수 입력 사항입니다.");
         } else if (memTelno.length() > 15) {
            System.out.println("-하이픈을 포함하여 15글자 이하로 작성해주세요. ex) 010-4419-8888");
         } else {
            break;
         }
      }

      String memAdress;
      while (true) {
         System.out.print("주소    : ");
         memAdress = ScanUtil.nextLine();
         if (memAdress.length() < 1) {
            System.out.println("-필수 입력 사항입니다.");
         } else if (memAdress.length() > 50) {
            System.out.println("-50글자 이하로 작성해주세요");
         } else {
            break;
         }
      }

      List<Object> param = new ArrayList<>();
      param.add(memNo);
      param.add(memPass);
      param.add(memName);
      param.add(memAge);
      param.add(memGender);
      param.add(memJumin);
      param.add(memTelno);
      param.add(memAdress);

      int result = memberdao.signUp(param);

      if (result > 0) {
         System.out.println("-회원가입 성공");
         System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
         return ViewEnum.MEMBER_CAR;
      } else {
         System.out.println("-회원 가입이 정상적으로 되지 않았습니다.");
         return ViewEnum.MEMBER_SIGNUP;
      }
   }

   public int memberCar() { // 주차권 발행
      System.out.print("-주차권이 필요하십니까? (Y/N) : ");
      String answer = ScanUtil.nextLine();
      if (answer.equals("N") || answer.equals("n")) {
         System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
         return ViewEnum.MEMBER_LOCKER;
      } else if (answer.equals("Y") || answer.equals("y")) {
         System.out.print("-차량번호를 입력해주세요. : ");
         String memCarNo = ScanUtil.nextLine();
         System.out.print("-본인 확인을 위해 아이디를 입력해주세요. : ");
         String memNo = ScanUtil.nextLine();

         List<Object> param = new ArrayList<>();
         param.add(memCarNo);
         param.add(memNo);

         int result = memberdao.Update_Car(param);

         if (result > 0) {
            System.out.println("-주차권 발부 완료. ");
            return ViewEnum.MEMBER_LOCKER;
         } else {
            System.out.println("-주차권 지정이 정상적으로 되지 않았습니다. ");
            System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
            return ViewEnum.MEMBER_CAR;
         }
         
      } else {
         System.out.println("-Y,y 또는 N,n로 입력해주세요. ");
         return ViewEnum.MEMBER_CAR;
      }

   }

   public int memberLocker() { // 사물함 지정

      String memNo;
      while (true) {
         //System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
         System.out.println("──────────────────────  사물함 지정   ────────────────────────");
         System.out.print("-본인 확인을 위해 아이디를 입력해주세요.  : ");
         memNo = ScanUtil.nextLine();
         if (memNo.length() < 1) {
            System.out.println("-필수 입력 사항입니다.");
         } else if (memNo.length() > 5) {
            System.out.println("-5글자 이하로 작성해주세요. ");
         } else {
            break;
         }
      }

      List<Object> list = new ArrayList<>();
      list = memberdao.memberLockerPicture();
      for (int i = 1; i <= 40; i++) {
         if (list.get(i - 1) == null) {
            System.out.print(".□.");
         } else {
            System.out.print(".■.");
         }
         if (i % 10 == 0) {
            System.out.println();
         }
      }

      System.out.println("-본인이 원하는 좌석을 입력하세요. ");
      System.out.println("   (왼쪽부터 1번 ~ 40번)  : ");

      int memLockNo = ScanUtil.nextInt();

      for (int i = 1; i <= 40; i++) {
         if (list.get(i - 1) != null) {
            if (i == memLockNo) {
               System.out.println("-이미 지정되어있는 사물합입니다. 다시 입력해주세요");
               return memberLocker();
            }
         }

      }

      List<Object> param1 = new ArrayList<>();
      param1.add(memNo);
      param1.add(memLockNo);

      int result = memberdao.Locker(param1); // 새로운 사물함 번호 업데이트

      if (result > 0) {
         System.out.println("-사물함 지정 성공");
         return ViewEnum.MEMBER_MEMBERSHIP;
      } else {
         System.out.println("-사물함 지정이 정상적으로 되지 않았습니다.");
         System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
         return ViewEnum.MEMBER_LOCKER;
      }
   }

   public int memberMemberShip() { // 회원권 구매
      //System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
      System.out.println("──────────────────────  회원권 구매   ────────────────────────");
      System.out.println("  1. 일반   2.스피닝   3.필라테스   4.PT  ");
      System.out.println(" 원하시는 번호를 선택해주세요 (1~4)  ");
      System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
      int selectnum = ScanUtil.nextInt();

      switch (selectnum) {
      case 1: // 일반 회원권 구매
         List<Object> list = new ArrayList<>();
         list = memberdao.employeeList_EE();
         System.out.println("-문의사항시 연락주세요. ");
         for (int i = 0; i < list.size(); i++) { // 관장 프로필 출력
            System.out.print(list.get(i) + " | ");
         }
         System.out.print("\n" + "구매하시겠습니까? (Y/N) : ");
         String enswer = ScanUtil.nextLine();
         if (enswer.equals("N") || enswer.equals("n")) {
            return memberMemberShip();

         }

         else if (enswer.equals("Y") || enswer.equals("y")) {
            System.out.println();
            System.out.println("  1. 10회권  |  50000원  ");
            System.out.println("  2. 20회권  |  90000원  ");
            System.out.println("  3. 30회권  |  140000원  ");
            System.out.println("-횟수를 입력해주세요 (1~3) : ");
            int selectnum_E = ScanUtil.nextInt(); // 횟수권 선택
            List<Object> list_E = new ArrayList<>(); // list_E에 매개변수 넣기
            System.out.println("-본인 확인을 위해 아이디를 입력해주세요. : ");
            String memNo = ScanUtil.nextLine(); // memNo
            String eNo = "E011"; // eNo
            String msKind = "일반"; // msKind
            int msCount = 0; // msCount
            int msCost = 0; // msCost
            switch (selectnum_E) {
            case 1:
               msCount = 10;
               msCost = 50000;
               break;
            case 2:
               msCount = 20;
               msCost = 90000;
               break;
            case 3:
               msCount = 30;
               msCost = 140000;
               break;
            }
            list_E.add(msKind);
            list_E.add(msCount);
            list_E.add(msCost);
            list_E.add(memNo);
            list_E.add(eNo);
            int result = memberdao.membershipUpdate(list_E);
            if (result > 0) {
               System.out.println("-회원권 구매 성공");
               System.out.println("-로그인 해서 운동 시작하세요!");
               System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
               return ViewEnum.HOME_MAIN;
            } else {
               System.out.println("-구매가 정상적으로 되지 않았습니다.");
               System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
               return ViewEnum.MEMBER_MEMBERSHIP;
            }
         } else {
            System.out.println("-Y,y 또는 N,n로 입력해주세요. ");
            return memberMemberShip();
         }
      case 2: // 스피닝 회원권 구매
         List<Object> list_S = new ArrayList<>();
         list_S = memberdao.employeeList_SS();
         for (int i = 0; i < list_S.size(); i++) { // 스피닝 프로필 출력
            System.out.print(list_S.get(i) + " | ");
            if ((i + 1) % 5 == 0) {
               System.out.println();
            }
         }
         System.out.println(" \n-스피닝 강사님을 선택해 주세요. ");
         System.out.println(" (1. 조지나,  2. 곽공두) : ");
         int selectnum_S = ScanUtil.nextInt();
         switch (selectnum_S) {
         case 1: // 조지나 선택
            System.out.print("\n" + "조지나 선생님께 배우시겠습니까? (Y/N) : ");
            String enswer_S_Z = ScanUtil.nextLine();
            if (enswer_S_Z.equals("N") || enswer_S_Z.equals("n")) {
               return memberMemberShip();

            }

            else if (enswer_S_Z.equals("Y") || enswer_S_Z.equals("y")) {
               System.out.println();
               System.out.println("  1. 10회권  |  200000원  ");
               System.out.println("  2. 20회권  |  390000원  ");
               System.out.println("  3. 30회권  |  580000원  ");
               System.out.println("-횟수를 입력해주세요 (1~3) : ");
               int selectnum_S_Z = ScanUtil.nextInt(); // 횟수권 선택
               List<Object> list_S_Z = new ArrayList<>(); // list에 매개변수 넣기
               System.out.println("-본인 확인을 위해 아이디를 입력해주세요. : ");
               String memNo_S_Z = ScanUtil.nextLine(); // memNo
               String eNo_S_Z = "E005"; // eNo
               String msKind_S_Z = "스피닝"; // msKind
               int msCount_S_Z = 0; // msCount
               int msCost_S_Z = 0; // msCost
               switch (selectnum_S_Z) {
               case 1:
                  msCount_S_Z = 10;
                  msCost_S_Z = 200000;
                  break;
               case 2:
                  msCount_S_Z = 20;
                  msCost_S_Z = 390000;
                  break;
               case 3:
                  msCount_S_Z = 30;
                  msCost_S_Z = 580000;
                  break;
               }
               list_S_Z.add(msKind_S_Z);
               list_S_Z.add(msCount_S_Z);
               list_S_Z.add(msCost_S_Z);
               list_S_Z.add(memNo_S_Z);
               list_S_Z.add(eNo_S_Z);
               int result_S_Z = memberdao.membershipUpdate(list_S_Z);
               if (result_S_Z > 0) {
                  System.out.println("-회원권 구매 성공");
                  System.out.println("-로그인 해서 운동 시작하세요!");
                  System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
                  return ViewEnum.HOME_MAIN;
               } else {
                  System.out.println("-구매가 정상적으로 되지 않았습니다.");
                  System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
                  return ViewEnum.MEMBER_MEMBERSHIP;
               }
            } else {
               System.out.println("-Y,y 또는 N,n로 입력해주세요. ");
               return memberMemberShip();
            }

         case 2: // 곽공두 선택
            System.out.print("\n" + "곽공두 선생님께 배우시겠습니까? (Y/N) : ");
            String enswer_S_K = ScanUtil.nextLine();
            if (enswer_S_K.equals("N") || enswer_S_K.equals("n")) {
               return memberMemberShip();

            }

            else if (enswer_S_K.equals("Y") || enswer_S_K.equals("y")) {
               System.out.println();
               System.out.println("  1. 10회권  |  200000원  ");
               System.out.println("  2. 20회권  |  390000원  ");
               System.out.println("  3. 30회권  |  580000원  ");
               System.out.println("-횟수를 입력해주세요 (1~3) : ");
               int selectnum_S_K = ScanUtil.nextInt(); // 횟수권 선택
               List<Object> list_S_K = new ArrayList<>(); // list_E에 매개변수 넣기
               System.out.println("-본인 확인을 위해 아이디를 입력해주세요. : ");
               String memNo_S_K = ScanUtil.nextLine(); // memNo
               String eNo_S_K = "E006"; // eNo
               String msKind_S_K = "스피닝"; // msKind
               int msCount_S_K = 0; // msCount
               int msCost_S_K = 0; // msCost
               switch (selectnum_S_K) {
               case 1:
                  msCount_S_K = 10;
                  msCost_S_K = 200000;
                  break;
               case 2:
                  msCount_S_K = 20;
                  msCost_S_K = 390000;
                  break;
               case 3:
                  msCount_S_K = 30;
                  msCost_S_K = 580000;
                  break;
               }
               list_S_K.add(msKind_S_K);
               list_S_K.add(msCount_S_K);
               list_S_K.add(msCost_S_K);
               list_S_K.add(memNo_S_K);
               list_S_K.add(eNo_S_K);
               int result_S_K = memberdao.membershipUpdate(list_S_K);
               if (result_S_K > 0) {
                  System.out.println("-회원권 구매 성공");
                  System.out.println("-로그인 해서 운동 시작하세요!");
                  System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
                  return ViewEnum.HOME_MAIN;
               } else {
                  System.out.println("-구매가 정상적으로 되지 않았습니다.");
                  System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
                  return ViewEnum.MEMBER_MEMBERSHIP;
               }
            } else {
               System.out.println("-Y,y 또는 N,n로 입력해주세요. ");
               return memberMemberShip();
            }
         }

      case 3: // 필라테스 회원권 구매
         List<Object> list_P = new ArrayList<>();
         list_P = memberdao.employeeList_PP();
         for (int i = 0; i < list_P.size(); i++) { // 필라테스 프로필 출력
            System.out.print(list_P.get(i) + " | ");
            if ((i + 1) % 5 == 0) {
               System.out.println();
            }
         }
         System.out.println(" \n-필라테스 강사님을 선택해 주세요. ");
         System.out.println(" (1. 신봉팔,  2. 홍둑애) : ");
         int selectnum_P = ScanUtil.nextInt();
         switch (selectnum_P) {
         case 1: // 신봉팔 선택
            System.out.print("\n" + "신봉팔 선생님께 배우시겠습니까? (Y/N) : ");
            String enswer_P_S = ScanUtil.nextLine();
            if (enswer_P_S.equals("N") || enswer_P_S.equals("n")) {
               return memberMemberShip();

            }

            else if (enswer_P_S.equals("Y") || enswer_P_S.equals("y")) {
               System.out.println();
               System.out.println("  1. 10회권  |  250000원  ");
               System.out.println("  2. 20회권  |  490000원  ");
               System.out.println("  3. 30회권  |  730000원  ");
               System.out.println("-횟수를 입력해주세요 (1~3) : ");
               int selectnum_P_S = ScanUtil.nextInt(); // 횟수권 선택
               List<Object> list_P_S = new ArrayList<>(); // list에 매개변수 넣기
               System.out.println("-본인 확인을 위해 아이디를 입력해주세요. : ");
               String memNo_P_S = ScanUtil.nextLine(); // memNo
               String eNo_P_S = "E003"; // eNo
               String msKind_P_S = "필라테스"; // msKind
               int msCount_P_S = 0; // msCount
               int msCost_P_S = 0; // msCost
               switch (selectnum_P_S) {
               case 1:
                  msCount_P_S = 10;
                  msCost_P_S = 250000;
                  break;
               case 2:
                  msCount_P_S = 20;
                  msCost_P_S = 490000;
                  break;
               case 3:
                  msCount_P_S = 30;
                  msCost_P_S = 730000;
                  break;
               }
               list_P_S.add(msKind_P_S);
               list_P_S.add(msCount_P_S);
               list_P_S.add(msCost_P_S);
               list_P_S.add(memNo_P_S);
               list_P_S.add(eNo_P_S);
               int result_P_S = memberdao.membershipUpdate(list_P_S);
               if (result_P_S > 0) {
                  System.out.println("-회원권 구매 성공");
                  System.out.println("-로그인 해서 운동 시작하세요!");
                  System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
                  return ViewEnum.HOME_MAIN;
               } else {
                  System.out.println("-구매가 정상적으로 되지 않았습니다.");
                  System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
                  return ViewEnum.MEMBER_MEMBERSHIP;
               }
            } else {
               System.out.println("-Y,y 또는 N,n로 입력해주세요. ");
               return memberMemberShip();
            }

         case 2: // 홍둑애 선택
            System.out.print("\n" + "홍둑애 선생님께 배우시겠습니까? (Y/N) : ");
            String enswer_P_H = ScanUtil.nextLine();
            if (enswer_P_H.equals("N") || enswer_P_H.equals("n")) {
               return memberMemberShip();

            } else if (enswer_P_H.equals("Y") || enswer_P_H.equals("y")) {
               System.out.println();
               System.out.println("  1. 10회권  |  250000원  ");
               System.out.println("  2. 20회권  |  490000원  ");
               System.out.println("  3. 30회권  |  730000원  ");
               System.out.println("-횟수를 입력해주세요 (1~3) : ");
               int selectnum_P_H = ScanUtil.nextInt(); // 횟수권 선택
               List<Object> list_P_H = new ArrayList<>(); // list_E에 매개변수 넣기
               System.out.println("-본인 확인을 위해 아이디를 입력해주세요. : ");
               String memNo_P_H = ScanUtil.nextLine(); // memNo
               String eNo_P_H = "E004"; // eNo
               String msKind_P_H = "필라테스"; // msKind
               int msCount_P_H = 0; // msCount
               int msCost_P_H = 0; // msCost
               switch (selectnum_P_H) {
               case 1:
                  msCount_P_H = 10;
                  msCost_P_H = 200000;
                  break;
               case 2:
                  msCount_P_H = 20;
                  msCost_P_H = 390000;
                  break;
               case 3:
                  msCount_P_H = 30;
                  msCost_P_H = 580000;
                  break;
               }
               list_P_H.add(msKind_P_H);
               list_P_H.add(msCount_P_H);
               list_P_H.add(msCost_P_H);
               list_P_H.add(memNo_P_H);
               list_P_H.add(eNo_P_H);
               int result_P_H = memberdao.membershipUpdate(list_P_H);
               if (result_P_H > 0) {
                  System.out.println("-회원권 구매 성공");
                  System.out.println("-로그인 해서 운동 시작하세요!");
                  System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
                  return ViewEnum.HOME_MAIN;
               } else {
                  System.out.println("-구매가 정상적으로 되지 않았습니다.");
                  System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
                  return ViewEnum.MEMBER_MEMBERSHIP;
               }
            } else {
               System.out.println("-Y,y 또는 N,n로 입력해주세요. ");
               return memberMemberShip();
            }
         }

      case 4: // PT 회원권 구매
         List<Object> list_PT = new ArrayList<>();
         list_PT = memberdao.employeeList_PT();
         for (int i = 0; i < list_PT.size(); i++) { // PT 프로필 출력
            System.out.print(list_PT.get(i) + " | ");
            if ((i + 1) % 5 == 0) {
               System.out.println();
            }
         }
         System.out.println(" \n-PT 강사님을 선택해 주세요. ");
         System.out.println(" (1. 신라연,  2. 이고숙) : ");
         int selectnum_PT = ScanUtil.nextInt();
         switch (selectnum_PT) {
         case 1: // 신라연 선택
            System.out.print("\n" + "신라연 선생님께 배우시겠습니까? (Y/N) : ");
            String enswer_PT_S = ScanUtil.nextLine();
            if (enswer_PT_S.equals("N") || enswer_PT_S.equals("n")) {
               return memberMemberShip();

            } else if (enswer_PT_S.equals("Y") || enswer_PT_S.equals("y")) {
               System.out.println();
               System.out.println("  1. 10회권  |  500000원  ");
               System.out.println("  2. 20회권  |  900000원  ");
               System.out.println("  3. 30회권  |  1400000원  ");
               System.out.println("-횟수를 입력해주세요 (1~3) : ");
               int selectnum_PT_S = ScanUtil.nextInt(); // 횟수권 선택
               List<Object> list_PT_S = new ArrayList<>(); // list에 매개변수 넣기
               System.out.println("-본인 확인을 위해 아이디를 입력해주세요. : ");
               String memNo_PT_S = ScanUtil.nextLine(); // memNo
               String eNo_PT_S = "E007"; // eNo
               String msKind_PT_S = "PT"; // msKind
               int msCount_PT_S = 0; // msCount
               int msCost_PT_S = 0; // msCost
               switch (selectnum_PT_S) {
               case 1:
                  msCount_PT_S = 10;
                  msCost_PT_S = 500000;
                  break;
               case 2:
                  msCount_PT_S = 20;
                  msCost_PT_S = 900000;
                  break;
               case 3:
                  msCount_PT_S = 30;
                  msCost_PT_S = 1400000;
                  break;
               }
               list_PT_S.add(msKind_PT_S);
               list_PT_S.add(msCount_PT_S);
               list_PT_S.add(msCost_PT_S);
               list_PT_S.add(memNo_PT_S);
               list_PT_S.add(eNo_PT_S);
               int result_S_Z = memberdao.membershipUpdate(list_PT_S);
               if (result_S_Z > 0) {
                  System.out.println("-회원권 구매 성공");
                  System.out.println("-로그인 해서 운동 시작하세요!");
                  System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
                  return ViewEnum.HOME_MAIN;
               } else {
                  System.out.println("-구매가 정상적으로 되지 않았습니다.");
                  System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
                  return ViewEnum.MEMBER_MEMBERSHIP;
               }

            } else {
               System.out.println("-Y,y 또는 N,n로 입력해주세요. ");
               return memberMemberShip();
            }

         case 2: // 이고숙 선택
            System.out.print("\n" + "이고숙 선생님께 배우시겠습니까? (Y/N) : ");
            String enswer_PT_E = ScanUtil.nextLine();
            if (enswer_PT_E.equals("N") || enswer_PT_E.equals("n")) {
               return memberMemberShip();
            } else if (enswer_PT_E.equals("Y") || enswer_PT_E.equals("y")) {
               System.out.println();
               System.out.println("  1. 10회권  |  500000원  ");
               System.out.println("  2. 20회권  |  900000원  ");
               System.out.println("  3. 30회권  |  1400000원  ");
               System.out.println("-횟수를 입력해주세요 (1~3) : ");
               int selectnum_PT_E = ScanUtil.nextInt(); // 횟수권 선택
               List<Object> list_PT_E = new ArrayList<>(); // list_E에 매개변수 넣기
               System.out.println("-본인 확인을 위해 아이디를 입력해주세요. : ");
               String memNo_PT_E = ScanUtil.nextLine(); // memNo
               String eNo_PT_E = "E004"; // eNo
               String msKind_PT_E = "PT"; // msKind
               int msCount_PT_E = 0; // msCount
               int msCost_PT_E = 0; // msCost
               switch (selectnum_PT_E) {
               case 1:
                  msCount_PT_E = 10;
                  msCost_PT_E = 200000;
                  break;
               case 2:
                  msCount_PT_E = 20;
                  msCost_PT_E = 390000;
                  break;
               case 3:
                  msCount_PT_E = 30;
                  msCost_PT_E = 580000;
                  break;
               }
               list_PT_E.add(msKind_PT_E);
               list_PT_E.add(msCount_PT_E);
               list_PT_E.add(msCost_PT_E);
               list_PT_E.add(memNo_PT_E);
               list_PT_E.add(eNo_PT_E);
               int result_P_H = memberdao.membershipUpdate(list_PT_E);
               if (result_P_H > 0) {
                  System.out.println("-회원권 구매 성공");
                  System.out.println("-로그인 해서 운동 시작하세요!");
                  System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
                  return ViewEnum.HOME_MAIN;
               } else {
                  System.out.println("-구매가 정상적으로 되지 않았습니다.");
                  System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
                  return ViewEnum.MEMBER_MEMBERSHIP;
               }
            } else {
               System.out.println("-Y,y 또는 N,n로 입력해주세요. ");
               return memberMemberShip();
            }
         }

      }
      return selectnum;
   }// switch 끝

   public int memberLogin() { // 회원 로그인
      //System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
      System.out.println("──────────────────────  회원 로그인   ────────────────────────");
      System.out.print("아이디를 입력해주세요. : ");
      String memNo = ScanUtil.nextLine();
      System.out.print("비밀번호를 입력해주세요. : ");
      String memPass = ScanUtil.nextLine();

      List<Object> param = new ArrayList<>();
      param.add(memNo);
      param.add(memPass);

      Map<String, Object> memberInfo = memberdao.memberLogin(param);

      if (memberInfo == null) {
         System.out.println("-사용자 아이디 혹은 비밀번호가 일치하지 않습니다. ");
         System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
         return memberLogin();
      } else {
         Controller.sessionStorage.put("loginInfo", memberInfo);
         String memNo1 = (String) memberInfo.get("MEM_NO");
         memberdao.updateAttence(memNo1);

         System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
         System.out.println();
         System.out.print("\n" + memberInfo.get("MEM_NAME") + "님 환영합니다! ");
         return ViewEnum.MEMBER_HOME;
      }
   }

      public int memberMyPage() { // 회원 마이페이지
            System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
            System.out.println(" 조회할 곳을 선택해 주세요 ");
            System.out.println(" 1. 개인정보  2. 회원권  3. 홈   ");
            System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
            int selectnum1 = ScanUtil.nextInt();
            switch (selectnum1) { 
            case 1: // 개인정보 조회
               Map<String, Object> memberInfo = (Map<String , Object>) Controller.sessionStorage.get("loginInfo");
               String memNo = (String) memberInfo.get("MEM_NO");
               Map<String, Object> memberInfo_M = new HashMap<String, Object>();
               memberInfo_M = memberdao.myList(memNo);
              // System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
               System.out.println("──────────────────────   개인 정보    ────────────────────────");
               System.out.println("1.아이디    : " + memberInfo_M.get("MEM_NO"));
               System.out.println("2.비밀번호 : " + memberInfo_M.get("MEM_PASS"));
               System.out.println("3.이름       : " + memberInfo_M.get("MEM_NAME"));
               System.out.println("4.나이       : " + memberInfo_M.get("MEM_AGE"));
               System.out.println("5.성별       : " + memberInfo_M.get("MEM_GENDER"));
               System.out.println("6.주민번호 : " + memberInfo_M.get("MEM_JUMIN"));
               System.out.println("7.전화번호 : " + memberInfo_M.get("MEM_TELNO"));
               System.out.println("8.주소       : " + memberInfo_M.get("MEM_ADDRESS"));
               System.out.println("9.차량번호 : " + memberInfo_M.get("MEM_CARNO"));
               System.out.println("10.사물함    : " + memberInfo_M.get("LOC_NO"));
               System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
               System.out.println("1. 수정 ");
               System.out.println("2. 뒤로가기");
               int selectnum2 = ScanUtil.nextInt();

               switch (selectnum2) {
               case 1: // 수정 눌렀을때
                  System.out.println("-수정하실 곳의 번호를 선택해주세요. ");
                  System.out.println("('아이디','나이,'성별','주민번호'는 수정이 불가하며, '사물함'은 '사물함 페이지'에서 변경 가능합니다.) ");
                  int selectnum3 = ScanUtil.nextInt();
                  switch (selectnum3) { // 수정할 영역
                  case 1: // 아이디 수정불가
                     System.out.println("-아이디는 수정이 불가합니다.");
                    return ViewEnum.MEMBER_MYPAGE;               
                  case 2: // 비밀번호 수정
                     System.out.println("-수정하실 내용을 입력해주세요 : ");
                     System.out.println("1.뒤로가기  ");
                     String modify = ScanUtil.nextLine();
                     if (modify.equals("1")) {
                        return ViewEnum.MEMBER_MYPAGE;               }

                     System.out.println("-수정하시겠습니까? (Y/N) : ");
                     String enswer = ScanUtil.nextLine();
                     if (enswer.equals("N") || enswer.equals("n")) {
                        return ViewEnum.MEMBER_MYPAGE;
                        } else if (enswer.equals("Y") || enswer.equals("y")) {
                        // modify
                        String memberNo = (String) memberInfo_M.get("MEM_NO");
                        List<Object> param = new ArrayList<>();

                        param.add(modify.trim());
                        param.add(memberNo.trim());
                        int result = memberdao.Update_Member_Pass(param);

                        if (result > 0) {
                           System.out.println("-수정이 완료되었습니다. ");
                          return ViewEnum.MEMBER_MYPAGE;                  } else {
                           System.out.println("-수정이 정상적으로 되지 않았습니다. ");
                           System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
                          return ViewEnum.MEMBER_MYPAGE;                  }
                     }
                     break;
                  case 3: // 이름 수정
                     System.out.println("-수정하실 내용을 입력해주세요 : ");
                     System.out.println("1.뒤로가기  ");
                     String modify_name = ScanUtil.nextLine();
                     if (modify_name.equals("1")) {
                        return ViewEnum.MEMBER_MYPAGE;
                        }

                     System.out.println("-수정하시겠습니까? (Y/N) : ");
                     String enswer1 = ScanUtil.nextLine();

                     if (enswer1.equals("N") || enswer1.equals("n")) {
                        return ViewEnum.MEMBER_MYPAGE;
                        } else if (enswer1.equals("Y") || enswer1.equals("y")) {
                        // modify_name
                        String memberNo = (String) memberInfo_M.get("MEM_NO");
                        List<Object> param = new ArrayList<>();
                        param.add(modify_name.trim());
                        param.add(memberNo.trim());
                        int result = memberdao.Update_Member_Name(param);

                        if (result > 0) {
                           System.out.println("-수정이 완료되었습니다. ");
                          return ViewEnum.MEMBER_MYPAGE;
                          } else {
                           System.out.println("-수정이 정상적으로 되지 않았습니다. ");
                           System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
                          return ViewEnum.MEMBER_MYPAGE;
                        }
                     }
                     break;
                  case 4: // 나이
                     System.out.println("-나이는 수정이 불가합니다.");
                    return ViewEnum.MEMBER_MYPAGE;               
                  case 5:
                     System.out.println("-성별은 수정이 불가합니다."); // 성별
                    return ViewEnum.MEMBER_MYPAGE;               
                  case 6:
                     System.out.println("-주민번호는 수정이 불가합니다."); // 주민번호
                    return ViewEnum.MEMBER_MYPAGE;               
                  case 7: // 전화번호 수정
                     System.out.println("-수정하실 내용을 입력해주세요 : ");
                     System.out.println("1.뒤로가기  ");
                     String modify_telno = ScanUtil.nextLine();
                     if (modify_telno.equals("1")) {
                        return ViewEnum.MEMBER_MYPAGE;
                        }

                     System.out.println("-수정하시겠습니까? (Y/N) : ");
                     String enswer2 = ScanUtil.nextLine();
                     if (enswer2.equals("N") || enswer2.equals("n")) {
                        return ViewEnum.MEMBER_MYPAGE;
                        } else if (enswer2.equals("Y") || enswer2.equals("y")) {
                        // modify_name
                        String memberNo = (String) memberInfo_M.get("MEM_NO");
                        List<Object> param = new ArrayList<>();
                        param.add(modify_telno.trim());
                        param.add(memberNo.trim());
                        int result = memberdao.Update_Member_Telno(param);

                        if (result > 0) {
                           System.out.println("-수정이 완료되었습니다. ");
                          return ViewEnum.MEMBER_MYPAGE;
                          } else {
                           System.out.println("-수정이 정상적으로 되지 않았습니다. ");
                           System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
                          return ViewEnum.MEMBER_MYPAGE;
                        }
                     }
                     break;
                  case 8: // 주소 수정
                     System.out.println("-수정하실 내용을 입력해주세요 : ");
                     System.out.println("1.뒤로가기  ");
                     String modify_address = ScanUtil.nextLine();
                     if (modify_address.equals("1")) {
                        return ViewEnum.MEMBER_MYPAGE;
                        }
                     System.out.println("수정하시겠습니까? (Y/N) : ");
                     String enswer3 = ScanUtil.nextLine();
                     if (enswer3.equals("N") || enswer3.equals("n")) {
                        return ViewEnum.MEMBER_MYPAGE;
                        } else if (enswer3.equals("Y") || enswer3.equals("y")) {
                        // modify_address
                        String memberNo = (String) memberInfo_M.get("MEM_NO");
                        List<Object> param = new ArrayList<>();
                        param.add(modify_address.trim());
                        param.add(memberNo.trim());
                        int result = memberdao.Update_Member_Address(param);

                        if (result > 0) {
                           System.out.println("-수정이 완료되었습니다. ");
                          return ViewEnum.MEMBER_MYPAGE;           
                          } else {
                           System.out.println("-수정이 정상적으로 되지 않았습니다. ");
                           System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
                          return ViewEnum.MEMBER_MYPAGE;                  }
                     }
                  case 9: // 차량번호 수정
                     System.out.println("-수정하실 내용을 입력해주세요 : ");
                     System.out.println("1.뒤로가기  ");
                     String modify_carno = ScanUtil.nextLine();
                     if (modify_carno.equals("1")) {
                        return ViewEnum.MEMBER_MYPAGE;               }
                     System.out.println("-수정하시겠습니까? (Y/N) : ");
                     String enswer4 = ScanUtil.nextLine();
                     if (enswer4.equals("N") || enswer4.equals("n")) {
                        return ViewEnum.MEMBER_MYPAGE;
                        } else if (enswer4.equals("Y") || enswer4.equals("y")) {
                        // modify_carno
                        String memberNo = (String) memberInfo_M.get("MEM_NO");
                        List<Object> param = new ArrayList<>();
                        param.add(modify_carno.trim());
                        param.add(memberNo.trim());
                        int result = memberdao.Update_Car(param);

                        if (result > 0) {
                           System.out.println("-수정이 완료되었습니다. ");
                          return ViewEnum.MEMBER_MYPAGE;                  } else {
                           System.out.println("-수정이 정상적으로 되지 않았습니다. ");
                           System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
                          return ViewEnum.MEMBER_MYPAGE;
                        }
                     }
                  
                  case 10:
                     System.out.println("-사물함은 사물함 페이지에서 수정이 가능합니다. "); // 사물함
                    return ViewEnum.MEMBER_MYPAGE;              
                     default:
                     System.out.println("-해당영역은 존재하지 않습니다. 번호를 다시 지정해주세요. ");
                    return ViewEnum.MEMBER_MYPAGE;              
                  }// 수정영역 선택 (1~10번) switch문 종료
               case 2:
                  return ViewEnum.MEMBER_MYPAGE;
               default:
                  System.out.println("-해당영역은 존재하지 않습니다. 번호를 다시 지정해주세요.");
                 return ViewEnum.MEMBER_MYPAGE;
               }// 수정, 마이페이지 선택 (1,2번)switch문 종료

            case 2: // 회원권 조회
               Map<String, Object> membershipInfo = (Map<String, Object>) Controller.sessionStorage.get("loginInfo");
               String memNo1 = (String) membershipInfo.get("MEM_NO");
               Map<String, Object> membershipInfo1 = new HashMap<String, Object>();
               membershipInfo1 = memberdao.membershipList(memNo1);
              // System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
               System.out.println("──────────────────────    회원권      ────────────────────────");
               System.out.println("1.회원권 종류    : " + membershipInfo1.get("MS_KIND"));
               System.out.println("2.등록일           : " + membershipInfo1.get("MS_DATE"));
               System.out.println("3.만기일           : " + membershipInfo1.get("MS_EDATE"));
               System.out.println("5.남은 횟수       : " + membershipInfo1.get("MS_COUNT"));
               System.out.println("6.가격              : " + membershipInfo1.get("MS_COST"));
               System.out.println("────────────────────── 트레이너 프로필  ────────────────────────");
               
               Map<String, Object> list_P = new HashMap<String, Object>();
               list_P = memberdao.employeeList_Mine(memNo1);
               
               String trainnerNo =  (String) list_P.get("E_NO"); // 본인의 트레이너 번호
               
               List<Object> list_P1 = new ArrayList<>();
               list_P1 = memberdao.employeeList_Mine1(trainnerNo);
               for (int i = 0; i < list_P1.size(); i++) { // 트레이너 프로필 출력
                  System.out.print(list_P1.get(i) + " | ");
               }
               System.out.println("\n────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
               System.out.println("1.뒤로가기");
               String selectnum_MS = ScanUtil.nextLine();
               if (selectnum_MS.equals("1")) {
                  return ViewEnum.MEMBER_MYPAGE;
               }else {
               System.out.println("-해당영역은 존재하지 않습니다. 번호를 다시 지정해주세요. ");
              return ViewEnum.MEMBER_MYPAGE;
               }         
               
            case 3:
              return Controller.memberhome();
         default :
               System.out.println("-해당영역은 존재하지 않습니다. 번호를 다시 지정해주세요.");
              return ViewEnum.MEMBER_MYPAGE;
            }// 개인정보조회 switch문 종료

         }

//   public int memberMileage() {
//      System.out.println("enter");
//      ScanUtil.nextLine();
//
//      System.out
//            .println("1.들어가기                                    2.돌아가기                                     3.로그아웃");
//
//      int a = (int) (Math.random() * 1000) + 1;
//
//      switch (ScanUtil.nextInt()) {
//      case 1:
//
//         Map<String, Object> mileageInfo = (Map<String, Object>) Controller.sessionStorage.get("loginInfo");
//         String memNo_M = (String) mileageInfo.get("MEM_NO");
//         Map<String, Object> mileageInfo1 = new HashMap<String, Object>();
//         mileageInfo1 = memberdao.mileageList(memNo_M);
//         System.out.println("---------------<<마일리지>>---------------------");
//         System.out.println("1.이름              : " + mileageInfo1.get("MEM_NAME"));
//         System.out.println("2.남은 마일리지 : " + mileageInfo1.get("MIL_POINT"));
//         System.out.println("---------------------------------------------");
//
//         if ((int) mileageInfo1.get("MIL_POINT") < 500) {
//            System.out.println("마일리지가 부족합니다.. 현재마일리지는 " + (int) mileageInfo1.get("MIL_POINT") + "입니다.");
//
//            return memberMileage();
//         } else {
//            // 이곳에 있어도 되나...
//            System.out.println("MEM_NO를 입력해 주세요 : ");
//            String memNoMo = ScanUtil.nextLine();
//            Map<String, Object> mileageModi = memberdao.mileageModify(memNoMo);
//            System.out.println(mileageModi.get("MIL_POINT"));
//
////                List<Object> param = new ArrayList<>();
////                  param.add(memCarNo);
////                  param.add(memNo);
////               
////               int a = (int)(Math.random()*1000)+1;
//
//            if (a <= 10) {
//               System.out.println("30회 등록권");
//               break;
//            } else if (11 <= a && a <= 60) {
//               System.out.println("종합비타민");
//               break;
//            } else if (61 <= a && a <= 160) {
//               System.out.println("PT 1회");
//               break;
//            } else if (161 <= a && a <= 360) {
//               System.out.println("사우나이용권..");
//               break;
//            } else if (361 <= a && a <= 560) {
//               System.out.println("단백질쉐이크");
//               break;
//            } else if (561 <= a && a <= 1000) {
//               System.out.println("꽝");
//               break;
//            }
//         }
//         return memberMileage();
//
//      case 2:
//         return 1;
//
//      case 3:
//
//         return 3;
//      }
//
//      return ViewEnum.MEMBER_MILEAGE;
//   }
//   public int memberMileage() { //마일리지 사용
//      Map<String, Object> memberInfo = (Map<String , Object>) Controller.sessionStorage.get("loginInfo");
//      
//      
//      List<String> memNo_M = new ArrayList<>();
//      memNo =  (List<String>) memberInfo.get("MEM_NO");
//      System.out.println(memNo_M);
//      
//      System.out.print(" -현재 나의 마일리지 잔액 : ");
//      Map<String, Object> mymileage = new HashMap<>();
//      mymileage = memberdao.mileageList1(memNo);
//      
//      
//      System.out.print(mymileage.get("MIL_POINT"));
//      return View.MEMBER_MILEAGE;
//   }

   public int memberParking() { // 멤버 주차권 발급
      System.out.print("-주차권이 필요하십니까? (Y/N) : ");
      String answer = ScanUtil.nextLine();

      if (answer.equals("N") || answer.equals("n")) {

         return ViewEnum.MEMBER_HOME;

      } else if (answer.equals("Y") || answer.equals("y")) {

         System.out.println("-차량번호를 입력해주세요. : ");
         String memCarNo = ScanUtil.nextLine();
         Map<String, Object> mileageInfo = (Map<String, Object>) Controller.sessionStorage.get("loginInfo");
         String memNo_C = (String) mileageInfo.get("MEM_NO");
         List<Object> carList = new ArrayList<>();
         carList = memberdao.memcarnoCheck(memNo_C);

         String RealCarNo = (String) carList.get(0); // 실제 CARNO

         if (RealCarNo == null) {
            System.out.println("-입력하신 아이디에 따른 차량번호가 등록되어 있지 않습니다. ");
            System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
            System.out.print("-등록하시겠습니까? (Y/N) :");
            String enswer5 = ScanUtil.nextLine();

            if (enswer5.equals("N") || enswer5.equals("n")) {
               return ViewEnum.MEMBER_HOME;
            } else if (enswer5.equals("Y") || enswer5.equals("y")) {
               System.out.println("-등록하실 차량번호를 눌러주세요 : ");
               String insertCarNo = ScanUtil.nextLine();

               List<Object> param = new ArrayList<>();
               param.add(insertCarNo);
               param.add(memNo_C);

               int result = memberdao.Update_Car(param);

               if (result > 0) {
                  System.out.println("-주차권 발부 완료. ");
                  System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
                  return ViewEnum.MEMBER_HOME;
               } else {
                  System.out.println("-주차권 지정이 정상적으로 되지 않았습니다. ");
                  System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
                  return ViewEnum.MEMBER_HOME;
               }
            } else {
               System.out.println("-Y,y 또는 N,n로 입력해주세요. ");
               return ViewEnum.MEMBER_CAR;
            }
         } else if (RealCarNo.equals(memCarNo)) { // 만약 본인이 입력한 실제로 들어있는 값이 일치한다면
            System.out.println("-확인되었습니다. 주차권 발부 완료. ");
            return ViewEnum.MEMBER_HOME;
         } else {
            System.out.println("-등록하신 차량번호와 일치하지 않습니다. ");
            System.out.print("-등록하시겠습니까? (Y/N) :");
            String enswer6 = ScanUtil.nextLine();

            if (enswer6.equals("N") || enswer6.equals("n")) {
               return ViewEnum.MEMBER_HOME;
            } else if (enswer6.equals("Y") || enswer6.equals("y")) {
               System.out.println("-등록하실 차량번호를 눌러주세요 : ");
               String insertCarNo = ScanUtil.nextLine();

               List<Object> param = new ArrayList<>();
               param.add(insertCarNo);
               param.add(memNo_C);

               int result = memberdao.Update_Car(param);

               if (result > 0) {
                  System.out.println("-주차권 발부 완료. ");
                  System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
                  return ViewEnum.MEMBER_HOME;
               } else {
                  System.out.println("-주차권 지정이 정상적으로 되지 않았습니다. ");
                  System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
                  return ViewEnum.MEMBER_HOME;
               }
            } else {
               System.out.println("-Y,y 또는 N,n로 입력해주세요. ");
               return ViewEnum.MEMBER_CAR;
            }
         }

      }
      return 9943020;

   }

   public int memberMemberShip1() { // 멤버 회원권 구매
      System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
      System.out.println("──────────────────────  회원권 구매   ─────────────────────────");
      System.out.println("  1. 일반   2.스피닝   3.필라테스   4.PT  5.종료 ");
      System.out.println(" 구매하실 번호를 선택해주세요 (1~4) ");
      System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
      int selectnum = ScanUtil.nextInt();
      Map<String, Object> mileageInfo = (Map<String, Object>) Controller.sessionStorage.get("loginInfo");
      String memNo_MS = (String) mileageInfo.get("MEM_NO");
      switch (selectnum) {
      case 1: // 일반 회원권 구매

         List<Object> list = new ArrayList<>();
         list = memberdao.employeeList_EE();
         System.out.println("-문의사항시 연락주세요. ");
         for (int i = 0; i < list.size(); i++) { // 관장 프로필 출력
            System.out.print(list.get(i) + " | ");
         }
         System.out.print("\n" + "-구매하시겠습니까? (Y/N):");
         String enswer = ScanUtil.nextLine();
         if (enswer.equals("N") || enswer.equals("n")) {
            memberMemberShip1();
            break;

         }

         else if (enswer.equals("Y") || enswer.equals("y")) {
            System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
            System.out.println("  1. 10회권  |  50000원  ");
            System.out.println("  2. 20회권  |  90000원  ");
            System.out.println("  3. 30회권  |  140000원  ");
            System.out.println(" 원하시는 번호를 선택해주세요 (1~3)  ");
            System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
            int selectnum_E = ScanUtil.nextInt(); // 횟수권 선택
            List<Object> list_E = new ArrayList<>(); // list_E에 매개변수 넣기
            String eNo = "E011"; // eNo
            String msKind = "일반"; // msKind
            int msCount = 0; // msCount
            int msCost = 0; // msCost
            switch (selectnum_E) {
            case 1:
               msCount = 10;
               msCost = 50000;
               break;
            case 2:
               msCount = 20;
               msCost = 90000;
               break;
            case 3:
               msCount = 30;
               msCost = 140000;
               break;
            }
            list_E.add(msKind);
            list_E.add(msCount);
            list_E.add(msCost);
            list_E.add(memNo_MS);
            list_E.add(eNo);
            int result = memberdao.membershipUpdate(list_E);
            if (result > 0) {
               System.out.println("-회원권 구매 성공");
               System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
               return ViewEnum.MEMBER_HOME;
            } else {
               System.out.println("-구매가 정상적으로 되지 않았습니다.");
               System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
               memberMemberShip1();
               break;
            }
         } else {
            System.out.println("-Y,y 또는 N,n로 입력해주세요. ");
            memberMemberShip1();
            break;
         }
         
      case 2: // 스피닝 회원권 구매
         List<Object> list_S = new ArrayList<>();
         list_S = memberdao.employeeList_SS();
         for (int i = 0; i < list_S.size(); i++) { // 스피닝 프로필 출력
            System.out.print(list_S.get(i) + " | ");
            if ((i + 1) % 5 == 0) {
               System.out.println(); //?? "여기 뭐 입력해야되는거 아닌가"
            }
         }
         System.out.println(" \n-스피닝 강사님을 선택해 주세요. ");
         System.out.println(" (1. 조지나,  2. 곽공두) : ");
         int selectnum_S = ScanUtil.nextInt();
         switch (selectnum_S) {
         case 1: // 조지나 선택
            System.out.print("\n" + "-조지나 선생님께 배우시겠습니까? (Y/N):");
            String enswer_S_Z = ScanUtil.nextLine();
            if (enswer_S_Z.equals("N") || enswer_S_Z.equals("n")) {
               memberMemberShip1();
               break;

            }

            else if (enswer_S_Z.equals("Y") || enswer_S_Z.equals("y")) {
               System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
               System.out.println("  1. 10회권  |  200000원  ");
               System.out.println("  2. 20회권  |  390000원  ");
               System.out.println("  3. 30회권  |  580000원  ");
               System.out.println(" 원하시는 번호를 선택해주세요 (1~3)  ");
               System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
               int selectnum_S_Z = ScanUtil.nextInt(); // 횟수권 선택
               List<Object> list_S_Z = new ArrayList<>(); // list에 매개변수 넣기
               String eNo_S_Z = "E005"; // eNo
               String msKind_S_Z = "스피닝"; // msKind
               int msCount_S_Z = 0; // msCount
               int msCost_S_Z = 0; // msCost
               switch (selectnum_S_Z) {
               case 1:
                  msCount_S_Z = 10;
                  msCost_S_Z = 200000;
                  break;
               case 2:
                  msCount_S_Z = 20;
                  msCost_S_Z = 390000;
                  break;
               case 3:
                  msCount_S_Z = 30;
                  msCost_S_Z = 580000;
                  break;
               }
               list_S_Z.add(msKind_S_Z);
               list_S_Z.add(msCount_S_Z);
               list_S_Z.add(msCost_S_Z);
               list_S_Z.add(memNo_MS);
               list_S_Z.add(eNo_S_Z);
               int result_S_Z = memberdao.membershipUpdate(list_S_Z);
               if (result_S_Z > 0) {
                  System.out.println("-회원권 구매 성공");
                  System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
                  return ViewEnum.MEMBER_HOME;
               } else {
                  System.out.println("-구매가 정상적으로 되지 않았습니다.");
                  System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
                  memberMemberShip1();
                  break; // 붙여봤어요.
               }
            } else {
               System.out.println("-Y,y 또는 N,n로 입력해주세요. ");
               memberMemberShip1();
               break;
            }
            
         case 2: // 곽공두 선택
            System.out.print("\n" + "-곽공두 선생님께 배우시겠습니까? (Y/N):");
            String enswer_S_K = ScanUtil.nextLine();
            if (enswer_S_K.equals("N") || enswer_S_K.equals("n")) {
               memberMemberShip1();
               break;
            }

            else if (enswer_S_K.equals("Y") || enswer_S_K.equals("y")) {
               System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
               System.out.println("  1. 10회권  |  200000원  ");
               System.out.println("  2. 20회권  |  390000원  ");
               System.out.println("  3. 30회권  |  580000원  ");
               System.out.println(" 원하시는 번호를 선택해주세요 (1~3)  ");
               System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
               int selectnum_S_K = ScanUtil.nextInt(); // 횟수권 선택
               List<Object> list_S_K = new ArrayList<>(); // list_E에 매개변수 넣기
               String eNo_S_K = "E006"; // eNo
               String msKind_S_K = "스피닝"; // msKind
               int msCount_S_K = 0; // msCount
               int msCost_S_K = 0; // msCost
               switch (selectnum_S_K) {
               case 1:
                  msCount_S_K = 10;
                  msCost_S_K = 200000;
                  break;
               case 2:
                  msCount_S_K = 20;
                  msCost_S_K = 390000;
                  break;
               case 3:
                  msCount_S_K = 30;
                  msCost_S_K = 580000;
                  break;
               }
               list_S_K.add(msKind_S_K);
               list_S_K.add(msCount_S_K);
               list_S_K.add(msCost_S_K);
               list_S_K.add(memNo_MS);
               list_S_K.add(eNo_S_K);
               int result_S_K = memberdao.membershipUpdate(list_S_K);
               if (result_S_K > 0) {
                  System.out.println("-회원권 구매 성공");
                  System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
                  return ViewEnum.MEMBER_HOME;
               } else {
                  System.out.println("-구매가 정상적으로 되지 않았습니다.");
                  System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
                  memberMemberShip1();
                  break;
               }
            } else {
               System.out.println("-Y,y 또는 N,n로 입력해주세요. ");
               memberMemberShip1();
               break;
            }
         }
         break;
      case 3: // 필라테스 회원권 구매
         List<Object> list_P = new ArrayList<>();
         list_P = memberdao.employeeList_PP();
         for (int i = 0; i < list_P.size(); i++) { // 필라테스 프로필 출력
            System.out.print(list_P.get(i) + " | ");
            if ((i + 1) % 5 == 0) {
               System.out.println();
            }
         }
         System.out.println(" \n-필라테스 강사님을 선택해 주세요. ");
         System.out.println(" (1. 신봉팔,  2. 홍둑애) : ");
         int selectnum_P = ScanUtil.nextInt();
         switch (selectnum_P) {
         case 1: // 신봉팔 선택
            System.out.print("\n" + "-신봉팔 선생님께 배우시겠습니까? (Y/N):");
            String enswer_P_S = ScanUtil.nextLine();
            if (enswer_P_S.equals("N") || enswer_P_S.equals("n")) {
               memberMemberShip1();

            }

            else if (enswer_P_S.equals("Y") || enswer_P_S.equals("y")) {
               System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
               System.out.println("  1. 10회권  |  250000원  ");
               System.out.println("  2. 20회권  |  490000원  ");
               System.out.println("  3. 30회권  |  730000원  ");
               System.out.println(" 원하시는 번호를 선택해주세요 (1~3)  ");
               System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
               int selectnum_P_S = ScanUtil.nextInt(); // 횟수권 선택
               List<Object> list_P_S = new ArrayList<>(); // list에 매개변수 넣기

               String eNo_P_S = "E003"; // eNo
               String msKind_P_S = "필라테스"; // msKind
               int msCount_P_S = 0; // msCount
               int msCost_P_S = 0; // msCost
               switch (selectnum_P_S) {
               case 1:
                  msCount_P_S = 10;
                  msCost_P_S = 250000;
                  break;
               case 2:
                  msCount_P_S = 20;
                  msCost_P_S = 490000;
                  break;
               case 3:
                  msCount_P_S = 30;
                  msCost_P_S = 730000;
                  break;
               }
               list_P_S.add(msKind_P_S);
               list_P_S.add(msCount_P_S);
               list_P_S.add(msCost_P_S);
               list_P_S.add(memNo_MS);
               list_P_S.add(eNo_P_S);
               int result_P_S = memberdao.membershipUpdate(list_P_S);
               if (result_P_S > 0) {
                  System.out.println("-회원권 구매 성공");
                  System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
                  return ViewEnum.MEMBER_HOME;
               } else {
                  System.out.println("-구매가 정상적으로 되지 않았습니다.");
                  System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
                  memberMemberShip1();
               }
            } else {
               System.out.println("-Y,y 또는 N,n로 입력해주세요. ");
               memberMemberShip1();
            }
            break;
         case 2: // 홍둑애 선택
            System.out.print("\n" + "-홍둑애 선생님께 배우시겠습니까? (Y/N):");
            String enswer_P_H = ScanUtil.nextLine();
            if (enswer_P_H.equals("N") || enswer_P_H.equals("n")) {
               memberMemberShip1();

            } else if (enswer_P_H.equals("Y") || enswer_P_H.equals("y")) {
               System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
               System.out.println("  1. 10회권  |  250000원  ");
               System.out.println("  2. 20회권  |  490000원  ");
               System.out.println("  3. 30회권  |  730000원  ");
               System.out.println(" 원하시는 번호를 선택해주세요 (1~3)  ");
               System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
               int selectnum_P_H = ScanUtil.nextInt(); // 횟수권 선택
               List<Object> list_P_H = new ArrayList<>(); // list_E에 매개변수 넣기

               String eNo_P_H = "E004"; // eNo
               String msKind_P_H = "필라테스"; // msKind
               int msCount_P_H = 0; // msCount
               int msCost_P_H = 0; // msCost
               switch (selectnum_P_H) {
               case 1:
                  msCount_P_H = 10;
                  msCost_P_H = 200000;
                  break;
               case 2:
                  msCount_P_H = 20;
                  msCost_P_H = 390000;
                  break;
               case 3:
                  msCount_P_H = 30;
                  msCost_P_H = 580000;
                  break;
               }
               list_P_H.add(msKind_P_H);
               list_P_H.add(msCount_P_H);
               list_P_H.add(msCost_P_H);
               list_P_H.add(memNo_MS);
               list_P_H.add(eNo_P_H);
               int result_P_H = memberdao.membershipUpdate(list_P_H);
               if (result_P_H > 0) {
                  System.out.println("-회원권 구매 성공");
                  System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
                  return ViewEnum.MEMBER_HOME;
               } else {
                  System.out.println("-구매가 정상적으로 되지 않았습니다.");
                  System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
                  memberMemberShip1();
               }
            } else {
               System.out.println("-Y,y 또는 N,n로 입력해주세요. ");
               memberMemberShip1();
            }
         }
         break;
      case 4: // PT 회원권 구매
         List<Object> list_PT = new ArrayList<>();
         list_PT = memberdao.employeeList_PT();
         for (int i = 0; i < list_PT.size(); i++) { // PT 프로필 출력
            System.out.print(list_PT.get(i) + " | ");
            if ((i + 1) % 5 == 0) {
               System.out.println();
            }
         }
         System.out.println(" \n-PT 강사님을 선택해 주세요. ");
         System.out.println(" (1. 신라연,  2. 이고숙) : ");
         int selectnum_PT = ScanUtil.nextInt();
         switch (selectnum_PT) {
         case 1: // 신라연 선택
            System.out.print("\n" + "-신라연 선생님께 배우시겠습니까? (Y/N):");
            String enswer_PT_S = ScanUtil.nextLine();
            if (enswer_PT_S.equals("N") || enswer_PT_S.equals("n")) {
               memberMemberShip1();

            } else if (enswer_PT_S.equals("Y") || enswer_PT_S.equals("y")) {
               System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
               System.out.println("  1. 10회권  |  500000원  ");
               System.out.println("  2. 20회권  |  900000원  ");
               System.out.println("  3. 30회권  |  1400000원  ");
               System.out.println(" 원하시는 번호를 선택해주세요 (1~3)  ");
               System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
               int selectnum_PT_S = ScanUtil.nextInt(); // 횟수권 선택
               List<Object> list_PT_S = new ArrayList<>(); // list에 매개변수 넣기

               String eNo_PT_S = "E007"; // eNo
               String msKind_PT_S = "PT"; // msKind
               int msCount_PT_S = 0; // msCount
               int msCost_PT_S = 0; // msCost
               switch (selectnum_PT_S) {
               case 1:
                  msCount_PT_S = 10;
                  msCost_PT_S = 500000;
                  break;
               case 2:
                  msCount_PT_S = 20;
                  msCost_PT_S = 900000;
                  break;
               case 3:
                  msCount_PT_S = 30;
                  msCost_PT_S = 1400000;
                  break;
               }
               list_PT_S.add(msKind_PT_S);
               list_PT_S.add(msCount_PT_S);
               list_PT_S.add(msCost_PT_S);
               list_PT_S.add(memNo_MS);
               list_PT_S.add(eNo_PT_S);
               int result_S_Z = memberdao.membershipUpdate(list_PT_S);
               if (result_S_Z > 0) {
                  System.out.println("-회원권 구매 성공");
                  System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
                  return ViewEnum.MEMBER_HOME;
               } else {
                  System.out.println("-구매가 정상적으로 되지 않았습니다.");
                  System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
                  memberMemberShip1();
               }

            } else {
               System.out.println("-Y,y 또는 N,n로 입력해주세요. ");
               memberMemberShip1();
            }
            break;
         case 2: // 이고숙 선택
            System.out.print("\n" + "-이고숙 선생님께 배우시겠습니까? (Y/N):");
            String enswer_PT_E = ScanUtil.nextLine();
            if (enswer_PT_E.equals("N") || enswer_PT_E.equals("n")) {
               memberMemberShip1();
            } else if (enswer_PT_E.equals("Y") || enswer_PT_E.equals("y")) {
               System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
               System.out.println("  1. 10회권  |  500000원  ");
               System.out.println("  2. 20회권  |  900000원  ");
               System.out.println("  3. 30회권  |  1400000원  ");
               System.out.println(" 원하시는 번호를 선택해주세요 (1~3)  ");
               System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
               int selectnum_PT_E = ScanUtil.nextInt(); // 횟수권 선택
               List<Object> list_PT_E = new ArrayList<>(); // list_E에 매개변수 넣기

               String eNo_PT_E = "E004"; // eNo
               String msKind_PT_E = "PT"; // msKind
               int msCount_PT_E = 0; // msCount
               int msCost_PT_E = 0; // msCost
               switch (selectnum_PT_E) {
               case 1:
                  msCount_PT_E = 10;
                  msCost_PT_E = 200000;
                  break;
               case 2:
                  msCount_PT_E = 20;
                  msCost_PT_E = 390000;
                  break;
               case 3:
                  msCount_PT_E = 30;
                  msCost_PT_E = 580000;
                  break;
               }
               list_PT_E.add(msKind_PT_E);
               list_PT_E.add(msCount_PT_E);
               list_PT_E.add(msCost_PT_E);
               list_PT_E.add(memNo_MS);
               list_PT_E.add(eNo_PT_E);
               int result_P_H = memberdao.membershipUpdate(list_PT_E);
               if (result_P_H > 0) {
                  System.out.println("-회원권 구매 성공");
                  System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
                  return ViewEnum.MEMBER_HOME;
               } else {
                  System.out.println("-구매가 정상적으로 되지 않았습니다.");
                  System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
                  memberMemberShip1();
               }
            } else {
               System.out.println("-Y,y 또는 N,n로 입력해주세요. ");
               memberMemberShip1();
            }
         }
         break;
      case 5: // 종료 선택
         return ViewEnum.MEMBER_HOME;
         
//      defalut:
//         break;
//      

      }
      return ViewEnum.MEMBER_HOME;
   }

   public int memberLockerMove() { // 사물함 지정
      System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
      System.out.println("──────────────────────  사물함 이동   ─────────────────────────");
      Map<String, Object> mileageInfo = (Map<String, Object>) Controller.sessionStorage.get("loginInfo");
      String memNo_LM = (String) mileageInfo.get("MEM_NO");

      List<Object> list = new ArrayList<>();
      list = memberdao.memberLockerPicture();
      for (int i = 1; i <= 40; i++) {
         if (list.get(i - 1) == null) {
            System.out.print(".□.");
         } else {
            System.out.print(".■.");
         }
         if (i % 10 == 0) {
            System.out.println();
         }
      }
      System.out.println();
      System.out.println("-본인이 이동하실 좌석을 입력하세요. ");
      System.out.println("    (왼쪽부터 1번 ~ 40번)  : ");
      int memLockNo = ScanUtil.nextInt();

      for (int i = 1; i <= 40; i++) {
         if (list.get(i - 1) != null) {
            if (i == memLockNo) {
               System.out.println("-이미 지정되어있는 사물합입니다. 다시 입력해주세요");
               return memberLockerMove();
            }
         }

      }

      // 현재 본인의 사물함 값을 삭제하기
      List<Object> param = new ArrayList<>();
      param.add(memNo_LM);
      memberdao.LockerRemove(param);

      List<Object> param1 = new ArrayList<>();
      param1.add(memNo_LM);
      param1.add(memLockNo);

      int result1 = memberdao.Locker(param1);

      if (result1 > 0) {
         System.out.println("-사물함 지정 성공");
         System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");

         return ViewEnum.MEMBER_HOME;
      } else {
         System.out.println("-사물함 지정이 정상적으로 되지 않았습니다.");
         System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
         return ViewEnum.MEMBER_HOME;
      }
   }

   public int memberNotice() {
   
      List<Map<String, Object>> list = memberdao.adminNoticeList();
      System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
        for (Map<String, Object> article : list) {
            System.out.println("글 번호 : " + article.get("NO_NO"));
            System.out.println("< 글 제목 : " + article.get("NO_TITLE") + ">");
        }
        System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
        
System.out.println("-게시글의 내용을 확인하시려면 게시글 번호를 입력해 주세요. (0번 입력 시  메인 페이지)");

        
        
        //막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분
     
           System.out.print("-입력하는 위치 :");
            String noNo = ScanUtil.nextLine(); //이거보기..
            //System.out.println(noNo); 여기서 잘 들어가네여

            if (noNo.equals("0")) {
                return ViewEnum.MEMBER_HOME;
            }
            List<Object> params = new ArrayList<>();
//          String temp = String.valueOf(noNo);
//          String temp = noNo; 
//          System.out.println(temp);
          params.add(noNo);
          Map<String, Object> article = memberdao.getNotice(params);
          
//          System.out.println("여기가 이상함여"); 헤헤 .. 해보자고 여기서 NULL 이거 뜨는거 지우는 법을 알아보자고.. 
//          System.out.println(article.get("NO_NO"));
//          System.out.println(article.get("NO_TITLE"));
//          System.out.println(article.get("NO_CONTENT"));

          if (article.get("NO_TITLE") == null && article.get("NO_CONTENT") == null) {
              System.out.println("-게시글 번호를 잘못 입력하셨습니다. 다시 입력해 주세요. ");
              return ViewEnum.ADMIN_NOTICE_LIST;
          }

          System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
          System.out.println(" 제목 : " + article.get("NO_TITLE"));
          System.out.println(" 내용 : " + article.get("NO_CONTENT"));
          System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ─────────────────────────");
          
          System.out.println("-다시 목록으로 돌아가시려면 1번을 눌러주세요.");
          
          int choice = ScanUtil.nextInt();
          switch (choice) {
          
          case 1:
              return ViewEnum.MEMBER_NOTICE;
          default:
              System.out.println("-메인페이지로 이동합니다...");
              return ViewEnum.MEMBER_HOME;
      }
   
}
}