# ✈️ SkyReserve: Flight Reservation Application

SkyReserve is a robust, full-stack flight reservation system designed to provide a seamless ticket booking experience. This project demonstrates a complete end-to-end implementation including a responsive frontend, a high-performance backend, and cloud-native deployment on AWS.

---
<img width="1000" height="700" alt="Gemini_Generated_Image_62b65e62b65e62b6" src="https://github.com/user-attachments/assets/1b4c0e9e-6433-4736-989b-313cca38bb23" />

## 🛠️ Tech Stack

### **Frontend (Client Side)**
* **Runtime:** Node.js & npm
* **Web Server:** Apache HTTP Server (Apache2)
* **Technologies:** HTML5, CSS3, JavaScript (React/Vite)

### **Backend (Application Layer)**
* **Language:** Java
* **Build Tool:** Apache Maven
* **Servlet Container:** Apache Tomcat
* **Framework:** Spring Boot

### **Database (Data Tier)**
* **Engine:** SQL (MySQL)
* **Architecture:** Relational Schema

### **Infrastructure & DevOps**
* **Cloud Provider:** AWS (Amazon Web Services)
* **Deployment:** EC2 Instance
* **Version Control:** Git & GitHub

---

## 🏗️ System Architecture

SkyReserve follows a classic **3-Tier Architecture** to ensure scalability and separation of concerns:

1.  **Presentation Tier:** User interface hosted via Apache2, handling client-side routing and responsive design.
2.  **Logic Tier:** A Java-based backend managed by Maven and running on a Tomcat server to process business logic and API requests.
3.  **Data Tier:** A persistent SQL database for secure storage of user profiles, flight schedules, and booking records.

---

## ✨ Key Features

* 🔍 **Flight Discovery:** Search by source, destination, and date.
* 🎫 **Booking Engine:** Real-time seat reservation and ticket generation.
* 🔐 **Secure Auth:** User registration and login with encrypted credentials.
* 📱 **Responsive Design:** Optimized for desktop, tablet, and mobile views.
* 📊 **User Dashboard:** Manage personal bookings and cancellation workflows.

---

## 📂 Modules

### 👤 User Module
* Secure authentication and profile management.
### ✈️ Flight Module
* Dynamic flight searching and availability tracking.
### 🎟️ Booking Module
* End-to-end transaction handling from selection to confirmation.
### 🛠️ Admin Module
* Administrative oversight for flight schedules and user management.

---

## 🚀 Deployment (AWS)

The application is deployed on **AWS EC2**, utilizing:
* **Security Groups:** Configured for HTTP (80), HTTPS (443), and custom application ports.
* **Apache2 Reverse Proxy:** Routing traffic efficiently to the Tomcat backend.
* **Lifecycle Management:** Maven-based builds for consistent deployment cycles.

---

## 🔮 Future Enhancements

* 💳 **Payment Gateway:** Integration with Stripe or Razorpay for real-world transactions.
* 📧 **Automated Alerts:** Email and SMS notifications for booking confirmations.
* 💺 **Interactive Seat Map:** Visual seat selection during the booking process.
* 🚢 **Containerization:** Moving to a Docker and Kubernetes-based orchestration.

---

## 📜 Conclusion
SkyReserve serves as a comprehensive showcase of modern web engineering, combining a reliable Java backend with a modern frontend, all orchestrated within the AWS cloud ecosystem.
