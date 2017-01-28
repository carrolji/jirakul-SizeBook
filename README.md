# jirakul-SizeBook
cmput 301 assignment1

Problem Description

Consider the situation of an avid shopper, who often buys clothes for family, relatives, and close friends (both online and in person). This activity is fraught with difficulties, but one basic issue is keeping track of each person's dimensions. What is needed is a simple, attractive, intuitive, mobile app to manage this data. Let us call this app: SizeBook.

For this simple, basic app, just focus on the dimensions needed to help size apparel worn on the main body (e.g., tops, bottoms, dresses, etc.) and not accessories (e.g., hats, shoes).

Also, set aside the broader matter of mapping dimensions to properly fitting clothing sizes (which can vary across clothing brands, geographic regions, and sizing categories).

Specifically, each person has a record with the following fields:

name (textual)
date (when the dimensions were reasonably valid, in yyyy-mm-dd format)
neck (circumference in inches, numeric)
bust (circumference in inches, numeric)
chest (circumference in inches, numeric)
waist (circumference in inches, numeric)
hip (circumference in inches, numeric)
inseam (length in inches, numeric)
comment (textual)
Only the name field is mandatory for a record. The other fields might be empty, particularly if not known by the user or not relevant for sizing the person's clothing expression.

All the numeric fields are positive decimal numbers, to at most one decimal place. You may offer suitable ranges and precision to a half unit.

The app should allow the user to:

show a list of all records, along with a count of the number of records
add a new record (which always appends to the bottom end of the list)
view the details of an existing record
edit the fields of a record
delete an existing record
The list need not show all the data for a record if space is limited. Minimally, each record in the list should show the name, and if available, bust, chest, waist, and inseam values.

The app must assist the user in proper data entry. For example, use appropriate user interface controls to enforce particular data types.

The app must be persistent. That is, exiting and fully stopping the app should not lose data.
