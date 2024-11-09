# AccountManagement

## Domain

### Entities
#### Email
| **Attribute** | **Description**  | **Java Type** |
|:-------------:|:----------------:|:-------------:|
|    emailId    | Email Identifier |     Long      |
| emailAddress  |  Email Address   |    String     |

#### User
| **Attribute**  | **Description**  |  **Java Type**   |
|:--------------:|:----------------:|:----------------:|
|    userUuid    |    User UUID     |       UUID       |
|    userName    |    User Name     |      String      |
|    password    |     Password     |      String      |
|     email      |      Email       | [Domain](#email) |
| inclusionDate  |  Inclusion Date  |  LocalDateTime   |
| exclusionDate  |  Exclusion Date  |  LocalDateTime   |
| lastUpdateDate | Last Update date |  LocalDateTime   |

## Use case

## Repository

## Infra
