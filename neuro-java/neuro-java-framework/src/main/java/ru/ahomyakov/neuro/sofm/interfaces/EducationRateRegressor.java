package ru.ahomyakov.neuro.sofm.interfaces;

import ru.ahomyakov.neuro.errors.IllegalInitDataException;

/**
 *
 */
public interface EducationRateRegressor {
    void setStep(double step) throws IllegalInitDataException;

    public double newEducationRate(double oldEducationRate);
}
