Feature: Wall Posts

Scenario: When user doesn't follow other users then nothing is shown
  Given application starts on "2014-01-01" at "17:00:01" in "Europe/London" timezone
  When user types the text "Alice wall"
  Then user sees no output

Scenario: When user follows other users with no posts then nothing is shown
  Given application starts on "2014-01-01" at "17:00:01" in "Europe/London" timezone
  And user types the text "Alice follows Bob"
  And user sees no output
  And user types the text "Alice follows Greg"
  And user sees no output
  And user types the text "Alice wall"
  And user sees no output
  And user types the text "Greg wall"
  And user sees no output
  When user types the text "Bob wall"
  Then user sees no output

Scenario: When user follows other users with some posts then they are shown
  Given application starts on "2014-01-01" at "17:00:01" in "Europe/London" timezone
  And user types the text "Bob -> Hi, I am Bob."
  And user sees no output
  And user types the text "Greg -> And I am Greg... :-)"
  And user sees no output
  And user types the text "Alice follows Bob"
  And user sees no output
  And user types the text "Alice follows Greg"
  And user sees no output
  And user types the text "Alice wall"
  And user sees 
    """
    Greg - And I am Greg... :-) (0 seconds ago)
    Bob - Hi, I am Bob. (0 seconds ago)
    """
  And user types the text "Greg wall"
  And user sees 
    """
    Greg - And I am Greg... :-) (0 seconds ago)
    """
  When user types the text "Bob wall"
  Then user sees 
    """
    Bob - Hi, I am Bob. (0 seconds ago)
    """

Scenario: Wall posts are displayed based on post creation time
  Given application starts on "2014-01-01" at "15:00:00" in "Europe/London" timezone
  And user sees no output
  And user types the text "Alice -> I love the weather today"
  And user sees no output
  And user types at "15:04:00" the text "Bob -> Damn! We lost!"
  And user sees no output
  And user types at "15:05:00" the text "Bob -> Good game though."
  And user sees no output
  And user types the text "Alice"
  And user sees 
    """
    I love the weather today (5 minutes ago)
    """
  And user types at "15:06:01" the text "Bob"
  And user sees 
    """
    Good game though. (1 minute ago)
    Damn! We lost! (2 minutes ago)
    """
  And user types the text "Charlie -> I'm in New York today! Anyone wants to have a coffee?"
  And user sees no output
  And user types the text "Charlie follows Alice"
  And user sees no output
  And user types at "15:06:03" the text "Charlie wall"
  And user sees 
    """
    Charlie - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago)
    Alice - I love the weather today (6 minutes ago)
    """
  And user types the text "Charlie follows Bob"
  And user sees no output
  When user types the text "Charlie wall"
  Then user sees 
    """
    Charlie - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago)
    Bob - Good game though. (1 minute ago)
    Bob - Damn! We lost! (2 minutes ago)
    Alice - I love the weather today (6 minutes ago)
    """
