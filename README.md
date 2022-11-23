# Übungsprojekt: Coworking Space

Coworking Space ist ein Arbeitsplatz-Management-System, welches mit Quarkus entwickelt wird.

## Start

Beim Starten werden die Testdaten aus dem import.sql geladen.
Darin sind 3 User, 3 Workspaces und 2 Buchungen. 
Wichtig ist, dass die User und Workstations vor den Buchungen erstellt werden, da diese zwei Tabellen darin referenziert werden.

## Datenbank

Die Daten werden in einer PostgreSQL-Datenbank gespeichert. In der Entwicklungsumgebung wird diese in der [docker-compose-yml](./.devcontainer/docker-compose.yml) konfiguriert.

### Datenbankadministration

Über http://localhost:5050 ist PgAdmin4 erreichbar. Damit lässt sich die Datenbank komfortabel verwalten. Der Benutzername lautet `zli@example.com` und das Passwort `zli*123`. Die Verbindung zur PostgreSQL-Datenbank muss zuerst mit folgenden Daten konfiguriert werden:
 - Host name/address: `db`
 - Port: `5432`
 - Maintenance database: `postgres`
 - Username: `postgres`
 - Password: `postgres`

### Login-Daten:
 - Admin: aholenstein.icloud.com / testpw
 - Member: test@example.com / password

### GitHub Repository:
 - https://github.com/Rtful/m223coworkingSpace

## Automatische Tests

Die automatischen Tests können mit `./mvnw quarkus:test` ausgeführt werden. Für die automatischen Tests wird nicht die PostgreSQL-Datenbank verwendet, sondern eine H2-Datenbank, welche sich im Arbeitsspeicher während der Ausführung befindet.
