package com.chinesejr.webservices.client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.chinesejr.webservices.client.MobileCodeWS.GetMobileCodeInfo;
import com.chinesejr.webservices.client.MobileCodeWS.GetMobileCodeInfoResponse;

public class MobileCodeWSClient extends WebServiceGatewaySupport {
	public GetMobileCodeInfoResponse getMobileCode(String mobile) {
		GetMobileCodeInfo request = new GetMobileCodeInfo();
		request.setMobileCode(mobile);
		GetMobileCodeInfoResponse response = (GetMobileCodeInfoResponse) getWebServiceTemplate()
				.marshalSendAndReceive(request,  
		                new SoapActionCallback(  
		                        "http://WebXml.com.cn/getMobileCodeInfo"));

		return response;
	}
}
