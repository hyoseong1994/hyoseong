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
         
         
      }catch (Exception e) {
		// TODO: handle exception
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

   void studentTotalList() {
      // TODO Auto-generated method stub

   }
}