# EECS4413-Project

_I. Applications/Systems used in the Project_

- We have used Intelliji IDEA (Please note that you need the Ultimate version in order to run this project).
- You will need to install MySQL as this project is based off MySQL. Please note that the version must be greater than ver. 8.0. 
- We worked with Windows 10 OS. 
- Important Note: Please keep Intelliji Idea running while testing the project as it is a local project.

---

_II. How to Set Up the Project_

1. Download the project from GitHub. Unzip the file and open the project in Intelliji Idea IDE.
2. Navigate to the _resources_ folder. You can access it from _project4413 -> src -> main -> resources_.
3. Open the _resources_ folder and open the _application.properties_ file. Lines 7 and 8 (username and password respectively) will need to be changed to your own MySQL settings.

      > Line 7 spring.datasource.username=
      
      > Line 8 spring.datasource.password=
      
4. In the same file as Step 3, Line 15 will need to be changed to your own project path. To do this, navigate to the _images_ folder that is located within the _resources_ folder. Right-click on the _images_ folder and select _Copy Path/Reference..._ and select _Absolute Path_. Delete the original path in Line 15 and paste the one copied. 

      > Line 15 file.upload.dir=

5. We need to now connect to the MySQL database. Open MySQL and create a schema. The name of this schema must be _imooc_mall_. 
6. Go back to the Intelliji Idea application. To the right of the Intelliji app window, there will be the heading _Database_. Select that to open the _Database_ window and click the _+_ symbol and select _Data Source -> MySQL_. Make sure that you have installed the driver (Intelliji Idea will give a warning if you do not. If it does, follow its instructions to have it properly installed). Click _Test Connection_ before closing the window to ensure that it has successfully connected to your own local MySQL.
7. Open the _imooc_mall_local.sql_ file. This is located within the same _resources_ folder as used in Steps 3 and 4. Select all of the text with CTRL + A and right-click _Execute_ (it is symbolized with a green play button). 

Congratulations! The set up for the project is now completed.

---

III. How to Run the Project
1. Run the main application. To navigate to it, go through the following path: _project4413 -> src -> main -> java -> com.imooc.mall_. There will be a file called _MallApplication_. Run the file (you can do this by pressing the green play button at the top of the Intelliji Idea application). Open your preferred browser and enter in the link http://localhost:8083/#/index in the URL bar to see the website.
