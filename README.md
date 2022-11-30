# EECS4413-Project

How to deploy the project on your own PC:

I'll take win10 OS as an example:

1: download the project from github, then open it in the IDEA IDE.

2: select resource folder, it's in the src/main/ path.

3: open application.properties file, change line 7 & 8 of username and password to your own mysql settings.

4: in the same file, change line 15 to your own project path, how to do this: click resources folder, there is a subfolder called images, right click this folder, then select Copy Path/References..., select Absolute Path; then copy this path to line 15 after codes "file.upload.dir=". 5: connect to your mySql database, then create a schema, it's name must be imooc_mall. How to do this:

  select Database on the right side of IDEA app window, then click + symbol, select Data source, then choose MySQL.
  IDEA shall now open a settings window for you, all you need to do is changing user and password to your own, 
  make sure you installed driver(IDEA will warn you if you not do so, simply follow the instruction and you are g2g)
  click Test Connection before close the setting window, it should be successfully connect to you own local MySQL now.
6: run imooc_mall_local.sql file under resources folder(double click the file and then ctal+a to select all codes, right click and choose Execute(the one with green play button))

Congratulations! You're all set up! It is best to do this step: open the main method, it's under src/main/java/, file is called MallApplication. Once you find this file, simply run it like a hello world progream :), then you should open your browser, type http://localhost:8083/#/index in the URL bar, press enter and you shall see the project!

Remember: KEEP IDEA RUNNING when you are showing or testing the project, because it is a local project.
