Feature: A new user account can be created if a proper unused username and
 password are given

Scenario: creation is successful with valid username and password
        Given command new user is selected
        When  username "mewqeiiiumiuu" and password "password10" are entered
        Then  system will respond with "new user registered"
  
Scenario: creation fails with already taken username and valid password
        Given command new user is selected
        When  username "pekka" and password "password12" are entered
        Then  system will respond with "new user not registered"

Scenario: creation fails with too short username and valid password
        Given command new user is selected
        When  username "p" and password "paswrod343" are entered
        Then  system will respond with "new user not registered"

Scenario: creation fails with valid username and too short password
        Given command new user is selected
        When  username "matthew" and password "pw2" are entered
        Then  system will respond with "new user not registered"

Scenario: creation fails with valid username and password enough long but consisting of only letters
        Given command new user is selected
        When  username "Michael" and password "jacksonfive" are entered
        Then  system will respond with "new user not registered"


Scenario: can login with successfully generated account
        Given user "eero" with password "salainen1" is created
        And   command login is selected
        When  username "eero" and password "salainen1" are entered
        Then  system will respond with "logged in"
