package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.JDBCUtil;

public class MemberDAO {
   
   private static MemberDAO instance = null;
   private MemberDAO() {}
   public static MemberDAO getInstance() {
      if(instance == null) instance = new MemberDAO();
      return instance;
   } 

   JDBCUtil jdbc = JDBCUtil.getInstance();
   
   public int signUp(List<Object> param) { // 회원가입에 필요한 쿼리문
      String sql = "INSERT INTO MEMBER "
            + " (MEM_NO, MEM_PASS, MEM_NAME, MEM_AGE, MEM_GENDER, "
            + "  MEM_JUMIN, MEM_TELNO, MEM_ADDRESS ) " //MEM_CARNO, MEM_LOCKER 생략
             + " VALUES "
            + " ( ?, ?, ?, ?, ?, ?, ?, ? )";
      return jdbc.update(sql,param);
   }
   
   public int Locker(List<Object> param1) { // 사물함 지정에 필요한 쿼리문
      String sql = "UPDATE LOCKER SET " 
            + " MEM_NO = ? "
            + " WHERE LOC_NO = ? ";
            
      return jdbc.update1(sql, param1);
   }
   
   /**
    *            연습 
    */
   
   public int MilGift(List<Object> param) { // 마일리지 업데이트 .. 
	      String sql = "UPDATE MILEAGE SET MIL_POINT = MIL_POINT - 500 WHERE MEM_NO = ?";
	            
	      return jdbc.update1(sql, param);
	   }
   
   
   
   
   
    /* 
    * 
    * 
    * 
    * 
    * 
    * 
    */
   
   public int Update_Car(List<Object> param) { // 주차권 발급에 필요한 쿼리문
      String sql = "UPDATE MEMBER SET " 
            + " MEM_CARNO = ?"
            + " WHERE MEM_NO = ?"; 
      return jdbc.update1(sql, param);
   }
   
   public int Update_Member_Pass(List<Object> param) { // 비밀번호 수정에 필요한 쿼리문
      String sql = "UPDATE MEMBER SET " 
            + "MEM_PASS = ?"
            + " WHERE MEM_NO = ?";
      return jdbc.update1(sql, param);
   }
   
   public int Update_Member_Name(List<Object> param) { // 이름 수정에 필요한 쿼리문
      String sql = "UPDATE MEMBER SET " 
            + "MEM_NAME = ?"
            + " WHERE MEM_NO = ?";
      return jdbc.update1(sql, param);
   }
   
   public int Update_Member_Telno(List<Object> param) { // 전화번호 수정에 필요한 쿼리문
      String sql = "UPDATE MEMBER SET " 
            + "MEM_TELNO = ?"
            + " WHERE MEM_NO = ?";
      return jdbc.update1(sql, param);
   }
   
   public int Update_Member_Address(List<Object> param) { // 주소 수정에 필요한 쿼리문
      String sql = "UPDATE MEMBER SET " 
            + "MEM_ADDRESS = ?"
            + " WHERE MEM_NO = ?";
      return jdbc.update1(sql, param);
   }
   
   public List<Object> memberLockerPicture() { // 사물함 목록 리스트를 불러오기 위한 쿼리문
      String sql = " SELECT MEM_NO FROM LOCKER ";
      return jdbc.load(sql);
   }

   public List<Object> employeeList_EE() { // 관장님 프로필 출력 쿼리문
      String sql = " SELECT E_NAME, E_JOB, E_AGE, E_GENDER, E_TELNO " 
                  + " FROM EMPLOYEE " 
                  + " WHERE E_NO = 'E011' ";
      return jdbc.load1(sql);
   }

   public List<Object> employeeList_SS() {   // 스피닝 강사 프로필 출력 쿼리문
      String sql = " SELECT E_NAME, E_JOB, E_AGE, E_GENDER, E_TELNO "
                  + " FROM EMPLOYEE "
                  + " WHERE E_NO = 'E005' OR E_NO = 'E006' ";
      return jdbc.load1(sql);
}
   
   public List<Object> employeeList_PP() {   // 필라테스 강사 프로필 출력 쿼리문
      String sql = " SELECT E_NAME, E_JOB, E_AGE, E_GENDER, E_TELNO "
               + " FROM EMPLOYEE "
               + " WHERE E_NO = 'E003' OR E_NO = 'E004' ";
      return jdbc.load1(sql);
   }
   
   public List<Object> employeeList_PT() {   // PT 강사 프로필 출력 쿼리문
      String sql = " SELECT E_NAME, E_JOB, E_AGE, E_GENDER, E_TELNO "
               + " FROM EMPLOYEE "
               + " WHERE E_NO = 'E007' OR E_NO = 'E008' ";
      return jdbc.load1(sql);
   }

   public int membershipUpdate(List<Object> list_E) { // 강사 선택후 membership에 업데이트 쿼리문
      String sql = "INSERT INTO MEMBERSHIP "
            + "(MS_NO, MS_KIND, MS_COUNT, MS_COST, MS_DATE, MS_EDATE, MEM_NO, E_NO) "
         + "VALUES "
         + "('MS' || LPAD(seq_membership.NEXTVAL,3,'0'), ?, ?, ?, SYSDATE, SYSDATE + INTERVAL '1' YEAR, ?, ?) ";
      return jdbc.update(sql, list_E);
   }

