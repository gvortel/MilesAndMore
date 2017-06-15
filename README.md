# MilesAndMore
Airline program for premium discounts (Data structure optimization)

- Read the existing members and their respective miles from a CSV file.
- When closing the application writes to a CSV the current status of each member and their miles.
- Provides a UI (GUI) to add new members with their points (initial 1000) and add additional points to existing members by a human operator.
- Provides the means for an operator to make inquires providing the ID of a member and getting back the rank of the member in following form: the highest ranking members belong to the top 1% and the lowest belong to the top 95% percent.
- The system’s is designed in such a way that it can serve 200-300 requests per second for returning the rank of member ids. Requests for current rankings should be served while new members are added or existing members’ points are adjusted at the same time.
