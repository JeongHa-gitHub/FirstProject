package service;

import controller.Controller;
import dao.AdminDao;

import java.util.*;

import util.ScanUtil;
import util.ViewEnum;


public class AdminService {
    private static AdminDao adminDao = AdminDao.getInstance();
    private static AdminService instance;

    private AdminService() {
    }

    public static AdminService getInstance() {
        if (instance == null)
            instance = new AdminService();
        return instance;
    }

    //ADMIN로그인 화면  아이디 비번 입력...
    public int adminLogin() {
    	System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
//        System.out.println("⌜⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⌝");
        System.out.println("관리자 로그인 화면입니다.                    ");
        System.out.println("아이디를 입력해 주세요. ");
        System.out.println("이전화면으로 돌아가시려면 0번을 입력해 주세요.");
        String eName = ScanUtil.nextLine();
//        System.out.println("⌞⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⌟");
        System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
        if ("0".equals(eName)) {
            return ViewEnum.HOME_MAIN;
        }

        if ("".equals(eName)) {
        	System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
            System.out.println("유효하지 않은 아이디입니다. 다시 입력해 주세요.");
            System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
            return ViewEnum.ADMIN_LOGIN;
        }
        // 정하 와일

        System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
        System.out.println("관리자 로그인 화면입니다.");
        System.out.println("비밀번호를 입력해 주세요:");
        System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
        String ePass = ScanUtil.nextLine();
        

        if ("".equals(ePass)) {
        	System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
            System.out.println("유효하지 않은 비밀번호입니다. 다시 입력해 주세요.");
            System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
            return ViewEnum.ADMIN_LOGIN;
        }
        
        //while

        // 사용자가 입력한 관리자의 아이디와 비밀번호를 순서를 지키기 위해 리스트에 저장
        List<Object> params = new ArrayList<>();
        params.add(eName);
        params.add(ePass);
        
//        
//        System.out.println(params.get(0));
//        System.out.println(params.get(1));

        Map<String, Object> result = adminDao.adminLogin(params);
        
//        System.out.println("불러온 E_NO  "+result.get("E_NO")); // MAP 여기서는 되는데 왜 저기서는 안되는지.
//        System.out.println("불러온 E_NAME  "+ result.get("E_NAME"));

        // 조회 데이터가 없는 경우
        try{
        	if (result.get("E_NAME") == null) {
        	            System.out.println("!!!!사용자 정보가 존재하지 않습니다. 다시 시도해 주세요.!!!!");
        	            return ViewEnum.ADMIN_LOGIN;
        	        } else {
        	            // 로그인한 정보를 Controller 클래스 변수에 저장합니다.
        	            Controller.sessionStorage = result;
        	            return ViewEnum.ADMIN_HOME;
        	        }
        	}catch (NullPointerException e){
        		System.out.println("값이 잘못됐어요 다시 시도하세요.");
        		return ViewEnum.ADMIN_LOGIN;
        	}
    }
    
    /*------------------------------------라인 ----------------------------------------------------------------------*/
    
    //관리자 공홈
    public int adminHome1() {
    	
    	System.out.println("──────────────────────    관리자      ────────────────────────");
		System.out.println("        1.공지사항       2.자재관리       3.매출보기       4.로그아웃   ");
		System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
		System.out.print(" 【  선택  】 ");

		switch (ScanUtil.nextInt()) {
		case 1:
			return ViewEnum.ADMIN_TM;
		case 2:
			return ViewEnum.ADMIN_IM;
		case 3:
			return ViewEnum.ADMIN_COST_HOME;
		case 4:
        	
			System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
            System.out.println("관리자 계정 로그아웃 되었습니다. 감사합니다. ");
            System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
            Controller.sessionStorage = new HashMap<>();

            return ViewEnum.HOME_MAIN;
		default:
			return ViewEnum.ADMIN_HOME;
		}
	}
    
    /*------------------------------------라인 ----------------------------------------------------------------------*/
    
    // 공지사항을 골랐을 때 ... 나오는 페이지 입니다. 공지사항.. 
    public int adminHome() {
    	System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
        System.out.println(Controller.sessionStorage.get("E_NAME") + "님, 환영합니다.");
        System.out.println("   1.공지사항 작성하기    2.공지사항 목록 확인하기    3.공홈    4.로그아웃");
        System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
        try {
            int choice2 = ScanUtil.nextInt();

            switch (choice2) {
                case 1:
                	//System.out.println("1번이 선택됐습니다.");
                	return writeBoard();
                	
                case 2:
                	//System.out.println("2번이 선택됐습니다.");
                    return ViewEnum.ADMIN_NOTICE_LIST;
                    
                case 3:
                	return ViewEnum.ADMIN_HOME;
                	
                case 4:
                    // sessionStorage를 초기화합니다.
                	System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
                    System.out.println("관리자 계정 로그아웃 되었습니다. 감사합니다. ");
                    System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
                    Controller.sessionStorage = new HashMap<>();

                    return ViewEnum.HOME_MAIN;
                default:
                	
                	
                    return ViewEnum.ADMIN_HOME;
            }
        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력해 주세요.");
//            sc = new Scanner(System.in);
            return ViewEnum.ADMIN_HOME;
        }
        catch (NullPointerException e){
    		System.out.println("값이 잘못됐어요 다시 시도하세요.");
    		return ViewEnum.ADMIN_HOME;
    	}
    }

