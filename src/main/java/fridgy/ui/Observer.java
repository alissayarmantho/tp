package fridgy.ui;

import fridgy.model.ingredient.Ingredient;
import fridgy.model.recipe.Recipe;

/**
 * Observes an active {@active Ingredient} or {@active Recipe} pr {@active Tab}.
 */
public interface Observer {
    void update(Ingredient newItem);
    void update(Recipe newItem);
    void update(TabEnum tabEnum);
}
