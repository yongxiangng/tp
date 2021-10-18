package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);
    private boolean showDetails = false;

    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Sets the details field to parameter passed.
     *
     * @param showDetails boolean value to show details.
     */
    public void setShowDetails(boolean showDetails) {
        this.showDetails = showDetails;
        refresh();
    }

    /**
     * Refreshes the entire component.
     */
    public void refresh() {
        logger.info("Refreshing person list panel");
        personListView.setItems(personListView.getItems());
        personListView.setCellFactory(listView -> new PersonListViewCell());
        personListView.refresh();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else if (showDetails) {
                setGraphic(new PersonDetailsCard(person).getRoot());
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }

}
