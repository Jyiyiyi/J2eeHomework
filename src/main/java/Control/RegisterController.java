package Control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/")
public class RegisterController {
	UserDAO userDao = new UserDAO();
	User user = new User();
	String mes = "";
	@RequestMapping(method = RequestMethod.POST)	
	public String register(String username,String password,String password2,String contact,ModelMap model) {
		if(!(username.isEmpty()) && !(password.isEmpty()) && password2.equals(password) && !(contact.isEmpty())) {
			System.out.println("Hi");
			user.setUsername(username);
			user.setPassword(password);
			user.setContact(contact);
			if(userDao.add(user) > 0){
				mes = "注册成功！请登录";
				model.addAttribute("MES", mes);
				
				return "login";	
			} else {
				mes = "注册失败！";
				model.addAttribute("MES", mes);
				
				return "register";
			}
			
		}else {
			mes = "非法输入！请重新输入注册信息";
			model.addAttribute("MES", mes);
			return "register";
		}
		
	}
	

}
