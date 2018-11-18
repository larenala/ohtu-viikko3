Feature: A new user account can be created if a proper unused username and
 password are given

Scenario: creation is successful with valid username and password
        Given command new user is selected
        When  username "mewqeiiiumiuu" and password "password10" are entered
        Then  system will respond with "new user registered"
  

    
