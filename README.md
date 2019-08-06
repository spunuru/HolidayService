# SOAP Service - HolidayService
* A simple SOAP service coded with Spring web services.
* Builds HolidayService.war file.
* It can be run from maven inside embedded tomcat server or deployed to external tomcat server.
* tomcat7-maven-plugin is used to run from maven command line.
Checkout maven goals [here](https://tomcat.apache.org/maven-plugin-2.0/tomcat7-maven-plugin/plugin-info.html)

## Requirements
* Java 8
* Ensure Maven 3 is installed. (It may work with maven 2, but not tested).

## Useful commands

### Build project from command line
```bash
git clone https://github.com/spunuru/HolidayService.git
cd <eclipse_workspace_dir>/HolidayService
mvn clean install
```

### Run HolidayService SOAP service in embedded Tomcat server from command line.
```bash
cd <eclipse_workspace_dir>/HolidayService
mvn tomcat7:run
```

### URLs
* WSDL URL - http://localhost:8080/HolidayService/holidayService/holiday.wsdl
* Endpoint URL - http://localhost:8080/HolidayService/holidayService/

### Sample SOAP request
```bash
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:sch="http://mycompany.com/hr/schemas">
   <soapenv:Header/>
   <soapenv:Body>
      <sch:HolidayRequest>
         <!--You may enter the following 2 items in any order-->
         <sch:Holiday>
            <sch:StartDate>2016-03-01</sch:StartDate>
            <sch:EndDate>2016-03-05</sch:EndDate>
         </sch:Holiday>
         <sch:Employee>
            <sch:Number>123</sch:Number>
            <sch:FirstName>Robert</sch:FirstName>
            <sch:LastName>Smith</sch:LastName>
         </sch:Employee>
      </sch:HolidayRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

### Sample SOAP response
```bash
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
   <SOAP-ENV:Header/>
   <SOAP-ENV:Body>
      <HolidayResponse xmlns="http://mycompany.com/hr/schemas">
         <Status>REJECTED</Status>
         <Detail>You had enough holidays. Get back to work now!</Detail>
      </HolidayResponse>
   </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```

### Logs
holiday.log file generated in the current working directory

### Last Update
I have not touched github in 3 years. Can you believe it? Time flies by.
