# Using the database functionality
For ease of use, the database functionality has been updated. To use the database manager to manage data in the database, simply use the `DataabaseManager` object located in the `scr\main\java\com\hotelreservationapp\models\DatabaseLogic` directory.

## Examples of how to use

```
DatabaseManager db = new DatabaseManager();
//create user
User user = db.userDbManager.createUser("user@email.com", "password", "user@email.com","505-555-1234");

// create reservation for the user
Reservation reservation = db.reservationDbManager.createReservation(user.getUserId(), 1, "2024-05-01", "2024-05-04", 300.00,2,"pending");

//add a new payment method for the user
UserPaymentMethod paymentMethod = db.userPaymentMethodDbManager.createUserPaymentMethod("5511665412369983", "New User", "Master Card", "2026-12-31", "999", user.getUserId());

//User pays for the reservation
Transaction transaction = db.transactionDbManager.createTransaction(user.getUserId(), reservation.getReservationId(), reservation.getTotalPrice(), paymentMethod.getUserPaymentMethodID());

//When a rservation is paid, the status needs to be updated to confirmed. Since there is not yet a function to do this, I want to use a RAW sql query
String sql = "UPDATE reservations A SET A.reservation_status = ? where a.reservation_id = ?";
Object[] params = new Object[]{"Confirmed", reservation.getReservationId()};
db.runCustomDBCommand(sql,params);

//To see information about this specific transactrion, I canuse one of the prepared models.
TransactionInformation finalTransactionInfo = db.getTransactionInformationFor(transaction.getTransactionId());
```