package com.lsy.vehicle.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.swing.JOptionPane;
import javax.xml.ws.Endpoint;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import org.springframework.stereotype.Service;

@Service
@WebService
public class CalculatorServiceBean {

    @WebMethod
    public Integer add(Integer a, Integer b)
    {
        return a+b;
    }
    
    @WebMethod
    public CalculatorResult calculate(CalculatorData data)
    {
        Double result = 0.0;
        for (Double value : data.getValues())
            switch (data.getOperator())
            {
            case ADD: 
                result += value;
            case DIVIDE: 
                result /= value;
            case MULTIPLY: 
                result *= value;
            case SUBSTRACT: 
                result -= value;
            }
        return new CalculatorResult(result);
    }
    
    @WebMethod()
    @WebResult(name="summary")
    @RequestWrapper(partName="request", localName="SumRequest")
    @ResponseWrapper(partName="response", localName="SumResponse")
    public Double sum(
       @WebParam(name="operator", partName="operation") String operator, 
       @WebParam(name="value", partName="values") Double... values)
    {
        Double result = 0.0;
        for(Double value : values)
            if ("+".equals(operator))
                result += value;
            else
                result -= value;
        return result;
    }
    
    @WebMethod
    public Double divide(Double a, Double b)
    {
        return a/b;
    }
    
    public static void main(String[] args) {
        String url = "http://localhost:8080/calculator";
        CalculatorServiceBean service = new CalculatorServiceBean();
        Endpoint endpoint = Endpoint.publish(url, service);
        
        JOptionPane.showMessageDialog(null, "Server is running...");
        
        endpoint.stop();
    }

}
