/**
 * Interceptor pre il controllo della latenza per ogni request, il metodo da controllare deve avere
 * l'annotazione @MetricPerformace, quelli che superano il tempo massimo maxLatency (milisecondi), sono
 * aggiunti nella lista FIFO (MetricQueue)  
 * 
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
 * @see MetricPerformace
 * @see MetricQueue
 *
 */
package it.peruvianit.web.metric.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import it.peruvianit.web.metric.dto.MetricDTO;
import it.peruvianit.web.metric.queue.MetricQueue;
import it.peruvianit.web.metric.service.MetricService;

public class ControlRequestPerformanceInterceptor implements HandlerInterceptor {
	
	protected String codiceApplicazione;
	protected Long maxLatency;
	protected Integer maxQueue;
	
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
		long startTime = System.currentTimeMillis();
		httpServletRequest.setAttribute("startTime", startTime);		
		return true;
	}
	
	public void afterCompletion(HttpServletRequest request,	HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}
	
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {
		long startTime = (Long)httpServletRequest.getAttribute("startTime");
		long endTime = System.currentTimeMillis();
		long executeTime = endTime - startTime;

		if ( MetricService.isMethodControl((HandlerMethod)handler) ){
			if (executeTime > maxLatency){
				
				MetricDTO metricDTO = new MetricDTO();
				metricDTO.setIdentifier(UUID.randomUUID());
				metricDTO.setStartRequest(startTime);
				metricDTO.setIpAddressLocale(httpServletRequest.getLocalAddr());
				metricDTO.setIpAddressRemote(httpServletRequest.getRemoteAddr());
				metricDTO.setMethod(httpServletRequest.getMethod());
				metricDTO.setAgent(httpServletRequest.getHeader("user-agent"));
				metricDTO.setContentType(httpServletResponse.getHeader("content-type"));
				metricDTO.setReference(httpServletRequest.getRequestURI());
				metricDTO.setEndRequest(endTime);		
				metricDTO.setElapsedTime(executeTime);
				metricDTO.setResponseCode(httpServletResponse.getStatus());
				metricDTO.setPayloadLength(httpServletResponse.getBufferSize());
				
				MetricQueue.getStreamInstance().add(metricDTO);
			}
		}
	}
	
	public String getCodiceApplicazione() {
		return codiceApplicazione;
	}

	public void setCodiceApplicazione(String codiceApplicazione) {
		this.codiceApplicazione = codiceApplicazione;
	}

	public Long getMaxLatency() {
		return maxLatency;
	}

	public void setMaxLatency(Long maxLatency) {
		this.maxLatency = maxLatency;
	}

	public Integer getMaxQueue() {
		return maxQueue;
	}

	public void setMaxQueue(Integer maxQueue) {
		this.maxQueue = maxQueue;
		MetricQueue.getStreamInstance().setMaxQueue(maxQueue);
	}
}