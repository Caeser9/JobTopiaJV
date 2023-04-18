import mysql.connector
import pyttsx3

# Initialize the pyttsx3 engine
engine = pyttsx3.init()

# Connect to the MySQL database
cnx = mysql.connector.connect(
    
    host="127.0.0.1",
    user="root",
    password="root",
    database="jobtopia"
)

# Create a cursor object to execute queries
cursor = cnx.cursor()

# Execute a SELECT query to retrieve data from a table
id=input("id")
query = ("SELECT lettreMotivation FROM candidatures where id="+id)
cursor.execute(query)

# Convert the data to speech
for (column_name,) in cursor:
    engine.say(column_name)

engine.runAndWait()

# Close the cursor and connection
cursor.close()
cnx.close()
