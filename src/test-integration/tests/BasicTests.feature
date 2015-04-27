Feature: User Posts

Scenario: When application is started then no output is shown
  Given application starts on "2014-01-01" at "17:00:01" in "Europe/London" timezone
  Then user sees no output

Scenario: When empty line is entered then nothing is shown
  Given application starts on "2014-01-01" at "17:00:01" in "Europe/London" timezone
  When user types the text ""
  Then user sees no output
  
Scenario: When username is entered and there are no posts then nothing is shown
  Given application starts on "2014-01-01" at "17:00:01" in "Europe/London" timezone
  When user types the text "Alice"
  Then user sees no output

Scenario: When user attempts to follow himself then error is shown
  Given application starts on "2014-01-01" at "17:00:01" in "Europe/London" timezone
  When user types the text "Alice follows Alice"
  Then error "ERROR: IllegalArgumentException User cannot follow himself: Alice" is shown

Scenario: When username is entered and there is one post then it is shown
  Given application starts on "2014-01-01" at "17:00:01" in "Europe/London" timezone
  When user types the text "Alice -> My first post"
  Then user sees no output
  When user types the text "Alice"
  Then user sees "My first post (0 seconds ago)" 

Scenario: When username is entered and there are two posts then they are shown
  Given application starts on "2014-01-01" at "17:00:01" in "Europe/London" timezone
  When user types the text "Alice -> My first post"
  Then user sees no output
  When user types at "17:00:02" the text "Alice -> Second post"
  Then user sees no output
  When user types the text "Alice"
  Then user sees 
  """
  Second post (0 seconds ago)
  My first post (1 second ago)
  """

Scenario: When username is entered and there are two posts then they are shown
  Given application starts on "2014-01-01" at "17:00:01" in "Europe/London" timezone
  When user types the text "Alice -> My first post"
  Then user sees no output
  When user types at "18:01:02" the text "Alice -> Second post"
  Then user sees no output
  When user types the text "Alice"
  Then user sees 
  """
  Second post (0 seconds ago)
  My first post (1 hour ago)
  """
  
Scenario: When username is entered and there are three posts with time difference then they are shown
  Given application starts on "2014-01-01" at "17:00:01" in "Europe/London" timezone
  When user types at "17:00:02" the text "Alice -> Testing the app"
  Then user sees no output
  When user types at "17:10:22" the text "Alice -> My second post"
  Then user sees no output
  When user types at "19:33:21" the text "Alice -> It is late"
  Then user sees no output
  When user types the text "Alice"
  Then user sees 
  """
  It is late (0 seconds ago)
  My second post (2 hours ago)
  Testing the app (2 hours ago)
  """
  
Scenario: When username is entered and there are five posts with long time difference then they are shown
  Given application starts on "2014-01-01" at "17:00:01" in "Europe/London" timezone
  When user types the text "Alice -> Testing the app"
  Then user sees no output
  When user types on "2014-01-02" at "15:00:01" the text "Alice -> My second post"
  Then user sees no output
  When user types on "2014-01-12" at "10:00:02" the text "Alice -> It is late"
  Then user sees no output
  When user types on "2014-03-12" at "03:00:01" the text "Alice -> It is very early"
  Then user sees no output
  When user types on "2014-06-12" at "11:00:01" the text "Alice -> My latest post"
  Then user sees no output
  When user types on "2014-06-13" at "11:10:11" the text "Alice"
  Then user sees 
  """
  My latest post (1 day ago)
  It is very early (93 days ago)
  It is late (152 days ago)
  My second post (161 days ago)
  Testing the app (162 days ago)
  """
  
Scenario: When username is entered and there are two posts entered around DST change then they are shown
  Given application starts on "2014-03-30" at "00:00:01" in "Europe/London" timezone
  When user types the text "Alice -> My first post"
  Then user sees no output
  When user types at "10:10:02" the text "Alice -> My second post"
  Then user sees no output  
  When user types the text "Alice"
  Then user sees 
  """
  My second post (0 seconds ago)
  My first post (11 hours ago)
  """
