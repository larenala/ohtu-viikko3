Feature: A new user account can be created if a proper unused username and
 password are given

Scenario: creation fails with too short username and valid password
        Given command new is selected
        When  username "pe" and password "akkep" are entered
        Then  system will respond with ""new user not registered"

    
  

    
