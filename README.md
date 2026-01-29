iMarble Management System 

The iMarble Management System is a complete backend solution designed for managing operations in a marble business. It covers orders, clients, stock, purchases, deliveries, staff management, attendance, salary, and role-based access using Spring Security and JWT.

The system ensures smooth workflow across different roles including Admin, Manager, Receptionist, Dispatcher, and Client.

Overview:

The application follows a role-based access model. Each user is assigned one of the following roles:

Admin – full access across all modules

Manager – monitoring access (orders, staff, reports)

Receptionist – order and client creation/update

Dispatcher – access to orders related to delivery

Client – can view and update own profile and check own orders

All sensitive endpoints are protected using JWT authentication.

Key Features:
Order Management:

Allows creation and updating of orders by Receptionist

Enables Managers to view all orders across the system

Admin has unrestricted access for creating, updating, viewing, and deleting orders

Dispatchers can view orders that are assigned or pending for delivery

Clients can securely view only their own orders using JWT authentication and role validationView orders based on role:

Client → own orders only

Receptionist → create & update

Manager → all orders

Dispatcher → delivery orders only

Product & Inventory Management

Manage products, brands, and categories

Track purchase entries and purchased items

Maintain stock with in/out entries

Update stock on purchase and sale

Track returns and adjustments

Client & Shop Management:

Add and manage client records

Shops stored and linked with orders

Clients can update their own profile

Staff Management:

Add and manage staff details

Attendance tracking

Salary generation and salary records

Manage staff advance payment

Track staff activity

Delivery System:

Track delivery status

Assign orders to delivery

Maintain delivery items

Dispatcher role with restricted access

Notifications:

System-wide notifications

Role-wise notification visibility

Order and delivery update alerts

Technology Stack:

Backend: Spring Boot (Spring MVC, Spring Security)

Authentication: JWT

Database: MySQL, JPA/Hibernate

Build Tool: Maven

Validation: Hibernate Validator

Architecture: Controller → Service → Repository (3-Layered Structure)

Mapping: DTO-based design to avoid exposing entity objects

Security Implementation:

JWT token is generated during login.

Every protected endpoint verifies the token and role permissions.

Spring Security loads user details and enforces authorization through annotations.

Why This Project Was Built:

This system was developed to bring automation to marble shop operations such as:

Maintaining accurate order records

Managing staff and client information

Reducing manual work in delivery tracking

Ensuring data consistency

Providing secure access based on roles

Future Improvements:

Generating PDF invoices

Real-time notifications using WebSocket

Dashboard UI for different roles

Product inventory and stock management
