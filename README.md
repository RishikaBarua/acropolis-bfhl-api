# Acropolis Campus Hiring (May 2026) - API Round

A robust, enterprise-grade, and deployment-ready **REST API** built using **Java (Spring Boot)**. This API satisfies the requirements of the Acropolis Campus Hiring API Challenge, featuring clean architecture, comprehensive exception handling, input validation, and full JUnit test coverage.

## Developer Information
- **Name:** Rishika Barua
- **Enrollment Number:** 0827CS231216
- **Email:** rishikabarua230031@acropolis.in
- **College Roll Number:** 0827CS231216

---

## Technology Stack
- **Core Framework:** Spring Boot 3.3.x
- **Language:** Java 17
- **Build Tool:** Maven (Bundled with Maven Wrapper)
- **Testing:** JUnit 5, Spring WebMvcTest, MockMvc, AssertJ
- **API Standard:** RESTful (Method: `POST`, Endpoint: `/bfhl`, Status Code: `200 OK`)

---

## Directory Structure
```
c:\Users\barua\OneDrive\Desktop\Bajaj
 │
 ├── pom.xml                                  # Project Maven build file
 ├── mvnw / mvnw.cmd                          # Maven wrapper executables
 ├── README.md                                # Project documentation (this file)
 ├── .mvn/wrapper/maven-wrapper.properties    # Maven wrapper properties
 └── src
     ├── main
     │   ├── java/com/acropolis/bfhl
     │   │   ├── BfhlApplication.java         # Spring Boot entry point
     │   │   ├── controller
     │   │   │   └── BfhlController.java      # REST Endpoint Controller
     │   │   ├── dto
     │   │   │   ├── BfhlRequest.java         # Request Body DTO
     │   │   │   └── BfhlResponse.java        # Response Body DTO
     │   │   ├── service
     │   │   │   ├── BfhlService.java         # Business Service Interface
     │   │   │   └── BfhlServiceImpl.java     # Service Implementation Class
     │   │   └── exception
     │   │       ├── GlobalExceptionHandler.java # REST Controller Exception Interceptor
     │   │       └── ErrorResponse.java       # Standard Error DTO
     │   └── resources
     │       └── application.properties       # Environment configuration & variables
     └── test
         └── java/com/acropolis/bfhl
             ├── controller
             │   └── BfhlControllerTest.java  # MockMvc Endpoint Controller Tests
             └── service
                 └── BfhlServiceTest.java     # Service Layer Unit Tests (JUnit 5)
```

---

## Local Setup & Execution

### Prerequisites
- **JDK 17 or higher** (JDK 23 is recommended)
- **Git**

### 1. Running Locally (Command Line)
The project includes a **Maven Wrapper**, meaning you do not need Maven pre-installed.

#### On Windows (Command Prompt/PowerShell):
Set your `JAVA_HOME` environment variable to your JDK 17+ path if the system default is older, then run the project:
```powershell
# Set JAVA_HOME to JDK 17+ (e.g. JDK 23)
$env:JAVA_HOME="C:\Program Files\Java\jdk-23"

# Run all unit tests to verify correctness
.\mvnw.cmd clean test

# Run the Spring Boot application
.\mvnw.cmd spring-boot:run
```

#### On Linux/macOS:
```bash
# Grant execution permissions to the wrapper
chmod +x mvnw

# Run tests
./mvnw clean test

# Run the application
./mvnw spring-boot:run
```

### 2. Running in IntelliJ IDEA / Eclipse
1. Open IntelliJ IDEA.
2. Select **Open** and point to the `c:\Users\barua\OneDrive\Desktop\Bajaj` folder.
3. IntelliJ will automatically detect the `pom.xml` and import dependencies.
4. Set the **Project SDK** to **Java 17** or higher (e.g. JDK 23).
5. Locate `BfhlApplication.java`, right-click, and select **Run 'BfhlApplication'**.
6. The API will start on `http://localhost:8080`.

---

## API Documentation

### Method & Route
- **Method:** `POST`
- **Route:** `/bfhl`
- **Headers:** `Content-Type: application/json`
- **Expected Success Status:** `200 OK`

### Validation Rule
- The request body **must** contain a `"data"` array/list of strings. A null array will trigger a `400 Bad Request` validation error.

---

