package controller;

import java.util.HashMap;
import java.util.Map;

import service.AdminService;
import service.MainService;
import service.MemberService;
import util.ScanUtil;
import util.ViewEnum;

public class Controller {
	public static Map<String, Object> sessionStorage = new HashMap<>();

	
	
	private MainService mainService = MainService.getInstance();
	private AdminService adminService = AdminService.getInstance();
	private MemberService memberservice = MemberService.getInstance();
	
	
	public static void main(String[] args) {
		new Controller().init();
	}

	public void init() {
		int VIEW = ViewEnum.HOME_MAIN;
		while (true) {
			switch (VIEW) {
				case ViewEnum.HOME_MAIN:
					VIEW = mainService.home();
					break;
				case ViewEnum.ADMIN_LOGIN:
					VIEW = adminService.adminLogin();
					break;
					
/*-----------------------------------------라인 ----------------------------------------------------------------------*/
					
					//메인
				case ViewEnum.ADMIN_HOME:
					VIEW = adminService.adminHome1();
					break;
					
/*-----------------------------------------라인 ----------------------------------------------------------------------*/
					
					//공지사항..
				case ViewEnum.ADMIN_TM:
					VIEW = adminService.adminHome();
					break;
					
					//쓰기
				case ViewEnum.ADMIN_NOTICE_WRITE:
					VIEW = adminService.adminNoticeWrite();
					break;
					
					//리스트
				case ViewEnum.ADMIN_NOTICE_LIST:
					VIEW = adminService.adminNoticeList();
					break;
					
					//지우기
				case ViewEnum.ADMIN_NOTICE_DELETE:
					VIEW = adminService.adminNoticeDelete();
					break;
/*-----------------------------------------라인 ----------------------------------------------------------------------*/

					//자재관리.. 자재관리 제대로 하기..
				case ViewEnum.ADMIN_IM:
					VIEW=adminService.adminItemHome();
					break;
					
				case ViewEnum.ADMIN_ITEM_WRITE:
					VIEW=adminService.adminItemWrite();
					break;
					
				case ViewEnum.ADMIN_ITEM_DELETE:
					VIEW=adminService.adminItemDelete();
					break;
					
				case ViewEnum.ADMIN_ITEM_LIST:
					VIEW=adminService.adminItemList();
					break;
					
				case ViewEnum.ADMIN_ITEM_MODIFY:
//					VIEW=adminService.adminItemModify();
					
					System.out.println("진짜 좋다... 버린 뷰입니다.");
					break;
					
/*-----------------------------------------라인 ----------------------------------------------------------------------*/
					
					//매출관리...
					case ViewEnum.ADMIN_COST_HOME:
					VIEW=adminService.adminCostHome();
					break;
					
					
					
					
					
/*-----------------------------------------라인 ----------------------------------------------------------------------*/					
				
					default:
					VIEW = ViewEnum.HOME_MAIN;
					break;
					
					
					
					
					
/*-----------------------------------------라인 ----------------------------------------------------------------------*/					
//					case ViewEnum.HOME_MAIN:
//						VIEW = mainService.home();
//			            break;
			            //여기서부터 조금..
			         case ViewEnum.MEMBER_SIGNUP:
			        	 VIEW = memberservice.memberSignUp();
			            break;
			         case ViewEnum.MEMBER_LOCKER:
			        	 VIEW = memberservice.memberLocker();
			            break;
			         case ViewEnum.MEMBER_CAR:
			        	 VIEW = memberservice.memberCar();
			             break;
			         case ViewEnum.MEMBER_MEMBERSHIP:
			        	 VIEW = memberservice.memberMemberShip();
			            break;
			         case ViewEnum.MEMBER_LOGIN:
			        	 VIEW = memberservice.memberLogin();
			            break;
			         case ViewEnum.MEMBER_HOME:
			        	 VIEW = memberhome();
			            break;
			            
			         case ViewEnum.MEMBER_PARKING:
			        	 VIEW = memberservice.memberParking();
			            break;
			            
			         case ViewEnum.MEMBER_MEMBERSHIP1:
			        	 VIEW = memberservice.memberMemberShip1();
			        	 break;
			            
			            
			            //memberMemberShip1
//			         case ViewEnum.MEMBER_MILEAGE:
//			        	 VIEW = memberservice.memberMileage();
//			        	 break;
			        	 
			         case ViewEnum.MEMBER_MYPAGE:
			        	 VIEW = memberservice.memberMyPage();
			        	 break;// 안붙인거 의도한건가요
			        	 
			         case ViewEnum.MEMBER_LOCKERMOVE:
			        	 VIEW = memberservice.memberLockerMove();
			        	 break;
			        	 
			         case ViewEnum.MEMBER_NOTICE:
			        	 VIEW = memberservice.memberNotice();
			        	 break;
			        	 
//			         case ViewEnum.MEMBER_TEST:
//			        	 VIEW =memberservice.privacy(selectnum1);
//			        	 break
			         
			}
		}
	}
	
/*-----------------------------------------라인 ----------------------------------------------------------------------*/

	  public static int memberhome() { //멤버 홈
          System.out.println();
          System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
          System.out.println("1. 마이페이지  2. 주차권  3. 회원권  4. 사물함  5. 공지사항  6. 로그아웃");
          System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");

          switch (ScanUtil.nextInt()) {
          case 1 : 
             return ViewEnum.MEMBER_MYPAGE; 
          case 2 :
             return ViewEnum.MEMBER_PARKING;
          case 3 :
             return ViewEnum.MEMBER_MEMBERSHIP1;
          case 4 :
             return ViewEnum.MEMBER_LOCKERMOVE;
          case 5 :
             return ViewEnum.MEMBER_NOTICE;
          case 6:
          	
  			System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
              System.out.println("회원님의 계정이 로그아웃 되었습니다. 감사합니다. ");
              System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
              Controller.sessionStorage = new HashMap<>();

              return ViewEnum.HOME_MAIN;
          default :
             return ViewEnum.MEMBER_HOME;
          }
       
          
       }
	
	
}


