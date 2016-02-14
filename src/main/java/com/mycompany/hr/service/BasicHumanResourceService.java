package com.mycompany.hr.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mycompany.hr.model.HolidayServiceResult;

@Service                                                                                 
public class BasicHumanResourceService implements HumanResourceService {
    
    private static final Logger logger = LoggerFactory.getLogger(BasicHumanResourceService.class);
    private final Random random = new Random(738873930303l);
    
    private List<String> approved = Arrays.asList("Enjoy Holidays!", "You really deserved Vacation!", "Hope you have safe trip!", "Stay warm in the cold weather!");
    private List<String> rejected = Arrays.asList("You had enough holidays. Stop requesting!", "Have you done any work in your life? Why do you need vacation?", "I do not like your name!", "Sorry!. HR is not happy with your performance!");

    public HolidayServiceResult bookHoliday(Date startDate, Date endDate, String name) {
        
        logger.info("Holiday requested for {} days for {}",  getDayCount(startDate, endDate), name);        
        HolidayServiceResult result = new HolidayServiceResult();
        
        random.nextInt(approved.size());
        
        if (random.nextBoolean()) {
            result.setStatus(HolidayServiceResult.Status.APPROVED);
            result.setDetail(approved.get(random.nextInt(approved.size())));
        }
        else {
            logger.info("rejecting the holiday request.");
            result.setStatus(HolidayServiceResult.Status.REJECTED);
            result.setDetail(rejected.get(random.nextInt(rejected.size())));
        }
        return result;
    }
    
    private int getDayCount(Date startDate, Date endDate) {
        
        LocalDate start = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period period = Period.between(start, end);
        return period.getDays();
    }
}