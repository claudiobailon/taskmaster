# taskmaster
An app to master your tasks!
## App Screenshots
![Homepage](screenshots/28homepage.png) 
![All Tasks](screenshots/28taskdetails.png)
## Change Log
### 11-14-20
Added Amplify and dynamoDb so that recyclerview shows items added to dynamodb from the add tasks page. Attempted to 
add cognito, storage, and notifications, but adding dependencies made gradle crash and I could not figure out why. 
### 10-22-2020
Added Room database dependencies to build.gradle and made Task an Entity.  Set up Database and taskTAO. Made 
add task page save form submissions into database and display them on home page. 

### 10-21-2020
Created task class and set up a recylerView for homepage. Hardcoded an arrayList of tasks to be displayed in 
the new recyclerView, then made each task clickable and display the title, body, and state in the detail view 
they are sent to.  

### 10-20-2020
Added three task buttons to home page that link to a task details page.  The task details page displays
the name of the task of the button that you clicked and also allows you to navigate back home.  A settings
page was also added with a link to it on the home page.  In the setting page, you can set a username that will
then display on the homepage. 

### 10-19-2020
Created app and added activities for home, add task, and all tasks.


