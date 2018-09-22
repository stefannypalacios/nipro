package sv.com.nipro.interfaz.controller;

import javax.faces.bean.ManagedBean;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.springframework.beans.factory.annotation.Autowired;

import sv.com.nipro.interfaz.entities.Token;
import sv.com.nipro.interfaz.repository.TokenRepository;

@ManagedBean(name = "BaseBean")
public class BaseBean {
	@Autowired
	public TokenRepository tokenService;

	public BaseBean() {	}
	
	public boolean isTokenActive(String token){
		
		Token t;
		if (tokenService != null){
			try {
				t = tokenService.findByToken(token);
				
				if (t != null){
					DateTime startDate = new DateTime(t.getLastUsage().getTime());
					DateTime endDate = new DateTime();
					
					Minutes minutes = Minutes.minutesBetween(startDate, endDate);
				    int numberOfMinutes = minutes.getMinutes();
				    
				    System.out.println("**************" + numberOfMinutes + "**************");
				    if (numberOfMinutes >= 30){
				    	t.setStatus(false);
				    	tokenService.save(t);
				    }
				    
				    return (numberOfMinutes < 30);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
