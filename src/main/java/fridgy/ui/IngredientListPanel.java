package fridgy.ui;

import java.util.logging.Logger;

import fridgy.commons.core.LogsCenter;
import fridgy.model.ingredient.Ingredient;
import fridgy.model.recipe.Recipe;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

/**
 * Panel containing the list of ingredients.
 */
public class IngredientListPanel extends UiPart<Region> implements Observer {
    private static final String FXML = "IngredientListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(IngredientListPanel.class);

    @FXML
    private ListView<Ingredient> ingredientListView;

    /**
     * Creates a {@code IngredientListPanel} with the given {@code ObservableList}.
     */
    public IngredientListPanel(Observable activeObservable, ObservableList<Ingredient> ingredientList,
                               ActiveItemPanel activeItemPanel) {
        super(FXML);
        activeObservable.setObserver(this);
        ingredientListView.setItems(ingredientList);
        ingredientListView.setCellFactory(listView -> new IngredientListViewCell());
        ingredientListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Ingredient>() {
                    public void changed(ObservableValue<? extends Ingredient> ov,
                                        Ingredient oldVal, Ingredient newVal) {
                        activeItemPanel.update(newVal);
                    }
                });
    }

    @Override
    public void update(Ingredient newItem) {
        ingredientListView.getSelectionModel().select(newItem);
    }

    /**
     * This should not be called
     */
    @Override
    public void update(Recipe newItem) {
        return;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code BaseIngredient} using a {@code IngredientCard}.
     */
    class IngredientListViewCell extends ListCell<Ingredient> {
        @Override
        protected void updateItem(Ingredient ingredient, boolean empty) {
            super.updateItem(ingredient, empty);

            if (empty || ingredient == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new IngredientCard(ingredient, getIndex() + 1).getRoot());
            }
        }
    }

}
