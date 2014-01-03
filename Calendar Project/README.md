/********************************************************
* Project: Calendar
* Class:   CMPSC 221
* Team:	   Richard Lichkus, Nicholas Hall, Anthony Blyler
*********************************************************/

/********************************************************
*	USER
*********************************************************/
- User
	- Username
	- Email Account
	- Password
	- User Type
		- Personal
		- Administrator
			- Link account
- End User

/********************************************************
*	VIEWS
*********************************************************/
- Month 
- N Week(s) 
- Day
- Custom

/********************************************************
*	CALENDAR 
*********************************************************/
- Event Cluster
	- Title 
	- Events
	- Box Color
	- Text Color
- End Event Cluster 

/********************************************************
*	EVENT TYPES
*********************************************************/
- Event Type

	- Title
	- Notes
	- Alert
	
	- Meeting
		- Start
		- End
		- Type
			- Conference Call
				- Number
				- Password
			- Place
				Address
		- Attendees
			- [Contact Info]
	- End Meeting
	
	- Class
		- Building
		- Room Number
		- Professor Name
			- Office Hours
			- Email
		- TA Name(s)
			- Office Hours
			- Email
		- [Sn][M][T][W][R][R][St]
		- Start
		- End
		- Attendees	(Friends that have same class)
	- End Class
	
	- Flight
		- Airline
		- Confirmation Code (Upgrade Auto-Generate Airline information)
		- Check-in Reminder (Default 24 hours before)
		- Flight Departure
		- Flight Boarding	(Default 30 minutes before)
		- 
	- End Flight

	- Vacation
		- Start
		- End
		- Location
		- Attendees
	- End Vacation
	
	- Deadline
		- Type
			- Project
			- Homework
		- Date
		- Percent complete
		- Hours to completion
	- End Deadline
	
	- Errand
		- 
	- End Errand
	
	- Appointment
		- Start
		- End
		- Duration
		- Attendees
			- [Contact Info]
		- Address
	- End Appointment
	
	- Fitness Routine (checkbox bulleted list, editable list to record)
		- Cardio
			- Equipment
		- Lifting
			- Add exerscise(s)		//Editable from GUI
		- [Sn][M][T][W][R][F][St]
		- Start
		- End
		- *Routine Evaluator		(*Far Reach)
	- End Fitness Routine
	
	- Diet (checkbox bulleted list, editable list to record)
		- Add items
		- [Sn][M][T][W][R][F][St]
	- End Diet
	
	- Day Descriptors
		- Title
	- End Day Descriptors
	
- End Event

/********************************************************
*	FUNCTIONS
*********************************************************/ 
- Schedule Compare(user, user2)
	Return Highlight schedule events that are the same
- End Schedule Compare 

- Contacts
	- Name
	- Phone #
		- Cell
		- Home 
		- Work
	- Email
	- Import()		(Possible?)	
- End Contacts
	
- Share Calendar
	- Import()
	- Export()
- End Share Calendar
	
/********************************************************
*	Settings
*********************************************************/
- Settings
	- Number of Weeks
	- Weekday Order
		- [X][X][X][X][X][X][X]
- End Settings



