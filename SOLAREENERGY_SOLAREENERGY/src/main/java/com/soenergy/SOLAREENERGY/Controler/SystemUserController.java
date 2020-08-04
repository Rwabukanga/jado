package com.soenergy.SOLAREENERGY.Controler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.soenergy.SOLAREENERGY.Domain.SystemUser;
import com.soenergy.SOLAREENERGY.Service.ISystemUserService;
import com.soenergy.SOLAREENERGY.Utility.Encryption;
import com.soenergy.SOLAREENERGY.Utility.Msg;
import com.soenergy.SOLAREENERGY.Utility.ResponseBean;



@RestController
@RequestMapping("/users")

public class SystemUserController {

	@Autowired
	private ISystemUserService iSystemUserService;

	/*
	 * Service to get all users
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getUsers(HttpServletRequest request) {
		ResponseBean responseBean = new ResponseBean();
		try {
			String userToken = request.getHeader("myum_token");

			if (userToken != null) {

				if (userToken.equalsIgnoreCase(Msg.token)) {

					responseBean.setCode(Msg.SUCCESS_CODE);
					responseBean.setDescription("OK");
					responseBean.setObject(iSystemUserService.all());
				} else {
					responseBean.setCode(Msg.INCORRECT_TOKEN);
					responseBean.setDescription("INCCORECT TOKEN ");
					responseBean.setObject(null);
				}

			} else {
				responseBean.setCode(Msg.TOKEN_NOT_FOUND);
				responseBean.setDescription(" TOKEN NOT FOUND ");
				responseBean.setObject(null);

			}
		} catch (Exception e) {
			responseBean.setCode(Msg.ERROR_CODE);
			responseBean.setDescription("SOMETHING WENT WRONG TRY AGAIN ");
			responseBean.setObject(null);
		}

		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	/*
	 * Service to get SystemUser by ID
	 */

