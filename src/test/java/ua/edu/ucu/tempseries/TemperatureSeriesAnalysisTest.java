package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;

import java.util.InputMismatchException;

import org.junit.Test;

import ua.edu.ucu.apps.tempseries.TempSummaryStatistics;
import ua.edu.ucu.apps.tempseries.TemperatureSeriesAnalysis;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void test() {
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        double actualResult = seriesAnalysis.average();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAverage() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.average();
    }

    @Test
    public void testDeviation() {
        double [] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 4.32049;
        double actualResult = seriesAnalysis.deviation();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMin() {
        double [] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -5.0;
        double actualResult = seriesAnalysis.min();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testMin2(){
        double [] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.min();
    }

    @Test
    public void testMax(){
        double [] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 5.0;
        double actualResult = seriesAnalysis.max();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToZero(){
        double [] temperatureSeries = {1.0, -1.0, -0.5, 0.5};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.5;
        double actualResult = seriesAnalysis.findTempClosestToZero();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToValue(){
        double [] temperatureSeries = {1.0, -1.0, -0.5, 0.5};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0; 
        double actualResult = seriesAnalysis.findTempClosestToValue(-0.75);
        assertEquals(expResult, actualResult, 0.00001);
    }
    

    @Test
    public void testFindTempsLessThan() {
        double[] temperatureSeries = {1.0, -1.0, -0.5, 0.5};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {-1.0, -0.5};

        double[] actualResult = seriesAnalysis.findTempsLessThen(0);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsGreaterThan() {
        double[] temperatureSeries = {1.0, -1.0, -0.5, 0.5};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {1.0, 0.5};

        double[] actualResult = seriesAnalysis.findTempsGreaterThen(0);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testSortTemps() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {-5.0, 1.0, 3.0, 5.0};


        double[] actualResult = seriesAnalysis.sortTemps();

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testAddTemps() {
        double[] temperatureSeries = {1.0, -1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        int expResult = 5; 

        int actualResult = seriesAnalysis.addTemps(2.0, -2.0, 0.0);

        assertEquals(expResult, actualResult);
    }

    @Test(expected = InputMismatchException.class)
    public void testAddTemps2() {
        double[] temperatureSeries = {1.0, -1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.addTemps(-300.0);
    }

    @Test
    public void testSummaryStatistics() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        TempSummaryStatistics stats = seriesAnalysis.summaryStatistics();

        assertEquals(1.0, stats.getAverage(), 0.00001);
        assertEquals(4.32049, stats.getDeviation(), 0.00001);
        assertEquals(-5.0, stats.getMin(), 0.00001);
        assertEquals(5.0, stats.getMax(), 0.00001);
    }
}
