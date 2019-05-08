package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.StudentVO;
import model.SubjectVO;

public class SubjectTabController implements Initializable {
	// 관리자 명

	@FXML
	private Label lblManagerName;
	@FXML
	private TextField txtSubjectNum;
	@FXML
	private TextField txtSubjectName;
	@FXML
	private TableView<SubjectVO> SubjectTableView = new TableView<>();
	@FXML
	private Button btnInsert;//학과 등록
	@FXML
	private Button btnUpdate;//학과 수정
	@FXML
	private Button btnDelete;//학과 삭제
	@FXML
	private Button btnRead;//학과 읽기
	
	public static ObservableList<SubjectVO> subjectDataList = FXCollections.observableArrayList();
	ObservableList<SubjectVO> selectSubject = null;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void subjectTotalList() {
		// TODO Auto-generated method stub

	}

}
