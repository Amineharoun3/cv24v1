README.txt
==========

  Guide de Deploiement de l'Application CV24
----------------------------------



Pré-requis:
--------------
1. Java JDK 21 ou plus 
2. Maven 3.6 or ou plus 
3. Docker installed on your machine (for running the application in a container)
4. Access to a MySQL database

Etapes de deploiement :
--------------------------------

1. Clone the repository:
   - Clonage de l'applicatio
     ```
     git clone https://github.com/Amineharoun3/cv24v1.git
     

2  Construction application:
   avec Maven:
     
    dans le repertoir du projet 
     mvn clean install
    

3. Deployer:

  mvvn spring-boot:run
    

4. Configuration  bdd:
   - S"assurer des données  MySQL dans `application.properties` 
     spring.datasource.url=jdbc:mysql:jdbc:mysql://b3k9ig4cm1xrnpm7oydn-mysql.services.clever-cloud.com:3306/b3k9ig4cm1xrnpm7oydn
     spring.datasource.username=umztbejdmqubcmrp
     spring.datasource.password=7ngmJ0fcdBDgYRDnr0X4
     ```

6. Verifier le deploiement:
   - Dans un naviguateur   `http://localhost:8080` 


