package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil01;

public class AdminDao {
    private static AdminDao instance=null;
    private AdminDao() {}
    public static AdminDao getInstance() {
        if(instance==null)instance=new AdminDao();
        return instance;
    }

    private JDBCUtil01 jdbc = JDBCUtil01.getInstance();

    public Map<String, Object> adminLogin(List<Object> params) {
        String sql = "select * from employee where E_NO = ? and E_PASS = ?";
        Map<String, Object> result = jdbc.selectOne(sql, params);

        return result;
    }
    
    public Map<String, Object> adminDelete(List<Object> params) {
        String sql = "select * from SITE_ITEM where I_ITEMNO = ?";
        Map<String, Object> result = jdbc.selectOne(sql, params);

        return result;
    }

    
//게시판 리스트 보여주는 DAO..
    public List<Map<String, Object>> adminNoticeList() {
        String sql = "select * from notice";
        List<Map<String, Object>> result = jdbc.selectList(sql);

        return result;
    }
    
//자재 리스트 보여주는 DAO..
    
    public List<Map<String, Object>> adminItemList() {
        String sql = "select * from SITE_ITEM";
        List<Map<String, Object>> result = jdbc.selectList(sql);

        return result;
    }
    
    // 매출 리스트 보여주는 DAO
    public List<Map<String, Object>> adminCostList() {
        String sql = "SELECT MS_KIND, SUM(MS_COST) MS_COST FROM MEMBERSHIP GROUP BY MS_KIND";
        List<Map<String, Object>> result = jdbc.selectList(sql);

        return result;
    }
    

  //=============================================================================================================
    //공지사항 불러오기  	
    public Map<String, Object> getNotice(List<Object> params) {
        String sql = "select * from NOTICE where NO_NO = ?";
        Map<String, Object> result = jdbc.selectOne(sql, params);

        return result;
    }
    
    // ITEMNO.. 
    public Map<String, Object> getItem(List<Object> params) {
//        String sql = "select * from SITE_ITEM where I_ITEMNO = ?";
        String sql = "SELECT I_ITEMNO, I_ITEMNAME, I_AMOUNT, I_PRICE, I_BUYDATE," 
        		+ "NVL2(I_MAINTAIN,I_MAINTAIN,'') I_MAINTAIN, I_TELNO, E_NO,"
        		+ "NVL2(I_REMARK,I_REMARK,'') I_REMARK "
        		+ "FROM SITE_ITEM "
        		+ "WHERE I_ITEMNO = ? ";
        Map<String, Object> result = jdbc.selectOne(sql, params);

        return result;
    }
    
    //월매출
    public List<Map<String, Object>> getCostMonth(List<Object> params) {
    	String sql = "SELECT MS_KIND, " + 
        		"SUM(MS_COST) AS TOTAL_SALES " + 
        		"FROM MEMBERSHIP " + 
        		" WHERE EXTRACT(MONTH FROM MS_DATE) = ? " + 
        		"GROUP BY MS_KIND " + 
        		"ORDER BY MS_KIND ";
    	
    	List<Map<String, Object>> result = jdbc.selectList(sql, params);

        return result;
    }
         
    //월매출 합계
    public List<Map<String, Object>> adminCostMonthTotal(List<Object> params) {
    	String sql = "SELECT SUM(TOTAL_SALES) AS GRAND_TOTAL "
    			+ "FROM (SELECT MS_KIND, SUM(MS_COST) AS TOTAL_SALES FROM MEMBERSHIP "
    			+ "WHERE EXTRACT(MONTH FROM MS_DATE) = ? GROUP BY MS_KIND) subquery";
    	
    	List<Map<String, Object>> result = jdbc.selectList(sql, params);

        return result;
    }
    
    //연매출
    public List<Map<String, Object>> adminCostYears(List<Object> params) {
    	String sql = "SELECT SUM(MS_COST) AS TOTAL_SALES FROM MEMBERSHIP WHERE EXTRACT(YEAR FROM MS_DATE) = ? GROUP BY EXTRACT(YEAR FROM MS_DATE) ORDER BY EXTRACT(YEAR FROM MS_DATE)";
    	
    	List<Map<String, Object>> result = jdbc.selectList(sql, params);

        return result;
    }
    
  //=============================================================================================================
    
    
    
//DB는 순서.
    public int adminNoticeWrite(List<Object> params) {
        String sql = "insert into notice(no_no,no_title,no_content, e_no) values ('NO' || LPAD(SEQ_NOTICE.NEXTVAL,3,'0'), ?, ?, ?)";
        int result = jdbc.update(sql, params);

        return result;
    }
    
    public int adminItemWrite(List<Object> params) {
    	String sql = "insert into SITE_ITEM(I_ITEMNO,I_ITEMNAME,I_AMOUNT,"
    			+ " I_PRICE,I_BUYDATE,I_MAINTAIN,I_TELNO,E_NO,I_REMARK) "
    			+ "values ('NO' || LPAD(SEQ_ITEM.NEXTVAL,3,'0'), ? , ? , ? , SYSDATE , ? , ? , ? , ?)"; //구매날짜... 빼기
        int result = jdbc.update(sql, params);

        return result;
    }
    
    public int adminNoticeModify(List<Object> params) {
        String sql = "update notice set no_title = ?, no_content= ? where no_no = ?";
        int result = jdbc.update(sql, params);

        return result;
    }
    
    public int adminItemModify(List<Object> params) {
        String sql = "update SITE_ITEM set I_AMOUNT = ?, I_PRICE= ?, I_TELNO=?, E_NO=?, I_REMARK=?"
        		+ " where I_ITEMNO = ?";
        int result = jdbc.update(sql, params); //네임부터 마지막no

        return result;
    }
    
    
  //=============================================================================================================
    // delete문 모아두기 (음.. input만 갖고와서 지워버리기..)
    
    //NOTICE 지우기 INPUT으로 NO_NO 갖고오기..
    public int deleteNotice(String input) {
		String sql = "DELETE FROM NOTICE WHERE NO_NO ='" + input + "'";
		return jdbc.update(sql);
	}
    
    //ITEM 지우기 
    public int deleteItem(String input) {
		String sql = "DELETE FROM SITE_ITEM WHERE I_ITEMNO ='" + input + "'";
		return jdbc.update(sql);
	}
    
    //=============================================================================================================
    
    
    //select인데 ? 포함해서 갖고오기
 
//    Map<String, Object> result = jdbc.selectOne(sql, params);
    
    public List<Map<String, Object>> adminCostMonth(List<Object> param) {
        String sql = "SELECT MS_KIND," + 
        		"SUM(MS_COST) MS_COST AS TOTAL_SALES" + 
        		"FROM MEMBERSHIP" + 
        		"WHERE EXTRACT(MONTH FROM MS_DATE) = ?" + 
        		"GROUP BY MS_KIND" + 
        		"ORDER BY MS_KIND";
        
        List<Map<String, Object>> result = jdbc.selectList(sql, param); //네임부터 마지막no

        return result;
    }
    		
    
  //=============================================================================================================
    
    
    /*걍 마지막 최후의 수단*/
    public int noList(List<Object> param1) {
		String sql = "INSERT INTO NOTICE "
				+ " (NO_NO, NO_TITLE, NO_CONTENT, E_NO) "
				+ " VALUES "
				+ " ('NO' || LPAD(SEQ_NOTICE.NEXTVAL,3,'0'), ?, ?, ?) ";
		return jdbc.update(sql, param1);

}
    
    
    
    }
