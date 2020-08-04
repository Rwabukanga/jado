package com.soenergy.SOLAREENERGY.Controler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.soenergy.SOLAREENERGY.Domain.TheGroup;
import com.soenergy.SOLAREENERGY.Service.IGroupService;
import com.soenergy.SOLAREENERGY.Utility.Msg;
import com.soenergy.SOLAREENERGY.Utility.ResponseBean;


@RestController
@RequestMapping("/groups")
public class GroupController {

	@Autowired
	private IGroupService iGroupService;
	
	
	/*
	 * Service to get all groups
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseBean getGroups(HttpServletRequest request) {
		ResponseBean responseBean = new ResponseBean();
		try {
			String userToken = request.getHeader("um_token");

			if (userToken != null) {

				if (userToken.equalsIgnoreCase(Msg.token)) {

					responseBean.setCode(Msg.SUCCESS_CODE);
					responseBean.setDescription("OK");
					responseBean.setObject(iGroupService.all());
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
	 * Service to get Group by ID
	 */

	@RequestMapping(value = "/by_id/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseBean get_group_by_id(HttpServletRequest request, @PathVariable("id") String id) {
		ResponseBean responseBean = new ResponseBean();
		try {
			String userToken = request.getHeader("um_token");

			if (userToken != null) {

				if (userToken.equalsIgnoreCase(Msg.token)) {

					responseBean.setCode(Msg.SUCCESS_CODE);
					responseBean.setDescription("OK");
					responseBean.setObject(iGroupService.findById(Long.parseLong(id)));
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
	 * Service to create new group
	 */

	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseBean create_group(@RequestBody TheGroup group, HttpServletRequest request) {
		ResponseBean responseBean = new ResponseBean();
		try {
			String userToken = request.getHeader("um_token");

			if (userToken != null) {

				if (userToken.equalsIgnoreCase(Msg.token)) {
					String message = iGroupService.create(group);
					if (message.contains(Msg.save)) {

						responseBean.setCode(Msg.SUCCESS_CODE);
						responseBean.setDescription("OK");
						responseBean.setObject(group);
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
	 * Service to update Group
	 */

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseBean update_group(@RequestBody TheGroup group, HttpServletRequest request) {
		ResponseBean responseBean = new ResponseBean();
		try {
			String userToken = request.getHeader("um_token");

			if (userToken != null) {

				if (userToken.equalsIgnoreCase(Msg.token)) {
					String message = iGroupService.update(group);
					if (message.contains(Msg.update)) {

						responseBean.setCode(Msg.SUCCESS_CODE);
						responseBean.setDescription("OK");
						responseBean.setObject(group);
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
	 * Service to delete group 
	 */

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseBean delete_group(HttpServletRequest request, @PathVariable("id") String id) {
		ResponseBean responseBean = new ResponseBean();
		try {
			String userToken = request.getHeader("um_token");

			if (userToken != null) {

				if (userToken.equalsIgnoreCase(Msg.token)) {
					if (iGroupService.delete(iGroupService.findById(Long.parseLong(id))).contains(Msg.delete)) { 
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
}
