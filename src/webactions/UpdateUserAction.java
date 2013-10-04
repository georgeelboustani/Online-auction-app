package webactions;

import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import pagebeans.UpdateBean;

import com.google.gson.Gson;

import model.ForUserUpdate;
import jdbc.UserDAO;
import jdbc.UserDAOImpl;


public class UpdateUserAction implements WebActionAjax {

	@Override
	public Map<String, Object> executeAjax(HttpServletRequest req,
			HttpServletResponse res, Logger logger) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String jsonData = req.getParameter("data");
		Gson gson = new Gson();
		ForUserUpdate updateData = gson.fromJson(jsonData, ForUserUpdate.class);
		
		UpdateBean updateBean = new UpdateBean();
		
		int uid = LoginUtils.getUserId(req);
		updateBean.setUid(uid);
		updateBean.setFirstName(updateData.getFirstName());
		updateBean.setLastName(updateData.getLastName());
		updateBean.setNickname(updateData.getNickName());
		updateBean.setAddress(updateData.getAddress());
		updateBean.setPassword(updateData.getPassword());
		
		try{
			SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-mm-dd");
			Date parsed = DateFormat.parse(updateData.getBirthDate());
			java.sql.Date sql = new java.sql.Date(parsed.getTime());
			updateBean.setYearOfBirth(sql);
			
		}catch(ParseException pe){
			pe.printStackTrace();
		}
		
		updateBean.setCreditCardNum(updateData.getCreditCardNum());
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<UpdateBean>> violations = validator.validate(updateBean);
		
		
			//TODO: Set customValidations if emails & usernames are to be unique 
			if(violations.size() > 0){
				//Returns error message for violations detected
				StringBuffer message = new StringBuffer();
				message.append("Could not process update, you had invalid inputs of: ");
				Iterator<ConstraintViolation<UpdateBean>> it = violations.iterator();
				while(it.hasNext()){
					message.append("<br/>" + it.next().getMessage());
				}
				resultMap.put("success", false);
				resultMap.put("message", message.toString());
				resultMap.put("redirect", "controller?action=viewProfile");
				
			}else{
				UserDAO userdao = new UserDAOImpl();
				try {
					userdao.updateUser(updateBean);
					System.out.println("It goes in..");
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				resultMap.put("success", true);
				resultMap.put("message", "You're Updated");
				resultMap.put("redirect", "controller?action=viewProfile");
			}
		
		
		// TODO - return the next page
		return resultMap;
	}
}