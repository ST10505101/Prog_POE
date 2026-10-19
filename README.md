# Prog_POE
## Part 1:
### Overview:
This repository contains the solution for **Part 1** of this Portfolio of evidence (POE). The project implements a secure **Registration and Login feature** in Java.

-----
### Features:
* **Username validation:** Ensures usernames contain anunderscore (`_`) and are no more than 5 characters long.
* **Password Complexity Checking:** Verifies that passwords are at least 8 characters long and contain a capital letter, a number, and a special character.
* **Cell Phone Number Verification:** Useses Regular Expressions (RegEx) to validate South African cell phone numbers with the international country code (`+27`).
* **User Authentication:** Handles user registration, secure login validation, and status messaging.

-----
### Tech Stack and Tools:
* **Language:** Java
* **Development Environment (IDE):** Visual Studio Code
* **Testing Framework:** JUnit (run via Apache NetBeans)
* **Version Control:** Git & GitHub

-----
### Testing:
Unit tests are implemented using JUnit to verify functionality against the POE specification guidelines (including both assertEquals and assertTrue/assertFalse assertions).

---
## Part 2:
### Overview:
This section contains the solution for **Part 2** of the Portfolio of Evidence (POE). The project implements the interactive **QuickChat Messaging System** in Java, extending Part 1 with advanced message validation, tracking, hashing, and local file storage capabilities.

-----
### Features:
- **Message Length Validation:** Ensures that message text does not exceed 250 characters, providing dynamic error messages with the exact character excess or a success confirmation.
- **Recipient Cell Verification:** Validates recipient cell phone numbers to ensure proper formatting and compliance with international code requirements (`+27`).
- **Unique Message ID Generation:** Automatically generates a random 10-digit tracking identifier for each processed message[cite: 1].
- **Message Hash Creation:** Autogenerates a capitalized custom hash combining the ID prefix, message index, and the first and last words of the text[cite: 1, 2].
- **Interactive Message Actions:** Allows users to choose between sending a message, disregarding it, or storing it to send later[cite: 1, 2].
- **JSON File Storage:** Automatically serializes and saves stored message details into local JSON files[cite: 1, 2].
- **Summary Tracking:** Accumulates and displays the total number of successfully processed messages at the end of the session.

-----
### Tech Stack and Tools:
- **Language:** Java
- **Development Environment (IDE):** Visual Studio Code / Apache NetBeans
- **Testing Framework:** JUnit 
- **Version Control:** Git & GitHub

-----
### Testing:
Comprehensive unit tests are implemented in `MessageTest.java` using JUnit to verify message length limits, cell number formatting, hash creation, ID generation, and user action selections against the POE specification guidelines.

-----
## Author:
Ammaarah Mostert- ST10505101