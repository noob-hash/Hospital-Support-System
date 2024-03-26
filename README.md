# Hospital-Support-System

## Introduction

In the hospital, it is difficult to keep track of every patient's data. These data are specially related to patients’ 
medical history, allergies, etc. Such data are important because they can be used as reference points for patients' 
health and for study purposes. 

Hospital Support System (HSS) is a web application designed to store patient record data like past medical history, 
reports, allergies, etc. such that the data can be easily accessed, shared, and viewed. It is a handy application 
for large hospitals as they need to manage a large amount of information about patients which can easily be lost or 
mismanaged which can affect the overall healthcare a patient can get. As such, the Hospital Support System can help both 
doctor and patient by keeping track of data and by making the information easy to access. It is a combination of a web 
portal and database with a graphical user interface to make it easier to use for any person. Doctors can use it for 
retrieving and updating information while users use it to make an appointment and retrieve online reports and 
diagnoses through the web. 

## Objective

The Hospital Support System is an organized computerized system designed and programmed to deal with day-to-day 
operations and management of hospital activities. The primary scope of the system is on managing medical records and 
making those records ready for concerned parties to use.

With the development of technology, the hospital must be also able to manage the needs of large customers. 
The history and medical record of the patient is a very important asset in health care. Thus, patient data management 
should be paid more attention. The Hospital Support System is the solution to large paper management and large data management 
of patient records. As the population increases it is obvious that more and more need for patient data management to manage a 
large amount of data more effectively and securely but also be able to make it easier and more efficient to use for users.

## Dependencies

The following tools and technologies are necessary to implement and run the system:
1. MySQL
2. Tomcat Apache server
3. Java IDE (preferably Apache NetBeans)
4. Bootstrap
5. Graph.js

### Hardware Specifications
* Processor -> Intel core i3 and above
* RAM Capacity -> 4 GB and above
* Hard Disk -> 256 GB and above

### Software Specifications
* Operating System -> Windows 7 and above
* Browser -> Chrome, Edge, etc
* Web/Application Server -> Tomcat 9.5
* Database Server -> Apache
* Database Connectivity-> JDBC  
* Other Tools & Technologies -> Java (JDK), Servlets, iText library, JSP
Jar files used in the project are provided in the '/src/jar' folder
### Note:
The Hardware, OS, and Browser specifications only mention what it was developed and tested upon providing what minimum technology is needed to operate the system.

## System Design

### Introduction
Systems design is the process of defining elements of a system like modules, architecture, components, and their interfaces and data for a system based on the specified requirements. It is the process of defining, developing, and designing systems that satisfy the specific needs and requirements of a business or organization.

### High-Level Design
 ![image](https://github.com/noob-hash/Hospital-Support-System/assets/80933227/f47d5971-b9ef-420f-a53a-b9a5e3005c8b)

Figure: High-level system design

The working mechanics of the HSS is based on client-server architecture. The user can perform different actions based on their roles from different devices through the internet accessing the system on a certain location provided as an IP address. 

### Process Modelling
* Use Case Diagram
![image](https://github.com/noob-hash/Hospital-Support-System/assets/80933227/223f9a2e-3399-4d1e-8482-0550bb7f2682)
Figure: Use Case Diagram for HSS

* ER Diagram
Entity Relationship diagram shows how data is kept in the database and the relationship between data. It helps in designing the database and the flow of data in the system. In ER diagram elements and the relation between elements are shown in pectoral format using lines, circles, rectangles, etc.

![image](https://github.com/noob-hash/Hospital-Support-System/assets/80933227/7e0e82ed-6fda-4074-9ff5-f139cc669fef)
Figure: Entity Relationship Diagram for HSS

*	Data Flow Diagram
A data-flow diagram is a way of representing a flow of data through a process or a system. The DFD also provides information about the outputs and inputs of each entity and the process itself. A data-flow diagram has no control flow — there are no decision rules and no loops.

![image](https://github.com/noob-hash/Hospital-Support-System/assets/80933227/c0270200-8a10-481a-8280-79348b3417e2)
Figure: Context diagram of the HSS
 
![image](https://github.com/noob-hash/Hospital-Support-System/assets/80933227/8217ebd2-d7a9-47d8-a072-0113d20e78b3)
Figure: Level 0 DFD of the HSS

 ![image](https://github.com/noob-hash/Hospital-Support-System/assets/80933227/452f14d5-d221-4ace-9305-ca9e2aae5608)
Figure: Level 1 DFD of HSS

 ![image](https://github.com/noob-hash/Hospital-Support-System/assets/80933227/d1611966-8a7a-4423-8ffd-52d288cd674a)
Figure: Level 2 DFD of HSS

## Testing
Testing is the process of executing a program to find errors. To make our software perform well it should be error-free. If testing is dome successful, it will remove all the errors from the software. It is the process of evaluating and verifying that a software product or application does what it is supposed to do. The benefits of testing include preventing bugs, reducing development costs, and improving performance.

![image](https://github.com/noob-hash/Hospital-Support-System/assets/80933227/c9197239-d648-422b-a356-255ad08c4e74)
![image](https://github.com/noob-hash/Hospital-Support-System/assets/80933227/29a0da93-f067-4ee5-b12c-7007079db17f)

Table: Verification & Validation testing result

For more comprehensive detail of the project check the project proposal and report.
