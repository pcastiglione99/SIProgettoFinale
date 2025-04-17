# Airline Booking System - Java Web Project ✈️

This is a final project demonstrating a web application for an airline company, allowing users to search for flights and administrators to manage flight data. The application is built using Java and the Spring Boot framework.

## 📝 Description

The system provides a web interface for:

1.  **Public Users:** Searching for available flights based on departure airport, arrival airport, date, and required baggage allowance. It displays available flights or informs the user if no suitable flights are found.
2.  **Administrators:** Logging into a secure area to manage flights, including adding new flights, modifying the aircraft assigned to existing flights, deleting flights, and viewing a report of future flights with booking details.

The application interacts with an on-premise-style SQL database.

## ✨ Features

**User Features:**

*   Search for flights using:
    *   Departure Airport (Dropdown/Select)
    *   Arrival Airport (Dropdown/Select)
    *   Departure Date (Date Picker)
    *   Baggage Weight (Numeric Input)
*   View a list of available flights (from the selected date onwards) matching the criteria with available seats and baggage capacity.
*   Receive informative messages if no flights match the search criteria or route.
*   Button to easily return to the home search page.

**Administrator Features:**

*   Secure login to the administration area (`/login`).
*   Admin dashboard (`/admin`) with menu options.
*   **Insert New Flight:** Add flight details (route, dates, times, aircraft type) to the database.
*   **Modify Existing Flight:** Change the assigned aircraft type for a flight (includes validation to ensure capacity constraints for existing bookings are met).
*   **Delete Flight:** Remove a flight from the system.
*   **View Flight Report:** Display a list of all future flights (from the current date onwards) showing:
    *   Flight details
    *   Number of passengers currently booked
    *   Total weight of checked baggage currently booked