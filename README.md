# MyFitnessApp

MyFitnessApp is designed to support users in elevating their fitness goals. It provides an easy way to track workouts and monitor daily calorie consumption.

## Features
### Authentication:
- User Registration: Users can sign up by entering basic details such as their name, email address, and password.
- Authentication & Authorization: After logging in, each user receives a JWT token, which must be included in future requests for secure access.
- Role-Based Access Control: The system includes two roles: Admin and User. Admins can perform all standard user actions, plus maintain product data and oversee user accounts.
### Fitness Tracking:
- Workout Tracking: Users can log workout sessions by noting exercise type, intensity, and total calories burned.
- Calorie Tracking: Users can track their daily calorie intake and macronutrient distribution.
- Food Catalog: Users can search for food items and save them to their personal food list.
- Product Management: Admins have the ability to add new products, expanding the available food database for users.

## API Endpoints
The application provides the following REST API routes. View the full OpenAPI specification at:
http://localhost:8080/v3/api-docs
- /api/v1/auth/register – Create a new user account.
- /api/v1/auth/login – Log in and receive a JWT token.
- /api/v1/auth/logout – Log out and invalidate the JWT token.
- /api/v1/user/ – Perform CRUD operations on user entities.
- /api/v1/admin/users – Admin-only route for managing user accounts.
- /api/v1/admin/products – Admin-only route for managing product entries.

## How to Run the Application

Follow the steps below to launch the application using Docker:
### Prerequisites
Make sure you have the latest version of Docker installed.

### Instructions
Clone the repository and move into the project directory:
```
git clone <project_url>
cd fitness-tracking-app
```

Build the Docker image:
```
docker build -t fitness-app .
```

Use Docker Compose to start all services:
```
docker-compose up
```
This will launch MyFitnessApp along with the Spring Boot backend and MongoDB instance. You can access the application at http://localhost:8080.