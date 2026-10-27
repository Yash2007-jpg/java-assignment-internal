import java.util.*;

class Library {
    Map<String, Boolean> books = new LinkedHashMap<>(); // name -> available?

    String add(String name) { books.put(name, true); return "Added."; }

    void show() { books.forEach((n, ok) -> System.out.println(n + " - " + (ok ? "Available" : "Borrowed"))); }

    String status(String name) { return !books.containsKey(name) ? "Not found." : books.get(name) ? "Available" : "Borrowed"; }

    String set(String name, boolean available) {  // false = borrow, true = return
        if (!books.containsKey(name)) return "Not found.";
        if (books.get(name) == available) return "Not possible.";
        books.put(name, available);
        return "Done.";
    }
}
