package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MainController implements Initializable {
	@FXML
	private TabPane mainPane;
	@FXML
	private Tab subject;
	// 참조변수명 부여 방법 : include시 명시한 id+"Controller"
	@FXML
	private SubjectTabController subjectTabController;
	@FXML
	private Tab student;
	@FXML
	private StudentTabController studentTabController;
	@FXML
	private Tab lesson;
	@FXML
	private LessonTabController lessonTabController;
	@FXML
	private Tab traineeTotal;
	@FXML
	private TraineeTotalTabController traineeTotalController;

	// 메뉴
	@FXML
	private MenuItem menuExit;
	@FXML
	private MenuItem menuLogout;
	@FXML
	private MenuItem menuInfo;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			mainPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {

				@Override
				public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
					// TODO Auto-generated method stub
					if (newValue == subject) {
						System.out.println("학과");
						try {
							subjectTabController.subjectTotalList();
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					} else if (newValue == student) {
						try {
							studentTabController.studentTotalList();
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					} else if (newValue == lesson) {
						try {
							lessonTabController.lessonTotalList();
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					} else if (newValue == traineeTotal) {
						try {
							traineeTotalController.traineeTotalList();
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
				}
			});

			// 메뉴 이벤트 등록
			menuExit.setOnAction(event -> handlerMenuExitAction(event));
			menuLogout.setOnAction(event -> handlermenuLogoutAction(event));
			menuInfo.setOnAction(event -> handlermenuInfoAction(event));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void handlermenuLogoutAction(ActionEvent event) {
		// TODO Auto-generated method stub
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scene = new Scene(mainView);
			Stage mainMtage = new Stage();
			mainMtage.setTitle("미래 대학교 학사관리");
			mainMtage.setResizable(false);
			mainMtage.setScene(scene);
			Stage oldStage = (Stage) mainPane.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void handlermenuInfoAction(ActionEvent event) {
		// TODO Auto-generated method stub
		Alert alert;
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("미래 대학교");
		alert.setHeaderText("미래 대학교 수강신청 프로그램.");
		alert.setContentText("Future Universit Register Courses Version 0.01");
		alert.setResizable(false);
		alert.showAndWait();
	}

	public void handlerMenuExitAction(ActionEvent event) {
		// TODO Auto-generated method stub
		Alert alert;
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("미래 대학교");
		alert.setHeaderText("미래 대학교 수강신청 프로그램 종료.");
		alert.setContentText("확인 버튼을 클릭하면 미래 대학교 수강신청 프로그램 종료합니다.");
		alert.setResizable(false);
		alert.showAndWait();
		Platform.exit();
	}

}
