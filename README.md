# CID Tokenization Application

A Spring Boot application designed to securely handle Customer Identifiable Data (CID) through tokenization. This application provides a secure way to store sensitive customer information and communicate with external systems using tokens instead of actual data.

## 🎯 Purpose

This application solves the problem of securely handling CID data by:
- Storing sensitive customer information with token mappings
- Providing APIs for token-based communication with external systems
- Generating reports with actual customer data resolved on-the-fly from tokens
- Ensuring CID data never leaves the secure environment in its original form

## 🏗️ Architecture

```
┌─────────────────┐    Token-based     ┌─────────────────┐
│   External App  │◄──────────────────►│  CID Token App  │
│  (Non-CID Data) │    Communication   │   (CID Data)    │
└─────────────────┘                    └─────────────────┘
                                              │
                                              ▼
                                       ┌─────────────┐
                                       │  Database   │
                                       │ ┌─────────┐ │
                                       │ │Customer │ │
                                       │ │ Data    │ │
                                       │ └─────────┘ │
                                       │ ┌─────────┐ │
                                       │ │ Token   │ │
                                       │ │Mappings │ │
                                       │ └─────────┘ │
                                       └─────────────┘
```

## 🚀 Features

- **Tokenization**: Automatic generation of unique tokens for sensitive data fields
- **Secure Storage**: CID data stored securely with token mappings
- **API Communication**: RESTful APIs for token-based data exchange
- **Report Generation**: On-the-fly resolution of tokens to actual values for reporting
- **Data Protection**: Sensitive information never exposed in external communications

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

## 🛠️ Installation & Setup

### 1. Clone/Download the Project
```bash
# If using Git
git clone <repository-url>
cd cid-tokenization

# Or download and extract the ZIP file
```

### 2. Build the Project
```bash
mvn clean install
```

### 3. Run the Application
```bash
mvn spring-boot:run
```

### 4. Verify Installation
- Application will start on `http://localhost:8080`
- H2 Database Console: `http://localhost:8080/h2-console`
- Sample data will be automatically loaded

## 🗄️ Database Configuration

The application uses H2 in-memory database for demonstration:

**Connection Details:**
- URL: `jdbc:h2:mem:ciddb`
- Username: `sa`
- Password: `password`
- Console: `http://localhost:8080/h2-console`

**Tables Created:**
- `customers` - Stores customer CID data
- `token_mappings` - Maps tokens to actual field values

## 📊 Sample Data

The application automatically loads sample customers on startup:

**Customer 1:**
- Name: Ramesh Yadav
- Account: ACC123456789
- Address: 123 Main Street, Mumbai, Maharashtra
- Phone: +91-9876543210
- Email: ramesh.yadav@email.com

**Customer 2:**
- Name: Bhimesh Solanki
- Account: ACC987654321
- Address: 456 Park Avenue, Mumbai, Maharashtra
- Phone: +91-8765432109
- Email: bhimesh.solnki@email.com

## 🔌 API Endpoints

### Base URL: `http://localhost:8080/api/cid`

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/customer` | Store new customer with token generation |
| POST | `/detokenize` | Convert tokens to actual values |
| GET | `/token/{token}` | Get actual value for specific token |
| POST | `/generate-report` | Generate report with resolved data |
| GET | `/customers` | List all customers |
| GET | `/tokens` | List all token mappings |

## 📝 API Usage Examples

### 1. Store New Customer

**Request:**
```bash
POST http://localhost:8080/api/cid/customer
Content-Type: application/json

{
    "customerName": "John Doe",
    "customerAccountNumber": "ACC111222333",
    "customerAddress": "789 Oak Street, Bangalore, Karnataka",
    "customerPhone": "+91-9988776655",
    "customerEmail": "john.doe@email.com"
}
```

**Response:**
```json
{
    "customerId": 3,
    "tokens": {
        "customerName": "1719158400201",
        "customerAccountNumber": "1719158400202",
        "customerAddress": "1719158400203",
        "customerPhone": "1719158400204",
        "customerEmail": "1719158400205"
    }
}
```

### 2. Detokenize Data

**Request:**
```bash
POST http://localhost:8080/api/cid/detokenize
Content-Type: application/json

{
    "customerName": "1719158400201",
    "customerAddress": "1719158400203"
}
```

**Response:**
```json
{
    "customerName": "John Doe",
    "customerAddress": "789 Oak Street, Bangalore, Karnataka"
}
```

### 3. Generate Report

**Request:**
```bash
POST http://localhost:8080/api/cid/generate-report
Content-Type: application/json

