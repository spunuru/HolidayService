package com.mycompany.hr.ws;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.mycompany.hr.model.HolidayServiceResult;
import com.mycompany.hr.service.HumanResourceService;
import com.mycompany.hr.util.XMLUtil;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.xpath.XPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Endpoint
public class HolidayEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(HolidayEndpoint.class);

    private static final String NAMESPACE_URI = "http://mycompany.com/hr/schemas";

    private XPath startDateExpression;

    private XPath endDateExpression;

    private XPath nameExpression;

    private HumanResourceService humanResourceService;

    @Autowired
    public HolidayEndpoint(HumanResourceService humanResourceService)
            throws JDOMException {
        this.humanResourceService = humanResourceService;

        Namespace namespace = Namespace.getNamespace("hr", NAMESPACE_URI);

        startDateExpression = XPath.newInstance("//hr:StartDate");
        startDateExpression.addNamespace(namespace);

        endDateExpression = XPath.newInstance("//hr:EndDate");
        endDateExpression.addNamespace(namespace);

        nameExpression = XPath.newInstance("concat(//hr:FirstName,' ',//hr:LastName)");
        nameExpression.addNamespace(namespace);
        System.out.println("HolidayEndpoint instance created");
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "HolidayRequest")
    @ResponsePayload
    public Element handleHolidayRequest(@RequestPayload Element holidayRequest)
            throws Exception {

        logger.info("Request:\n" + XMLUtil.xmlElementToString(holidayRequest));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = dateFormat.parse(startDateExpression.valueOf(holidayRequest));
        Date endDate = dateFormat.parse(endDateExpression.valueOf(holidayRequest));
        String name = nameExpression.valueOf(holidayRequest);

        HolidayServiceResult result = humanResourceService.bookHoliday(startDate, endDate, name);
        Element response = new Element("HolidayResponse", NAMESPACE_URI);
        Element status = new Element("Status", NAMESPACE_URI);
        status.setText(result.getStatus().toString());
        Element detail = new Element("Detail", NAMESPACE_URI);
        detail.setText(result.getDetail());
        response.addContent(status);
        response.addContent(detail);
        
        logger.info("Response:\n" + XMLUtil.xmlElementToString(response));
        return response;
    }

}