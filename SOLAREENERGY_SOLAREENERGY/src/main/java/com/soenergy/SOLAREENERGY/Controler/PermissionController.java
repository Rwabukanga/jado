package com.soenergy.SOLAREENERGY.Controler;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.soenergy.SOLAREENERGY.Domain.AssignedPermission;
import com.soenergy.SOLAREENERGY.Domain.Permission;
import com.soenergy.SOLAREENERGY.Service.IAssgnedPService;
import com.soenergy.SOLAREENERGY.Service.IPermissionService;
import com.soenergy.SOLAREENERGY.Utility.Msg;
import com.soenergy.SOLAREENERGY.Utility.ResponseBean;


@RestController
@RequestMapping("/permissions")
public class PermissionController {

	@Autowired
	private IPermissionService iPermissionService;
	@Autowired
	private IAssgnedPService iAssgnedPService;
	
	/*
	 * Service to get all Permissions
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseBean getPermissions(HttpServletRequest request) {
		ResponseBean responseBean = new ResponseBean();
		try {
			String userToken = request.getHeader("um_token");

			if (userToken != null) {

				if (userToken.equalsIgnoreCase(Msg.token)) {

					responseBean.setCode(Msg.SUCCESS_CODE);
					responseBean.setDescription("OK");
					responseBean.setObject(iPermissionService.all());
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

		return responseBean;
	}
	
	
	
	/*
	 * Service to get Permission by ID
	 */

	@RequestMapping(value = "/by_id/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseBean get_permission_by_id(HttpServletRequest request, @PathVariable("id") String id) {
		ResponseBean responseBean = new ResponseBean();
		try {
			String userToken = request.getHeader("um_token");

			if (userToken != null) {

				if (userToken.equalsIgnoreCase(Msg.token)) {

					responseBean.setCode(Msg.SUCCESS_CODE);
					responseBean.setDescription("OK");
					responseBean.setObject(iPermissionService.findById(Long.parseLong(id)));
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

		return responseBean;
	}
	
	
	
	
	/*
	 * Service to create new Permission
	 */

	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseBean create_permission(@RequestBody Permission permission, HttpServletRequest request) {
		ResponseBean responseBean = new ResponseBean();
		try {
			String userToken = request.getHeader("um_token");

			if (userToken != null) {

				if (userToken.equalsIgnoreCase(Msg.token)) {
					String message = iPermissionService.create(permission);
					if (message.contains(Msg.save)) {

						responseBean.setCode(Msg.SUCCESS_CODE);
						responseBean.setDescription("OK");
						responseBean.setObject(permission);
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

		return responseBean;
	}
	
	
	/*
	 * Service to update Permission
	 */

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseBean update_permission(@RequestBody Permission permission, HttpServletRequest request) {
		ResponseBean responseBean = new ResponseBean();
		try {
			String userToken = request.getHeader("um_token");

			if (userToken != null) {

				if (userToken.equalsIgnoreCase(Msg.token)) {
					String message = iPermissionService.update(permission);
					if (message.contains(Msg.update)) {

						responseBean.setCode(Msg.SUCCESS_CODE);
						responseBean.setDescription("OK");
						responseBean.setObject(permission);
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

		return responseBean;
	}
	
	/*
	 * Service to delete Permission
	 */

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseBean delete_profile(HttpServletRequest request, @PathVariable("id") String id) {
		ResponseBean responseBean = new ResponseBean();
		try {
			String userToken = request.getHeader("um_token");

			if (userToken != null) {

				if (userToken.equalsIgnoreCase(Msg.token)) {
					if (iPermissionService.delete(iPermissionService.findById(Long.parseLong(id))).contains(Msg.delete)) { 
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

		return responseBean;
	}
	
	
	
	/*
	 * Service to update Assign Permission
	 */

	@RequestMapping(value = "/assign", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseBean assign_permission(@RequestBody AssignedPermission assignedPermission, HttpServletRequest request) {
		ResponseBean responseBean = new ResponseBean();
		try {
			String userToken = request.getHeader("um_token");

			if (userToken != null) {

				if (userToken.equalsIgnoreCase(Msg.token)) {
					String message = iAssgnedPService.create(assignedPermission);
					if (message.contains(Msg.update)) {

						responseBean.setCode(Msg.SUCCESS_CODE);
						responseBean.setDescription("OK");
						responseBean.setObject(assignedPermission);
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

		return responseBean;
	}
}
