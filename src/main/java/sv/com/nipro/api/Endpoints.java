package sv.com.nipro.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import sv.com.nipro.api.request.CheckoutRequest;
import sv.com.nipro.api.request.MessageRequest;
import sv.com.nipro.api.request.UsuarioRequest;
import sv.com.nipro.api.response.CheckoutResponse;
import sv.com.nipro.api.response.MessageResponse;
import sv.com.nipro.api.response.UsuarioResponse;

public interface Endpoints {
	
	@GET("checkin")
    Call<UsuarioResponse> checkin(@Query("AppUser") String AppUser, @Query("Password") String Password);
	
	@POST("acceptMessage")
	Call<MessageResponse> acceptMessage(@Body MessageRequest messageRequest);
	
	@GET("checkout")
	Call<CheckoutResponse> checkout(@Query("token") String token);
}
