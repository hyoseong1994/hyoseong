package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.JoinVO;

public class JoinDAO {
	public boolean getManagerRegiste(JoinVO jvo)throws Exception{
		
		
		String sql = "insert into managerjoin"+"(id,password,name)"+"values"+"(?,?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean joinSucess = false;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, jvo.getId());
			pstmt.setString(2, jvo.getPassword());
			pstmt.setString(3, jvo.getName());
			
			int i = pstmt.executeUpdate();
			
			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("������ ���");
				alert.setHeaderText(jvo.getName()+"������ ��� �Ϸ�");
				alert.setContentText("������ ��� ����!!!");
				alert.showAndWait();
				joinSucess = true;
			}else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("������ ���");
				alert.setHeaderText("������ ��� ����");
				alert.setContentText("������ ��� ����!!!");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("e=["+e+"]");
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("e=["+e+"]");
		}finally {
			try {
				//6 ������ ���̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if(pstmt != null) {
					pstmt.close();
				}if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
			}
		}
		return joinSucess;
	}
	
	//���̵� �ߺ� üũ
	public boolean getidOverlap(String idOverlap) throws Exception {

		String sql = "select*from managerjoin where id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean joinSucess = false;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, idOverlap);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				idOverlapResult = true; //�ߺ��� ���̵� �ִ�.
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("e=["+e+"]");
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("e=["+e+"]");
		}finally {
			try {
				//6 ������ ���̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if (rs != null) {
					rs.close();
				}if(pstmt != null) {
					pstmt.close();
				}if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
			}
		}
		return idOverlapResult;
	}
}
