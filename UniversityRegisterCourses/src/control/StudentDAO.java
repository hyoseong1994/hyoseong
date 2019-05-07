package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.StudentVO;

public class StudentDAO {
	// 로그인 학생이름

	public String getLoginName(String loginId) throws Exception {
		String sql = "select sd_name from student where sd_id=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String loginName = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				loginName = rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
		return loginName;
	}

	// 학생 로그인
	public boolean getLogin(String loginId, String loginPassword) throws Exception {
		String sql = "select * from student where sd_id=? and sd_passwd = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean loginResult = false;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginId); // 첫번째 들어오는건 ID
			pstmt.setString(2, loginPassword); // 두번째 들어오는거 P/W
			rs = pstmt.executeQuery();
			if (rs.next()) {
				loginResult = true; // 아이디와 패스워드 일치한다
			}
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 데이터 베이스와의 연결에 사용되었던 오브젝트를 해제
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
		return loginResult;
	}

	// 학생 전체 목록
	public ArrayList<StudentVO> getStudentTotalList() throws Exception {
		ArrayList<StudentVO> list = new ArrayList<>();

		String sql = "select st.no as no,sd_num , sd_name , sd_id, sd_passwd, su.s_name as s_num, "
				+ "sd_birthday,sd_phone,sd_address ,sd_email , sd_date" + "from STUDENT st, SUBECT su"
				+ "where st.s_num=su.s_num" + "order by no";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StudentVO sVo = null;
		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				sVo = new StudentVO();
				sVo.setNo(rs.getInt("no"));
				sVo.setSd_num(rs.getString("sd_num"));
				sVo.setSd_name(rs.getString("sd_name"));
				sVo.setSd_id(rs.getString("sd_id"));
				sVo.setSd_passwd(rs.getString("sd_passwd"));
				sVo.setS_num(rs.getString("s_num"));
				sVo.setSd_birthday(rs.getString("sd_birthday"));
				sVo.setSd_phone(rs.getString("sd_phone"));
				sVo.setSd_address(rs.getString("sd_address"));
				sVo.setSd_email(rs.getString("sd_email"));
				sVo.setSd_date(rs.getDate("sd_date") + "");

				list.add(sVo);

			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {

			}
		}
		return list;
	}

	// 동일 학과 학생 일련번호
	public String getStudentCount(String subejectNum) throws Exception {
		String sql = "select LPAD(count(*)+1,4,'0') as studentCount from student where s_num=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String serialNumber = "";
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, subejectNum);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				serialNumber = rs.getString("studentCount");
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {

			}
		}
		return serialNumber;

	}
//학생 아이디 중복 체크

}
