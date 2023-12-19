package service;

import util.ScanUtil;
import util.ViewEnum;

public class MainService {

	private static MainService instance;

	private MainService() {
	}

	public static MainService getInstance() {
		if (instance == null)
			instance = new MainService();
		return instance;
	}

	public int home() {
		System.out.println("██████╗  █████╗ ███████╗██████╗ ███████╗ ██████╗ ██╗  ██╗\r\n" + 
		           "██╔══██╗██╔══██╗██╔════╝██╔══██╗██╔════╝██╔═══██╗██║ ██╔╝\r\n" + 
		           "██║  ██║███████║█████╗  ██║  ██║█████╗  ██║   ██║█████╔╝ \r\n" + 
		           "██║  ██║██╔══██║██╔══╝  ██║  ██║██╔══╝  ██║   ██║██╔═██╗ \r\n" + 
		           "██████╔╝██║  ██║███████╗██████╔╝███████╗╚██████╔╝██║  ██╗\r\n" + 
		           "╚═════╝ ╚═╝  ╚═╝╚══════╝╚═════╝ ╚══════╝ ╚═════╝ ╚═╝  ╚═╝\r\n" + 
		            "              ██████╗██╗   ██╗███╗   ███╗                \r\n" + 
	             	"             ██╔════╝╚██╗ ██╔╝████╗ ████║                \r\n" + 
		            "             ██║  ███╗╚████╔╝ ██╔████╔██║                \r\n" + 
		            "             ██║   ██║ ╚██╔╝  ██║╚██╔╝██║                \r\n" + 
		            "             ╚██████╔╝  ██║   ██║ ╚═╝ ██║                \r\n" + 
		            "              ╚═════╝   ╚═╝   ╚═╝     ╚═╝    ");
		 System.out.println();
	      System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
	      System.out.println("안녕하세요, 대덕짐입니다..");
	      System.out.println("해당되는 번호를 입력해주세요..");
	      System.out.println("1. 신규등록     2. 로그인    3. 관리자 로그인 ");
	      System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");

		int choice = ScanUtil.nextInt();

		switch (choice) {
		case 1:
			return ViewEnum.MEMBER_SIGNUP;

		case 2:
			return ViewEnum.MEMBER_LOGIN;
			
		case 3:
			return ViewEnum.ADMIN_LOGIN;

		default:
//			System.out.println("값이 올바x 다시");
			return home();

//        try {
//            int choice = ScanUtil.nextInt();
//
//            switch (choice) {
//                case 1:
//                    return ViewEnum.ADMIN_LOGIN;
//                case 2:
//                    return ViewEnum.ADMIN_LOGIN;
//                default:
//                    return ViewEnum.HOME_MAIN;
//            }
//        } catch (InputMismatchException e) {
//            System.out.println("숫자를 입력해 주세요.");
//            sc = new Scanner(System.in);
//        }

		}
	}
}