   public Map<String, Object> memberLogin(List<Object> param) { // 해당 member가 있는 조회하는 쿼리문
      String sql = "SELECT * FROM MEMBER "
            + "WHERE MEM_NO = ? AND MEM_PASS = ?" ;
   return jdbc.selectOne(sql, param);
}
  
   public HashMap<String, Object> myList(String memNo2) {   // 아이디에 따른 마이페이지 조회하는 쿼리문
      String sql = " SELECT  M.MEM_NAME, M.MEM_NO, M.MEM_CARNO, M.MEM_TELNO, M.MEM_ADDRESS, M.MEM_GENDER, "
                  + " M.MEM_AGE, M.MEM_JUMIN, M.MEM_PASS, L.LOC_NO "
                  + " FROM MEMBER M "
                  + " LEFT JOIN LOCKER L ON M.MEM_NO = L.MEM_NO "
                  + " WHERE M.MEM_NO = ? ";

   return (HashMap<String, Object>) jdbc.selectOne(sql, memNo2);

   }
   
   public void updateAttence(String memNo) { // 출석시 마일리지 +, 멤버쉽- 쿼리문
      String sql = "INSERT INTO ATTENDANCE(AT_NO, A_ENROLL, MEM_NO) "
           + "VALUES('AT' || LPAD((SEQ_ATTENDANCE.NEXTVAL), 3, '0'), SYSDATE, ?) ";
      jdbc.update2(sql, memNo);
   
}
   
   public HashMap<String, Object> membershipList(String memNo) { //멤버쉽 테이블 조회
         String sql = "SELECT MS_KIND, MS_DATE, MS_EDATE, MS_COUNT, MS_COST "
               + "FROM MEMBERSHIP "
               + "WHERE MEM_NO = ? ";
      return (HashMap<String, Object>) jdbc.selectOne1(sql, memNo);
         
}
   
   public Map<String, Object> employeeList_Mine(String memNo1) { // 1단계-본인에게 해당되는 트레이너 번호 찾기 
	     String sql = "SELECT E_NO "
	           + "FROM MEMBERSHIP "
	           + "WHERE MEM_NO= ?";

	      return jdbc.load2(sql, memNo1);
	}
   
   public List<Object> employeeList_Mine1(String trainnerNo) { // 2단계-트레이너 프로필 출력
     String sql = "SELECT E_NAME, E_JOB, E_AGE, E_GENDER, E_TELNO, E_CAREER "
           + "FROM EMPLOYEE "
           + "WHERE E_NO = ? ";

      return jdbc.load3(sql, trainnerNo); 
}
   
   public Map<String, Object> mileageList(String memNo_M) { //마일리지 리스트 출력
      String sql = "SELECT M.MEM_NAME, P.MIL_POINT "
                 + "FROM MEMBER M, MILEAGE P "
                +  "WHERE M.MEM_NO = P.MEM_NO "
                +  "AND P.MEM_NO = ? ";
   return (HashMap<String, Object>) jdbc.selectOne2(sql, memNo_M);
}
   
   public Map<String, Object> mileageModify(String memNoMo) { //마일리지 리스트 출력
	      String sql = "update mileage set mil_point = MIL_POINT - 500 where mem_no = ?";
	   return (HashMap<String, Object>) jdbc.selectOne3(sql, memNoMo);
	}
   
//   public Map<String, Object> mileageList1(List<String> memNo) { //나의 마일리지 잔액 조회
//      String sql = "SELECT MIL_POINT"
//            + "FROM MILEAGE "
//            + "WHERE MEM_NO = ? ";
//
//      return jdbc.getRowFromOracle(sql,memNo);
//}
   
   public List<Object> memcarnoCheck(String memNo_C) { // 1단계-본인에게 해당되는 차량번호 찾기
       String sql = "SELECT MEM_CARNO "
             + "FROM MEMBER "
             + "WHERE MEM_NO= ?";
        return jdbc.load4(sql, memNo_C);
  }
   

   public List<Object> currentLockerCheck(String memNo_LM) { //본인에게 해당되는 사물함 번호 찾기
	      String sql = "SELECT LOC_NO "
	              + "FROM LOCKER "
	              + "WHERE MEM_NO= ?";

	         return jdbc.load5(sql, memNo_LM); 
	} 
   
   public  void LockerRemove(List<Object> param) {
	   String sql = "UPDATE LOCKER "
			   + "SET MEM_NO = '' "
			  + "WHERE MEM_NO= ? ";
 
	jdbc.update3(sql, param);
}
   public List<Map<String, Object>> adminNoticeList() {
       String sql = "select * from notice";
       List<Map<String, Object>> result = jdbc.selectList(sql);

       return result;
   }
   
   public Map<String, Object> getNotice(List<Object> params) {
       String sql = "select * from NOTICE where NO_NO = ?";
       Map<String, Object> result = jdbc.selectOne11(sql, params);

       return result;
   }

}