    /*------------------------------------라인 ----------------------------------------------------------------------*/
    
    //게시글 작성
    public int adminNoticeWrite() {
    	System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
        System.out.println(" 먼저, 공지사항의 제목을 입력해 주세요.");
        System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
        ScanUtil.nextLine(); // 버퍼 비우기용..
        String noTitle = ScanUtil.nextLine();

        System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
        System.out.println(" 먼저, 공지사항 내용을 입력해 주세요.");
        System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
        String noContent = ScanUtil.nextLine();

        if ("".equals(noTitle.trim()) || "".equals(noContent.trim())) {
            System.out.println("제목 또는 내용은 최소 한 글자를 입력해 주셔야 합니다.");
            return ViewEnum.ADMIN_HOME;
        }

        System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
        System.out.println(" 제목 : " + noTitle);
        System.out.println(" 내용 : " + noContent);
        System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");

        System.out.println(" 값을 저장합니다...");

        List<Object> params = new ArrayList<>();
        params.add(noContent);
        params.add((String) Controller.sessionStorage.get("E_NO"));
        params.add(noTitle);
        int r = adminDao.adminNoticeWrite(params);

        if (r == 0) {
            System.out.println("글 작성에 실패하였습니다. 다시 시도해 주세요.");
            return ViewEnum.ADMIN_NOTICE_WRITE;
        } else {
            System.out.println("글 작성에 성공하였습니다.");
            return ViewEnum.ADMIN_NOTICE_LIST;
        }
    }
    
      
    //게시글작성 2
    public int writeBoard() {
    	System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
		System.out.println("   작성하고 싶은 게시글의 제목을 작성해 주세요.           ");
		System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");

		String title = ScanUtil.nextLine();

		System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
		System.out.println("   작성하고 싶은 게시글의 내용을 작성해 주세요.           ");
		System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");

		String desc = ScanUtil.nextLine();
		
		System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
		System.out.println("   작성한 직원의 ID를 입력하세요.                          ");
		System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");

		String eno =ScanUtil.nextLine();


		System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
		System.out.println("   작성하신 내용이 맞습니까? 맞으시면 1, 틀리면 2");
		System.out.println("제목 : ");
		System.out.println(title);
		System.out.println("내용 : ");
		System.out.println(desc);
		System.out.println("관리자 : ");
		System.out.println(eno);
		System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");

		List<Object> params = new ArrayList<>();	
		
		params.add(title);
		params.add(desc);
		params.add(eno);
		
		// params.get 에 순서대로 제대로 들어갔는지 확인해봣어요
//		System.out.println(params.get(0));
		
		switch (ScanUtil.nextInt()) {
		case 1: 
			//이제 여기에 불러와야지... 해보자... 
			
			adminDao.adminNoticeWrite(params);
//			int memberInfo1 = adminDao.adminNoticeWrite(params);
			//멤버 인포에 값이 정확히 입력됐나..? 그러면 int 이기 때문에 .. memberInfo1 의 입력값은 1이 나와용..
			//System.out.println(memberInfo1);
			

			System.out.println("성공적으로 저장했습니다!");
			return ViewEnum.ADMIN_TM;
		case 2:
			System.out.println("다시 작성해 주세요!");
			return writeBoard();
		default:
			System.out.println("잘못 입력하셨습니다.");
			return ViewEnum.ADMIN_TM;
		}
	}
    
    /*------------------------------------라인 ----------------------------------------------------------------------*/
    
