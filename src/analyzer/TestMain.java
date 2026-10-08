package analyzer;

import java.util.Scanner;

public class TestMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedListManager list = new LinkedListManager();
        list.showMenu(sc);
        sc.close();
    }
}