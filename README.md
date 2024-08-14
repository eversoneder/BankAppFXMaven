# Econo Bank

## Introduction
Econo Bank is a simple banking application developed in Java, featuring a JavaFX Graphic User Interface (GUI) with design elements created in Corel Draw. This project was built using the Model-View-Controller (MVC) architectural pattern to enhance safety through encapsulation with strict access modifiers. It serves as a learning platform for JavaFX, building upon my previous experience with JavaSwing GUI. The application aims to provide a practical example of a banking system with a modern interface and robust backend storage using a MySQL database.

To further ensure security and efficient memory management, the application implements the singleton pattern across all classes, allowing only one instantiation of each class.

<img src="BankAppFXMaven/src/main/resources/img/econobankexample.png" alt="Econo Bank Example" width="800">
[Check the App Pages Structure by clicking here!](https://github.com/eversoneder/BankAppFXMaven/blob/master/Econo_Bank_App_Structure.md%29)


## Features
- Sign-up/Log-in system
- Account management capabilities
- Transaction functionalities: deposit, withdraw, transfer
- Account balance and transaction history (statements)
- Editable user account information

## Technologies
- Java
- JavaFX for the GUI
- Corel Draw for GUI design elements
- MySQL Workbench for database management

## Prerequisites
Prerequisites needed to run the application:
- Java Development Kit (JDK) version: 21
- MySQL Workbench 8.0 CE installation

Additionally, the 'SQL DB Script' located in the repository is required to set up the database schema.

## Installation
Follow these steps to set up your development environment:
1. Clone the repository
2. Install JDK 21
3. Install MySQL Workbench 8.0 CE
4. Run the 'SQL DB Script' code in your MySQL Workbench to create the database schema

## Configuration
The database is configured to connect to `localhost` (127.0.0.1). The connection settings can be found and modified in the 'DatabaseService' java class.

## Contributors
- Everson Spinola <everson_spinola@hotmail.com>

## Contact
For any queries or support, please contact me at everson_spinola@hotmail.com.