    //공지사항 목록을 보여주는 메소드
    public int adminNoticeList() {
        List<Map<String, Object>> list = adminDao.adminNoticeList();

        // 작성된 게시글이 하나도 없는 경우..
        if (list.size() == 0) {
        	System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
            System.out.println("작성된 글이 아직 없네요. 새로운 글을 작성해 보세요!");
            System.out.println("(글을 작성하시려면 1번, 관리자 메인 페이지로 넘어가려면 2번을 눌러 주세요.)");
            System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");

            try {
                int choice = ScanUtil.nextInt();

                switch (choice) {
                    case 1:
                        return ViewEnum.ADMIN_NOTICE_WRITE;
                    case 2:
                        return ViewEnum.ADMIN_HOME;
                    default:
                        System.out.println("잘못 누르셨습니다. 다시 한번 눌러 주세요.");
                        return ViewEnum.ADMIN_NOTICE_LIST;
                }
            } catch (InputMismatchException e) {
                System.out.println("숫자를 입력해 주세요.");
//                sc = new Scanner(System.in);
                return ViewEnum.ADMIN_NOTICE_LIST;
            }catch (NullPointerException e ) {
            	System.out.println("잘못쳤어요 .. 다시 돌아가서 입력해주세요.. ");
            	return ViewEnum.ADMIN_NOTICE_LIST;
            }
        }

        // 작성된 게시글이 하나라도 있는 경우
        System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
        for (Map<String, Object> article : list) {
            System.out.println("글 번호 : " + article.get("NO_NO"));
            System.out.println("< 글 제목 : " + article.get("NO_TITLE") + ">");
        }
        System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
        System.out.println("게시글의 내용을 확인하시려면 게시글 번호를 입력해 주세요. (0번 입력 시 관리자 메인 페이지)");

        
        
        //막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분
        try {
        	System.out.print("입력하는 위치 :");
            String noNo = ScanUtil.nextLine(); //이거보기..
            //System.out.println(noNo); 여기서 잘 들어가네여

            if (noNo.equals("0")) {
                return ViewEnum.ADMIN_HOME;
            }

            List<Object> params = new ArrayList<>();
//            String temp = String.valueOf(noNo);
//            String temp = noNo; 
//            System.out.println(temp);
            params.add(noNo);
            Map<String, Object> article = adminDao.getNotice(params);
            
//            System.out.println("여기가 이상함여"); 헤헤 .. 해보자고 여기서 NULL 이거 뜨는거 지우는 법을 알아보자고.. 
//            System.out.println(article.get("NO_NO"));
//            System.out.println(article.get("NO_TITLE"));
//            System.out.println(article.get("NO_CONTENT"));

            if (article.get("NO_TITLE") == null && article.get("NO_CONTENT") == null) {
                System.out.println("게시글 번호를 잘못 입력하셨습니다. 다시 입력해 주세요!");
                return ViewEnum.ADMIN_NOTICE_LIST;
            }

            System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
            System.out.println(" 제목 : " + article.get("NO_TITLE"));
            System.out.println(" 내용 : " + article.get("NO_CONTENT"));
            System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");

            System.out.println("게시글을 수정하려면 0번을 눌러주세요.");
            System.out.println("게시글을 삭제하려면 1번을 눌러주세요.");
            System.out.println("다시 목록으로 돌아가시려면 2번을 눌러주세요.");

            int choice = ScanUtil.nextInt();
            switch (choice) {
                case 0:
                    return adminNoticeModify(
                            (String) article.get("NO_NO"),
                            (String) article.get("NO_TITLE"),
                            (String) article.get("NO_CONTENT"));
                case 1:
                    return ViewEnum.ADMIN_NOTICE_DELETE;
                case 2:
                    return ViewEnum.ADMIN_NOTICE_LIST;
                default:
                    System.out.println("관리자 메인 페이지로 돌아갑니다..");
                    return ViewEnum.ADMIN_HOME;
            }
        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력해 주세요.");
//            sc = new Scanner(System.in);
            return ViewEnum.ADMIN_NOTICE_LIST;
        }catch (NullPointerException e ) {
        	System.out.println("잘못쳤어요 .. 다시 돌아가서 입력해주세요.. ");
        	return ViewEnum.ADMIN_NOTICE_LIST;
        }
    }
    
    
    //수정하기
    public int adminNoticeModify(String noNo, String noTitle, String noContent) {

        try {
            List<Object> params = new ArrayList<>();
            params.add(noNo);
            
//            System.out.println("359줄 도착했다."); 알았따
//            ScanUtil.nextLine();
            System.out.println("수정할 글 제목을 입력해 주세요. (수정하기 싫으면 그냥 엔터)");
            String modifiedTitle = ScanUtil.nextLine();
            System.out.println("수정할 글 내용을 입력해 주세요. (수정하기 싫으면 그냥 엔터)");
            String modifiedContent = ScanUtil.nextLine();

            if (modifiedTitle.length() == 0) {
                modifiedTitle = noTitle;
            }
            if (modifiedContent.length() == 0) {
                modifiedContent = noContent;
            }

            List<Object> modified = new ArrayList<>();
            modified.add(modifiedTitle);
            modified.add(modifiedContent);
            modified.add(String.valueOf(noNo));

            int result = adminDao.adminNoticeModify(modified);

            if (result == 0) {
                System.out.println("수정에 실패했습니다. 다시 시도해 주세요.");
                return adminNoticeModify(noNo, noTitle, noContent);
            }

            System.out.println("게시글을 수정했습니다.");
            Map<String, Object> modifiedArticle = adminDao.getNotice(params);
            System.out.println("게시판 번호 : "+modifiedArticle.get("NO_NO"));
            System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
            System.out.println(" 제목 : " + modifiedArticle.get("NO_TITLE"));
            System.out.println(" 내용 : " + modifiedArticle.get("NO_CONTENT"));
            System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");

            System.out.println("목록으로 돌아가려면 아무 키나 눌러주세요.");

            ScanUtil.nextLine();
            return ViewEnum.ADMIN_NOTICE_LIST;

        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력해 주세요.");
            return ViewEnum.ADMIN_HOME;
        }
    }

    
    //완벽한 delete문 
    public int adminNoticeDelete() {
    	
    	try {
    		System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
			System.out.println("   삭제할 게시판의 게시판 번호를 적어주세요                                         ");
			System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
			System.out.print(" 【  선택  】 : ");
			adminDao.deleteNotice(ScanUtil.nextLine());
			//delete 문 만들어보기.. 
			System.out.println("완료됐습니다!");
    		
			return ViewEnum.ADMIN_NOTICE_LIST;
    	}catch (InputMismatchException e) {
    		System.out.println("없는 값입니다 다시 쓰세영");
    		return adminNoticeDelete();
    	}

    }
    
    
    
