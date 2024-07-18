CloudVendor API
This is a simple Spring Boot application that provides CRUD operations for managing Cloud Vendors. 
The application includes several best practices for logging, exception handling, testing, monitoring, and API documentation using Swagger.

Features
CRUD Operations
The API supports the following operations:

Create Cloud Vendor: public String createCloudVendor(CloudVendor cloudVendor);
Update Cloud Vendor: public String updateCloudVendor(CloudVendor cloudVendor);
Delete Cloud Vendor: public String deleteCloudVendor(Integer cloudVendorId);
Get Cloud Vendor by ID: public CloudVendor getCloudVendorById(Integer cloudVendorId);
Get All Cloud Vendors: public List<CloudVendor> getAllCloudVendors();

Logging
Added logging using SLF4J and Logback to keep track of application events and errors.

Monitoring
Integrated Spring Boot Actuator for application monitoring.
Added a custom actuator endpoint to expose specific application metrics.

Exception Handling
Implemented centralized exception handling to provide consistent and informative error responses.

Response Handling
Added a custom response handler to standardize API responses.

Testing
Conducted thorough testing of all logic using JUnit, Mockito, and AssertJ.
Utilized H2 database for in-memory testing.
All tests passed successfully.

Documentation
Integrated Swagger for API documentation with a custom configuration class.

Getting Started

Prerequisites

Java 17 or higher
Maven 3.6.0 or higher

Installation
Clone the repository:
Build the project using Maven:


mvn clean install
Run the application:
mvn spring-boot:run

Usage
The API can be accessed via the following endpoints:

Create Cloud Vendor:
POST /api/cloudvendors

Update Cloud Vendor:
PUT /api/cloudvendors

Delete Cloud Vendor:
DELETE /api/cloudvendors/{id}

Get Cloud Vendor by ID:
GET /api/cloudvendors/{id}

Get All Cloud Vendors:
GET /api/cloudvendors

Swagger API Documentation
Access the Swagger UI to explore and test the API endpoints:
http://localhost:8080/swagger-ui.html (change the port accordingly)
http://localhost:8080/api-docs


Actuator Endpoints
Access monitoring and custom actuator endpoints:
Health Check:
GET /actuator/health

Custom Actuator Endpoint:
GET /actuator/custom-endpoint

Contributing
Contributions are welcome! Please create a pull request with your changes or open an issue for any bugs or feature requests.

License
This project is Free for anyone
