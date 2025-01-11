package controller;

import domain.Book;
import domain.Librarian;
import domain.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.IService;

import java.util.Collection;

public class LibrarianAccountController {
    @FXML
    private Label welcomeLabel;
    @FXML
    private TableColumn<Member, String> tableColumnName;
    @FXML
    private TableColumn<Member, String> tableColumnCNP;
    @FXML
    private TableColumn<Member, String> tableColumnPhoneNumber;
    @FXML
    private TableColumn<Book, String> tableColumnTitle;
    @FXML
    private TableColumn<Book, String> tableColumnAuthor;
    @FXML
    private TableColumn<Book, String> tableColumnStatus;
    IService service;
    ObservableList<Book> modelBooks = FXCollections.observableArrayList();
    @FXML
    private TableView<Book> tableViewBooks;
    ObservableList<Member> modelMembers = FXCollections.observableArrayList();
    @FXML
    private TableView<Member> tableViewMembers;
    Librarian currentlibrarian;

    public void setService(IService service, Librarian currentlibrarian) {
        this.service = service;
        this.currentlibrarian = currentlibrarian;

        welcomeLabel.setText("Welcome, " + currentlibrarian.getName());

        initModel();
    }

    private void initModel() {
        modelBooks.clear();
        Collection<Book> books = service.findAllBooks();
        modelBooks.setAll(books);
        tableViewBooks.setItems(modelBooks);

        modelMembers.clear();
        Collection<Member> members = service.findAllMembers();
        modelMembers.setAll(members);
        tableViewMembers.setItems(modelMembers);
    }

    @FXML
    private void initialize() {
        tableColumnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tableColumnAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnCNP.setCellValueFactory(new PropertyValueFactory<>("CNP"));
        tableColumnPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    }
}