    /*------------------------------------라인 ----------------------------------------------------------------------*/
    
    
    //자재관리
    
    public int adminItemHome() {
    	//1. LIST 보기 .. 2.LIST 수정   3.관리자 메인페이지로 돌아가기.. 
    	
    	System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
		System.out.println("   1. LIST 보기     2.자재추가하기         3.삭제하기                    4.로그아웃          ");
		System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
    	System.out.print("숫자를 선택하세요 : ");
		//String select = ScanUtil.nextLine();
    	try {
            int choice1 = ScanUtil.nextInt();

            switch (choice1) {
                case 1:
//                    System.out.println("1번");
                	return ViewEnum.ADMIN_ITEM_LIST;
//                case 2:
//                	
//                	System.out.println("2번");
//                    return ViewEnum.ADMIN_ITEM_MODIFY;
                    
                case 2:
//                	System.out.println("5번");
                    return ViewEnum.ADMIN_ITEM_WRITE;
                    
                case 3:
//                	System.out.println("3번");
                    return ViewEnum.ADMIN_ITEM_DELETE;
                    
                    
                case 4:
                	System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
                     System.out.println(" 관리자 계정 로그아웃 되었습니다. 감사합니다. ");
                     System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
                     Controller.sessionStorage = new HashMap<>();

                     return ViewEnum.HOME_MAIN;
                     
                
                     
                default:
                	System.out.println("잘못된 값입니다.");
                    return ViewEnum.ADMIN_IM;
            }
        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력해 주세요.");
//            sc = new Scanner(System.in);
            return ViewEnum.ADMIN_IM;
        }
		
    	
   
    }
    
