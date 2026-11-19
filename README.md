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

------

## Part 3 — Message Management, Data Persistence & Unit Testing

### 🚀 Overview & New Features
Part 3 expands QuickChat to support full message categorization, JSON persistence, dynamic searching, and structured reporting.

* **Dynamic Array & List Management:**
  * `sentMessages`: Stores all messages with the `"Sent"` status flag.
  * `disregardedMessages`: Stores all draft messages flagged as `"Disregard"`.
  * `storedMessages`: Manages all offline messages saved with the `"Stored"` status flag.
  * `messageIDs` & `messageHashes`: Tracks all system-generated 10-digit IDs and message hashes across the application lifecycle.

* **JSON File Persistence & Startup Restoration:**
  * Implemented automated JSON generation (`storeMessage()`) which exports stored message instances to individual files (`message_<n>.json`).
  * Integrated startup parsing (`Message.loadStoredMessagesFromJson()`) to scan the project directory on launch and reconstruct stored message objects back into memory without duplicate entry.

* **Stored Messages Submenu (Main Menu Option 4):**
  * **a) Senders & Recipients:** Displays sender and recipient details for all saved messages.
  * **b) Longest Message:** Computes and returns the longest message body across all recorded messages.
  * **c) Search by Message ID:** Locates any message by its unique 10-digit ID and returns recipient and text details.
  * **d) Search by Recipient:** Queries both sent and stored messages to list all communications associated with a specific cell phone number.
  * **e) Delete by Message Hash:** Deletes a targeted message record using its exact hash key and returns a deletion status confirmation.
  * **f) Full Report Generation:** Produces a structured report detailing the Message Hash, Recipient, and Message text for stored/sent entries.

---

### 🧪 Unit Testing & Quality Assurance
Automated unit tests were implemented using **JUnit 5** in `MessageTest.java` to achieve comprehensive coverage across all business logic and edge cases:

1. **Sent & Disregarded Array Verification:** Asserts that disregarded messages are correctly recorded in `disregardedMessages` while remaining excluded from `sentMessages`.
2. **JSON Loading & Persistence:** Verifies that stored messages write to JSON, clear from memory on reset (`resetArrays()`), and reload cleanly from file storage.
3. **ID & Recipient Search Verification:** Ensures valid message lookup via `searchByMessageID()` and verifies that non-existent IDs return graceful error notifications.
4. **Recipient Multi-Message Search:** Confirms `searchMessagesByRecipient()` aggregates all messages for a targeted recipient across both sent and stored collections.
5. **Hash Deletion Testing:** Verifies `deleteMessageByHash()` removes the specified record and returns the required system status output.
6. **Report Generation Output:** Validates that output reports contain all mandatory fields (`Message Hash`, `Recipient`, and `Message`).

-----
## Author:
Ammaarah Mostert- ST10505101