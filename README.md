# quote-api
Random inspirational quote API with IP-based rate limiting - Spring Boot RESTful service
# Random Inspirational Quote API

## Features

- `GET /api/quote` - Returns a random inspirational quote in JSON format
- IP-based rate limiting (5 requests per minute per IP address)
- HTTP 429 response with retry information when rate limit is exceeded
- Thread-safe implementation using ConcurrentHashMap
- Simple in-memory storage with no database required

## Tech Stack

- **Java 21** - Programming language
- **Spring Boot** - Web framework
- **Maven** - Dependency management and build tool
- **ConcurrentHashMap** - Thread-safe rate limiting storage

## API Endpoint

**Base URL:** `http://localhost:8080/api/quote`

## Quick Start

### Prerequisites
- Java 21 or higher
- Maven (included with the project via Maven Wrapper)

### Installation & Running

1. **Download or clone the project**
   ```bash
   # If you have the zip file, extract it first
   unzip quote-api.zip
   cd quote-api
