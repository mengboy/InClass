package com.swjtu.cn.action;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swjtu.cn.bean.Classroom_pick;
import com.swjtu.cn.bean.Light;
import com.swjtu.cn.bean.Student;
import com.swjtu.cn.common.QueryBase;
import com.swjtu.cn.common.Response;
import com.swjtu.cn.common.Status;
import com.swjtu.cn.mapper.Classroom_pickMapper;
import com.swjtu.cn.service.LightService;
import com.swjtu.cn.utils.ActionUtil;
import com.swjtu.cn.utils.TimeUtil;

@RestController
public class LightAction {
	Log logger = LogFactory.getLog(LightAction.class);
	@Autowired
	LightService lightService;
	@Autowired
	Classroom_pickMapper classroom_pickMapper;

	@RequestMapping("/inclass/api/light/add")
	public Object add(HttpServletRequest request,@RequestParam String classroom_id,String light_order){
		int status = 0;
		try {
			Light light = new Light();
			int light_id = Integer.parseInt(classroom_id+light_order);
			System.out.println(light_id);
			if(this.lightService.get(light_id) != null)
				status = Status.EXISTS;
			else{
				System.out.println(light_id);
				light.setId(light_id);
				light.setTime(new Date());
				status = this.lightService.add(light);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status = Status.ERROR;
		}
		return new Response(status);
	}
	
	//学生查找可以控制的终端,未登录返回15
	@RequestMapping("/inclass/api/light/getcontrollightsbystu")
	public Object getcontrollightsbystu(HttpServletRequest request){
		int status = 0;
		List<Map<String,Object>> list = null;
		try {
			Student stu = (Student)ActionUtil.getCurrentUser(request);
			if(stu == null)
				return new Response(Status.USER_IS_NULL);
//			System.out.println("number:"+stu.getStudentNumber());
			list = this.lightService.selectcontrolbystu(stu.getStudentNumber());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status = Status.ERROR;
		}
		return new Response(status,list);
	}
	
	
	//管理员查找终端状态
	@RequestMapping("/inclass/api/light/getlightbyadmin")
	public Object getlightbyadmin(HttpServletRequest request,@RequestParam long requestPage,@RequestParam long pageSize,@RequestParam String type,@RequestParam String condition){
		QueryBase querybase = new QueryBase();
		querybase.setPageSize(pageSize);
		querybase.setCurrentPage(requestPage);
		Map<String,Object> parameters = new HashMap<String,Object>();
//		System.out.println(condition);
		if("1".equals(type))
			parameters.put("condition", condition+"%");
		else
			parameters.put("condition", condition);
		parameters.put("type", type);
		querybase.setParameters(parameters);
		int status = this.lightService.selectByAdmin(querybase); 
		return new Response(status,querybase);
	}
	
	//更新控制模式
	@RequestMapping("/inclass/api/light/updatemode")
	public Object updatemode(HttpServletRequest request,@RequestParam int mode,@RequestParam String classroom_id){
		System.out.println(classroom_id);
		int status = this.lightService.updatemode(classroom_id, mode);
		return new Response(status);
	}
	
	//更新远程控制
	@RequestMapping("/inclass/api/light/updatestatus")
	public Object updatestatus(HttpServletRequest request,@RequestParam int status,@RequestParam int id){
		int statuss = this.lightService.updatestatus(id, status);
		return new Response(statuss);
	}
	
	//与网关交互的接口
	@RequestMapping("/inclass/api/light/packagemessage")
	public Object packageInfo(HttpServletRequest request,@RequestParam String message,@RequestParam(required=false) String peo_num){
		System.out.print("message" + message + peo_num);
		int sta = 0;
		Light light_update = null;
		List<Light> list_status = this.lightService.getstatuschange();
		List<Light> list_mode = this.lightService.getmodechange();
		String statuschange = "";
		String send_modes = "";
		logger.info("------packagemessage------");
		//peo_num = "1*04,2*03,3*06,4*02";
		logger.info("message:"+message+"////peo_num:"+peo_num);
		//先获得状态更改的终端,每次只处理第一个变化的
		if(list_status.size() != 0){
			Light light = list_status.get(0);
			{
				String idid = light.getId() + "";
				String send_id = "";
				String change_status = "";
				if("1101".equals(idid.substring(0, 4)))
					send_id = "1";
				if("1102".equals(idid.substring(0, 4)))
					send_id = "2";
				if("1201".equals(idid.substring(0, 4)))
					send_id = "3";
				if("1202".equals(idid.substring(0, 4)))
					send_id = "4";
				List<Light> list_change_classroom_lights = this.lightService.getlightstatuschange(idid.substring(0, 4)+"_");
				for(Light light1:list_change_classroom_lights){
					if((int)light1.getId() == (int)light.getId()){
						//System.out.println("lightid"+light.getId()+",light1 idid:"+light1.getId()+"getchange:"+light1.getChanged());
						change_status = change_status + light1.getChanged();
					}
					else{
						//System.out.println("lightid"+light.getId()+",light1 idid:"+light1.getId()+"getstatus:"+light1.getStatus());
						change_status = change_status + light1.getStatus();
					}
				}
				statuschange = statuschange + send_id + "*" + change_status;
			}
			light_update = new Light();
			light_update.setChangeissuccess(1);
			light_update.setIschange(0);
			light_update.setStatus(light.getChanged());
			light_update.setId(light.getId());
			sta = this.lightService.update(light_update);
			//logger.info("更新light，id:"+light.getId()+",statuschangeissuccess:"+1+",statusischange:"+0+",status:"+light_update.getStatus());
			if(sta > 0){
				sta = Status.ERROR;
				logger.info("error1");
				return new Response(sta,"error");
			}
			
		}
		
		//更新远程发过来的终端信息
/*		Light light_remote = new Light();
		Date time = TimeUtil.getdate();
		String[] strs =  message.split(",");
		for(String ss:strs){
			String[] eachs = ss.split("\\*");
			String str_id = eachs[0];
			String str_status = eachs[1];
			try {//判断转型是否有异常
				light_remote.setId(Integer.parseInt(str_id));
				light_remote.setStatus(Integer.parseInt(str_status));
				if(this.lightService.get(Integer.parseInt(str_id)).getStatus() != Integer.parseInt(str_status)){
					sta = this.lightService.update(light_remote);
					light_remote.setTime(time);
					logger.info("远程更新状态成功,id:"+str_id+",status:"+str_status);
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new Response(Status.ERROR);
			}
			if(sta > 0){
				logger.info("error3");
				return new Response(sta);
			}
		}*/
		Light light_remote = new Light();
		Date time = TimeUtil.getdate();
		String[] strs =  message.split(",");
		for(String ss:strs){
			String[] eachs = ss.split("\\*");
			String str_id = eachs[0];
			String str_status = eachs[1];
			String classroom_number = null;
			String light_number = null;
			String lightstatus = null;
			for(int i = 0;i < 4;i++){
				try {//判断转型是否有异常
					if("1".equals(str_id)||"2".equals(str_id))
						classroom_number = "110" + str_id;
					else
						classroom_number = "120" + (Integer.parseInt(str_id)-2);
					light_number = classroom_number + (i+1);
					lightstatus = str_status.substring(i, i+1);
					//System.out.println("lightstatus:"+lightstatus);
					//System.out.println("lightnumber:"+light_number+",lightstatus:"+lightstatus);
					light_remote.setId(Integer.parseInt(light_number));
					light_remote.setStatus(Integer.parseInt(lightstatus));
					if(this.lightService.get(Integer.parseInt(light_number)).getStatus() != Integer.parseInt(lightstatus)){
						if(this.lightService.get(Integer.parseInt(light_number)).getMode() == 0){
							sta = this.lightService.update(light_remote);
							light_remote.setTime(time);
							logger.info("远程更新状态成功,id:"+light_number+",status:"+lightstatus);
						}
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new Response(Status.ERROR);
				}
				if(sta > 0){
					logger.info("error3");
					return new Response(sta);
				}
			}
		}
		
		//获取人数 id*num,
		if(peo_num != null){
			String[] peos = peo_num.split(",");
			String room_id = null;
			for(String peo:peos){
				String[] each_peo = peo.split("\\*");
				if("1".equals(each_peo[0])||"2".equals(each_peo[0]))
					room_id = "110"+each_peo[0];
				else
					room_id = "120"+(Integer.parseInt(each_peo[0])-2);
				String num = each_peo[1];
				Classroom_pick cp = new Classroom_pick();
				cp.setClassroomId(Integer.parseInt(room_id));
				cp.setNum(Integer.parseInt(num));
				cp.setPickTime(new Date());
				sta = this.classroom_pickMapper.insertSelective(cp);
				logger.info("更新采集的教室人数信息,classroom_id"+room_id+",num:"+num);
				if(sta == 0){
					sta = Status.ERROR;
					logger.info("error3");
					return new Response(sta,"error");
				}
			}
		}
		System.out.println("size:"+list_mode.size());
		//获得变化的控制模式
		if(list_mode.size() != 0){
			Light light1 = list_mode.get(0);
			String idid = light1.getId() + "";
			String send_id = "";
			if("1101".equals(idid.substring(0, 4)))
				send_id = "1";
			if("1102".equals(idid.substring(0, 4)))
				send_id = "2";
			if("1201".equals(idid.substring(0, 4)))
				send_id = "3";
			if("1202".equals(idid.substring(0, 4)))
				send_id = "4";
			
			light_update = new Light();
			send_modes = send_modes + send_id + "*" + light1.getChanged();
			light_update.setChangeissuccess(2);//模式成功
			light_update.setIschange(0);
			light_update.setMode(light1.getChanged());
			light_update.setId(light1.getId());
			sta = this.lightService.updatemodebyremote(light_update,idid.substring(0, 4)+"_");
			logger.info("更新变化的控制模式,id"+light1.getId()+",Modechangeissuccess:"+1+",Modeischange:"+0+"mode:"+light1.getChanged());
			if(sta > 0){
				sta = Status.ERROR;
				logger.info("error2");
				return new Response(sta,"error");
			}
		}
		
		logger.info("扫描的信息:send_modes"+send_modes+",statuschange"+statuschange);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("modes", send_modes);//模式变化
		map.put("change", statuschange);//状态变化
		return new Response(sta,map);
//		return new Response(0);
	}
	
	//批量删除
	@RequestMapping("/inclass/api/light/deletes")
	public Object deletes(@RequestParam String[] ids){
		int status = 0;
		Light light = null;
		try {
			for(String id:ids){
				light = new Light();
				light.setId(Integer.parseInt(id));
				status = this.lightService.delete(light);
				if(status != 0)
					return new Response(status);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status = Status.ERROR;
		}
		return new Response(status);
	}
	
	public static void main(String[] args) throws Exception {
/*		FileImageOutputStream fo = null;
		FileImageInputStream fs = null;
		try {
			fs = new FileImageInputStream(new File("G:/ss.jpg"));
			byte[] img = new byte[(int)fs.length()];
			fs.read(img);
			fo = new FileImageOutputStream(new File("G:/s.jpg"));
			fo.write(img);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			fo.close();
			fs.close();
		}*/
		FileOutputStream fo = null;
		try {
			String str = "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";
			byte[] bytes = new Base64().decode(str);
			fo = new FileOutputStream(new File("ii.jpg"));
			fo.write(bytes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			fo.close();
		}
	}
	
}