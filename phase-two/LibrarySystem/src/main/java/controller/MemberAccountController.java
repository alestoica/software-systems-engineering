package controller;

import domain.Book;
import domain.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.IService;

import java.util.Collection;

public class MemberAccountController {
    @FXML
    public TableColumn<Book, String> tableColumnAvailableTitle;
    @FXML
    public TableColumn<Book, String> tableColumnAvailableAuthor;
    @FXML
    public TableColumn<Book, String> tableColumnLoanedTitle;
    @FXML
    public TableColumn<Book, String> tableColumnLoanedAuthor;
    @FXML
    private Label welcomeLabel;
    IService service;
    ObservableList<Book> modelAvailableBooks = FXCollections.observableArrayList();
    @FXML
    private TableView<Book> tableViewAvailableBooks;
    ObservableList<Book> modelLoanedBooks = FXCollections.observableArrayList();
    @FXML
    private TableView<Book> tableViewLoanedBooks;
    @FXML
    private Button buttonBorrow;
    @FXML
    private Button buttonReturn;
    Member currentMember;

    public void setService(IService service, Member currentMember) {
        this.service = service;
        this.currentMember = currentMember;

        welcomeLabel.setText("Welcome, " + currentMember.getName());

        initModel();
    }

    private void initModel() {
        modelAvailableBooks.clear();
        Collection<Book> availableBooks = service.findAllAvailableBooks();
        modelAvailableBooks.setAll(availableBooks);
        tableViewAvailableBooks.setItems(modelAvailableBooks);

        modelLoanedBooks.clear();
        Collection<Book> loanedBooks = service.findAllLoanedBooks(currentMember);
        modelLoanedBooks.setAll(loanedBooks);
        tableViewLoanedBooks.setItems(modelLoanedBooks);
    }

    @FXML
    private void initialize() {
        tableColumnAvailableTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tableColumnAvailableAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));

        tableColumnLoanedTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tableColumnLoanedAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
    }
}
