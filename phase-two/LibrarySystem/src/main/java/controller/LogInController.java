package controller;

import domain.Librarian;
import domain.Member;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.IService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LogInController {
    IService service;
    @FXML
    private TextField textFieldId;
    @FXML
    private TextField textFieldPassword;
    @FXML
    private ComboBox<String> comboBoxAccountType;
    Stage currentStage;

    public void setService(IService service, Stage currentStage) {
        this.service = service;
        this.currentStage = currentStage;

        List<String> types = new ArrayList<>();
        types.add("Librarian");
        types.add("Member");
        comboBoxAccountType.getItems().setAll(types);
        comboBoxAccountType.getSelectionModel().selectFirst();
    }

    @FXML
    private void handleLogIn() {
        String id = textFieldId.getText();
        String password = textFieldPassword.getText();

        if (Objects.equals(comboBoxAccountType.getSelectionModel().getSelectedItem(), "Librarian")) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../views/librarian-account-view.fxml"));
                AnchorPane layout = loader.load();

                Stage dialogStage = new Stage();
                dialogStage.setTitle("My Account - Librarian");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.setScene(new Scene(layout));

                LibrarianAccountController librarianAccountController = loader.getController();

                Librarian librarian = service.findOneLibrarian(id, password);

                if (librarian == null)
                    MessageAlert.showErrorMessage(null, "Wrong username or password!\n");
                else {
                    librarianAccountController.setService(service, librarian);

                    dialogStage.show();

                    currentStage.close();
                }
            } catch (Exception e) {
                MessageAlert.showErrorMessage(null, e.getMessage());
            }
        } else {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../views/member-account-view.fxml"));
                AnchorPane layout = loader.load();

                Stage dialogStage = new Stage();
                dialogStage.setTitle("My Account - Member");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.setScene(new Scene(layout));

                MemberAccountController memberAccountController = loader.getController();

                Member member = service.findOneMember(id, password);

                if (member == null)
                    MessageAlert.showErrorMessage(null, "Wrong username or password!\n");
                else {
                    memberAccountController.setService(service, member);

                    dialogStage.show();

                    currentStage.close();
                }
            } catch (Exception e) {
                MessageAlert.showErrorMessage(null, e.getMessage());
            }
        }
    }
}
