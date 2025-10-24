# MessengerYouTrack

A Java Spring Boot application that integrates messaging functionality with YouTrack platform. This project allows users to interact with YouTrack issues and 
periodically retrieves a list of notifications from your YouTrack insatnce via a messaging interface.

## Technologies

- **Backend:** Java, Spring Boot
- **Build Tool:** Maven
- **Others:** Jackson for JSON, Lombok for boilerplate code reduction

## Prerequisites

Before running the MessengerYouTrack project, make sure you have the following installed:

- **Java JDK 17+** – Required to build and run Spring Boot applications.  
- **Maven 3.8+** - Build tool for compiling and packaging the project.
- **Git** – To clone the repository
- **YouTrack instance** - To get youtrack token and youtrack.url

## Installation & Running

### YouTrack Instance Setup

To use the MessengerYouTrack application, you need a YouTrack instance and an API token. Follow these steps:

1. **Create a YouTrack account**
   - Go to [YouTrack website](https://www.jetbrains.com/youtrack/) and sign up for an account.
   - You can use the free cloud version (e.g., `https://<your-team>.myjetbrains.com/youtrack`).

2. **Create a new project (optional)**
   - After logging in, create a project where issues will be tracked.

3. **Generate a permanent API token**
   - Click your avatar (top-right) → **Profile** → **Authentication** → **Permanent Tokens** → **New Token**.
   - Give it a name (e.g., `MessengerYouTrackToken`) and select scopes
   - Click **Create**, and copy the token. Keep it secure.

4. **Get the base URL of your YouTrack instance**
   - Example: https://<project-temas>.youtrack.cloud/api

5. **Configure your application**
   - Open `application.properties`
   - Set the following:
     ```properties
     youtrack.url=https://<your-team>.youtrack.cloud/api
     youtrack.token=YOUR_API_TOKEN
     ```
   - This token will allow the backend to call YouTrack REST API endpoints securely.

6. **Test your connection**
   - You can test your token using a simple curl request:
     ```bash
     curl -H "Authorization: Bearer YOUR_API_TOKEN" \
          https://<your-team>.myjetbrains.com/youtrack/api/admin/projects
     ```
   - You should get a JSON response listing projects.

Now your MessengerYouTrack backend can interact with your YouTrack instance using the API.

### Scheduler Setup

The application uses a scheduler to periodically fetch notifications from your YouTrack instance.  
You can configure the interval (in minutes) by setting the following property in `application.properties`:

```properties
app.scheduler.notification.interval-minutes=5
```

This will determine how often the scheduler checks for new notifications.

### Clone the repository
```bash

git clone https://github.com/MilosMrdja/MessengerYouTrack.git
cd MessengerYouTrack
```

### Build the project
```bahs
mvn clean install
```

### Run the application
```bash
mvn spring-boot:run
```

### Create Issue via CLI

You can create a new YouTrack issue directly from the command line after starting the application.  

Usage: 
```
/createIssue --project-id <project-id> <summary> <description>
```

## Used API Endpoints

| Method | Endpoint | Description |
|--------|---------|-------------|
| GET    | `{apiBaseUrl}/notifications?fields=id,recipient(id),metadata` | Retrieves all notifications from your YouTrack instance |
| POST   | `{apiBaseUrl}/issues?fields=idReadable,summary` | Creates a new issue in YouTrack and returns its readable ID and summary. |
| GET    | `{apiBaseUrl}/users/me?fields=id,name` | Retrieves information about the current authenticated user, including user ID and name. |
| GET    | `{apiBaseUrl}/admin/projects?fields=id,name,shortName,team(users(id),id)` | Retrieves all projects from YouTrack, including project ID, name, short name, and team members’ IDs. |


## Author & License

- **Author:** Milos Mrdja


