📱 Aura-Telecom: Advanced GSM Billing & Subscriber Management
Aura-Telecom is a high-performance GSM billing simulation engine developed as part of the Software Engineering curriculum at Karadeniz Technical University (KTÜ). The system orchestrates complex subscriber operations, real-time service cost calculation, and automated financial auditing for telecommunication providers.

🛠 Core Architecture
The project is built on a robust Object-Oriented (OOP) foundation with four specialized modules:

Subscriber: Manages user identity, age-based demographics, and service interactions.

ServiceProvider: The "Brain" of the system. It handles the dynamic pricing engine for Voice, SMS, and Data services.

Invoice: Manages real-time spending tracking, credit limits, and automated service blocking.

SimulateSystem: The command-center providing a comprehensive CLI for administrative and customer operations.

⚡ Key Technical Features
1. Dynamic Pricing Engine

The system applies real-time cost calculation based on two primary variables:

Time-Sensitive Rates: Automatic discounts for services used during off-peak hours (e.g., night-time internet/calls).

Demographic Incentives: Integrated discount logic for student users (age < 18) and senior citizens (age > 65).

2. Smart Financial Guardrails

Automated Limit Enforcement: Every transaction is checked against the user's usageLimit before execution.

Identity Management: Features unique ID generation logic for both Providers (500-600) and Subscribers (1000+).

3. Comprehensive Service Support

Voice Calls: Variable duration billing with multi-layered discount application.

Messaging: Bulk-sending support with specific quantity-based bonus discounts.

Internet: Data consumption tracking with specific time-window incentives.

🚀 Getting Started
Prerequisites

Java Development Kit (JDK) 8 or higher.

Compilation & Execution

To run the system locally, navigate to the project directory and execute:

Bash
javac *.java
java SimulateSystem
🎓 Academic Context
This project was developed by a 2nd-year Software Engineering student at Karadeniz Technical University to demonstrate proficiency in Java OOP principles, class relationships, and algorithmic cost management.