	@RequestMapping(value = "/by_id/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> get_user_by_id(HttpServletRequest request, @PathVariable("id") String id) {
		ResponseBean responseBean = new ResponseBean();
		try {
			String userToken = request.getHeader("myum_token");

			if (userToken != null) {

				if (userToken.equalsIgnoreCase(Msg.token)) {

					responseBean.setCode(Msg.SUCCESS_CODE);
					responseBean.setDescription("OK");
					responseBean.setObject(iSystemUserService.findById(Long.parseLong(id)));
				} else {
					responseBean.setCode(Msg.INCORRECT_TOKEN);
					responseBean.setDescription("INCCORECT TOKEN ");
					responseBean.setObject(null);
				}

			} else {

				responseBean.setCode(Msg.TOKEN_NOT_FOUND);
				responseBean.setDescription(" TOKEN NOT FOUND ");
				responseBean.setObject(null);

			}
		} catch (Exception e) {
			responseBean.setCode(Msg.ERROR_CODE);
			responseBean.setDescription("SOMETHING WENT WRONG TRY AGAIN ");
			responseBean.setObject(null);
		}

		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	/*
	 * Service to create new SystemUser
	 */

	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create_system_user(@RequestBody SystemUser systemUser, HttpServletRequest request) {
		ResponseBean responseBean = new ResponseBean();
		try {
			String userToken = request.getHeader("myum_token");
			String doneBy = request.getHeader("doneby");
			if (userToken != null) {

				if (userToken.equalsIgnoreCase(Msg.token)) {
					systemUser.setDoneBy(doneBy);
					String message = iSystemUserService.create(systemUser);
					System.out.println("SAVING SYSTEM USER CALLLED+++++++++++++++========");
					if (message.contains(Msg.save)) {
					
						responseBean.setCode(Msg.SUCCESS_CODE);
						responseBean.setDescription(Msg.save);
						responseBean.setObject(systemUser);
					} else {
						responseBean.setCode(Msg.ERROR_CODE);
						responseBean.setDescription("SOMETHING WENT WRONG TRY AGAIN ");
						responseBean.setObject(null);
					}
				} else {
					responseBean.setCode(Msg.INCORRECT_TOKEN);
					responseBean.setDescription("INCCORECT TOKEN ");
					responseBean.setObject(null);
				}

			} else {

				responseBean.setCode(Msg.TOKEN_NOT_FOUND);
				responseBean.setDescription(" TOKEN NOT FOUND ");
				responseBean.setObject(null);

			}
		} catch (Exception e) {
			responseBean.setCode(Msg.ERROR_CODE);
			responseBean.setDescription("SOMETHING WENT WRONG TRY AGAIN ");
			responseBean.setObject(null);
		}

		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	/*
	 * Service to update SystemUser
	 */

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update_system_user(@RequestBody SystemUser systemUser, HttpServletRequest request) {
		ResponseBean responseBean = new ResponseBean();
		try {
			String userToken = request.getHeader("um_token");

			if (userToken != null) {

				if (userToken.equalsIgnoreCase(Msg.token)) {
					SystemUser sysUser=iSystemUserService.findById(systemUser.getId());
							systemUser.setId(sysUser.getId());
							systemUser.setObjectId(sysUser.getObjectId());
							systemUser.setObjectName(sysUser.getObjectName());
							systemUser.setApplicationName(sysUser.getApplicationName());
							systemUser.setPassword(sysUser.getPassword());
							systemUser.setUserLocked(sysUser.isUserLocked());
							systemUser.setActive(sysUser.getActive());
							systemUser.setRole(sysUser.getRole());
							
					String message = iSystemUserService.update(systemUser);
					if (message.contains(Msg.update)) {

						responseBean.setCode(Msg.SUCCESS_CODE);
						responseBean.setDescription("Updated successfully ");
						responseBean.setObject(systemUser);
					} else {
						responseBean.setCode(Msg.ERROR_CODE);
						responseBean.setDescription("SOMETHING WENT WRONG TRY AGAIN ");
						responseBean.setObject(null);
					}
				} else {
					responseBean.setCode(Msg.INCORRECT_TOKEN);
					responseBean.setDescription("INCCORECT TOKEN ");
					responseBean.setObject(null);
				}

			} else {

				responseBean.setCode(Msg.TOKEN_NOT_FOUND);
				responseBean.setDescription(" TOKEN NOT FOUND ");
				responseBean.setObject(null);

			}
		} catch (Exception e) {
			responseBean.setCode(Msg.ERROR_CODE);
			responseBean.setDescription("SOMETHING WENT WRONG TRY AGAIN ");
			responseBean.setObject(null);
		}

		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	/*
	 * Service to delete user
	 */

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> delete_user(HttpServletRequest request, @PathVariable("id") String id) {
		ResponseBean responseBean = new ResponseBean();
		try {
			String userToken = request.getHeader("um_token");

			if (userToken != null) {

				if (userToken.equalsIgnoreCase(Msg.token)) {
					if (iSystemUserService.delete(iSystemUserService.findById(Long.parseLong(id)))
							.contains(Msg.delete)) {
						responseBean.setCode(Msg.SUCCESS_CODE);
						responseBean.setDescription("OK");
						responseBean.setObject(null);
					} else {
						responseBean.setCode(Msg.ERROR_CODE);
						responseBean.setDescription("SOMETHING WENT WRONG TRY AGAIN ");
						responseBean.setObject(null);
					}
				} else {
					responseBean.setCode(Msg.INCORRECT_TOKEN);
					responseBean.setDescription("INCCORECT TOKEN ");
					responseBean.setObject(null);
				}

			} else {

				responseBean.setCode(Msg.TOKEN_NOT_FOUND);
				responseBean.setDescription(" TOKEN NOT FOUND ");
				responseBean.setObject(null);

			}
		} catch (Exception e) {
			responseBean.setCode(Msg.ERROR_CODE);
			responseBean.setDescription("SOMETHING WENT WRONG TRY AGAIN ");
			responseBean.setObject(null);
		}

		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	/*
	 * service for login
	 */

	@CrossOrigin(origins = "http://localhost:4002")
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> login(@RequestBody UserLogin user) {
		ResponseBean rs = new ResponseBean();

		if (user != null) {
			Map<String, Object> map = new HashMap<>();
			System.out.println(user.getApplicationName()+"=================");
            
			if ((iSystemUserService.login(user.getApplicationName(), user.getUsername(),
					Encryption.md5(user.getPassword())) != null)) {
				SystemUser systemUser = iSystemUserService.login(user.getApplicationName(), user.getUsername(),
						Encryption.md5(user.getPassword()));
				rs.setCode(Msg.SUCCESS_CODE);
				rs.setDescription("SUCCESS");
				map.put("user", systemUser);
				map.put("permissions", iSystemUserService.user_permissions(user.getApplicationName(),
						systemUser.getId(), "SystemUser"));
				rs.setObject(map);
			
			} else {
				rs.setCode(Msg.ERROR_CODE);
				rs.setDescription("Username or password is incorrect");
				rs.setObject(null);
			}
				
		} else {
			rs.setCode(Msg.ERROR_CODE);
			rs.setDescription("We couldn't receive user data. Contact administrator");
			rs.setObject(null);
		}

		// TODO: call persistence layer to update
		return new ResponseEntity<Object>(rs, HttpStatus.OK);
	}

	/**
	 * Locking the user
	 */
	// @CrossOrigin(origins = "http://localhost:9999")
	@RequestMapping(value = "/lock/{username}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> lock_user(@PathVariable("username") String username) {
		ResponseBean rs = new ResponseBean();

		if (username != null) {
			Map<String, Object> map = new HashMap<>();

			if (iSystemUserService.lock_user(username).equalsIgnoreCase(Msg.update)) {

				rs.setCode(Msg.SUCCESS_CODE);
				rs.setDescription("User is Locked Successfull");
				rs.setObject(null);
			} else {
				rs.setCode(Msg.ERROR_CODE);
				rs.setDescription("Unable to lock user. Try Again later");
				rs.setObject(null);
			}
		} else {
			rs.setCode(Msg.ERROR_CODE);
			rs.setDescription("We couldn't receive user data. Contact administrator");
			rs.setObject(null);
		}
           // TODO: call persistence layer to update
		return new ResponseEntity<Object>(rs, HttpStatus.OK);
	}
     @RequestMapping(value = "/unlock/{username}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> unlock_user(@PathVariable("username") String username) {
		ResponseBean rs = new ResponseBean();

		   try{
				     if (username != null) {
				Map<String, Object> map = new HashMap<>();

				SystemUser sysUser = iSystemUserService.findByUsername(username);
				if (sysUser != null) {
					sysUser.setUserLocked(false);
					iSystemUserService.update(sysUser);
					rs.setCode(Msg.SUCCESS_CODE);
					rs.setDescription("User is Unlocked Successfull");
					rs.setObject(null);
				} else {
					rs.setCode(Msg.ERROR_CODE);
					rs.setDescription("We couldn't find the user ");
					rs.setObject(null);
				}

			} else {

				rs.setCode(Msg.ERROR_CODE);
				rs.setDescription("We couldn't receive user data. Contact administrator");
				rs.setObject(null);
			}

			 }catch(Exception e){
				 e.printStackTrace();
			 }
		// TODO: call persistence layer to update
		return new ResponseEntity<Object>(rs, HttpStatus.OK);
	}

     /**
	  * Lock the user based on object ID
	  */
     @RequestMapping(value = "/block/{objectId}/status/{status}", method = RequestMethod.GET)
	public ResponseEntity<Object> lock_user(@PathVariable("objectId") int objectId,@PathVariable("status") boolean status) {
		ResponseBean rs = new ResponseBean();

		    try {
				rs.setCode(Msg.SUCCESS_CODE);
		     	rs.setDescription(iSystemUserService.blockUser(objectId, status));
			    rs.setObject(null);
			} catch (Exception e) {
				rs.setCode(Msg.ERROR_CODE);
		     	rs.setDescription(Msg.error);
				//TODO: handle exception
				System.out.println("Handle Lock The User "+e.getMessage());
			}

		// TODO: call persistence layer to update
		return new ResponseEntity<Object>(rs, HttpStatus.OK);
	}

	/*
	 * service for changing Password
	 */

	// @CrossOrigin(origins = "http://localhost:9999")
	@RequestMapping(value = "/change_password", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> change_password(@RequestBody UserLogin user) {
		ResponseBean rs = new ResponseBean();

		if (user != null) {
			Map<String, Object> map = new HashMap<>();
			if (user.getPassword().equalsIgnoreCase(user.getConfirmPassword())) {

				SystemUser systemUser = iSystemUserService.findByUsername(user.getUsername());
				systemUser.setPassword(Encryption.md5(user.getPassword()));
				
				if (iSystemUserService.update(systemUser).equalsIgnoreCase(Msg.update)) {

					rs.setCode(Msg.SUCCESS_CODE);
					rs.setDescription("Password successfully changed");
					map.put("user", systemUser);
					map.put("permissions", iSystemUserService.user_permissions(user.getApplicationName(),
							systemUser.getId(), "SystemUser"));
					rs.setObject(map);
				} else {
					rs.setCode(Msg.ERROR_CODE);
					rs.setDescription(Msg.error);
					rs.setObject(null);
				}
			} else {
				rs.setCode(Msg.ERROR_CODE);
				rs.setDescription("Password mismatch");
				rs.setObject(null);
			}
		} else {
			rs.setCode(Msg.ERROR_CODE);
			rs.setDescription("We couldn't receive user data. Contact administrator");
			rs.setObject(null);
		}

		// TODO: call persistence layer to update
		return new ResponseEntity<Object>(rs, HttpStatus.OK);
	}

	/*
	 * service for checking current Password
	 */

	// @CrossOrigin(origins = "http://localhost:9999")
	@RequestMapping(value = "/check_current_password/{username}/{password}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> change_password(@PathVariable("username") String username,
			@PathVariable("password") String password) {
		ResponseBean rs = new ResponseBean();

		if (username != null && password != null) {
			Map<String, Object> map = new HashMap<>();

			SystemUser systemUser = iSystemUserService.findByUsername(username);

			if (systemUser.getPassword().equalsIgnoreCase(Encryption.md5(password))) {

				rs.setCode(Msg.SUCCESS_CODE);
				rs.setDescription("SUCCESS");
				rs.setObject(map);
			} else {
				rs.setCode(Msg.ERROR_CODE);
				rs.setDescription("Password mismatch");
				rs.setObject(null);
			}

		} else {
			rs.setCode(Msg.ERROR_CODE);
			rs.setDescription("We couldn't receive username or password parameters");
			rs.setObject(null);
		}

		// TODO: call persistence layer to update
		return new ResponseEntity<Object>(rs, HttpStatus.OK);
	}

	/*
	 * Service to get all superadmins
	 */
	@RequestMapping(value = "/adminsAndEntityManagers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAdmins(HttpServletRequest request, @RequestBody SystemUser systemUser) {
		ResponseBean responseBean = new ResponseBean();
		try {
			String userToken = request.getHeader("um_token");

			if (userToken != null) {

				if (userToken.equalsIgnoreCase(Msg.token)) {

					responseBean.setCode(Msg.SUCCESS_CODE);
					responseBean.setDescription("OK");
					responseBean.setObject(iSystemUserService.adminsAndEntityManagers(systemUser.getApplicationName(),
							systemUser.getObjectName(), systemUser.getObjectId()));
				} else {
					responseBean.setCode(Msg.INCORRECT_TOKEN);
					responseBean.setDescription("INCCORECT TOKEN ");
					responseBean.setObject(null);
				}

			} else {
				responseBean.setCode(Msg.TOKEN_NOT_FOUND);
				responseBean.setDescription(" TOKEN NOT FOUND ");
				responseBean.setObject(null);

			}
		} catch (Exception e) {
			responseBean.setCode(Msg.ERROR_CODE);
			responseBean.setDescription("SOMETHING WENT WRONG TRY AGAIN ");
			responseBean.setObject(null);
		}

		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@RequestMapping(value = "/superadmins/{applicationName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getSuperAdmins(HttpServletRequest request,
			@PathVariable("applicationName") String applicationName) {
		ResponseBean responseBean = new ResponseBean();
		try {
			String userToken = request.getHeader("um_token");

			if (userToken != null) {

				if (userToken.equalsIgnoreCase(Msg.token)) {

					responseBean.setCode(Msg.SUCCESS_CODE);
					responseBean.setDescription("OK");
					responseBean.setObject(iSystemUserService.super_admin(applicationName));
				} else {
					responseBean.setCode(Msg.INCORRECT_TOKEN);
					responseBean.setDescription("INCCORECT TOKEN ");
					responseBean.setObject(null);
				}

			} else {
				responseBean.setCode(Msg.TOKEN_NOT_FOUND);
				responseBean.setDescription(" TOKEN NOT FOUND ");
				responseBean.setObject(null);
			}
		} catch (Exception e) {
			responseBean.setCode(Msg.ERROR_CODE);
			responseBean.setDescription("SOMETHING WENT WRONG TRY AGAIN ");
			responseBean.setObject(null);
		}

		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@RequestMapping(value = "/resetpassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> resetpassword(@RequestBody SystemUser systemUser, HttpServletRequest request) {
		ResponseBean responseBean = new ResponseBean();
		try {
			String userToken = request.getHeader("um_token");
			String doneBy = request.getHeader("doneby");
			if (userToken != null) {
				if (userToken.equalsIgnoreCase(Msg.token)) {
					SystemUser s = iSystemUserService.findByUUId(systemUser.getUuid());
					if (s == null) {
						responseBean.setCode(Msg.ERROR_CODE);
						responseBean.setDescription(Msg.error);
						responseBean.setObject(null);
					} else {
						s.setLastUpdatedBy(doneBy);
						
						s.setPassword(Encryption.md5(systemUser.getPassword()));
						iSystemUserService.update(s);
						responseBean.setCode(Msg.SUCCESS_CODE);
						responseBean.setDescription(Msg.reset);
						responseBean.setObject(s);
					}
				} else {
					responseBean.setCode(Msg.INCORRECT_TOKEN);
					responseBean.setDescription("INCCORECT TOKEN ");
					responseBean.setObject(null);
				}

			} else {

				responseBean.setCode(Msg.TOKEN_NOT_FOUND);
				responseBean.setDescription(" TOKEN NOT FOUND ");
				responseBean.setObject(null);

			}
		} catch (Exception e) {
			responseBean.setCode(Msg.ERROR_CODE);
			responseBean.setDescription("SOMETHING WENT WRONG TRY AGAIN ");
			responseBean.setObject(null);
		}

		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	/*
	 * @RequestMapping(value = "/login", method = RequestMethod.POST, produces =
	 * MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<UserLogin>
	 * update(@RequestParam Map<String,String>map) {
	 * 
	 * // if (user != null) {
	 * 
	 * // } UserLogin user=new UserLogin(); user.setUsername(map.get("username"));
	 * user.setPassword(map.get("password"));
	 * 
	 * // TODO: call persistence layer to update return new
	 * ResponseEntity<UserLogin>(user, HttpStatus.OK); }
	 */

	public static class UserLogin {
		private String username;
		private String password;
		private String applicationName;
		private String confirmPassword;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getApplicationName() {
			return applicationName;
		}

		public void setApplicationName(String applicationName) {
			this.applicationName = applicationName;
		}

		public String getConfirmPassword() {
			return confirmPassword;
		}

		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
		}

	}

}
