# Random Inspirational Quote API

A simple Spring Boot RESTful API that provides random inspirational quotes with IP-based rate limiting to prevent abuse.

## 🌟 Features

- `GET /api/quote` - Returns a random inspirational quote in JSON format
- IP-based rate limiting (5 requests per minute per IP address)
- HTTP 429 response with retry information when rate limit is exceeded
- Thread-safe implementation
- No database required - simple and lightweight

## 🚀 Quick Start

### Prerequisites
- Java 21 or higher

### Installation & Running

1. **Clone the repository**
   ```bash
   git clone https://github.com/TejasriBailika/quote-api.git
   cd quote-api

2. Run the application

   ▶ Option 1: Using Maven Wrapper
      Windows: mvnw.cmd spring-boot:run
      Linux/Mac: ./mvnw spring-boot:run

4. API is now running at:
   http://localhost:8080

5. Test the endpoint:
   http://localhost:8080/api/quote