    //자재 목록을 보여주는 메소드
    public int adminItemList() {
    	
    	List<Map<String, Object>> listItem = adminDao.adminItemList();

        // 작성된 게시글이 하나도 없는 경우..
        if (listItem.size() == 0) {
        	System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
            System.out.println("자재가 아직 없네요. 새로운 자재를 입력해보세요!");
            System.out.println("(글을 작성하시려면 1번, 관리자 메인 페이지로 넘어가려면 2번을 눌러 주세요.)");
            System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");

            try {
                int choiceItem = ScanUtil.nextInt();
/**
 * 현재 하고있는 부분 ..현재 하고있는 부분 ..현재 하고있는 부분 ..현재 하고있는 부분 ..현재 하고있는 부분 ..현재 하고있는 부분 ..현재 하고있는 부분 .. 있다고 가정하고 넘기기
 */
                switch (choiceItem) {
                    case 1:
                        return ViewEnum.ADMIN_ITEM_WRITE;
                    case 2:
                        return ViewEnum.ADMIN_HOME;
                    default:
                        System.out.println("잘못 누르셨습니다. 다시 한번 눌러 주세요.");
                        return ViewEnum.ADMIN_ITEM_LIST;
                }
            } catch (InputMismatchException e) {
                System.out.println("숫자를 입력해 주세요.");
//                sc = new Scanner(System.in);
                return ViewEnum.ADMIN_ITEM_LIST;
            }
        }

        // 작성된 게시글이 하나라도 있는 경우
        System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
        for (Map<String, Object> article : listItem) {
        	System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
            System.out.println("자재 번호 : " + article.get("I_ITEMNO"));
            System.out.println("자재 이름 : " + article.get("I_ITEMNAME"));
            System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
//            System.out.println("<" + article.get("I_REMARK") + ">");
        }
        System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
        System.out.println("자재 내용을 확인하시려면 자재 번호를 입력해 주세요. (0번 입력 시 관리자 메인 페이지)");

        
        
        //막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분막힌부분
        try {
        	System.out.print("입력하는 위치 :");
            String itemNo = ScanUtil.nextLine(); //이거보기..
//            System.out.println("자재번호"+itemNo);

            if (itemNo.equals("0")) {
                return ViewEnum.ADMIN_HOME;
            }

            List<Object> params = new ArrayList<>();
            String temp =itemNo;
//            System.out.println("temp에 itemNo 들어갔니?"+temp);
            params.add(temp);
            Map<String, Object> article = adminDao.getItem(params);
            

            if (article.get("I_ITEMNO") == null && article.get("I_ITEMNAME") == null) {
                System.out.println("게시글 번호를 잘못 입력하셨습니다. 다시 입력해 주세요!");
                return ViewEnum.ADMIN_NOTICE_LIST;
            }

            System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
            System.out.println(" 자재번호 : " + article.get("I_ITEMNO"));
            System.out.println(" 자재명 : " + article.get("I_ITEMNAME"));
            System.out.println(" 자재량 : " + article.get("I_AMOUNT"));
            System.out.println(" 가격 : " + article.get("I_PRICE"));
            System.out.println(" 구매날짜 : " + article.get("I_BUYDATE"));
            System.out.println(" 유지보수 : " + article.get("I_MAINTAIN"));
            System.out.println(" 번호 : " + article.get("I_TELNO"));
            System.out.println(" 관리자번호 : " + article.get("E_NO"));
            System.out.println(" 비고 : " + article.get("I_REMARK"));  //NVL2(I_REMARK,I_REMARK,'') ALIAS .. 쿼리 이부분..
            System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");

            System.out.println("게시글을 수정하려면 0번을 눌러주세요.");
            System.out.println("게시글을 삭제하려면 1번을 눌러주세요.");
            System.out.println("다시 목록으로 돌아가시려면 2번을 눌러주세요.");

            
            //자재량 가격 유지보수 번호 관리자번호 비고
            /**자재명 : " + article.get("I_ITEMNAME")
             * " 가격 : " + article.get("I_PRICE")
             * " 유지보수 : " + article.get("I_MAINTAIN")
             * " 번호 : " + article.get("I_TELNO")
             * " 관리자번호 : " + article.get("E_NO")
             * " 비고 : " + article.get("I_REMARK")
             * 
             * adminItemModify(
             * (String) article.get("I_ITEMNO"),
                            (String) article.get("I_ITEMNAME"),
                            (String) article.get("I_PRICE"),
                            (String) article.get("I_MAINTAIN")
                            (String) article.get("I_TELNO")
                            (String) article.get("E_NO")
                            (String) article.get("I_REMARK"));
             * 
             */

            int choice = ScanUtil.nextInt();
            switch (choice) {
                case 0:
                	
                    return  adminItemModify(
                    		(String) article.get("I_ITEMNO"),
                            (String) article.get("I_ITEMNAME"),
                            String.valueOf (article.get("I_PRICE")),
                            
                            (String) article.get("I_TELNO"),
                            (String) article.get("E_NO"),
                            (String) article.get("I_REMARK"));
                case 1:
                    return ViewEnum.ADMIN_ITEM_DELETE;
                case 2:
                    return ViewEnum.ADMIN_ITEM_LIST;
                default:
                    System.out.println("관리자 메인 페이지로 돌아갑니다..");
                    return ViewEnum.ADMIN_HOME;
            }
        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력해 주세요.");
//            sc = new Scanner(System.in);
            return ViewEnum.ADMIN_ITEM_LIST;
        }
    	
    }
    
