package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class StudentTabController implements Initializable {

	//ÇÐ»ý µî·Ï ÅÇ
	
@FXML
private ComboBox<SubjectVO> cbx_subjectName;
@FXML
private TextField txtsd_num;
@FXML
private TextField txtsd_id;
@FXML
private PasswordField txtsd_passwd;
@FXML
private TextField txtsd_birthday;
@FXML
private TextField txtsd_phone;
@FXML
private TextField txtsd_address;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
