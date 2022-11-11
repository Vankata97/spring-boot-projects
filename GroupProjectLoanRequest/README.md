# Team-1-Deyan Georgiev

Banking software for loan request application.

## Description

```
   This is a banking software that has database with clients e-mails.

   Bank sends e-mail with a proposal to its clients where they can apply for 
   a loan request in a few steps:
   1. First they fill in their personal information.
   2. On the second step they fill in their financial status.
   When the information below is sent to the bank, and if the given information 
   meets the loan request criteria bank sends credit offer.
   3. Every client that is approved receives a bank offer and is able to respond to it.
   The rest of the clients receives a rejection e-mails.
   4. If the response is accurate, the client receives an e-mail with payment schedule 
   for the whole period (separated month by month) and registration token.
   5. The client continues and registers in the online banking system with username and password.
   Password confirmation is required from the client. The system also allows the client to upload 
   pdf files like ID card, driving license and etc. with restriction of the file size.
  
  After successful registration the registered clients of the bank can access their data including 
  personal information and credit details.

  The application allows the bank adminisrators to access, maitain and support registered 
  client requests while using the bank software. 
 
```
## Project status

```
First release November 2022.
```
## Installation

```
RESTful API.
Requred software for usage - InteliJ IDEA, SQL Oracle DB, Postman.
```
## Functionalities

| Controller name               | Authentication | Method/Url                              | USER | ADMIN | EVERYONE |
| ----------------------------- |----------------|-----------------------------------------| ---- |-------|----------|
| RegistrationController        | NO             | Post("/svc/registration")               | YES  | YES   | YES      |
|                               | YES            | Post("/svc/login")                      | YES  | YES   | NO       |
|                               | YES            | Post("/svc/roles")                      | NO   | YES   | NO       |
| PersonalInformationController | NO             | Post("/svc/personalinformation")        | YES  | YES   | YES      |
|                               | YES            | Put("/svc/personalinformation/{id}")    | NO   | YES   | NO       |
|                               | YES            | Delete("/svc/personalinformation/{id}") | NO   | YES   | NO       |
|                               | YES            | Get("/svc/personalinformation/{id}")    | NO   | YES   | NO       |
|                               | YES            | Get("/svc/personalinformation")         | NO   | YES   | NO       |
| FinancialStatusController     | NO             | Post("/svc/financialstatus")            | YES  | YES   | YES      |
|                               | YES            | Get("/svc/financialstatus")             | NO   | YES   | NO       |
| FileController                | YES            | Post("/svc/upload")                     | YES  | YES   | NO       |
| EmailController               | YES            | Post("/svc/sendMail")                   | NO   | YES   | NO       |
| ClientResponseController      | NO             | Post("/svc/response")                   | YES  | YES   | YES      |
| ClientInfoController          | YES            | Get("/svc/clientinfo")                  | YES  | NO    | NO       |
| ClientController              | YES            | Post("/svc/client")                     | NO   | YES   | NO       |
|                               | YES            | Put("/svc/client/{id}")                 | NO   | YES   | NO       |
|                               | YES            | Delete("/svc/client/{id}")              | NO   | YES   | NO       |
|                               | YES            | Get("/svc/client/{id}")                 | NO   | YES   | NO       |
|                               | YES            | Get("/svc/client")                      | NO   | YES   | NO       |
| BankOfferController           | NO             | Get("/svc/bankoffer")                   | YES  | YES   | YES      |
## Authors

```
Ismet Brahimbashev
Ivan Hadjiev
Vladislav Stamenov
Ana Popova
```
## License
Team #1.
