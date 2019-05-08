package control;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javax.jws.HandlerChain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.StudentVO;
import model.SubjectVO;

public class StudentTabController implements Initializable {

	// 학생 등록 탭

	@FXML
	private ComboBox<SubjectVO> cbx_subjectName;
	@FXML
	private TextField txtsd_num;
	@FXML
	private TextField txtsd_name; // 이름
	@FXML
	private TextField txtsd_id; // 아이디
	@FXML
	private PasswordField txtsd_passwd; // 암호
	@FXML
	private TextField txtsd_birthday; // 생년월일
	@FXML
	private TextField txtsd_phone; // 휴대폰번호
	@FXML
	private TextField txtsd_address; // 주소
	@FXML
	private TextField txtsd_email; // 이메일
	@FXML
	private Button btnIdCheck; // 아이디체크
	@FXML
	private Button btnStudentInsert; // 학생등록
	@FXML
	private Button btnStudentUpdate; // 학생정보수정
	@FXML
	private Button btnStudentInit; // 초기화
	@FXML
	private Button btnStudentTatoList; // 학생 전체 목록
	@FXML
	private TableView<StudentVO> studentTableView = new TableView<>();

	ObservableList<StudentVO> studentDataList = FXCollections.observableArrayList();
	ObservableList<StudentVO> selectStudent = null; // 학생등록 테이블에서 선택한 정보 저장
	int selectedStudentIndex; // 학생등록 탭에서 선택한 학과 정보 인덱스 저장
	String studentNumber = "";
	private String selectSubjectNum;; // 선택한 학과명의 학과코드

