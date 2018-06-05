package com.example.user.gotaagota;

import com.example.user.gotaagota.objetos.deuda;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void prueba1() throws Exception {
        deuda d = new deuda(200000,20,4,new ArrayList<String>());
        assertEquals(d.ValorCuota(), 60500,0);
    }
}