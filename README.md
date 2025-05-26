# Personal Blog

My completion of Roadmap.sh's Personal Blog project. <https://roadmap.sh/projects/personal-blog>

A modern, secure personal blog application built with Spring Boot and Thymeleaf.

## Features

- Secure authentication and authorization using Spring Security
- Modern web interface using Thymeleaf templates
- RESTful API endpoints for blog management
- Development tools for hot-reloading
- Comprehensive test coverage

## Tech Stack

- Java 21
- Spring Boot 3.4.6
- Spring Security
- Thymeleaf
- Gradle
- JUnit 5 for testing

## Prerequisites

- Java 21 or higher
- Gradle (included in the project)
- Your favorite IDE (VS Code, IntelliJ IDEA, etc.)

## Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/dhar135/personal-blog.git
   cd personal-blog
   ```

2. Build the project:
   ```bash
   ./gradlew build
   ```

3. Run the application:
   ```bash
   ./gradlew bootRun
   ```

The application will be available at `http://localhost:8080`

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── io/github/dhar135/personal_blog/
│   │       ├── article/         # Blog article management
│   │       ├── securingweb/     # Security configuration
│   │       └── PersonalBlogApplication.java
│   └── resources/
│       └── templates/           # Thymeleaf templates
└── test/                       # Test files
```

## Development

The project uses Spring Boot DevTools for development convenience. Changes to the code will automatically trigger a restart of the application.

## Testing

Run the test suite:
```bash
./gradlew test
```

## Security

The application implements Spring Security for authentication and authorization. Security configurations can be found in the `securingweb` package.

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the terms of the license included in the repository.

## Contact

Donovan Harrison - [@dhar135](https://github.com/dhar135)

Project Link: [https://github.com/dhar135/personal-blog](https://github.com/dhar135/personal-blog)
