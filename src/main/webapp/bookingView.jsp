<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles/UserStyles.css" />
        <title>Book Your Trip</title>
		
    </head>
	<style>
		body {
			background-image: url('images/bg3.jpg');
		}
	</style>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
	<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
    <body>
        <div class="container">			
            <h1>Book Your Trip</h1>		
			
            <a href="mainView.jsp"><p class="back"><</p></a>
			
            <form name="bookingForm" action="ReservationManagement" method="post">
				<label for="guests">Number of Guests</label><br>
				<button type="button" onclick="this.parentNode.querySelector('#guests').stepDown()" id="increment1">- </button>
				<input type="number" id="guests" name="guests" class="number-input" min="1" value="1" max="100" />
				<button type="button" onclick="this.parentNode.querySelector('#guests').stepUp()"  id="increment2">+</button><br>			
				
				<label for="pets">Bringing Pets?</label><br>
				<select name="pets" id="pets">
				  <option value="false">No</option>
				  <option value="true">Yes</option>
				</select><br><br>
				
				<label for="dates">Select Dates</label><br>
				<input type="text" name="dates" id="dates" />
				
				<br><br>				
				<button type="submit">SELECT ROOM</button>
				
			</form>
        </div>
		<script>
			$('input[name="dates"]').daterangepicker();
			
			const inputs = document.querySelectorAll('.number-input');

			inputs.forEach(input => {
				input.addEventListener('change', function() {
					if (this.value === '' || isNaN(this.value)) {
						this.value = 1;
					}
				});
			});
		</script>		
		<img class="logo" src="images/logo.png" alt="Logo">
		<style>
			.daterangepicker {
				color: black;
			}
		</style>
    </body>
</html>