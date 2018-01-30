package ua.kiyv.training.testingSystem.controller.validate;

/**
 * Generic Validator interface which should return  object with errors
 * @param <T>
 */
public interface Validator<T> {
    /**
     * main methods which validates input data
     * @param t
     * @return  object with errors
     */
    Errors validate(T t);
}
