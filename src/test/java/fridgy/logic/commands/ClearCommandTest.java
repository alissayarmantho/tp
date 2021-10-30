package fridgy.logic.commands;

import static fridgy.testutil.TypicalIngredients.CHICKEN;
import static fridgy.testutil.TypicalIngredients.FLOUR;

import org.junit.jupiter.api.Test;

import fridgy.model.Inventory;
import fridgy.model.Model;
import fridgy.model.ModelManager;
import fridgy.model.RecipeBook;
import fridgy.model.UserPrefs;
import fridgy.model.ingredient.Ingredient;
import fridgy.model.recipe.Recipe;
import fridgy.testutil.TypicalIngredients;
import fridgy.ui.Observer;
import fridgy.ui.TabEnum;

public class ClearCommandTest {

    @Test
    public void execute_emptyInventory_success() {
        Model model = new ModelManager();
        model.getActiveTabObservable().setObserver(new ObserverStub());

        Model expectedModel = new ModelManager();
        expectedModel.getActiveTabObservable().setObserver(new ObserverStub());
        expectedModel.setActiveTab(TabEnum.INGREDIENT);

        CommandTestUtil.assertCommandSuccess(new ClearCommand(false), model, ClearCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void execute_nonEmptyInventory_success() {
        Model model = new ModelManager(TypicalIngredients.getTypicalInventory(), new RecipeBook(), new UserPrefs());
        model.getActiveTabObservable().setObserver(new ObserverStub());

        Model expectedModel = new ModelManager(TypicalIngredients.getTypicalInventory(), new RecipeBook(),
                new UserPrefs());
        expectedModel.setInventory(new Inventory());
        expectedModel.getActiveTabObservable().setObserver(new ObserverStub());
        expectedModel.setActiveTab(TabEnum.INGREDIENT);

        CommandTestUtil.assertCommandSuccess(new ClearCommand(false), model, ClearCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void execute_allExpiredIngredients_success() {
        Model model = new ModelManager(TypicalIngredients.getTypicalInventory(), new RecipeBook(), new UserPrefs());
        model.getActiveTabObservable().setObserver(new ObserverStub());

        Model expectedModel = new ModelManager(new Inventory(), new RecipeBook(), new UserPrefs());
        expectedModel.getActiveTabObservable().setObserver(new ObserverStub());
        expectedModel.setActiveTab(TabEnum.INGREDIENT);

        CommandTestUtil.assertCommandSuccess(new ClearCommand(true), model,
                ClearCommand.MESSAGE_SUCCESS_EXPIRED, expectedModel);
    }

    @Test
    public void execute_expiredIngredients_success() {
        Model model = new ModelManager(TypicalIngredients.getTypicalInventory(), new RecipeBook(), new UserPrefs());
        model.add(CHICKEN);
        model.add(FLOUR);
        model.getActiveTabObservable().setObserver(new ObserverStub());

        Model expectedModel = new ModelManager(new Inventory(), new RecipeBook(), new UserPrefs());
        expectedModel.add(CHICKEN);
        expectedModel.add(FLOUR);
        expectedModel.getActiveTabObservable().setObserver(new ObserverStub());
        expectedModel.setActiveTab(TabEnum.INGREDIENT);

        CommandTestUtil.assertCommandSuccess(new ClearCommand(true), model,
                ClearCommand.MESSAGE_SUCCESS_EXPIRED, expectedModel);
    }

    @Test
    public void execute_noExpiredIngredients_success() {
        Model model = new ModelManager(new Inventory(), new RecipeBook(), new UserPrefs());
        model.add(CHICKEN);
        model.add(FLOUR);
        model.getActiveTabObservable().setObserver(new ObserverStub());

        Model expectedModel = new ModelManager(new Inventory(), new RecipeBook(), new UserPrefs());
        expectedModel.add(CHICKEN);
        expectedModel.add(FLOUR);
        expectedModel.getActiveTabObservable().setObserver(new ObserverStub());
        expectedModel.setActiveTab(TabEnum.INGREDIENT);

        CommandTestUtil.assertCommandSuccess(new ClearCommand(true), model,
                ClearCommand.MESSAGE_SUCCESS_EXPIRED, expectedModel);
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
