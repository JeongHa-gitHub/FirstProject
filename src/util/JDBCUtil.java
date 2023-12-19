package util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCUtil {

   private static JDBCUtil instance = null;

   private JDBCUtil() {
   }

   public static JDBCUtil getInstance() {
      if (instance == null)
         instance = new JDBCUtil();
      return instance;
   }
   // 헤헷 172.30.1.8
   // 원래 192.168.35.120
   // 정하 핫스팟 192.168.92.68
   private final String URL = "jdbc:oracle:thin:@192.168.35.120:1521/xe";
   private final String USER = "hmsystem";
//   private final String USER = "HMSYSTEM_TEST";
   private final String PASSWORD = "java";

   private Connection conn = null;
   private ResultSet rs = null;
   private PreparedStatement ps = null;
   private Statement stmt = null;

   //확확인
   public int update(String sql, List<Object> param) { // 개인정보 업데이트
      int result = 0;
      try {
         conn = DriverManager.getConnection(URL, USER, PASSWORD);
         ps = conn.prepareStatement(sql);
         for (int i = 0; i < param.size(); i++) {
            ps.setObject(i + 1, param.get(i));
         }
         result = ps.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         if (rs != null)
            try {
               rs.close();
            } catch (Exception e) {
            }
         if (ps != null)
            try {
               ps.close();
            } catch (Exception e) {
            }
         if (conn != null)
            try {
               conn.close();
            } catch (Exception e) {
            }
      }
      return result;
   }

   public int update1(String sql, List<Object> param1) { // 사물함 업데이트
      int result = 0;
      try {
         conn = DriverManager.getConnection(URL, USER, PASSWORD);
         ps = conn.prepareStatement(sql);
         for (int i = 0; i < param1.size(); i++) {
            ps.setObject(i + 1, param1.get(i));
         }
         result = ps.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         if (rs != null)
            try {
               rs.close();
            } catch (Exception e) {
            }
         if (ps != null)
            try {
               ps.close();
            } catch (Exception e) {
            }
         if (conn != null)
            try {
               conn.close();
            } catch (Exception e) {
            }
      }
      return result;

   }

   public void update2(String sql, String memNo) {    // 출석시 마일리지 +, 멤버쉽- 업데이트

      try {
         conn = DriverManager.getConnection(URL, USER, PASSWORD);
         ps = conn.prepareStatement(sql);
         ps.setObject(1, memNo);
         ps.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         if (rs != null)
            try {
               rs.close();
            } catch (Exception e) {
            }
         if (ps != null)
            try {
               ps.close();
            } catch (Exception e) {
            }
         if (conn != null)
            try {
               conn.close();
            } catch (Exception e) {
            }
      }

   }

   public List<Object> load(String sql) { // 사물함 리스트 불러오기
      List<Object> param = new ArrayList<>();
      try {
         conn = DriverManager.getConnection(URL, USER, PASSWORD);
         stmt = conn.createStatement();
         rs = stmt.executeQuery(sql);

         while (rs.next()) {
            String list = rs.getNString("MEM_NO");
            param.add(list);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         if (rs != null)
            try {
               rs.close();
            } catch (Exception e) {
            }
         if (ps != null)
            try {
               ps.close();
            } catch (Exception e) {
            }
         if (conn != null)
            try {
               conn.close();
            } catch (Exception e) {
            }
      }
      return param;
   }

   public List<Object> load1(String sql) { // 선생님 리스트 불러오기
      List<Object> param = new ArrayList<>();
      try {
         conn = DriverManager.getConnection(URL, USER, PASSWORD);
         stmt = conn.createStatement();
         rs = stmt.executeQuery(sql);

         while (rs.next()) {
            String eName = rs.getNString("E_NAME");
            String eJob = rs.getNString("E_JOB");
            String eAge = rs.getNString("E_AGE");
            String eGender = rs.getNString("E_GENDER");
            String eTelno = rs.getNString("E_TELNO");
            param.add(eName);
            param.add(eJob);
            param.add(eAge);
            param.add(eGender);
            param.add(eTelno);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         if (rs != null)
            try {
               rs.close();
            } catch (Exception e) {
            }
         if (ps != null)
            try {
               ps.close();
            } catch (Exception e) {
            }
         if (conn != null)
            try {
               conn.close();
            } catch (Exception e) {
            }
      }
      return param;
   }

   public Map<String, Object> selectOne(String sql, List<Object> param) { // 로그인 정보 여부 찾기
      Map<String, Object> row = null;

      try {
         conn = DriverManager.getConnection(URL, USER, PASSWORD);
         ps = conn.prepareStatement(sql);
         for (int i = 0; i < param.size(); i++) {
            ps.setObject(i + 1, param.get(i));
         }
         rs = ps.executeQuery(); // 값들 넣기
         ResultSetMetaData rsmd = rs.getMetaData(); // 컬럼 넣기
         int columCount = rsmd.getColumnCount(); // 컬럼 갯수 넣기
         while (rs.next()) {
            row = new HashMap<>();
            for (int i = 1; i <= columCount; i++) {
               String key = rsmd.getColumnLabel(i);
               Object value = rs.getObject(i);
               row.put(key, value);
            }

         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         if (rs != null)
            try {
               rs.close();
            } catch (Exception e) {
            }
         if (ps != null)
            try {
               ps.close();
            } catch (Exception e) {
            }
         if (conn != null)
            try {
               conn.close();
            } catch (Exception e) {
            }
      }

      return row;
   }

   public Map<String, Object> selectOne(String sql, String memNo2) { // 마이페이지 리스트 불러오기
      Map<String, Object> row = null;

      try {
         conn = DriverManager.getConnection(URL, USER, PASSWORD);
         ps = conn.prepareStatement(sql);
         ps.setObject(1, memNo2);
         rs = ps.executeQuery(); // 값들 넣기
         ResultSetMetaData rsmd = rs.getMetaData(); // 컬럼 넣기
         int columCount = rsmd.getColumnCount(); // 컬럼 갯수 넣기
         while (rs.next()) {
            row = new HashMap<>();

            for (int i = 1; i <= columCount; i++) {
               String key = rsmd.getColumnLabel(i);
               Object value = rs.getObject(i);
               row.put(key, value);
            }

         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         if (rs != null)
            try {
               rs.close();
            } catch (Exception e) {
            }
         if (ps != null)
            try {
               ps.close();
            } catch (Exception e) {
            }
         if (conn != null)
            try {
               conn.close();
            } catch (Exception e) {
            }
      }

      return row;
   }

   public Map<String, Object> selectOne1(String sql, String memNo) { // 회원권 리스트 불러오기
      Map<String, Object> row = null;

      try {
         conn = DriverManager.getConnection(URL, USER, PASSWORD);
         ps = conn.prepareStatement(sql);
         ps.setObject(1, memNo);
         rs = ps.executeQuery(); // 값들 넣기
         ResultSetMetaData rsmd = rs.getMetaData(); // 컬럼 넣기
         int columCount = rsmd.getColumnCount(); // 컬럼 갯수 넣기
         while (rs.next()) {
            row = new HashMap<>();

            for (int i = 1; i <= columCount; i++) {
               String key = rsmd.getColumnLabel(i);
               Object value = rs.getObject(i);
               row.put(key, value);
            }

         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         if (rs != null)
            try {
               rs.close();
            } catch (Exception e) {
            }
         if (ps != null)
            try {
               ps.close();
            } catch (Exception e) {
            }
         if (conn != null)
            try {
               conn.close();
            } catch (Exception e) {
            }
      }

      return row;
   }

   public Map<String, Object> selectOne2(String sql, String memNo_M) { //마일리지 리스트 불러오기
      Map<String, Object> row = null;

      try {
         conn = DriverManager.getConnection(URL, USER, PASSWORD);
         ps = conn.prepareStatement(sql);
         ps.setObject(1, memNo_M);
         rs = ps.executeQuery(); // 값들 넣기
         ResultSetMetaData rsmd = rs.getMetaData(); // 컬럼 넣기
         int columCount = rsmd.getColumnCount(); // 컬럼 갯수 넣기
         while (rs.next()) {
            row = new HashMap<>();

            for (int i = 1; i <= columCount; i++) {
               String key = rsmd.getColumnLabel(i);
               Object value = rs.getObject(i);
               row.put(key, value);
            }

         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         if (rs != null)
            try {
               rs.close();
            } catch (Exception e) {
            }
         if (ps != null)
            try {
               ps.close();
            } catch (Exception e) {
            }
         if (conn != null)
            try {
               conn.close();
            } catch (Exception e) {
            }
      }

      return row;
   }
   
   public Map<String, Object> selectOne3(String sql, String memNoMo) { //마일리지 리스트 불러오기
	      Map<String, Object> row = null;

	      try {
	         conn = DriverManager.getConnection(URL, USER, PASSWORD);
	         ps = conn.prepareStatement(sql);
	         ps.setObject(1, memNoMo);
	         rs = ps.executeQuery(); // 값들 넣기
	         ResultSetMetaData rsmd = rs.getMetaData(); // 컬럼 넣기
	         int columCount = rsmd.getColumnCount(); // 컬럼 갯수 넣기
	         while (rs.next()) {
	            row = new HashMap<>();

	            for (int i = 1; i <= columCount; i++) {
	               String key = rsmd.getColumnLabel(i);
	               Object value = rs.getObject(i);
	               row.put(key, value);
	            }

	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         if (rs != null)
	            try {
	               rs.close();
	            } catch (Exception e) {
	            }
	         if (ps != null)
	            try {
	               ps.close();
	            } catch (Exception e) {
	            }
	         if (conn != null)
	            try {
	               conn.close();
	            } catch (Exception e) {
	            }
	      }

	      return row;
	   }
   
   public Map<String, Object> load2(String sql, String memNo1) {  // 1단계- 트레이너 프로필 불러오기
	      Map<String, Object> row = null;

	      try {
	            conn = DriverManager.getConnection(URL, USER, PASSWORD);
	            ps = conn.prepareStatement(sql);
	            ps.setObject(1, memNo1);
	            rs = ps.executeQuery(); // 값들 넣기
	            ResultSetMetaData rsmd = rs.getMetaData(); // 컬럼 넣기
	            int columCount = rsmd.getColumnCount(); // 컬럼 갯수 넣기
	            while (rs.next()) {
	               row = new HashMap<>();

	               for (int i = 1; i <= columCount; i++) {
	                  String key = rsmd.getColumnLabel(i);
	                  Object value = rs.getObject(i);
	                  row.put(key, value);
	               }

	            }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         if (rs != null)
	            try {
	               rs.close();
	            } catch (Exception e) {
	            }
	         if (ps != null)
	            try {
	               ps.close();
	            } catch (Exception e) {
	            }
	         if (conn != null)
	            try {
	               conn.close();
	            } catch (Exception e) {
	            }
	      }
	      return row;
	      
	   }
   
   public List<Object> load3(String sql, String trainnerNo) { // 2단계 끝- 트레이너 프로필 불러오기
      List<Object> param = new ArrayList<>();

      try {
         conn = DriverManager.getConnection(URL, USER, PASSWORD);
         ps = conn.prepareStatement(sql);
         ps.setObject(1, trainnerNo);
         rs = ps.executeQuery();

         while (rs.next()) {
            String eName = rs.getNString("E_NAME");
            String eJob = rs.getNString("E_JOB");
            String eAge = rs.getNString("E_AGE");
            String eGender = rs.getNString("E_GENDER");
            String eTelno = rs.getNString("E_TELNO");
            String ecareer = rs.getNString("E_CAREER");
            
            param.add(eName);
            param.add(eJob);
            param.add(eAge);
            param.add(eGender);
            param.add(eTelno);
            param.add(ecareer);
         }
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         if (rs != null)
            try {
               rs.close();
            } catch (Exception e) {
            }
         if (ps != null)
            try {
               ps.close();
            } catch (Exception e) {
            }
         if (conn != null)
            try {
               conn.close();
            } catch (Exception e) {
            }
      }
      
      return param;
      
   }
   
//************************************************************************************************************//
   
   // -> 공통 준비물: rs, ps, conn를 닫기 위한 메소드
   public void closeConnection(ResultSet rs, PreparedStatement pstmt, Connection conn) {
           try {
               rs.close();
               pstmt.close();
               conn.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
   
   // sql문을 통해 특정 데이터 1개를 조회할 때 사용
   public Map<String, Object> getRowFromOracle(String sql, List<String> memNo) {
        Map<String, Object> row = new HashMap<>();
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < memNo.size(); i++) {
                ps.setObject(i + 1, memNo.get(i));
            }
            rs = ps.executeQuery(); // 실행하고자 하는 sql문과 매개변수 값으로 결과값을 도출한다
                              // MEM_NO | MEM_NAME | MEM_ADDRESS
                              // M005     | 김정하    | 충청북도 청주시 상당구...

            row = saveRowData(row, rs);  // 새로운 보따리와 출력 값들을 들고 간다 --> 리턴된 Map을 row가 참조한다. 즉 row에 Map을 저장했다
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(rs, ps, conn);
        }
        return row; // row를 들고 호출한 곳으로 간다.
    }
   
   // ㄴ> 준비물: 출력한 데이터 값을 Map에 넣어준다. (key값과 value)
   public Map<String, Object> saveRowData(Map<String, Object> row, ResultSet rs) {
        try {
            ResultSetMetaData rsmd = rs.getMetaData(); // 컬럼 값만 도출한다  rsmd == 컬럼 값들
            int columnCount = rsmd.getColumnCount();   // 컬럼 값의 갯수를 도출한다  columCount == 컬럼 갯수
            while (rs.next()) {                   // 다음 줄이 없을때까지 데이터를 집어 넣는다
                for (int i = 0; i < columnCount; i++) {
                    String key = rsmd.getColumnLabel(i + 1); // i+1번의 컬럼값을 key에 넣는다 - *sql는 첫번째 자리가 1이다
                    //or   String key=rsmd.getColumnName(i);
                    Object value = rs.getObject(i + 1); // i+1번의 데이터 값을 value에 넣는다
                    row.put(key, value);            // 다음 줄이 없을때까지 데이터를 집어 넣는다
                }
            }
        } catch (SQLException e) {                  // 예외가 발생한다면
            e.printStackTrace();                  // 그 예외를 프린트해라 
        }
        return row;                              // (key와 value)데이터가 완벽하게 담긴 Map을 들고 호출한 곳에 간다
    }
   //++===========================================================================================================
   public List<Object> load4(String sql, String memNo_C) {  // 본인에게 해당되는 차량번호 찾기
       List<Object> param = new ArrayList<>();
     
       try {
          conn = DriverManager.getConnection(URL, USER, PASSWORD);
          ps = conn.prepareStatement(sql);
          ps.setObject(1, memNo_C);
          rs = ps.executeQuery();
        
          while (rs.next()) {
             String list = rs.getNString("MEM_CARNO");
             param.add(list);
          }
      
          
       } catch (SQLException e) {
          e.printStackTrace();
       } finally {
          if (rs != null)
             try {
                rs.close();
             } catch (Exception e) {
             }
          if (ps != null)
             try {
                ps.close();
             } catch (Exception e) {
             }
          if (conn != null)
             try {
                conn.close();
             } catch (Exception e) {
             }
       }
       return param;
       
    }
   
   public List<Object> load5(String sql, String memNo_LM) {
	      List<Object> param = new ArrayList<>();

	         try {
	            conn = DriverManager.getConnection(URL, USER, PASSWORD);
	            ps = conn.prepareStatement(sql);
	            ps.setObject(1, memNo_LM);
	            rs = ps.executeQuery();

	            while (rs.next()) {
	               String list = rs.getNString("LOC_NO");
	               param.add(list);
	            }
	            
	         } catch (SQLException e) {
	            e.printStackTrace();
	         } finally {
	            if (rs != null)
	               try {
	                  rs.close();
	               } catch (Exception e) {
	               }
	            if (ps != null)
	               try {
	                  ps.close();
	               } catch (Exception e) {
	               }
	            if (conn != null)
	               try {
	                  conn.close();
	               } catch (Exception e) {
	               }
	         }
	         return param;
	         
	}

   
   public void update3(String sql, List<Object> param) {
	   try {
	         conn = DriverManager.getConnection(URL, USER, PASSWORD);
	         ps = conn.prepareStatement(sql);
	         for (int i = 0; i < param.size(); i++) {
	            ps.setObject(i + 1, param.get(i));
	         }
	        ps.executeUpdate();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         if (rs != null)
	            try {
	               rs.close();
	            } catch (Exception e) {
	            }
	         if (ps != null)
	            try {
	               ps.close();
	            } catch (Exception e) {
	            }
	         if (conn != null)
	            try {
	               conn.close();
	            } catch (Exception e) {
	            }
	      }
}
   
   public List<Map<String, Object>> selectList(String sql){
		// sql => "SELECT * FROM MEMBER"
		// sql => "SELECT * FROM JAVA_BOARD"
		// sql => "SELECT * FROM JAVA_BOARD WHERE BOARD_NUM > 10" 값이 다 있다.. ? x  ? > .param 
		List<Map<String, Object>> result = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while(rs.next()) {
				if(result == null) result = new ArrayList<>();
				Map<String, Object> row = new HashMap<>();
				for(int i = 1; i <= columnCount; i++) {
					String key = rsmd.getColumnLabel(i);
					Object value = rs.getObject(i);
					row.put(key, value);
				}
				result.add(row);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try { rs.close(); } catch(Exception e) {}
			if(ps != null) try { ps.close(); } catch(Exception e) {}
			if(conn != null) try { conn.close(); } catch(Exception e) {}
		}
		return result;
	}
   
   public Map<String, Object> selectOne11(String sql, List<Object> param){
		// sql => "SELECT * FROM JAVA_BOARD WHERE BOARD_NUMBER=?"
		// param => [1]
		//
		// sql => "SELECT * FROM JAVA_BOARD WHERE WRITER=? AND TITLE=?"
		// param => ["홍길동", "안녕"]
		Map<String, Object> row = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(sql);
			for(int i = 0; i < param.size(); i++) {
				ps.setObject(i + 1, param.get(i));
			}
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while(rs.next()) {
				row = new HashMap<>();
				for(int i = 1; i <= columnCount; i++) {
					String key = rsmd.getColumnLabel(i);
					Object value = rs.getObject(i);
					row.put(key,value);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {  rs.close();  } catch (Exception e) { }
			if(ps != null) try {  ps.close();  } catch (Exception e) { }
			if(conn != null) try { conn.close(); } catch (Exception e) { }
		}
		
		return row;
	}
   
   
}