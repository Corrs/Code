package com.chinesejr.webservices.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.chinesejr.webservices.client.MobileCodeWSClient;
import com.chinesejr.webservices.client.ValidateCodeWebServiceClient;

@Configuration
public class WSClientConfig {
	@Bean(value="mobileMarshaller")
    public Jaxb2Marshaller mobileMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.chinesejr.webservices.client.MobileCodeWS");
        return marshaller;
    }
	
	@Bean(value="validCodeMarshaller")
    public Jaxb2Marshaller validCodeMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.chinesejr.webservices.client.ValidateCodeWebService");
        return marshaller;
    }
	
	@Bean
    public ValidateCodeWebServiceClient validCodeWSClient(@Qualifier("validCodeMarshaller") Jaxb2Marshaller marshaller) {
		ValidateCodeWebServiceClient client = new ValidateCodeWebServiceClient();
		client.setDefaultUri("http://ws.webxml.com.cn/WebServices/ValidateCodeWebService.asmx?wsdl");
		client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client; 
    }
	
    @Bean
    public MobileCodeWSClient mobileWSClient(@Qualifier("mobileMarshaller") Jaxb2Marshaller marshaller) {
    	MobileCodeWSClient client = new MobileCodeWSClient();
    	client.setDefaultUri("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
