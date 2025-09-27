# OOP-Railway-System-Project
This project is a comprehensive Railway Management System designed using Java and object-oriented programming principles.  Key functionalities include user account management, ticket booking, payment processing, and train scheduling


# Classes:

Main.java – Entry point; menu-driven interface for managers (manage trains, stations, routes, bookings) and users (book/view/cancel tickets). Integrates managers and user classes with error handling.

Person.java – Abstract base class with name, email, getters, and abstract displayDetails(). Parent for Manager and User.

User.java – Extends Person; manages bookings and payments. Provides methods to add/remove/view bookings and payments. Implements displayDetails().

Booking.java – Connects User with Train; manages seat availability, ticket creation, and payment processing.

Ticket.java – Represents tickets (ID, booking, train, seat). Uses composition with Booking. Provides ticket details and unique ID generation.

CreditCardPayment.java – Implements Payment; simulates credit card processing.

PayPalPayment.java – Implements Payment; simulates PayPal payments with email/password.

StationManager.java – Manages Station objects. Implements Manageable; supports add, update, delete, search, and list retrieval.

Train.java – Represents trains (ID, name, capacity, seats). Handles booking, availability, and train updates.

TrainManager.java – Manages multiple Train objects. Implements Manageable; supports CRUD operations and searching.

Route.java – Represents a route with stations and distance calculation. Interacts with StationManager.

Station.java – Represents a station (ID, name, location). Supports updating and displaying stations.

Manager.java – Extends Person; adds username, password, and validation for authentication.

ManagerAuth.java – Handles manager login/account creation with static HashMap.

Manageable.java – Generic interface for CRUD operations, promoting reusability across managers.



# OOP Concepts Used

Abstraction: Person (abstract class) and Payment (interface) define templates without implementation.

Encapsulation: Classes like User, Train, and StationManager encapsulate data and expose methods for controlled access.

Inheritance: User and Manager extend Person, reusing and customizing common attributes.

Polymorphism: displayDetails() implemented differently in User and Manager; Payment interface allows different payment types (CreditCard, PayPal).

Composition: Booking contains Ticket and links to Train and User.

Interfaces/Generics: Manageable<T> provides a reusable contract for entity management.



<img width="1091" height="756" alt="image" src="https://github.com/user-attachments/assets/00262610-bad7-481e-8935-272f478e03be" />

