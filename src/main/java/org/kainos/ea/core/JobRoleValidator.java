package org.kainos.ea.core;

import org.CFT.cli.DeliveryEmployeeRequest;

public class JobRoleValidator {

    public String isValidDeliveryEmployee(DeliveryEmployeeRequest deliveryEmployee) {
        if (deliveryEmployee.getName().length() > 50) {
            return "Delivery employee name should be under 50 characters";
        }
        if (deliveryEmployee.getSalary() < 10) {
            return "Invalid salary";
        }
        if (deliveryEmployee.getNational_insurance_number().length() != 9) {
            return "National insurance number should be 9 digits";
        }
        if (deliveryEmployee.getBank_account_number().length() != 10) {
            return "Bank account number should be 10 digits";
        }
        return null;
    }
}