    //수정하기
    public int adminItemModify(String itemNo, String itemName, String itemPrice, 
    		String itemTelNo,
    		String eNo,String itemRemark) {
    	try {
            List<Object> params = new ArrayList<>();
            params.add(itemNo); //아직 정확히 모름
            
            System.out.println("enter 치시면 실행이됩니다.");
            ScanUtil.nextLine();
            System.out.println("자재량을 입력해 주세요. (수정하기 싫으면 그냥 엔터)");
            String modifiedName = ScanUtil.nextLine();
            System.out.println("가격을 입력해 주세요. (수정하기 싫으면 그냥 엔터)");
            String modifiedPrice = ScanUtil.nextLine();
//            System.out.println("유지보수를 입력해 주세요. (수정하기 싫으면 그냥 엔터)");
//            String modifiedMaintain = ScanUtil.nextLine();
            System.out.println("번호를 입력해 주세요. (수정하기 싫으면 그냥 엔터)");
            String modifiedTelNo = ScanUtil.nextLine();
            System.out.println("관리자번호를 입력해 주세요. (수정하기 싫으면 그냥 엔터)");
            String modifiedeNo = ScanUtil.nextLine();
            System.out.println("비고를 입력해 주세요. (수정하기 싫으면 그냥 엔터)");
            String modifiedRemark = ScanUtil.nextLine();
//sdasdasdasdasdsa
            if (modifiedName.length() == 0) {
            	modifiedName = itemName;
            }
            if (modifiedPrice.length() == 0) {
            	modifiedPrice = itemPrice;
            }
//            if (modifiedMaintain.length() == 0) {
//            	modifiedMaintain = itemMaintain;
//            }
            if (modifiedTelNo.length() == 0) {
            	modifiedTelNo = itemTelNo;
            }
            if (modifiedeNo.length() == 0) {
            	modifiedeNo = eNo;
            }
            if (modifiedRemark.length() == 0) {
            	modifiedRemark = itemRemark;
            }

            List<Object> modified = new ArrayList<>();
            modified.add(modifiedName);
            modified.add(modifiedPrice);
//            modified.add(modifiedMaintain);
            modified.add(modifiedTelNo);
            modified.add(modifiedeNo);
            modified.add(modifiedRemark);
            modified.add(itemNo);

            int result = adminDao.adminItemModify(modified);
//            System.out.println("여기 라인");
//            System.out.println(result);
            

            if (result == 0) {
                System.out.println("수정에 실패했습니다. 다시 시도해 주세요.");
                return adminItemModify(itemNo, itemName, itemPrice, itemTelNo, eNo, itemRemark);
            }

            System.out.println("게시글을 수정했습니다.");
            Map<String, Object> modifiedItem = adminDao.getItem(params);
//            System.out.println("I_ITEMNO<자재번호> 의 값"+modifiedItem.get("I_ITEMNO"));
            System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
            System.out.println("자재명 : " + modifiedItem.get("I_ITEMNAME"));
            System.out.println(" 자재량 : " + modifiedItem.get("I_AMOUNT"));
            System.out.println(" 가격 : " + modifiedItem.get("I_PRICE"));
//            System.out.println(" 유지보수 : " + modifiedItem.get("I_MAINTAIN"));
            System.out.println(" 번호 : " + modifiedItem.get("I_TELNO"));
            System.out.println(" 관리자번호 : " + modifiedItem.get("E_NO"));
            System.out.println(" 비고 : " + modifiedItem.get("I_REMARK"));
            System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");

            System.out.println("목록으로 돌아가려면 아무 키나 눌러주세요.");

            ScanUtil.nextLine();
            return ViewEnum.ADMIN_ITEM_LIST;

        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력해 주세요.");
    	
    	return ViewEnum.ADMIN_ITEM_LIST;
    }
    
    }
    
    
    //delete문 
    public int adminItemDelete() {
    	
    		
    	List<Map<String, Object>> listItem = adminDao.adminItemList();

    			System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
    	        for (Map<String, Object> article : listItem) {
    	        System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
    	        System.out.println("자재 번호 : " + article.get("I_ITEMNO"));
    	        System.out.println("자재 이름 : " + article.get("I_ITEMNAME"));
    	        System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
    	        
    	        }
    	
    	
    	
    	
    		System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
			System.out.println("   삭제할 게시판의 자재번호를 적어주세요                              ");
			System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
			
			
			
			
			System.out.print(" 【  선택  】 : ");//자 아이템no에 따른 해당 게시물 유무 불러오기..
			String a =ScanUtil.nextLine();
			
//			adminDao.deleteItem(a);
			
			List<Object> params = new ArrayList<>();
			params.add(a);
			
			
			Map<String, Object> result = adminDao.adminDelete(params);
			//delete 문 만들어보기.. 
			
			try{
	        	if (result.get("I_ITEMNO") == null) {
	        	            System.out.println("!!!!제품명이 존재하지 않습니다. 다시 시도해 주세요.!!!!");
	        	            return adminItemHome();
	        	        } else {
	        	            // 로그인한 정보를 Controller 클래스 변수에 저장합니다.
	        	        	adminDao.deleteItem(a);
	        	            Controller.sessionStorage = result;
	        	            return adminItemHome();
	        	        }
	        	}catch (NullPointerException e){
	        		System.out.println("값이 잘못됐어요 다시 시도하세요.");
	        		return adminItemDelete();
	        	}
			
    		
			
    	
    }
    
    //게시글 작성
    
