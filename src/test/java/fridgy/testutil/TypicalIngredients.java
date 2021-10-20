package fridgy.testutil;

import static fridgy.logic.commands.CommandTestUtil.VALID_DESCRIPTION_ALMOND;
import static fridgy.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BASIL;
import static fridgy.logic.commands.CommandTestUtil.VALID_DESCRIPTION_FISH;
import static fridgy.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE;
import static fridgy.logic.commands.CommandTestUtil.VALID_NAME_ALMOND;
import static fridgy.logic.commands.CommandTestUtil.VALID_NAME_BASIL;
import static fridgy.logic.commands.CommandTestUtil.VALID_NAME_FISH;
import static fridgy.logic.commands.CommandTestUtil.VALID_QUANTITY_ALMOND;
import static fridgy.logic.commands.CommandTestUtil.VALID_QUANTITY_BASIL;
import static fridgy.logic.commands.CommandTestUtil.VALID_QUANTITY_FISH;
import static fridgy.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static fridgy.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fridgy.model.Inventory;
import fridgy.model.ingredient.Ingredient;

/**
 * A utility class containing a list of {@code Ingredient} objects to be used in tests.
 */
public class TypicalIngredients {

    public static final Ingredient APPLE = new IngredientBuilder().withName("Apple")
            .withDescription("123, Jurong West Ave 6, #08-111")
            .withQuantity("94351253").withTags("friends")
            .withExpiryDate("20-08-2010").build();
    public static final Ingredient BANANA = new IngredientBuilder().withName("Banana")
            .withDescription("311, Clementi Ave 2, #02-25")
            .withQuantity("98765432").withTags("owesMoney", "friends")
            .withExpiryDate("20-08-2010").build();
    public static final Ingredient CARROT = new IngredientBuilder().withName("Carrot Slices").withQuantity("95352563")
            .withDescription("wall street")
            .withExpiryDate("20-08-2010").build();
    public static final Ingredient DUCK = new IngredientBuilder().withName("Duck breast").withQuantity("87652533")
            .withDescription("10th street").withTags("friends")
            .withExpiryDate("20-08-2010").build();
    public static final Ingredient EGG = new IngredientBuilder().withName("Egg mayo").withQuantity("9482224")
            .withDescription("michegan ave")
            .withExpiryDate("20-08-2010").build();
    public static final Ingredient FIGS = new IngredientBuilder().withName("Fig jam").withQuantity("9482427")
            .withDescription("little tokyo")
            .withExpiryDate("20-08-2010").build();
    public static final Ingredient GRAPES = new IngredientBuilder().withName("Grape").withQuantity("9482442")
            .withDescription("4th street")
            .withExpiryDate("20-08-2010").build();

    // Manually added
    public static final Ingredient HOON = new IngredientBuilder().withName("Hoon Meier").withQuantity("8482424")
            .withDescription("little india")
            .withExpiryDate("20-08-2010").build();
    public static final Ingredient IDA = new IngredientBuilder().withName("Ida Mueller").withQuantity("8482131")
            .withDescription("chicago ave")
            .withExpiryDate("20-08-2010").build();

    // Manually added - Ingredient's details found in {@code CommandTestUtil}
    public static final Ingredient ALMOND = new IngredientBuilder().withName(VALID_NAME_ALMOND)
            .withQuantity(VALID_QUANTITY_ALMOND)
            .withDescription(VALID_DESCRIPTION_ALMOND).withTags(VALID_TAG_FRIEND).build();
    public static final Ingredient BASIL = new IngredientBuilder().withName(VALID_NAME_BASIL)
            .withQuantity(VALID_QUANTITY_BASIL)
            .withDescription(VALID_DESCRIPTION_BASIL).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withExpiryDate("20-08-2010").build();
    public static final Ingredient FISH = new IngredientBuilder().withName(VALID_NAME_FISH)
            .withQuantity(VALID_QUANTITY_FISH)
            .withDescription(VALID_DESCRIPTION_FISH)
            .withExpiryDate(VALID_EXPIRY_DATE).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalIngredients() {} // prevents instantiation

    /**
     * Returns an {@code Inventory} with all the typical ingredients.
     */
    public static Inventory getTypicalInventory() {
        Inventory ab = new Inventory();
        for (Ingredient ingredient : getTypicalIngredients()) {
            ab.add(ingredient);
        }
        return ab;
    }

    public static List<Ingredient> getTypicalIngredients() {
        return new ArrayList<>(Arrays.asList(APPLE, BANANA, CARROT, DUCK, EGG, FIGS, GRAPES));
    }
}