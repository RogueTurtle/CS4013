            HELP FILE
---------------------------------
When running the program you will be asked to input the RestaurantID. For testing purposes this can be 1 or 2.
You will be given an option menu to log-in or sign-up. Input the number in bracket to make your selection.
Log-in:
    - Your name must be present in the database (case doesn't matter).
    - Your password must match the password associated with the name in the database (case sensitive).
Sign-up:
    - Your name must NOT be present in the database.

After logging in, you will be brought to the main menu. From here you have 3 options.
Main Menu:
    - Reservations -> brings you to reservations menu.
    - Check menu -> brings up the restaurant menu.
    - Admin -> brings you to Admin menu if you are authorized.

Reservations menu:
    - Check available tables and reserve -> you will be asked to input a date and time.
        It will bring up tables that are available at that date and time.
        The user is asked to select a table and their reservation will be logged.
    - Check reservations -> brings up all reservations associated with the current account.
    - Cancel reservations -> cancels all reservations associated with the current account.
    - Go back -> brings you back to the main menu.

Admin menu:
    - Promote a user -> if the current account is an owner (level 6), select an account and promote them to a higher authority.
    - Create an order -> if the current account