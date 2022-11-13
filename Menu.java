import java.util.ArrayList;

public class Menu {

    private ArrayList<Food> starters = new ArrayList<>();
    private ArrayList<Food> soups = new ArrayList<>();
    private ArrayList<Food> main_course = new ArrayList<>();
    private ArrayList<Food> dessert = new ArrayList<>();
    private ArrayList<Food> allMeals = new ArrayList<>();

    public Menu() {

    }

    //This method is used to add a meal to allMeals and into one of the specified categories
    public void addMeal(String name, String type, String desc) {
        Food newMeal = new Food(name, type, desc);

        if(type.toUpperCase().contains("STARTER")) {
            starters.add(newMeal);
        }
        else if(type.toUpperCase().contains("SOUP")) {
            soups.add(newMeal);
        }
        else if(type.toUpperCase().contains("MAIN")) {
            main_course.add(newMeal);
        }
        else if(type.toUpperCase().contains("DESSERT")) {
            dessert.add(newMeal);
        }
        else {
            //Apparently not needed so can remove since I have exception in Food already
            throw new RuntimeException("Error, the meal does not fit into any of the categories. Please specify the category");
        }
        allMeals.add(newMeal);
    }

    //Removes meals when the name of the meal is specified
    public void removeMeal(String meal) {
        ArrayList<Food> allMealsTemp = new ArrayList<>(allMeals);
        for(Food food : allMealsTemp) {
            if(food.getName().equals(meal)) {
                starters.remove(food);
                soups.remove(food);
                main_course.remove(food);
                dessert.remove(food);
                allMeals.remove(food);
            }
        }
    }

    public ArrayList<Food> getStarters() {
        return starters;
    }

    public ArrayList<Food> getSoups() {
        return soups;
    }

    public ArrayList<Food> getMainCourse() {
        return main_course;
    }

    public ArrayList<Food> getDessert() {
        return dessert;
    }
}
