package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.StudentVO;
import model.TraineeVO;

public class TraineeController implements Initializable {
	// 메뉴
	@FXML
	private MenuItem menuExit;
	@FXML
	private MenuItem menuLogout;
	@FXML
	private MenuItem menuInfo;

	// 수강 신청 탭
	@FXML
	private TextField txtStudentName;
	@FXML
	private TextField txtStudentNum;
	@FXML
	private TextField txtSubjectName;
	@FXML
	private RadioButton rbMajor;
	@FXML
	private RadioButton rbMinor;
	@FXML
	private RadioButton rbCulture;
	@FXML
	private TextField txtSectionName;
	@FXML
	private Button btnTraineeInsert;
	@FXML
	private Button btnTraineeCancel;
	@FXML
	private Button btnTraineeExit;
	@FXML
	private TableView<TraineeVO> traineeTableView = new TableView<>();

	ObservableList<TraineeVO> traineeDataList = FXCollections.observableArrayList();
	ObservableList<TraineeVO> selectTrainee = null; // 테이블에서 선택한 정보 저장
	int selectedTraineeIndex; // 테이블에서 선택한 수강 신청 인덱스 저장

	String studentLoginId;// 로그인 아이디
	String l_num;// 과목 번호
	String t_section;// 과목 구분
	String sd_num;// 로그인한 학생의 학번

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			studentLoginId = LoginController.studentId;

			if (!studentLoginId.equals("")) {
				StudentVO sVo = new StudentVO();
				TraineeDAO tDao = new TraineeDAO();
				sVo = tDao.getStudentSubjectName(studentLoginId);

				txtStudentNum.setText(sVo.getSd_num());
				txtStudentName.setText(sVo.getSd_name());
				txtSubjectName.setText(sVo.getS_num());

				sd_num = txtStudentNum.getText().trim();

				btnTraineeCancel.setDisable(true);
				traineeTableView.setEditable(false);

				// 학생 정보 수정 금지
				txtStudentName.setEditable(false);
				txtStudentNum.setEditable(false);
				txtSubjectName.setEditable(false);
				txtSectionName.setEditable(false);

				// 수강 테이블 뷰 칼럼이름 설정
				TableColumn colNo = new TableColumn("NO.");
				colNo.setPrefWidth(50);
				colNo.setStyle("fx-allignment:CENTER");
				colNo.setCellValueFactory(new PropertyValueFactory<>("no"));

				TableColumn colSdNum = new TableColumn("학번");
				colSdNum.setPrefWidth(90);
				colSdNum.setStyle("-fx-allignment:CENTER");
				colSdNum.setCellValueFactory(new PropertyValueFactory<>("sd_num"));

				TableColumn colLNum = new TableColumn("과목명");
				colLNum.setPrefWidth(100);
				colLNum.setStyle("-fx-allignment:CENTER");
				colLNum.setCellValueFactory(new PropertyValueFactory<>("l_num"));

				TableColumn colTSection = new TableColumn("과목 구분");
				colTSection.setPrefWidth(100);
				colTSection.setStyle("-fx-allignment:CENTER");
				colTSection.setCellValueFactory(new PropertyValueFactory<>("t_Section"));

				TableColumn colTDate = new TableColumn("등록 날짜");
				colTDate.setPrefWidth(160);
				colTDate.setStyle("-fx-allignment:CENTER");
				colTDate.setCellValueFactory(new PropertyValueFactory<>("t_date"));

				traineeTableView.setItems(traineeDataList);
				traineeTableView.getColumns().addAll(colNo, colSdNum, colLNum, colTSection, colTDate);

				// 수강 전체 목록
				traineeTotalList();

				// 메뉴 이벤트 등록
				menuExit.setOnAction(event -> handlerMenuExitAction(event));
				menuLogout.setOnAction(event -> handlerMenuLogoutAction(event));
				menuInfo.setOnAction(event -> handlermenuInfoAction(event));
				// 수강 과목 선택 이벤트

				// 수강 등록, 삭제 이벤트 등록
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private Object handlermenuInfoAction(ActionEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object handlerMenuLogoutAction(ActionEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object handlerMenuExitAction(ActionEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	public void traineeTotalList() {
		// TODO Auto-generated method stub

	}

}
