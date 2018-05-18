/**
 * Tutti metodi che saranno monitorati devono essere marcati con l'annotazione @MetricPerformance
 * <pre>
 * {@code
 *    @RequestMapping("...")
 *	  @MetricPerformace(identifier=AppConstant.NOME_IDENTIFICATIVO_METODO)
 *
 *    AppConstant.java
 *    --------------------------------------------------------------------
 *    public final static int NOME_IDENTIFICATIVO_METODO = 1;
 *     
 * }
 * </pre>
 * 
 * @author Sergio Arellano Diaz
 * @version 1.0.1
 * @since 17/05/2018
 *
 */
package it.peruvianit.web.metric.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(METHOD)
@Retention(RUNTIME)
public @interface MetricPerformace {
	int identifier() default 0;
}
