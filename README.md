# Scrum Board

In Computer Science, specifically in the fields of software development and engineering, it is common to develop projects using agile methodologies such as Scrum.

Projects based on Scrum make use of a Scrum Board (image below), which consists of a board that gathers information about User Stories and Tasks related to the project (such as status, assignments, progress, etc.) to assist in development management and risk mitigation.

![Scrum Board](/media/image.png)

The Scrum Board system aims to provide a standard backend for the development of Scrum boards, making it easier to develop Scrum-based projects.

### User Stories

>US 1 - As a User, I would like to have access to a CRUD (Create, Read, Update, Delete) for users in the system, based on information such as full name, username, and email.
<br> NOTE: Only the user themselves can perform operations on their own profile.

>US 2 - As a Scrum Master, I would like to have access to a CRUD for projects in the system, based on information such as project name, description, and partner institution.
<br> NOTE: The Scrum Master is automatically associated with the project created in the role of Scrum Master.

>US 3 - As a Scrum Master, I would like to have access to roles that can be assumed by users associated with projects.
<br> NOTE: The available roles should be: product owner, researcher, developer, and intern.

>US 4 - As a Scrum Master, I would like to associate a user registered in the system with a specific project, specifying the user's role in the project.
<br> NOTE: Only the Scrum Master can associate users with a project to which they belong.

>US 5 - As a Scrum Master, I would like to have access to development stages in the projects created in the system. Each User Story in the project will be associated with a development stage.
<br> NOTE: The available stages should be: TODO, Work in Progress, To Verify, and Done.

>US 6 - As a User, I would like to have access to a CRUD for User Stories, based on information such as the title and description of the User Story.
<br> NOTE: Users can only perform operations on User Stories of projects they are part of.
<br> NOTE: Every User Story created belongs to the TODO development stage.

>US 7 - As a User, I would like to be able to assign myself to a specific User Story as one of the possible responsible parties for its development.
<br> NOTE: More than one user can be associated with the same User Story.
<br> NOTE: Only users with the roles of researcher, developer, or intern can be associated with a User Story.

>US 8 - As a Scrum Master, I would like to be able to assign a specific User Story to a user associated with the project.
<br> NOTE: Only users with the roles of researcher, developer, or intern can be assigned to a User Story.

>US 9 - As a User, I would like to have access to a CRUD for Tasks, based on information such as the title, description of the task, and associated User Story.
<br> NOTE: Only the Scrum Master or users assigned to the associated User Story can perform operations on the task in question.
<br> NOTE: Every task created has the status of not done.

>US 10 - As a User, I would like to mark a Task as done.
<br> NOTE: Only the Scrum Master or users assigned to the associated User Story can mark the task as done.
<br> NOTE: Only tasks with the status of not done can be marked as done.

>US 11 - As a User, I would like to be able to move a specific User Story from the Work in Progress stage to the To Verify stage.
<br> NOTE: Only User Stories in the Work in Progress stage can be moved to To Verify.
<br> NOTE: Only the Scrum Master or users assigned to the User Story can move it to the To Verify stage.

>US 12 - As a Scrum Master, I would like to be able to move a specific User Story from the To Verify stage to the Done stage.
<br> NOTE: Only User Stories in the To Verify stage can be moved to Done.
<br> NOTE: Only the Scrum Master can change the stage of User Stories from To Verify to Done.

>US 13 - As the Scrum Board administrator, I would like the system to automatically change the development stage of registered User Stories. The changes should be made after the following events:
<br> NOTE: TODO to Work in Progress: Whenever a User Story is assigned to a user associated with the project.
<br> NOTE: Work in Progress to To Verify: Whenever all tasks of a User Story are marked as done.

>US 14 - As a User, I would like to express interest in a User Story in development to be notified of each update to the User Story.
<br> NOTE: Notifications should be represented as messages in the application terminal, indicating who is receiving the notification and the reason for it.
<br> NOTE: Updates of interest in a User Story include changes in description and changes in stage.

>US 15 - As a Scrum Master, I would like to receive notifications for each update of a User Story in the system.
<br> NOTE: Notifications should be represented as messages in the application terminal, indicating who is receiving the notification and the reason for it.
<br> NOTE: Updates of interest in a User Story include changes in description, changes in stage, and tasks marked as done.

>US 16 - As a Product Owner, I would like to receive notifications for each User Story completed in the system (marked as Done).
<br> NOTE: Notifications should be represented as messages in the application terminal, indicating who is receiving the notification and the reason for it.

>US 17 - As a User, I would like to have access to descriptive reports on the status of the developed User Stories to which I am assigned. The report should include:
<br> NOTE: The percentage and total number of User Stories assigned to the user.
<br> NOTE: The percentage and number of User Stories assigned to the user in each development stage.

>US 18 - As a Product Owner, I would like to have access to descriptive reports on the status of User Stories developed in the entire project. The report should include:
<br> NOTE: The percentage and number of User Stories in each development stage.

>US 19 - As a Scrum Master, I would like to have access to descriptive reports on the status of User Stories developed in the entire project, with details of user activities. The report should include:
<br> NOTE: The percentage and total number of User Stories assigned to each user.
<br> NOTE: The percentage and number of User Stories assigned to each user in each development stage.
<br> NOTE: The percentage and number of User Stories in each development stage.

### Developers:

- @AlefAdonis
- @huggoparcelly
- @danielMaciel710
- @marianezei
- @johanssonlucena