## Sample API Requests & Responses

### Example A: Standard Mixed Array
#### Request Body
```json
{
 "data": ["a", "1", "334", "4", "R", "$"]
}
```
#### Response Body (200 OK)
```json
{
 "is_success": true,
 "user_id": "rishika_barua_26052026",
 "email": "rishikabarua230031@acropolis.in",
 "roll_number": "0827CS231216",
 "odd_numbers": ["1"],
 "even_numbers": ["334", "4"],
 "alphabets": ["A", "R"],
 "special_characters": ["$"],
 "sum": "339",
 "concat_string": "Ra"
}
```

### Example B: Long Segregated Array
#### Request Body
```json
{
 "data": ["2", "a", "y", "4", "&", "-", "*", "5", "92", "b"]
}
```
#### Response Body (200 OK)
```json
{
 "is_success": true,
 "user_id": "rishika_barua_26052026",
 "email": "rishikabarua230031@acropolis.in",
 "roll_number": "0827CS231216",
 "odd_numbers": ["5"],
 "even_numbers": ["2", "4", "92"],
 "alphabets": ["A", "Y", "B"],
 "special_characters": ["&", "-", "*"],
 "sum": "103",
 "concat_string": "ByA"
}
```

### Example C: Alphabets Only
#### Request Body
```json
{
 "data": ["A", "ABCD", "DOE"]
}
```
#### Response Body (200 OK)
```json
{
 "is_success": true,
 "user_id": "rishika_barua_26052026",
 "email": "rishikabarua230031@acropolis.in",
 "roll_number": "0827CS231216",
 "odd_numbers": [],
 "even_numbers": [],
 "alphabets": ["A", "ABCD", "DOE"],
 "special_characters": [],
 "sum": "0",
 "concat_string": "EoDdCbAa"
}
```

---

## Testing with `curl`

Once the server is running locally (on port `8080`), you can test the API by opening terminal and running:

```bash
curl -X POST http://localhost:8080/bfhl \
  -H "Content-Type: application/json" \
  -d "{\"data\": [\"a\", \"1\", \"334\", \"4\", \"R\", \"$\"]}"
```

---

## Hosting & Deployment (Render / Railway)

### Preparing for Deployment (GitHub Upload)
To deploy your API, you need to push this repository to GitHub first:

1. Initialize git repository locally:
   ```bash
   git init
   ```
2. Create a `.gitignore` file to avoid pushing compiled classes and settings:
   ```bash
   # Create a .gitignore file with these contents:
   target/
   .idea/
   *.iml
   .mvn/wrapper/maven-wrapper.jar
   ```
3. Add files and make your first commit:
   ```bash
   git add .
   git commit -m "Initial commit - Acropolis BFHL API"
   ```
4. Create a new repository on your GitHub account, copy the repository link, and run:
   ```bash
   git remote add origin <YOUR_GITHUB_REPO_URL>
   git branch -M main
   git push -u origin main
   ```

### 1. Hosting on Render
1. Sign up/Log in to **[Render](https://render.com/)**.
2. Click **New +** and select **Web Service**.
3. Connect your GitHub repository.
4. Configure the web service with:
   - **Name:** `acropolis-bfhl-api`
   - **Environment:** `Docker` OR `Web Service (Java)`
     - *If choosing Java:*
       - **Runtime:** `Java` (Ensure Java version is set to 17 or 21 in Render settings)
       - **Build Command:** `./mvnw clean package -DskipTests`
       - **Start Command:** `java -jar target/bfhl-0.0.1-SNAPSHOT.jar --server.port=$PORT`
5. Click **Deploy Web Service**. Render will build and deploy the API. Your endpoint URL will be: `https://<your-service-name>.onrender.com/bfhl`

### 2. Hosting on Railway
1. Sign up/Log in to **[Railway](https://railway.app/)**.
2. Click **New Project** -> **Deploy from GitHub repo**.
3. Connect your GitHub repository.
4. Railway will automatically detect the Maven configuration and build it.
5. In the **Variables** tab of the service, add:
   - `PORT` = `8080` (or Railway will inject it automatically)
6. Once deployed, click **Generate Domain** under Settings to expose the service to the internet. Your endpoint URL will be: `https://<generated-subdomain>.up.railway.app/bfhl`
