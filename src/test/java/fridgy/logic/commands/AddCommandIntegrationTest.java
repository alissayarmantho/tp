package fridgy.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fridgy.model.Model;
import fridgy.model.ModelManager;
import fridgy.model.RecipeBook;
import fridgy.model.UserPrefs;
import fridgy.model.ingredient.Ingredient;
import fridgy.model.ingredient.IngredientDefaultComparator;
import fridgy.model.recipe.Recipe;
import fridgy.testutil.IngredientBuilder;
import fridgy.testutil.TypicalIngredients;
import fridgy.ui.Observer;
import fridgy.ui.TabEnum;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalIngredients.getTypicalInventory(), new RecipeBook(), new UserPrefs());
    }

    @Test
    public void execute_newIngredient_success() {
        Ingredient validIngredient = new IngredientBuilder().build();

        model.getActiveTabObservable().setObserver(new ObserverStub());

        Model expectedModel = new ModelManager(model.getInventory(), new RecipeBook(), new UserPrefs());
        expectedModel.add(validIngredient);
        expectedModel.sortIngredient(new IngredientDefaultComparator());
        expectedModel.getActiveTabObservable().setObserver(new ObserverStub());
        expectedModel.setActiveTab(TabEnum.INGREDIENT);

        CommandTestUtil.assertCommandSuccess(new AddCommand(validIngredient), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validIngredient), expectedModel);
    }

    @Test
    public void execute_duplicateIngredient_throwsCommandException() {
        model.sortIngredient(new IngredientDefaultComparator());
        Ingredient ingredientInList = model.getInventory().getList().get(0);
        CommandTestUtil.assertCommandFailure(new AddCommand(ingredientInList), model,
                AddCommand.MESSAGE_DUPLICATE_INGREDIENT);
    }

    private class ObserverStub implements Observer {
        @Override
        public void update(Ingredient ingredient) {
            return;
        }

        @Override
        public void update(Recipe recipe) {
            return;
        }

        public void update(TabEnum tabEnum) {
            return;
        }
    }

}
