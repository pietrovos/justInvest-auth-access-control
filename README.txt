# Secure Authentication & Access Control System  

A Java-based prototype implementing **user authentication**, **access control**, and a **proactive password policy** for a fictional financial platform, *justInvest*.  
Developed as part of the **SYSC 4810A: Introduction to Software and Network Security** course.  

## 📌 Features  
- **Role-Based Access Control (RBAC)** enforcing different permissions for clients, premium clients, and employees.  
- **Secure password storage** with salted hashing.  
- **Proactive password checker** enforcing:  
  - Length between 8–12 characters  
  - At least 1 uppercase, 1 lowercase, 1 digit, and 1 special character  
  - No common weak passwords  
  - No password matching the username  
- **Graphical User Interface (GUI)** for signup and login.  
- **Preset test accounts** for quick demonstration.  

## 🚀 How to Run  
**⚠️ Important:** Running `Problem2cTest` will clear the password file and remove preset users (due to `@BeforeAll`). Run it **last** if you wish to keep preset accounts for testing.  

1. **Download** the ZIP folder into:  /home/sysc4810/Downloads/

2. **Extract** the contents into:  /home/sysc4810/Downloads/

3. Open a terminal and navigate to:  cd /home/sysc4810/Downloads/SYSC4810_Assignment/out/artifacts/SYSC4810_Assignment_jar/

4. Run the application:  java -jar SYSC4810_Assignment.jar

5. The GUI will appear. You can **Sign up** for a new account or **Login** using one of the preset accounts below.  

## 👤 Preset Accounts  
| Username        | Password         | Role                  |
|-----------------|------------------|-----------------------|
| SashaKim        | SashaKim1!       | Client                |
| EmeryBlake      | EmeryBlake1!     | Client                |
| NoorAbbasi      | NoorAbbasi1!     | Premium Client        |
| ZuriAdebayo     | ZuriAbe1!        | Premium Client        |
| MikaelChen      | MikaelChen1!     | Financial Advisor     |
| JordanRiley     | JordanRil1!      | Financial Advisor     |
| EllisNakamura   | EllisNak1!       | Financial Planner     |
| HarperDiaz      | HarperDiaz1!     | Financial Planner     |
| AlexHayes       | AlexHayes1!      | Teller                |
| AdairPatel      | AdairPatel1!     | Teller                |

> Roles for new signups must be manually assigned by modifying the `passwd.txt` file.  

## 🛠 Technologies Used  
- **Java** (Core + Swing for GUI)  
- **File-based password storage** with hashing & salting  
- **Role-Based Access Control** logic  

## 📄 License  
This project was completed as part of an academic assignment and is intended for educational purposes.  