{
    "customerName": "1719158400201",
    "customerAccountNumber": "1719158400202",
    "customerEmail": "1719158400205"
}
```

**Response:**
```json
{
    "tokenizedData": {
        "customerName": "1719158400201",
        "customerAccountNumber": "1719158400202",
        "customerEmail": "1719158400205"
    },
    "actualData": {
        "customerName": "John Doe",
        "customerAccountNumber": "ACC111222333",
        "customerEmail": "john.doe@email.com"
    },
    "reportType": "Customer Report"
}
```

### 4. Get Value by Token

**Request:**
```bash
GET http://localhost:8080/api/cid/token/1719158400201
```

**Response:**
```
John Doe
```

## 🔧 Testing with cURL

### Store Customer:
```bash
curl -X POST http://localhost:8080/api/cid/customer \
  -H "Content-Type: application/json" \
  -d '{
    "customerName": "Test User",
    "customerAccountNumber": "ACC999888777",
    "customerAddress": "Test Address",
    "customerPhone": "+91-1234567890",
    "customerEmail": "test@email.com"
  }'
```

### Detokenize Data:
```bash
curl -X POST http://localhost:8080/api/cid/detokenize \
  -H "Content-Type: application/json" \
  -d '{
    "customerName": "TOKEN_VALUE_HERE"
  }'
```

## 🏢 Integration with External Applications

### Communication Flow:
1. **External App** sends tokenized data to this CID app
2. **CID App** resolves tokens to actual values
3. **CID App** processes/generates reports with real data
4. **CID App** returns results (can be tokenized or actual based on requirements)

### Example Integration:
```java
// External app sends tokens
Map<String, String> tokenizedRequest = Map.of(
    "customerName", "1719158400101",
    "customerAddress", "1719158400103"
);

// CID app resolves and processes
// Returns actual data for reports
Map<String, String> actualData = cidService.getActualValuesFromTokens(tokenizedRequest);
```

## 🔒 Security Considerations

- **Token Rotation**: Implement token rotation for enhanced security
- **Access Control**: Add authentication/authorization for production use
- **Encryption**: Consider encrypting sensitive data at rest
- **Audit Logging**: Implement audit trails for compliance
- **Network Security**: Use HTTPS in production environments

## 🚀 Production Deployment

### Database Migration:
Replace H2 with production database (PostgreSQL/MySQL):

```yaml
# application-prod.yml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/ciddb
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
    driver-class-name: org.postgresql.Driver
  jpa:
    database-platform: org.hibernate.dialect.PostgreSQLDialect
    hibernate:
      ddl-auto: validate
```

### Environment Variables:
```bash
export DB_USERNAME=your_db_user
export DB_PASSWORD=your_db_password
export SPRING_PROFILES_ACTIVE=prod
```

### Build for Production:
```bash
mvn clean package -Pprod
java -jar target/cid-tokenization-0.0.1-SNAPSHOT.jar
```

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/cidapp/
│   │   ├── CidApplication.java          # Main application class
│   │   ├── controller/
│   │   │   └── CidController.java       # REST API endpoints
│   │   ├── service/
│   │   │   ├── CidService.java          # Business logic
│   │   │   └── TokenService.java        # Token generation
│   │   ├── entity/
│   │   │   ├── Customer.java            # Customer entity
│   │   │   └── TokenMapping.java        # Token mapping entity
│   │   ├── repository/
│   │   │   ├── CustomerRepository.java  # Customer data access
│   │   │   └── TokenMappingRepository.java # Token mapping data access
│   │   ├── dto/
│   │   │   ├── CustomerDto.java         # Customer DTO
│   │   │   ├── TokenizedCustomerDto.java # Tokenized response DTO
│   │   │   └── ReportDto.java           # Report DTO
│   │   └── config/
│   │       └── DataInitializer.java     # Sample data loader
│   └── resources/
│       └── application.yml              # Application configuration
└── test/
    └── java/                           # Test files
```

## 🐛 Troubleshooting

### Common Issues:

**1. Port Already in Use:**
```bash
# Change port in application.yml
server:
  port: 8081
```

**2. Database Connection Issues:**
```bash
# Check H2 console at http://localhost:8080/h2-console
# Verify connection URL: jdbc:h2:mem:ciddb
```

**3. Token Not Found Errors:**
```bash
# Verify token exists in database
GET http://localhost:8080/api/cid/tokens
```

## 📈 Future Enhancements

- [ ] Token expiration and rotation
- [ ] Enhanced security with JWT tokens
- [ ] Batch processing APIs
- [ ] Data encryption at rest
- [ ] Performance monitoring
- [ ] Comprehensive audit logging
- [ ] Integration with external key management systems

## 🤝 Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/new-feature`)
3. Commit changes (`git commit -am 'Add new feature'`)
4. Push to branch (`git push origin feature/new-feature`)
5. Create Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 📞 Support

For support and questions:
- Create an issue in the repository
- Email: support@cidapp.com
- Documentation: [Wiki](link-to-wiki)

---

**Note:** This application is designed for demonstration purposes. Please implement additional security measures for production use.