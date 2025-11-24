\# Campus Course \& Records Manager (CCRM)



A console-based Java application for managing students, courses, enrollments, and records for an educational institute.



---



\## How to Run



\* \*JDK Version:\* Java 17 or later is recommended.

\* \*IDE:\* This project is designed to be run from an IDE like Eclipse.

\* \*Steps to Run:\*

&nbsp;   1.  Import the project into your Eclipse workspace (File > Import > General > Existing Projects into Workspace).

&nbsp;   2.  Locate the CCRMApp.java file inside the src/edu/ccrm/cli package.

&nbsp;   3.  Right-click on the CCRMApp.java file.

&nbsp;   4.  Select "Run As > Java Application".

\* \*Notes on Enabling Assertions:\* To enable assertions in Eclipse, go to Run > Run Configurations..., select your CCRMApp configuration, go to the Arguments tab, and in the VM arguments box, type -ea.



---



\## Core Java Concepts Demonstrated



| Concept / Requirement             | File / Class / Method                                       |

| --------------------------------- | ----------------------------------------------------------- |

| \*OOP - Inheritance\* | Student.java and Instructor.java extend Person.java.    |

| \*OOP - Abstraction\* | Person.java is an abstract class with an abstract method.   |

| \*OOP - Encapsulation\* | All domain classes use private fields with public getters.    |

| \*OOP - Polymorphism\* | getProfile() is overridden in Student and Instructor.   |

| \*Design Pattern - Builder\* | Course.java uses a static nested Builder class.           |

| \*Java SE - Lambdas \& Streams\* | CourseService.java \& DataIOService.java use streams.      |

| \*Java SE - NIO.2 File I/O\* | DataIOService.java uses Path and Files for I/O.         |

| \*Java SE - Recursion\* | DataIOService.java uses the calculateDirectorySize() method.|

| \*Java SE - Custom Exceptions\* | EnrollmentException.java is thrown by EnrollmentService.  |

| \*Java SE - Enums with Fields\* | Grade.java has a constructor and gradePoint field.        |

| \*Java SE - Date/Time API\* | Enrollment.java \& DataIOService.java use LocalDate/LocalDateTime.|



---



\## Java Architecture Explained



\### Java ME vs. SE vs. EE



| Feature        | Java ME (Micro Edition)                             | Java SE (Standard Edition)                            | Java EE (Enterprise Edition)                             |

| -------------- | --------------------------------------------------- | ----------------------------------------------------- | -------------------------------------------------------- |

| \*Target\* | Small, resource-constrained devices (e.g., old feature phones, sensors). | General-purpose desktop, server, and console applications. | Large-scale, distributed, network-based enterprise applications. |

| \*Core API\* | A small subset of the Java SE API.                  | The foundational, core Java API (Core Java).          | A superset of the Java SE API with additional libraries.   |

| \*Use Case\* | Embedded systems, early mobile apps.                | Desktop applications (like this project), servers.    | Web services, e-commerce platforms, banking software.    |



\### JDK vs. JRE vs. JVM



\* \*JVM (Java Virtual Machine):\* An abstract machine that provides the runtime environment to execute Java bytecode. It is what makes Java "platform-independent".

\* \*JRE (Java Runtime Environment):\* The software package that contains everything needed to run a compiled Java application. It includes the JVM and core Java libraries.

\* \*JDK (Java Development Kit):\* A superset of the JRE. It contains everything in the JRE, plus the tools necessary to develop Java applications, such as the compiler (javac) and debugger.



---

\## Setup Screenshots



(This section is where you would manually add your screenshots if desired, but having them in the screenshots folder is the main requirement.)

