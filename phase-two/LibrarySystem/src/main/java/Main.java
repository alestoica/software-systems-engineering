import controller.LogInController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import repository.*;
import service.IService;
import service.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Properties props = new Properties();

        try {
            props.load(new FileReader("db.config"));
            System.out.println("Properties set.");
        } catch (IOException e) {
            System.out.println("Cannot find db.config " + e);
        }

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("views/login-view.fxml"));
            AnchorPane layout = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Log In");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(layout));

            DBUtils utils = new DBUtils(props);
            IMemberRepository memberRepository = new MemberRepository(utils);
            ILibrarianRepository librarianRepository = new LibrarianRepository(utils);
            IBookRepository bookRepository = new BookRepository(utils);
            ILoanRepository loanRepository = new LoanRepository(utils, memberRepository, bookRepository);

            IService service = new Service(memberRepository, librarianRepository, bookRepository, loanRepository);

            LogInController logInController = loader.getController();

            logInController.setService(service, dialogStage);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}