    public int adminItemWrite() {
    	
    	
    	System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
		System.out.println("   작성하고 싶은 자재명을 작성해 주세요.           ");
		System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");

		String itemname = ScanUtil.nextLine();
		
		System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
		System.out.println("   자재 수량을 선택해주세요.           ");
		System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");

		String itemamount =ScanUtil.nextLine();
		
		System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
		System.out.println("   자재가격을 작성해 주세요.           ");
		System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");

		String itemprice = ScanUtil.nextLine();
		
		
		System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
		System.out.println("   유지보수를 작성해 주세요<(EX)YYYYMMDD>. (NULL값일시 ENTER를 쳐주세요)");
		System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");

		String itemmaintain = ScanUtil.nextLine();
		
		System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
		System.out.println("   전화번호를 작성해 주세요.           ");
		System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");

		String itemtelno = ScanUtil.nextLine();
		
		System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
		System.out.println("   관리자의 eno을 작성해 주세요.           ");
		System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");

		String itemeno = ScanUtil.nextLine();
		
		System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
		System.out.println("   주석을 작성해 주세요.           ");
		System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");

		String itemremark = ScanUtil.nextLine();


		System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
		System.out.println("   작성하신 내용이 맞습니까? 맞으시면 1, 틀리면 2");
		System.out.println("자재명 : ");
		System.out.println(itemname);
		System.out.println("자재량 : ");
		System.out.println(itemamount);
		System.out.println("가격 : ");
		System.out.println(itemprice);
		System.out.println("유지보수 : ");
		System.out.println(itemmaintain);
		System.out.println("구매처번호 : ");
		System.out.println(itemtelno);
		System.out.println("관리자명 : ");
		System.out.println(itemeno);
		System.out.println("주석 : ");
		System.out.println(itemremark);
		System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");

		List<Object> params = new ArrayList<>();	
	
		params.add(itemname);
		params.add(itemamount);
		params.add(itemprice);
		params.add(itemmaintain);
		params.add(itemtelno);
		params.add(itemeno);
		params.add(itemremark);
		
//		System.out.println("궁금해서 뽑아봄여"+params.get(0));
		
		switch (ScanUtil.nextInt()) {
		case 1: 
			
			int memberInfo1 = adminDao.adminItemWrite(params);
			
			System.out.println("멤버인포"+memberInfo1);

			System.out.println("성공적으로 저장했습니다!");
			return ViewEnum.ADMIN_ITEM_LIST;
		case 2:
			System.out.println("다시 작성해 주세요!");
			return adminItemWrite();
		default:
			System.out.println("잘못 입력하셨습니다.");
			return ViewEnum.ADMIN_ITEM_LIST;
		}
    	
    	
    }
    
    
    /*------------------------------------라인 ----------------------------------------------------------------------*/
    
    
    //매출 공홈
    public int adminCostHome() {
    	System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
		System.out.println("   1. 운동별 매출  2. 종합매출     3.뒤로가기         4.로그아웃 ");
		System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
    	System.out.print("숫자를 선택하세요 : ");
    	
    	try {
            int choice1 = ScanUtil.nextInt();

            switch (choice1) {
                case 1:
//                    System.out.println("1번"); 확인
                	return adminCostPractice();

                	
                case 2:
//                	System.out.println("2번");  확인
                    return adminCostTotal();
                    
                case 3:
                	return ViewEnum.ADMIN_HOME;
                    
                case 4:
                	
                	System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
                     System.out.println(" 관리자 계정 로그아웃 되었습니다. 감사합니다. ");
                     System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
                     Controller.sessionStorage = new HashMap<>();

                     return ViewEnum.HOME_MAIN;
                     
                    
                default:
                	System.out.println("잘못된 값입니다.");
                    return ViewEnum.ADMIN_COST_HOME;  //고치기
            }
            
        } catch (InputMismatchException e) {
        	
            System.out.println("숫자를 입력해 주세요.");

            return ViewEnum.ADMIN_IM;
        }
    	catch (NullPointerException e){
    		System.out.println("값이 잘못됐어요 다시 시도하세요.");
    		return ViewEnum.ADMIN_IM;
    	}
    	
    	
    }
    
    public int adminCostPractice() {
    	
    	List<Map<String, Object>> list = adminDao.adminCostList();
    	
//    	System.out.println(adminDao.adminCostList()); 이거 출력하면 다나옴 개신기.. 
//    	System.out.println(list);이거 출력하면 다나옴 개신기..
    	
    	
    	
  
    	
    	System.out.println("────────────────────── 운동별 매출액  ────────────────────────");
         for (Map<String, Object> article : list) {
             System.out.println("회원권 종류 : " + article.get("MS_KIND"));
             System.out.println("총합 :  <" + article.get("MS_COST") + ">");
         }
         System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
         System.out.println("1. 공홈  2. 뒤로가기 ");
    	
    	
         
    	
         try {
             int choiceItem = ScanUtil.nextInt();

             switch (choiceItem) {
             	 case 1:
             		 return ViewEnum.ADMIN_HOME;
             
                 case 2:
                     return ViewEnum.ADMIN_COST_HOME;
                     
                 default:
                     System.out.println("잘못 누르셨습니다. 다시 한번 눌러 주세요.");
                     return adminCostPractice();
             }
         } catch (InputMismatchException e) {
             System.out.println("숫자를 입력해 주세요.");
             return adminCostPractice();
         }catch (NullPointerException e){
     		System.out.println("값이 잘못됐어요 다시 시도하세요.");
     		return adminCostPractice();
     	}
         
         
         
    
    }
    
    public int adminCostMonth() {
    	
    	List<Object> params = new ArrayList<>();
    	
    	System.out.println("원하는 월 입력 : ");
    	int a=ScanUtil.nextInt();
    	params.add(a);
    	
    	List<Map<String, Object>> list = adminDao.getCostMonth(params);
    	
//    	System.out.println(list);
    	
    	System.out.println("────────────────────── 운동별 매출액  ────────────────────────");
        for (Map<String, Object> article : list) {
            System.out.println("회원권 종류 : " + article.get("MS_KIND"));
            System.out.println("총합 :  <" + article.get("TOTAL_SALES") + ">");
        }
        System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
        System.out.println("1. 공홈  2. 뒤로가기 ");
    	

        try {
            int choiceItem = ScanUtil.nextInt();

            switch (choiceItem) {
            	 case 1:
            		 return ViewEnum.ADMIN_HOME;
            
                case 2:
                    return ViewEnum.ADMIN_COST_HOME;
                    
                default:
                    System.out.println("잘못 누르셨습니다. 다시 한번 눌러 주세요.");
                    return adminCostMonth();
            }
        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력해 주세요.");
            return adminCostMonth();
        }
        catch (NullPointerException e){
    		System.out.println("값이 잘못됐어요 다시 시도하세요.");
    		return adminCostMonth();
    	}
        
    	
   
    }
    
