package ua.edu.ucu.apps.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private static final double ABSOLUTE_ZERO = -273.0;
    private double[] temperatures;

    public TemperatureSeriesAnalysis() {
        this.temperatures = new double[0];

    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        if (temperatureSeries == null) {
            throw new IllegalArgumentException();
        }
        for (double temp : temperatureSeries)  {
            if (temp < ABSOLUTE_ZERO) {
                throw new InputMismatchException();
            }
        }
        this.temperatures = Arrays.copyOf(temperatureSeries, 
                                          temperatureSeries.length);

    }
    
    public double average() {
        if (temperatures == null || temperatures.length == 0) {
            throw new IllegalArgumentException();
        }
        double sum = 0;
        for (double temp : temperatures) {
            sum += temp;
        }
        return sum / temperatures.length;
        
    }

    public double deviation() {
        if (temperatures == null || temperatures.length == 0) {
            throw new IllegalArgumentException();
        }
        double mean = average();
        double sumSq = 0;
        for (double temp : temperatures) {
            sumSq += (temp - mean) * (temp - mean);
        }
        return Math.sqrt(sumSq / (temperatures.length - 1));
        
    }

    public double min() {
        if (temperatures == null || temperatures.length == 0) {
            throw new IllegalArgumentException();
        }
        double min = temperatures[0];
        for (double temp : temperatures) {
            if (temp < min) {
                min = temp;
            }
        }
        return min;
    }

    public double max() {
        if (temperatures == null || temperatures.length == 0) {
            throw new IllegalArgumentException();
        }
        double max = temperatures[0];
        for (double temp : temperatures) {
            if (temp > max) {
                max = temp;
            }
        }
        return max;
    }

    public double findTempClosestToZero() {
        if (temperatures == null || temperatures.length == 0) {
            throw new IllegalArgumentException();
        }
        double closest = temperatures[0];
        for (double temp : temperatures) {
            if (Math.abs(temp) < Math.abs(closest) 
                || (Math.abs(temp) == Math.abs(closest) && temp > closest)) {
                closest = temp;
                }
            }
            return closest;
        }
        public double findTempClosestToValue(double tempValue) {
            if (temperatures == null || temperatures.length == 0) {
                throw new IllegalArgumentException();
            }
            
            double closest = temperatures[0];
            for (double temp : temperatures) {
                if (Math.abs(temp - tempValue) 
                    < Math.abs(closest - tempValue)) {
                    closest = temp;
                }
                else if (Math.abs(temp - tempValue) 
                        == Math.abs(closest - tempValue) 
                         && temp > closest && tempValue == 0) {
                    closest = temp;
                }
            }
            return closest;
        }
        
        

    public double[] findTempsLessThen(double tempValue) {
        return Arrays.stream(temperatures)
            .filter(temp -> temp < tempValue)
            .toArray();
    }

    public double[] findTempsGreaterThen(double tempValue) {
        return Arrays.stream(temperatures)
            .filter(temp -> temp > tempValue)
            .toArray();
    }

    public double[] findTempsInRange(double lowerBound, double upperBound) {
        return Arrays.stream(temperatures)
            .filter(temp -> temp >= lowerBound 
                    && temp <= upperBound)
            .toArray();
    }

    public void reset() {
        temperatures = new double[0];

    }

    public double[] sortTemps() {
        double [] sortTemps = temperatures.clone();
        Arrays.sort(sortTemps);
        return sortTemps;
    }

    

    public TempSummaryStatistics summaryStatistics() {
        if (temperatures == null || temperatures.length == 0) {
            throw new IllegalArgumentException();
        }
        double mean = average();
        double deviation = deviation();
        double min = min();
        double max = max();
        return new TempSummaryStatistics(mean, deviation, min, max);
    }

    public int addTemps(double... temps) {
        for (double temp : temps) {
            if (temp < ABSOLUTE_ZERO) {
                throw new InputMismatchException();
            }
        }
        int old = temperatures.length;
        temperatures = Arrays.copyOf(temperatures, old + temps.length);
        System.arraycopy(temps, 0, temperatures, old, temps.length);
        return temperatures.length;
    }
}

    
