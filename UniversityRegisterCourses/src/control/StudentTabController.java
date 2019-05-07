package control;

import java.net.URL;
import java.util.ResourceBundle;

import javax.jws.HandlerChain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

	// �л� ��� ��

	@FXML
	private ComboBox<SubjectVO> cbx_subjectName;
	@FXML
	private TextField txtsd_num;
	@FXML
	private TextField txtsd_name; // �̸�
	@FXML
	private TextField txtsd_id; // ���̵�
	@FXML
	private PasswordField txtsd_passwd; // ��ȣ
	@FXML
	private TextField txtsd_birthday; // �������
	@FXML
	private TextField txtsd_phone; // �޴�����ȣ
	@FXML
	private TextField txtsd_address; // �ּ�
	@FXML
	private TextField txtsd_email; // �̸���
	@FXML
	private Button btnIdCheck; // ���̵�üũ
	@FXML
	private Button btnStudentInsert; // �л����
	@FXML
	private Button btnStudentUpdate; // �л���������
	@FXML
	private Button btnStudentInit; // �ʱ�ȭ
	@FXML
	private Button btnStudentTatoList; // �л� ��ü ���
	@FXML
	private TableView<StudentVO> studentTableView = new TableView<>();

	ObservableList<StudentVO> studentDataList = FXCollections.observableArrayList();
	ObservableList<StudentVO> selectStudent = null; // �л���� ���̺��� ������ ���� ����
	int selectedStudentIndex; // �л���� �ǿ��� ������ �а� ���� �ε��� ����
	String studentNumber = "";
	private String selectSubjectNum;; // ������ �а����� �а��ڵ�

	public void initialize(URL location, ResourceBundle resources) {
		try {
			// �л���� �ʱ�ȭ
			btnStudentInsert.setDisable(true);
			btnStudentUpdate.setDisable(true);
			btnStudentInit.setDisable(true);
			studentTableView.setEditable(false);

			// �й� ���� ����
			txtsd_num.setEditable(false);

			// �л� ���̺� �� �÷��̸� ����
			@SuppressWarnings("rawtypes")
			TableColumn colStudentNo = new TableColumn("NO.");
			colStudentNo.setPrefWidth(30);
			colStudentNo.setStyle("fx-allignment:CENTER");
			colStudentNo.setCellValueFactory(new PropertyValueFactory<>("no"));

			TableColumn colStudentNum = new TableColumn("�й�");
			colStudentNum.setPrefWidth(70);
			colStudentNum.setStyle("-fx-allignment:CENTER");
			colStudentNum.setCellValueFactory(new PropertyValueFactory<>("sd_num"));

			TableColumn colStudentName = new TableColumn("�̸�");
			colStudentName.setPrefWidth(80);
			colStudentName.setStyle("-fx-allignment:CENTER");
			colStudentName.setCellValueFactory(new PropertyValueFactory<>("sd_name"));

			TableColumn colStudentId = new TableColumn("���̵�");
			colStudentId.setPrefWidth(80);
			colStudentId.setStyle("-fx-allignment:CENTER");
			colStudentId.setCellValueFactory(new PropertyValueFactory<>("sd_id"));

			TableColumn colStudentPassword = new TableColumn("��й�ȣ");
			colStudentPassword.setPrefWidth(80);
			colStudentPassword.setStyle("-fx-allignment:CENTER");
			colStudentPassword.setCellValueFactory(new PropertyValueFactory<>("sd_passwd"));

			TableColumn colSubjectNum = new TableColumn("�а���");
			colSubjectNum.setPrefWidth(70);
			colSubjectNum.setStyle("-fx-allignment:CENTER");
			colSubjectNum.setCellValueFactory(new PropertyValueFactory<>("s_num"));

			TableColumn ColStudentBirthday = new TableColumn("�������");
			ColStudentBirthday.setPrefWidth(80);
			ColStudentBirthday.setStyle("-fx-allignment:CENTER");
			ColStudentBirthday.setCellValueFactory(new PropertyValueFactory<>("sd_birthday"));

			TableColumn ColStudentPhone = new TableColumn("����ó");
			ColStudentPhone.setPrefWidth(80);
			ColStudentPhone.setStyle("-fx-allignment:CENTER");
			ColStudentPhone.setCellValueFactory(new PropertyValueFactory<>("sd_phone"));

			TableColumn ColStudentAddress = new TableColumn("�ּ�");
			ColStudentAddress.setPrefWidth(150);
			ColStudentAddress.setStyle("-fx-allignment:CENTER");
			ColStudentAddress.setCellValueFactory(new PropertyValueFactory<>("sd_address"));

			TableColumn ColStudentEmail = new TableColumn("�̸���");
			ColStudentEmail.setPrefWidth(80);
			ColStudentEmail.setStyle("-fx-allignment:CENTER");
			ColStudentEmail.setCellValueFactory(new PropertyValueFactory<>("sd_email"));

			TableColumn colStudentDate = new TableColumn("�����");
			colStudentDate.setPrefWidth(80);
			colStudentDate.setStyle("-fx-allignment:CENTER");
			colStudentDate.setCellValueFactory(new PropertyValueFactory<>("sd_date"));

			studentTableView.setItems(studentDataList);
			studentTableView.getColumns().addAll(colStudentNo, colStudentNum, colStudentName, colStudentId,
					colStudentPassword, colSubjectNum, ColStudentBirthday, ColStudentPhone, ColStudentAddress,
					ColStudentEmail, colStudentDate);

			// �л� ��ü ���

			studentTotalList();

			// �߰��� �а��� ȣ��
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

	// �л� ��ü ��� �̺�Ʈ

	public void handlerBtnStudentTatoListAction(ActionEvent event) {
		try {
			studentDataList.removeAll(studentDataList);
			studentTotalList();
		} catch (Exception e) {
			e.printStackTrace(); // ���� �޼����� �߻� �ٿ����� ã�Ƽ� �ܰ躰�� ������ ����Ѵ�.
		}
	}

	// �л� �ʱ�ȭ �̺�Ʈ
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
			e.printStackTrace(); // ���� �޼����� �߻� �ٿ����� ã�Ƽ� �ܰ躰�� ������ ����Ѵ�.
		}

	}

	// �л� ���� ���� �̺�Ʈ
	public void handlerBtnStudentUpdateAction(ActionEvent event) {
		try {
			boolean sucess;
			
			StudentDAO sdao = new StudentDAO();
			
			
		}
		
	}

	private Object handlerStudentTableViewAction(MouseEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object handlerBtnIdCheckActio(ActionEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object HandlerCbx_subjectNameAction(ActionEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object handlerBtnStudentInsertAction(ActionEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	private void studentTotalList() {
		// TODO Auto-generated method stub

	}

}
