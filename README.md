Order Project
A lightweight service to manage orders: create, update, query and cancel. Designed for clarity and testability with a simple REST API and persistent storage layer.
Key features
- Order creation, retrieval, update and cancellation
- Validation and basic domain rules (status transitions, item totals)
- Unit and integration tests
- Configurable persistence (in-memory by default, pluggable DB)
Tech stack
- JVM-based service (Java/Kotlin/Scala) — open to the project language in the repository
- Build: mvn or gradle (check pom.xml or build.gradle)
- Test: JUnit / Test framework used in the repo
Repo layout
- src/main/java or src/main/kotlin - application code
- src/test/java or src/test/kotlin - tests
- src/main/resources - configuration files
- pom.xml or build.gradle - build configuration
Quickstart
Install prerequisites: JDK 11+ and Maven or Gradle.
Build:
mvn clean package
or
./gradlew build
Run:
java -jar target/order-service.jar
Adjust paths if your build outputs a different artifact.
Configuration
Environment variables or application.properties / application.yml in src/main/resources:
ORDER_DB_URL=jdbc:postgresql://localhost:5432/orders
ORDER_DB_USER=orders
ORDER_DB_PASS=secret
SERVER_PORT=8080
Defaults use in-memory storage for development.
API (example)
Create order:
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d '{"customerId":"cust-123","items":[{"sku":"sku-1","qty":2,"price":9.99}], "currency":"USD"}'
Get order:
curl http://localhost:8080/api/orders/{orderId}
Update order status:
curl -X PATCH http://localhost:8080/api/orders/{orderId}/status \
  -H "Content-Type: application/json" \
  -d '{"status":"CANCELLED"}'
Testing
Run unit and integration tests:
mvn test
or
./gradlew test
Contributing
- Follow existing coding conventions and tests.
- Add unit tests for new logic.
- Open a pull request with a clear description of changes.
Troubleshooting
- Check logs for startup errors and port conflicts.
- Verify database connectivity if using external DB.
