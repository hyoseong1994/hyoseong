package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class LoginController implements Initializable{
	@FXML
	private Label lblLogin;
	@FXML
	private TextField txtId;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnLogin;
	@FXML
	private Button btnJoin;
	@FXML
	private ToggleGroup loginGroup;
	@FXML
	private RadioButton rbManager;
	@FXML
	private RadioButton rbStudent;
	@FXML
	private Label lblIconImg;
	@FXML
	private ImageView IconImg;
	public static String managerName = "";
	public static String studentId = "";
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		//아이디 입력에서 Enter키 이벤트 적용
		txtId.setOnKeyPressed(event -> handlerTxtIdKeyPressed(event));
		//패스워드 입력에서 Enter키 이벤트 적용
		txtPassword.setOnKeyPressed(event -> handlerTxtPasswordKeyPressed(event));
		btnJoin.setOnAction(event -> handlerBtnJoinAction(event));//관리자 등록창으로 전환
		btnLogin.setOnAction(event -> handlerBtnLoginAction(event));//로그인
		btnCancel.setOnAction(event -> handlerBtnCancelAction(event));//로그인창 닫기
		rbManager.setOnAction(event -> handlerRbManagerAction(event));
		rbStudent.setOnAction(event -> handlerRbStudentAction(event));
	}


	private Object handlerRbStudentAction(ActionEvent event) {
		// TODO Auto-generated method stub
		return null;
	}


	private Object handlerRbManagerAction(ActionEvent event) {
		// TODO Auto-generated method stub
		return null;
	}


	private Object handlerBtnCancelAction(ActionEvent event) {
		// TODO Auto-generated method stub
		return null;
	}


	private Object handlerBtnLoginAction(ActionEvent event) {
		// TODO Auto-generated method stub
		return null;
	}


	private Object handlerBtnJoinAction(ActionEvent event) {
		// TODO Auto-generated method stub
		return null;
	}


	private Object handlerTxtPasswordKeyPressed(KeyEvent event) {
		// TODO Auto-generated method stub
		return null;
	}


	private Object handlerTxtIdKeyPressed(KeyEvent event) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
