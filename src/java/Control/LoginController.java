package Control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/")
public class LoginController {
	UserDAO userDao = new UserDAO();
	User user = new User();
	String mes = "";
	@RequestMapping(method = RequestMethod.POST)	
	public String register(String username,String password,ModelMap model) {
		if(!(username.isEmpty()) && !(password.isEmpty()) && password2.equals(password) && !(contact.isEmpty())) {
			System.out.println("Hi");
			user.setUsername(username);
			user.setPassword(password);
			User userData=userDao.login(user);
			if(user.getPassword().equals(userData.getPassword())) {
				System.out.println("login："+userData.toString());
				model.addAttribute("loginUser", userData);
				return "index";
			}else {
				mes="登录失败，密码错误"; 
				model.addAttribute("MES", mes);
				return "login";
				
			}
		
}
