package Control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping
public class GroupController {
	//增加队伍
	@RequestMapping(value="/addGroup",method=RequestMethod.POST)
	public String addGroup(String groupname,ModelMap model) {
		String mes = "";
		if(!(groupname.isEmpty())) {
			System.out.println("Hi");
			GroupDAO groupDao = new GroupDAO();
			if(groupDao.add(groupname) > 0){
				mes = "添加成功！";
				model.addAttribute("MES", mes);	
			} else {
				mes = "添加失败！";
				model.addAttribute("MES", mes);
			}
			
		}else {
			mes = "非法输入！请重新输入信息";
			model.addAttribute("MES", mes);
		}
		
	return "index";	
	}
	//解散队伍
	@RequestMapping(value="/deleteGroup",method=RequestMethod.GET)
	public String deleteGroup(int groupid,ModelMap model) {
	String mes = "";
	if(GroupDao.delete(groupid) > 0){
		mes = "删除成功!";
	} else {
		mes = "删除失败!";
	}
		model.addAttribute("MES", mes);
		return "index";
	}
	
	//更改队伍信息
	@RequestMapping(value="/updateGroup",method=RequestMethod.POST)
	public String updateGroup(int groupid,String groupname,String info,ModelMap model) {
		String mes = "";
		Group group = new Group();
		GroupDAO groupDao = new GroupDAO();    		
		group = groupDao.getById(groupid);
		if(!(group.isEmpty()) &&  !(info.isEmpty())) {
			System.out.println("Hi");
			group.setTitle(groupname);
			group.setContent(info);
			System.out.println(group.toString());
			if(groupDao.update(group) > 0){
				mes = "修改成功！";
				model.addAttribute("MES", mes);
				return "index";	
			} else {
				mes = "修改失败！";
				model.addAttribute("MES", mes);
	
			}
		}else {
			mes = "非法输入！请重新输入注册信息";
			model.addAttribute("MES", mes);
			
		}
	}

}
