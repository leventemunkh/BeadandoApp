package com.example.beadandoapp;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class AccountController {
    @FXML
    private TextArea accountInfoArea;

    private final AccountService accountService = new AccountService();

    @FXML
    public void initialize() {
        loadAccountInfo();
    }

    private void loadAccountInfo() {
        String accountDetails = accountService.getAccountDetails();
        accountInfoArea.setText(accountDetails);
    }
}