    public int adminCostMonthTotal() {
    	
    	List<Object> params = new ArrayList<>();
    	
    	System.out.println("원하는 월 입력 : ");
    	int a=ScanUtil.nextInt();
    	params.add(a);
    	
    	List<Map<String, Object>> list = adminDao.adminCostMonthTotal(params);
    	
//    	System.out.println(list);
    	
    	
    	System.out.println("────────────────────── 운동별 매출액  ────────────────────────");
           for (Map<String, Object> article : list) {
               System.out.println("회원권 종류  ");
               System.out.println("총합 :  <" + article.get("GRAND_TOTAL") + ">");
           }
           System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
           System.out.println("1. 공홈  2. 뒤로가기 ");
       	

           try {
               int choiceItem = ScanUtil.nextInt();

               switch (choiceItem) {
               	 case 1:
               		 return ViewEnum.ADMIN_HOME;
               
                   case 2:
                       return ViewEnum.ADMIN_COST_HOME;
                       
                   default:
                       System.out.println("잘못 누르셨습니다. 다시 한번 눌러 주세요.");
                       return adminCostMonthTotal();
               }
           } catch (InputMismatchException e) {
               System.out.println("숫자를 입력해 주세요.");
               return adminCostMonthTotal();
           }
           catch (NullPointerException e){
       		System.out.println("값이 잘못됐어요 다시 시도하세요.");
       		return adminCostMonthTotal();
       	}
           
        
    }
    
    public int adminCostYears() {
    	
    	List<Object> params = new ArrayList<>();
    	
    	System.out.println("원하는 연도 입력 : ");
    	int a=ScanUtil.nextInt();
    	params.add(a);
    	
    	List<Map<String, Object>> list = adminDao.adminCostYears(params);
    	
//    	System.out.println(list);
    	
    	
    	System.out.println("──────────────────────   총연매출    ────────────────────────");
          for (Map<String, Object> article : list) {
              
              System.out.println("총합 :  <" + article.get("TOTAL_SALES") + ">");
          }
          System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
          System.out.println("1. 공홈  2. 뒤로가기 ");
      	

          try {
              int choiceItem = ScanUtil.nextInt();

              switch (choiceItem) {
              	 case 1:
              		 return ViewEnum.ADMIN_HOME;
              
                  case 2:
                      return ViewEnum.ADMIN_COST_HOME;
                      
                  default:
                      System.out.println("잘못 누르셨습니다. 다시 한번 눌러 주세요.");
                      return adminCostYears();
              }
          } catch (InputMismatchException e) {
              System.out.println("숫자를 입력해 주세요.");
              return adminCostYears();
          }
          catch (NullPointerException e){
      		System.out.println("값이 잘못됐어요 다시 시도하세요.");
      		return adminCostYears();
      	}
          
    }
      
    public int adminCostTotal() {
    	System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
		System.out.println("1. 월매출(종목별)  2. 총 월매출     3. 총 연매출     4. 뒤로가기   5. 로그아웃");
		System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
    	System.out.print("숫자를 선택하세요 : ");
    	
    	
    	try {
            int choice1 = ScanUtil.nextInt();

            switch (choice1) {
                case 1:
                	
//                    System.out.println("1번");
                    
                	return adminCostMonth();

                	
                	
                case 2:
                	
//                	System.out.println("2번");
                	
                	
                    return adminCostMonthTotal();
                    
                    
                case 3:
                	
//                	System.out.println("3번");
                	
                	
                    return adminCostYears();
                    
                case 4:
                	
//                	System.out.println("4번");
                	
                	
                    return adminCostHome();
                    
                    
                case 5:
                	
                	 System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
                     System.out.println(" 관리자 계정 로그아웃 되었습니다. 감사합니다. ");
                     System.out.println("────────────────────── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────────────────────────");
                     Controller.sessionStorage = new HashMap<>();

                     return ViewEnum.HOME_MAIN;
                     
                    
                default:
                	System.out.println("잘못된 값입니다.");
                    return ViewEnum.ADMIN_COST_HOME;
            }
            
        } catch (InputMismatchException e) {
        	
            System.out.println("숫자를 입력해 주세요.");

            return ViewEnum.ADMIN_COST_HOME;
        }
    	catch (NullPointerException e){
    		System.out.println("값이 잘못됐어요 다시 시도하세요.");
    		return ViewEnum.ADMIN_COST_HOME;
    	}

    }

    
    /*------------------------------------라인 ----------------------------------------------------------------------*/
    }
