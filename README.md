# EECS4413-Project

Hi! welcome to EECS4413 team U project github pase! 

I strongly recommend that you test our web project in your browser via HTML pages so that you can get the best user experience.


our project URL: 
- for main page:
http://47.88.57.165/#/index

- for admin manage system:
http://47.88.57.165/admin/index.html#/

- for customer account:
username:mumu	password:12345678

- for Admin account:
username:eecs4413	password:12345678



- Please do not modify these account info in database, as the password is MD5+salted encrypted, which do not store the paswd in plain text.

- Please note that our project contains quite a lot of images. I have switched on 50Mbps internet bandwidth, 
 a high frequency of page refreshing may cause the quota to run out very quickly.

- If you must refreshing the page many times, please enable your browser cache functionality, this could save quota.

- If you find the server denies your request, please contact any of team member to help, the server may be offline for maintenance, 
or the instance's firewall may not pass some IP addresses request.

- If you find the server takes too long to response your request(rare case), please contact us, we will restart the server.

---
---
---

How to set project in your local PC:

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

_III. How to Run the Project_

1. Run the main application. To navigate to it, go through the following path: _project4413 -> src -> main -> java -> com.imooc.mall_. There will be a file called _MallApplication_. Run the file (you can do this by pressing the green play button at the top of the Intelliji Idea application). Open your preferred browser and enter in the link http://localhost:8080/#/index in the URL bar to see the website.

---

_IV. Deployed application on the cloud_

As mentioned in the report, the link to our deployed application on the cloud is the following: http://47.88.57.165/#/index
