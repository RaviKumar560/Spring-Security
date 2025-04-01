package in.sp.main.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RestController
public class WebClintContoller {

    private  WebClient webClient;
    
    public WebClintContoller() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:9947") // Change as per your API's base URL
                .build();
    }


    @GetMapping("/web")
    public String display() {
   
            return webClient.get()
                    .uri("/get")
                    .headers(headers -> headers.setBasicAuth("ravi", "ravi@123")) 
                    .retrieve()
                    .bodyToMono(String.class)
                    .block(); 
       
    }
}
