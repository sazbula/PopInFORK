package net.javaguids.popin.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import net.javaguids.popin.database.RegistrationDAO;
import net.javaguids.popin.database.UserDAO;
import net.javaguids.popin.models.Event;
import net.javaguids.popin.models.User;

import java.util.ArrayList;
import java.util.List;

public class AttendeeListController {

    @FXML private ListView<String> attendeeListView;

    private final RegistrationDAO registrationDAO = new RegistrationDAO();
    private final UserDAO userDAO = new UserDAO();

    private Event event;

    public void setEvent(Event event) {
        this.event = event;
        loadAttendees();
    }

    private void loadAttendees() {
        try {
            List<Integer> userIds = registrationDAO.findUserIdsByEvent(event.getId());
            List<String> usernames = new ArrayList<>();

            for (int id : userIds) {
                User user = userDAO.findById(id);
                if (user != null) {
                    usernames.add(user.getUsername());
                }
            }

            ObservableList<String> list = FXCollections.observableArrayList(usernames);
            attendeeListView.setItems(list);

        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setContentText(msg);
        alert.show();
    }
}

