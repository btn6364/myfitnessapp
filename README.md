# MyFitnessApp

MyFitnessApp is created to help users take their fitness journey to the next level. The app is aimed to help users track workouts and caloric intakes.

## Features
### Authentication:
- User Registration: A user can create an account by providing their information such as name, email, and password.
- Authentication and Authorization: Each user will receive a JWT token after logging in. They will need to use it in the subsequent requests for authorization.
- Role-Based Access Control: There are 2 roles in the system: Admin and User. Besides all the normal functionality of the users, an admin can add products to the system and managing user accounts.
### Fitness Tracking:
- Workout Tracking: Users can track their workouts by recording the exercise intensity, type and total calories burned.
- Calorie Tracking: Users can monitor their daily macros and calorie intake.
- Food Catalog: Users can search for food items and add them to their food catalog.
- Product Management: Admins can add new products to the system, enriching food choices for users.

## API Endpoints
The application exposes the following RESTful API endpoints. Access the OpenAPI documentation at: http://localhost:8080/v3/api-docs
- `/api/v1/auth/register` - Register a new user account.
- `/api/v1/auth/login` - User login to obtain JWT token.
- `/api/v1/auth/logout` - User logout to invalidate JWT token.
- `/api/v1/user/` - CRUD operations for users
- `/api/v1/admin/users` - Admin-only endpoint for managing user accounts.
- `/api/v1/admin/products` - Admin-only endpoint for managing products.

## How to run the application
Below are the steps to deploy the application using Docker:
### Prerequisites
Ensure the lastest Docker version is installed on your machine.

### Instructions
1. Clone the repository and navigate to the project directory
```
git clone <project_url>
cd fitness-tracking-app
```
2. Build the Docker image:
```
docker build -t fitness-app .
```
3. Run Docker Compose to start the application:
```
docker-compose up
```
This will start the MyFitnessApp, including the Spring Boot backend and the MongoDB database. Access the application at `http://localhost:8080`.




