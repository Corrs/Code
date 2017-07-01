package com.chinesejr.webservices.client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.chinesejr.webservices.client.ValidateCodeWebService.EnValidateByte;
import com.chinesejr.webservices.client.ValidateCodeWebService.EnValidateByteResponse;

public class ValidateCodeWebServiceClient extends WebServiceGatewaySupport {
	public EnValidateByteResponse getImageByteResponse(String value) {
		EnValidateByte request = new EnValidateByte();
		request.setByString(value);
		EnValidateByteResponse response = (EnValidateByteResponse) getWebServiceTemplate()
				.marshalSendAndReceive(request, new SoapActionCallback(
		                        "http://WebXml.com.cn/enValidateByte"));
		return response;
	}
}
