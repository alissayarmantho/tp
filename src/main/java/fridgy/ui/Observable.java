package fridgy.ui;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import fridgy.model.ingredient.Ingredient;
import fridgy.model.recipe.Recipe;


/**
 * The type Observable that reports changes to an active {@code Ingredient} or {@code Recipe} to an {@code Observer}.
 */
public class Observable {
    private List<Observer> observers = new ArrayList<>();

    /**
     * Adds the observer into a list of observers which will all observe the item
     *
     * @param observer
     */
    public void setObserver(Observer observer) {
        requireNonNull(observer);
        this.observers.add(observer);
    }

    /**
     * Changes the item being observed by the observer.
     *
     * @param newItem new Ingredient to be observed by the observers
     */
    public void change (Ingredient newItem) {
        requireNonNull(newItem);
        for (Observer observer: observers) {
            observer.update(newItem);
        }
    }

    /**
     * Changes the item being observed by the observer.
     *
     * @param newItem new Recipe to be observed by the observers
     */
    public void change (Recipe newItem) {
        requireNonNull(newItem);
        for (Observer observer: observers) {
            observer.update(newItem);
        }
    }
}
