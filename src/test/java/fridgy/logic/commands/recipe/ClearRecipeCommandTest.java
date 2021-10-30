package fridgy.logic.commands.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import fridgy.model.Inventory;
import fridgy.model.Model;
import fridgy.model.ModelManager;
import fridgy.model.UserPrefs;
import fridgy.model.ingredient.Ingredient;
import fridgy.model.recipe.Recipe;
import fridgy.testutil.TypicalRecipes;
import fridgy.ui.Observer;
import fridgy.ui.TabEnum;

public class ClearRecipeCommandTest {
    @Test
    public void execute_emptyRecipeBook_success() {
        Model testModel = new ModelManager();
        testModel.getActiveTabObservable().setObserver(new ObserverStub());

        Model expectedModel = new ModelManager();
        expectedModel.getActiveTabObservable().setObserver(new ObserverStub());
        expectedModel.setActiveTab(TabEnum.RECIPE);

        RecipeCommandTestUtil.assertRecipeCommandSuccess(new ClearRecipeCommand(), testModel,
                ClearRecipeCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyRecipeBook_success() {
        Model testModel = new ModelManager(new Inventory(),
                TypicalRecipes.getTypicalRecipeBook(), new UserPrefs());
        testModel.getActiveTabObservable().setObserver(new ObserverStub());

        Model expectedModel = new ModelManager();
        expectedModel.getActiveTabObservable().setObserver(new ObserverStub());
        expectedModel.setActiveTab(TabEnum.RECIPE);

        RecipeCommandTestUtil.assertRecipeCommandSuccess(new ClearRecipeCommand(), testModel,
                ClearRecipeCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals_otherObjectIsNull_returnsFalse() {
        ClearRecipeCommand testCommand = new ClearRecipeCommand();
        assertNotEquals(testCommand, null);
    }

    @Test
    public void equals_otherObjectWrongType_returnsFalse() {
        ClearRecipeCommand testCommand = new ClearRecipeCommand();
        assertNotEquals(testCommand, 5);
    }

    @Test
    public void equals_otherObjectSameObject_returnsTrue() {
        ClearRecipeCommand testCommand = new ClearRecipeCommand();
        assertEquals(testCommand, testCommand);
    }

    @Test
    public void equals_otherObjectDifferentClearRecipeCommand_returnsTrue() {
        ClearRecipeCommand testCommand = new ClearRecipeCommand();
        ClearRecipeCommand targetCommand = new ClearRecipeCommand();
        assertEquals(testCommand, targetCommand);
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
