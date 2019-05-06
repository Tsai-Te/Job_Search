# Job-Search



String date="03/12/2019";
        Date dateOfBirth=new SimpleDateFormat("MM/dd/yyyy").parse(date);
        expectedUser.setDateOfBirth(dateOfBirth);
        
        
        
 unit test
 @WebAppConfiguration
 @ContextConfiguration(classes={AppConfig.class}) 
 @RunWith(SpringJUnit4ClassRunner.class)
 @ActiveProfiles("unit")
 
 mvn clean compile flyway:migrate -P unit -Ddb_url=localhost:1234/jobsearch_unit -Ddb_username= -Ddb_password=
 
 how to generate a package from maven:
 mvn clean compile package -DskipTests=true