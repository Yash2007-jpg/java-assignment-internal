import com.sun.net.httpserver.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.stream.Collectors;

public class Server {
    static void send(HttpExchange e, String body, String type) throws IOException {
        byte[] b = body.getBytes(StandardCharsets.UTF_8);
        e.getResponseHeaders().set("Content-Type", type + "; charset=utf-8");
        e.sendResponseHeaders(200, b.length);
        e.getResponseBody().write(b);
        e.close();
    }

    public static void main(String[] args) throws Exception {
        Library lib = new Library();
        for (String b : new String[]{"dune", "pride and prejudice", "1984", "the hobbit", "sapiens"}) lib.add(b);

        HttpServer s = HttpServer.create(new InetSocketAddress(8080), 0);
        s.createContext("/", e -> send(e, Files.readString(Path.of("index.html")), "text/html"));
        s.createContext("/api/", e -> {
            String action = e.getRequestURI().getPath().substring(5);
            String q = e.getRequestURI().getQuery();                       // "n=<book name>"
            String name = q == null ? "" : q.substring(2).trim().toLowerCase();
            if (!name.isEmpty()) {
                if (action.equals("add")) lib.add(name);
                else if (action.equals("borrow")) lib.set(name, false);
                else if (action.equals("return")) lib.set(name, true);
                else if (action.equals("delete")) lib.books.remove(name);
            }
            send(e, lib.books.entrySet().stream()
                .map(b -> "{\"name\":\"" + b.getKey().replace("\\", "\\\\").replace("\"", "\\\"") + "\",\"out\":" + !b.getValue() + "}")
                .collect(Collectors.joining(",", "[", "]")), "application/json");
        });
        s.start();
        System.out.println("Open http://localhost:8080");
    }
}
