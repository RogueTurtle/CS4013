public class MenuTest {

    public static void main(String[] args) {
        Menu menu = new Menu();

        System.out.println(menu.getStarters());

        menu.addMeal("Porridge", "Sx", "Our fine porridge");

        System.out.println(menu.getStarters());
        menu.removeMeal("Porridge");
        System.out.println(menu.getStarters());


    }
}
