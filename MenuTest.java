/**
 * Made by Ahmed Abdalla
 * Student: 21316333
 */


public class MenuTest {

    public static void main(String[] args) {
        Menu menu = new Menu();

        System.out.println(menu.getStarters());
        System.out.println();

        menu.addMeal("Porridge", "Starter", "Our fine porridge", 10);
        menu.addMeal("Chicken", "Main", "Our fine porridge", 10);

        System.out.println(menu.getMainCourse());
        System.out.println();
       // System.out.println(menu.getStarters());
        //menu.removeMeal("Porridge");
        //System.out.println(menu.getStarters());
        System.out.println(menu);


    }
}
