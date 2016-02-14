package com.mycompany.hr.service;

import java.util.Date;

import com.mycompany.hr.model.HolidayServiceResult;

public interface HumanResourceService {
    HolidayServiceResult bookHoliday(Date startDate, Date endDate, String name);
}
