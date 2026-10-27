# Library Management System (Java Assignment / Internal)

Small library manager: add, list, search, borrow and return books.

## Files
- `Main.java` - console menu
- `Library.java` - book logic used by both console and web versions
- `Server.java` - tiny Java web server (port 8080) serving `index.html` and a JSON API
- `index.html` - web UI (talks to `Server.java`)
- `deploy/index.html` - browser-only copy of the UI (saves in localStorage), used for the Vercel deployment

## Run
Console:
```
javac Main.java Library.java && java Main
```
Web:
```
javac Server.java Library.java && java Server
```
Then open http://localhost:8080
