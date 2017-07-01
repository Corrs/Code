package com.chinesejr.webservices.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.chinesejr.util.L;
import com.chinesejr.webservices.service.HumanResourceService;

@Service
public class StubHumanResourceService implements HumanResourceService {

	@Override
	public void bookHoliday(Date startDate, Date endDate, String name) {
		// TODO Auto-generated method stub
		L.i("Booking holiday for [{"+startDate+"} - {"+endDate+"}] for [{"+name+"}] ");
	}

}
