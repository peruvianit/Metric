/**
 * Controlla che il metodo della request che stata richiesta, sia annotata con l'annotazione
 * @MetricPerformace
 * 
 * @author Sergio Arellano Diaz
 * @version 1.0.1
 * @since 17/05/2018
 * @see MetricPerformace
 *
 */
package it.peruvianit.web.metric.service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.springframework.web.method.HandlerMethod;

import it.peruvianit.web.metric.annotation.MetricPerformace;

public class MetricService {

	/**
	 * <p>Ritorna un boolean, se il metodo ha assegnato l'annotazione @MetricPerformace.</p>
	 * 
	 * @param handlerMethod parametro che viene dal interceptor per avere informazione dal
	 *        metodo chiamante.
	 * 
	 * @return boolean 
	 * 
	 * @since 1.0
	 */
	public static boolean isMethodControl(final HandlerMethod handlerMethod) {
        boolean isSecured = false;
        final Method method = handlerMethod.getMethod();
        final Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
        for (final Annotation annotation : declaredAnnotations) {
            if (MetricService.isAnnotation(annotation)) {
                isSecured = true;
                break;
            }

        }
        return isSecured;
	}

	/**
	 * <p>Ritorna un boolean, se l'annotazione è equale a @MetricPerformace.</p> 
	 * 
	 * @param annotation arriva ogni annotazione trovata sulla lista trovata nella funzione
	 *        {@link MetricService#isMethodControl(HandlerMethod) }
	 * 
	 * @return boolean 
	 * 
	 * @since 1.0
	 */
	private static boolean isAnnotation(final Annotation annotation) {
        boolean isMetric = false;
        if (annotation instanceof MetricPerformace) {
        		isMetric = true;
        }
        return isMetric;
    }
}
