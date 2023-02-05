package malisac;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExamSE {


    String message;

    public  double  computeQuotient(double x, double y) throws IllegalArgumentException {
        return -99999999.9;
    }

    @BeforeEach
    void Setup(){
        message = "Starting tests";
    }

    @Test
    void CheckParam(){
        assertNotEquals(computeQuotient(9,8), computeQuotient(9.5, 8.9));

    }

    @Test
    void CheckException(){
        fail(String.valueOf(computeQuotient(5,3)));
    }


}


