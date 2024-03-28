<h1>Intructions to run:</h1>
Requirements: Ant and Apache Tomcat
<h3>Running Manually</h3>
<ol>
    <li> Build the project in order to compile classes</li>
    <li> Navigate to the root directory and run 'ant build'. 
         This will generate the compiled classes within a folder named target.
         It will also generate a war file using those classes and the .jars in the lib folder.
    </li>
    <li>
        To manually run the web app you must copy the generated .war file into the webapps within the
        tomcat installation directory.
    </li>
    <li>
        Start the server using the startup.bat (startup.sh for Linux) file located 
        within the bin directory of your tomcat installation. You can then visit the webpage at
        http://localhost:8080/HotelReservationApp/
    </li>
</ol>
<h3> Running Using IntelliJ </h3>
<ol>
    <li>The server should be setup and configured upon loading the project. If it is then skip to step 4. If it isn't then go
        to 'Run / Debug Configurations' and add a local Tomcat server. 
    </li>
    <li>
        For the Tomcat server ensure that the 'Before Launch' configurations are for the server config are correct. 
        The first task should be to build the project, then the ant build task should come next, 
        and finally it should build the artifacts. Go to deployment and make sure the generated war file is selected.
    </li>
    <li>
        Go to File > Project Structure and select artifacts and make sure there is an artifact called HotelReservationApp 
        that include all project files to be compiled. Add one if needed. It should be of the type Web App Archive.
    </li>
    <li>
        Run Tomcat from the top of the IDE and the server will start and deploy itself. 
        The index.jsp page should open automatically.
    </li>
</ol>
    
