public class Main {
    public static void main(String[] args) {
        LinkedListManager list = new LinkedListManager();

        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        System.out.print("Linked List: ");
        list.display();
    }
}