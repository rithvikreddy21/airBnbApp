# Hotel Booking App (airBnb Mock) - Java Backend
This is a Java backend implementation of a simplified Airbnb-style application. Built as a personal project to explore backend development patterns, REST APIs, and database integration.

## Requirements
### User Roles:
- Admin (Hotel Manager)
  * Can add hotels and rooms.
  * Can manage hotel details, room types, and room inventory.
  * Can manage and view bookings.


- Guest
  * Can search hotels based on city, dates, and number of rooms.
  * Can view hotel and room details.
  * Can create bookings by selecting a hotel, room type and dates.
  * Can add guests and make payments via payment gateway.

    
## Entities 

1. User
2. Hotel
3. Guest
4. Room
5. Inventory
6. Booking
7. HotelContactInfo

![entities_img](https://github.com/rithvikreddy21/airBnbApp/blob/f4339cd66096d5aaa5ecbf1cf4291b1d39c3fc2d/airbnbEntities.png)


### APIs Overview

- Authentication APIs
```
# Auth Controller

POST /v1/auth/signup              # User signup
POST /v1/auth/login               # Existing User Login

```


- Admin APIs
```
# Hotel Controller 

POST    /v1/admin/hotels                           # add hotel 
GET     /v1/admin/hotels/{hotelId}                 # get the hotel profile
PUT     /v1/admin/hotels/{hotelId}                 # update hotel profile
DELETE  /v1/admin/hotels/{hotelId}                 # remove a hotel
GET     /v1/admin/hotels                           # get list of hotels
PATCH   /v1/admin/hotels/{hotelId}/activate        # initiate inventory
GET     /v1/admin/hotels/{hotelId}/reports         # hotel report
GET     /v1/admin/hotels/{hotelId}/bookings        # booking details of a hotel

# Room Admin Controller

POST   /v1/admin/hotels/{hotelId}/rooms            # add room to a hotel
GET    /v1/admin/hotels/{hotelId}/rooms/{roomId}   # get room profile
PUT    /v1/admin/hotels/{hotelId}/rooms/{roomId}   # modify room profile
DELETE /v1/admin/hotels/{hotelId}/rooms/{roomId}   # delete room and inventory
GET    /v1/admin/hotels/{hotelId}/rooms            # get all rooms in a hotel
```

- User APIs

```
# User Controller

GET    /v1/users/profile                     # get user profile
PATCH  /v1/users/profile                     # modify user profile
GET    /v1/users/myBookings                  # list bookings of user

# Hotel Booking Controller

POST   /v1/bookings/init                     # initiate booking
POST   /v1/bookings/{bookingId}/payments     # initiate payment
POST   /v1/bookings/{bookingId}/cancel       # cancel booking
POST   /v1/bookings/{bookingId}/addGuests    # add guests
```
