# Course Registration Web Application

## Link for the demo : https://drive.google.com/file/d/1VALqXPQPPUgrV2K5a1itZKSxPkBgxo4p/view?usp=drive_link
> I didn’t mention certain details in the video due to the time limitation (the video must be at most 10 minutes long).
> Some of the things I didn’t show are:
> 1)The validations on the fields of the objects in the classes User, Course, CourseInstance, Department, Enrollment
> (for example: the required minimum password length, the minimum capacity, the maximum department name length, etc.).
> 2)How the system prevents enrollment in a specific course instance once the capacity limit of that course instance
> has been reached.
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
## Things you need to know:
>1)I assume that all departments consist of 140 total credits.
> 2)I am referring to the field year in the CourseInstance class as the field that represents the year in which this
> course is taught. For example, if it is a first-year course in the degree, then year = 1; and if it is
> a second-year course in the degree, then year = 2.
> 3)For each CourseInstance, there may be more than one lecturer, teaching in different semesters.

## Credentials 

| Role     | Username   | Password   |
|----------|------------|------------|
| Admin    | `admin`    | `admin123` |
| Student  | `yaelm`    | `yael123`  |
| Student  | `Markme`   | `stud123`  |
| Student  | `student`  | `stud123`  |
| Student  | `danacoh`  | `123456`   |
| Lecturer | `adamg`    | `adam123`  |
| Lecturer | `lecturer` | `lect123`  |

> You can modify or extend these users through the Admin panel.


---

## Notes

- The `UserSession` bean is session-scoped and holds user info after login.
- All forms are validated server-side; invalid data will display error feedback.
- In case of unhandled exceptions, the user is redirected to a custom `500` error page.
- The `admin-panel` is fully protected and available only to users with `ADMIN` role.
