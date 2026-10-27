import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();
        while (true) {
            System.out.print("\n1.Add 2.List 3.Search 4.Borrow 5.Return 6.Exit: ");
            String c = sc.nextLine();
            if (c.equals("6")) break;
            else if (c.equals("2")) lib.show();
            else if (c.matches("[1345]")) {
                System.out.print("Book: ");
                String n = sc.nextLine().toLowerCase();
                if (c.equals("1")) System.out.println(lib.add(n));
                else if (c.equals("3")) System.out.println(lib.status(n));
                else System.out.println(lib.set(n, c.equals("5")));
            } else System.out.println("Invalid choice!");
        }
    }
}