	public void initialize(URL location, ResourceBundle resources) {
		try {
			// 학생등록 초기화
			btnStudentInsert.setDisable(true);
			btnStudentUpdate.setDisable(true);
			btnStudentInit.setDisable(true);
			studentTableView.setEditable(false);

			// 학번 수정 금지
			txtsd_num.setEditable(false);

			// 학생 테이블 뷰 컬럼이름 설정
			@SuppressWarnings("rawtypes")
			TableColumn colStudentNo = new TableColumn("NO.");
			colStudentNo.setPrefWidth(30);
			colStudentNo.setStyle("fx-allignment:CENTER");
			colStudentNo.setCellValueFactory(new PropertyValueFactory<>("no"));

			TableColumn colStudentNum = new TableColumn("학번");
			colStudentNum.setPrefWidth(70);
			colStudentNum.setStyle("-fx-allignment:CENTER");
			colStudentNum.setCellValueFactory(new PropertyValueFactory<>("sd_num"));

			TableColumn colStudentName = new TableColumn("이름");
			colStudentName.setPrefWidth(80);
			colStudentName.setStyle("-fx-allignment:CENTER");
			colStudentName.setCellValueFactory(new PropertyValueFactory<>("sd_name"));

			TableColumn colStudentId = new TableColumn("아이디");
			colStudentId.setPrefWidth(80);
			colStudentId.setStyle("-fx-allignment:CENTER");
			colStudentId.setCellValueFactory(new PropertyValueFactory<>("sd_id"));

			TableColumn colStudentPassword = new TableColumn("비밀번호");
			colStudentPassword.setPrefWidth(80);
			colStudentPassword.setStyle("-fx-allignment:CENTER");
			colStudentPassword.setCellValueFactory(new PropertyValueFactory<>("sd_passwd"));

			TableColumn colSubjectNum = new TableColumn("학과명");
			colSubjectNum.setPrefWidth(70);
			colSubjectNum.setStyle("-fx-allignment:CENTER");
			colSubjectNum.setCellValueFactory(new PropertyValueFactory<>("s_num"));

			TableColumn ColStudentBirthday = new TableColumn("생년월일");
			ColStudentBirthday.setPrefWidth(80);
			ColStudentBirthday.setStyle("-fx-allignment:CENTER");
			ColStudentBirthday.setCellValueFactory(new PropertyValueFactory<>("sd_birthday"));

			TableColumn ColStudentPhone = new TableColumn("연락처");
			ColStudentPhone.setPrefWidth(80);
			ColStudentPhone.setStyle("-fx-allignment:CENTER");
			ColStudentPhone.setCellValueFactory(new PropertyValueFactory<>("sd_phone"));

			TableColumn ColStudentAddress = new TableColumn("주소");
			ColStudentAddress.setPrefWidth(150);
			ColStudentAddress.setStyle("-fx-allignment:CENTER");
			ColStudentAddress.setCellValueFactory(new PropertyValueFactory<>("sd_address"));

			TableColumn ColStudentEmail = new TableColumn("이메일");
			ColStudentEmail.setPrefWidth(80);
			ColStudentEmail.setStyle("-fx-allignment:CENTER");
			ColStudentEmail.setCellValueFactory(new PropertyValueFactory<>("sd_email"));

			TableColumn colStudentDate = new TableColumn("등록일");
			colStudentDate.setPrefWidth(80);
			colStudentDate.setStyle("-fx-allignment:CENTER");
			colStudentDate.setCellValueFactory(new PropertyValueFactory<>("sd_date"));

			studentTableView.setItems(studentDataList);
			studentTableView.getColumns().addAll(colStudentNo, colStudentNum, colStudentName, colStudentId,
					colStudentPassword, colSubjectNum, ColStudentBirthday, ColStudentPhone, ColStudentAddress,
					ColStudentEmail, colStudentDate);

			// 학생 전체 목록

			studentTotalList();

			// 추가된 학과명 호출
			// addSubjectName();

			btnStudentInsert.setOnAction(event -> handlerBtnStudentInsertAction(event));
			cbx_subjectName.setOnAction(event -> HandlerCbx_subjectNameAction(event));
			btnIdCheck.setOnAction(event -> handlerBtnIdCheckActio(event));
			studentTableView.setOnMouseClicked(event -> handlerStudentTableViewAction(event));
			btnStudentUpdate.setOnAction(event -> handlerBtnStudentUpdateAction(event));
			btnStudentInit.setOnAction(event -> handlerBtnStudentInitAction(event));
			btnStudentTatoList.setOnAction(event -> handlerBtnStudentTatoListAction(event));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addSubjectName() throws Exception {
		StudentDAO sDao = new StudentDAO();
		ArrayList subjetNameList = new ArrayList<>();
		subjetNameList = sDao.subjectTotalList();
		// 학생 등록 탭 학과 번호 콤보 값 설정
		cbx_subjectName.setItems(FXCollections.observableArrayList(subjetNameList));

	}

	// 학생 전체 목록 이벤트

	public void handlerBtnStudentTatoListAction(ActionEvent event) {
		try {
			studentDataList.removeAll(studentDataList);
			studentTotalList();
		} catch (Exception e) {
			e.printStackTrace(); // 에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력한다.
		}
	}

	// 학생 초기화 이벤트
	public void handlerBtnStudentInitAction(ActionEvent event) {
		try {
			studentDataList.removeAll(studentDataList);
			studentTotalList();

			txtsd_num.clear();
			txtsd_name.clear();
			txtsd_id.clear();
			txtsd_passwd.clear();
			txtsd_birthday.clear();
			txtsd_phone.clear();
			txtsd_address.clear();
			txtsd_email.clear();

			txtsd_num.setEditable(true);
			txtsd_name.setEditable(true);
			txtsd_id.setEditable(true);

			btnIdCheck.setDisable(false);
			cbx_subjectName.setDisable(false);
			btnStudentUpdate.setDisable(true);
			btnStudentInit.setDisable(true);
			btnStudentInsert.setDisable(true);

		} catch (Exception e) {
			e.printStackTrace(); // 에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력한다.
		}

	}

	// 학생 정보 수정 이벤트
	public void handlerBtnStudentUpdateAction(ActionEvent event) {
		try {
			boolean sucess;

			StudentDAO sdao = new StudentDAO();
			sucess = sdao.getStudentUpdate(selectedStudentIndex, txtsd_passwd.getText().trim(),
					txtsd_birthday.getText().trim(), txtsd_phone.getText().trim(), txtsd_address.getText().trim(),
					txtsd_email.getText().trim());
			if (sucess) {
				studentDataList.removeAll(studentDataList);
				studentTotalList();

				txtsd_num.clear();
				txtsd_name.clear();
				txtsd_id.clear();
				txtsd_passwd.clear();
				txtsd_birthday.clear();
				txtsd_phone.clear();
				txtsd_address.clear();
				txtsd_email.clear();

				txtsd_num.setEditable(true);
				txtsd_name.setEditable(true);
				txtsd_id.setEditable(true);

				btnIdCheck.setDisable(false);
				cbx_subjectName.setDisable(false);
				btnStudentUpdate.setDisable(true);
				btnStudentInit.setDisable(true);
				btnStudentInsert.setDisable(true);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 학생 테이블 뷰 더블 클릭 이벤트 핸들러
	public void handlerStudentTableViewAction(MouseEvent event) {
		if (event.getClickCount() == 2) {
			try {
				selectStudent = studentTableView.getSelectionModel().getSelectedItems();
				selectedStudentIndex = selectStudent.get(0).getNo();
				String selectedSd_num = selectStudent.get(0).getSd_num();
				String selectedSd_name = selectStudent.get(0).getSd_name();
				String selectedSd_id = selectStudent.get(0).getSd_id();
				String selectedSd_passwd = selectStudent.get(0).getSd_passwd();
				String selectedSd_birthday = selectStudent.get(0).getSd_birthday();
				String selectedSd_phone = selectStudent.get(0).getSd_phone();
				String selectedSd_address = selectStudent.get(0).getSd_address();
				String selectedSd_email = selectStudent.get(0).getSd_email();

				txtsd_num.setText(selectedSd_num);
				txtsd_name.setText(selectedSd_name);
				txtsd_id.setText(selectedSd_id);
				txtsd_passwd.setText(selectedSd_passwd);
				txtsd_birthday.setText(selectedSd_birthday);
				txtsd_phone.setText(selectedSd_phone);
				txtsd_address.setText(selectedSd_address);
				txtsd_email.setText(selectedSd_email);

				txtsd_num.setEditable(false);
				txtsd_name.setEditable(false);
				txtsd_id.setEditable(false);

				btnIdCheck.setDisable(true);
				cbx_subjectName.setDisable(true);
				btnStudentUpdate.setDisable(false);
				btnStudentInit.setDisable(false);
				btnStudentInsert.setDisable(true);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 학생 아이디 중복 체크
	public void handlerBtnIdCheckActio(ActionEvent event) {
		btnStudentInsert.setDisable(false);
		btnIdCheck.setDisable(true);

		StudentDAO sDao = null;

		String searchId = "";
		boolean searchResult = true;

		try {
			searchId = txtsd_id.getText().trim();
			sDao = new StudentDAO();
			searchResult = (boolean) sDao.getStudentIdOverlap(searchId);
			if (!searchResult && !searchId.equals("")) {
				txtsd_id.setDisable(true);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("아이디 중 복 검사");
				alert.setHeaderText(searchId + "를 사용할 수 있습니다.");
				alert.setContentText("패스워드를 입력하세요");
				alert.showAndWait();

				btnStudentInsert.setDisable(false);
				btnIdCheck.setDisable(true);

			} else if (searchId.equals("")) {
				btnStudentInsert.setDisable(true);
				btnIdCheck.setDisable(false);
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("아이디 중복 검색");
				alert.setHeaderText("아이디를 입력하시오");
				alert.setContentText("등록할 아이디를 입력하세요");
				alert.showAndWait();
			} else {
				btnStudentInsert.setDisable(true);
				btnIdCheck.setDisable(false);
				txtsd_id.clear();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("아이디 중복 검사");
				alert.setHeaderText(searchId + "를 사용할 수 없습니다");
				alert.setContentText("아이디를 다른것으로 입력하세요");
				alert.showAndWait();

				txtsd_id.requestFocus();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("아이디 중복 검사 오류");
			alert.setHeaderText("아이디 중복 검사에 오류가 발생하였습니다.");
			alert.setContentText("다시하세요");
			alert.showAndWait();
		}

	}

	// 학생 등록 탭의 학과 선택 이벤트 핸들러
	public void HandlerCbx_subjectNameAction(ActionEvent event) {
		SubjectDAO sudao = new SubjectDAO();
		StudentDAO sdao = new StudentDAO();
		String serialNumber = ""; // 일련번호
		String sdYear = "";

		try {
			selectSubjectNum = sudao.getSubjectNum(cbx_subjectName.getSelectionModel().getSelectedItem() + "");

			// 학번은 8자리로 구성한다 (연도 2자리 +학과2자리 + 일련번호 4자리 - 예로06010001)
			SimpleDateFormat sdf = new SimpleDateFormat("yy");
			sdYear = sdf.format(new Date());

			serialNumber = sdao.getStudentCount(selectSubjectNum);

		} catch (Exception e) {
			e.printStackTrace();
		}
		studentNumber = sdYear + selectSubjectNum + serialNumber;
		txtsd_num.setText(studentNumber);

	}

	// 학생 등록 이벤트 핸들러
	public void handlerBtnStudentInsertAction(ActionEvent event) {
		try {
			studentDataList.removeAll(studentDataList);

			StudentVO svo = null;

			StudentDAO sdao = null;

			svo = new StudentVO(txtsd_num.getText().trim(), txtsd_name.getText().trim(), txtsd_id.getText().trim(),
					txtsd_passwd.getText().trim(), selectSubjectNum, txtsd_birthday.getText().trim(),
					txtsd_phone.getText().trim(), txtsd_address.getText().trim(), txtsd_email.getText().trim());
			sdao = new StudentDAO();
			sdao.getStudentRegiste(svo);

			if (sdao != null) {
				studentTotalList();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("학생 입력");
				alert.setHeaderText(txtsd_name.getText() + "학생이 성공적으로 추가되었습니다.");
				alert.setContentText("다음 학생을 입력하세요");
				alert.showAndWait();

				txtsd_num.clear();
				selectSubjectNum = "";
				txtsd_birthday.clear();
				txtsd_phone.clear();
				txtsd_address.clear();
				txtsd_email.clear();
				txtsd_name.requestFocus();

			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("학과 정보 입력");
			alert.setHeaderText("학과 정보를 정확히 입력하시오.");
			alert.setContentText("다음에는 주의하세요");
			alert.showAndWait();
		}
	}

	// 학생 전체 목록
	public void studentTotalList() throws Exception {
		studentDataList.removeAll(studentDataList);
		StudentDAO sDao = new StudentDAO();
		StudentVO sVo = null;
		ArrayList<String> title;
		ArrayList<StudentVO> list;

		title = sDao.getStudentColumnName();
		int columnCount = title.size();

		list = sDao.getStudentTotalList();
		int rowCount = list.size();
		for (int index = 0; index < rowCount; index++) {
			sVo = list.get(index);
			studentDataList.add(sVo);
		}
		// 추가된 학과명 호출
		addSubjectName();
	}

}