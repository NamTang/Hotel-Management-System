$(document)
		.ready(
				function() {
					$
							.ajax({
								type : "GET",
								url : "http://localhost:8080/admin/getCurrentAdmin",
								success : function(result) {
									if (result.role.role == "Clerk") {
										$('#userNav').remove();
									}
									$('#adminEmail').html(result.email);
									$('.dropdown-menu')
											.append(
													"<li><a href='/admin/user/editUser/"
															+ result.user_id
															+ "'>Edit profile</a></li>"
															+ "<li><a href='/logout'>Log out</a></li>");
								}
							});
					$('.dropdown-toggle').dropdown();
				});