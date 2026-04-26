
#  SkyReserve – Flight Reservation System (AWS Infrastructure Deployment)

SkyReserve is a full-stack flight reservation system that allows users to search, book, and manage flights. This guide provides step-by-step instructions to deploy the application on AWS infrastructure using a single EC2 instance.

---

##  Deployment Overview

This deployment uses:

* EC2 instance (Ubuntu)
* MySQL (installed locally on server)
* Spring Boot (Backend)
* React + Vite (Frontend)
* Apache2 (Web Server)

>  Note: This setup uses a local MySQL database instead of AWS RDS for simplicity.

---

##  Steps to Deploy Application

---

## 🔹 1. Clone Repository

```bash
git clone https://github.com/OmkarMemane09/SkyReserve.git
cd SkyReserve
```

---

## 🔹 2. Install MySQL Server and Setup Database

```bash
apt update -y
apt install mysql-server -y
```

Secure MySQL installation:

```bash
mysql_secure_installation
```

Login to MySQL:

```bash
mysql -u root -p
```

Run the following SQL commands:

```sql
CREATE USER 'linux' IDENTIFIED BY 'Redhat';
GRANT ALL PRIVILEGES ON *.* TO 'linux';
FLUSH PRIVILEGES;

CREATE DATABASE flightdb;
EXIT;
```

> This creates a dedicated user and database for the application.

---

## 🔹 3. Deploy Backend (Spring Boot)

Navigate to backend directory:

```bash
cd FlightReservationSystem
```

Install required dependencies:

```bash
apt install openjdk-17-jdk -y
apt install maven -y
```

Set environment variables:

```bash
export DATASOURCE_URL="jdbc:mysql://localhost:3306/flightdb"
export DATASOURCE_USER="linux"
export DATASOURCE_PASSWORD="Redhat"
export FRONTEND_URL="http://<YOUR_PUBLIC_IP>:80"
```

>  Replace `<YOUR_PUBLIC_IP>` with your EC2 instance public IP.

Build the application:

```bash
mvn clean package
```

---

## 🔹 4. Deploy Frontend (React + Vite)

Open a new terminal session and run:

```bash
cd ~/SkyReserve/frontend
```

Install Node.js and npm:

```bash
apt install nodejs npm -y
```

Set backend API URL:

```bash
export VITE_API_URL=http://<YOUR_PUBLIC_IP>:8080
```

Install dependencies and build:

```bash
npm install
npm run build
```

---

## 🔹 5. Setup Web Server (Apache)

```bash
apt install apache2 -y
```

Copy build files to web root:

```bash
cp -r dist/* /var/www/html/
```

Start Apache:

```bash
systemctl start apache2
systemctl enable apache2
```

>  Frontend will be accessible at: `http://<YOUR_PUBLIC_IP>`

---

## 🔹 6. Run Backend Application

After frontend is deployed, go back to backend terminal:

```bash
java -jar target/flight*.jar
```

> Backend runs on port `8080`

---

##  Application Flow

1. User accesses frontend via browser
2. Frontend sends API requests to backend
3. Backend processes logic and interacts with MySQL
4. Response is returned to frontend and displayed to user

---

##  Ports Used

| Service  | Port |
| -------- | ---- |
| Frontend | 80   |
| Backend  | 8080 |
| MySQL    | 3306 |

>  Ensure these ports are open in your EC2 Security Group.

---

##  Security Notes

* Avoid using root MySQL user for applications
* Use environment variables for sensitive credentials
* Restrict inbound traffic in Security Groups
* Consider using HTTPS (SSL) for production

---


##  Conclusion

SkyReserve demonstrates a complete deployment of a full-stack application on AWS EC2, integrating frontend, backend, and database within a single instance. It provides hands-on experience with real-world deployment practices.

---

## 👨‍💻 Author

**Omkar Memane**
GitHub: https://github.com/OmkarMemane09

---
