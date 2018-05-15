// Confirm deletion modal script
$('#confirm-delete').on('show.bs.modal', function(e) {
	$(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));

	$('.debug-url').html('Delete URL: <strong>' + $(this).find('.btn-ok').attr('href') + '</strong>');
});
// End Confirm deletion modal script

// Check password match
var password = document.getElementById("password")
, confirm_password = document.getElementById("password_retyped");

function validatePassword(){
	
	if(password.value != confirm_password.value) {
	  confirm_password.setCustomValidity("Passwords Don't Match");
	  document.getElementById('submit').disabled = true;
	} else {
	  confirm_password.setCustomValidity('');
	  document.getElementById('submit').disabled = false;
	}
}

password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;
// End check password match