/**
 * Oggetto DTO che sera utilizzato per la creazione di ogni singola instanza quando
 * la request supere il maxLatency(milisecondi), che è configurato sul file di 
 * configurazione per l'interceptor.
 * 
 * <pre>
 * {@code
 *    <mvc:interceptors>
		<mvc:interceptor>
    		<bean class="it.peruvianit.web.metric.interceptor.ControlRequestPerformanceInterceptor" >
    			<property name="codiceApplicazione">
					<value type="java.lang.String">RIC-001</value>
				</property>
    			<property name="maxLatency">
					<value type="java.lang.Long">3000</value><!-- tempo in milisecondi -->
				</property>
				<property name="maxQueue">
					<value type="java.lang.Integer">999</value><!-- Massimo valore che almacena il QUEUE, 
																    Se non è valorizzata questa property, 
																    il valore per default è 100 -->
				</property>
			</bean>	
		</mvc:interceptor>
	</mvc:interceptors>
 *     
 * }
 * </pre>
 * 
 * @author Sergio Arellano Diaz
 * @version 1.0.1
 * @since 17/05/2018
 *
 */
package it.peruvianit.web.metric.dto;

import java.io.Serializable;
import java.util.UUID;

public class MetricDTO implements Serializable {
	private static final long serialVersionUID = 5294890145821717465L;

	// UUID	
	private UUID identifier;
	
	// User
	private String codiceApplicazione;
	
	// Info Request
	private Long startRequest;
	private String ipAddressLocale;
	private String ipAddressRemote;	
	private String method;
	private String agent;
	private String contentType;
	private String reference;
	
	// Info Response
	private String paramsQuery;
	private Integer payloadLength;
	private Long endRequest;
	private Integer responseCode;
	
	// Summary Metrics	
	private Long elapsedTime;

	public UUID getIdentifier() {
		return identifier;
	}

	public void setIdentifier(UUID identifier) {
		this.identifier = identifier;
	}

	public String getCodiceApplicazione() {
		return codiceApplicazione;
	}

	public void setCodiceApplicazione(String codiceApplicazione) {
		this.codiceApplicazione = codiceApplicazione;
	}

	public Long getStartRequest() {
		return startRequest;
	}

	public void setStartRequest(Long startRequest) {
		this.startRequest = startRequest;
	}

	public String getIpAddressLocale() {
		return ipAddressLocale;
	}

	public void setIpAddressLocale(String ipAddressLocale) {
		this.ipAddressLocale = ipAddressLocale;
	}

	public String getIpAddressRemote() {
		return ipAddressRemote;
	}

	public void setIpAddressRemote(String ipAddressRemote) {
		this.ipAddressRemote = ipAddressRemote;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getParamsQuery() {
		return paramsQuery;
	}

	public void setParamsQuery(String paramsQuery) {
		this.paramsQuery = paramsQuery;
	}

	public Integer getPayloadLength() {
		return payloadLength;
	}

	public void setPayloadLength(Integer payloadLength) {
		this.payloadLength = payloadLength;
	}

	public Long getEndRequest() {
		return endRequest;
	}

	public void setEndRequest(Long endRequest) {
		this.endRequest = endRequest;
	}

	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	public Long getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(Long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
}
