# Course Registration Web Application

## General Functionality

This web application simulates a **course registration system** for a university. It supports three roles:
- **Student**: can view available courses, register/unregister, track earned credits, and view department offerings.
- **Lecturer**: can view the list of students enrolled in the courses they teach.
- **Admin**: has access to a control panel for full management of:
    - Users (create/edit/delete)
    - Courses and course instances
    - Departments
    - Enrollments

The app includes:
- Role-based access and routing
- Login session handling using Spring Security
- Validation on both frontend and backend
- Custom error pages
- Full Bootstrap styling
- Server-side robustness (thread-safe services, validation, and route control)

## Technologies Used
- **Backend**: Spring Boot, Spring Security, JPA, Hibernate
- **Frontend**: Thymeleaf, Bootstrap
- **Database**: MySQL 
- **Session**: `UserSession` bean stores login details
- **Validation**: Bean Validation (`@Valid`, `@NotNull`, etc.)

---

## Known Bugs

- When it is supposed to show Error404 page , it shows Error500 page.

---

## SQL File Import 

You can import the following SQL seed file to initialize the database with data:

```
📄 ex4/initial_data.sql
```

> This file contains insert statements to populate demo users, departments, courses, and enrollments.


---
##Things you need to know:
>

## Credentials 

| Role     | Username   | Password   |
|----------|------------|------------|
| Admin    | `admin`    | `admin123` |
| Student  | `yaelm`    | `yael123`  |
| Lecturer | `lecturer` | `lect123`  |

> You can modify or extend these users through the Admin panel.


---

## Notes

- The `UserSession` bean is session-scoped and holds user info after login.
- All forms are validated server-side; invalid data will display error feedback.
- In case of unhandled exceptions, the user is redirected to a custom `500` error page.
- The `admin-panel` is fully protected and available only to users with `ADMIN` role.
