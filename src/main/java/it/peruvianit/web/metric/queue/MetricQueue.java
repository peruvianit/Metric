/**
 * FIFO per l'almacenamento delle request che superano il maxLatency(milisecondi)
 * ha un valore massimo per default di 100, però puo essere configurato. Questa lista deve eseere 
 * svuotata per l'applicazione a uso custom, con MetricQueue.poll() che ritorna un MetricDTO.
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
package it.peruvianit.web.metric.queue;

import java.util.LinkedList;
import java.util.Queue;

import it.peruvianit.web.metric.dto.MetricDTO;

public class MetricQueue {
	static Queue<MetricDTO> queue = new LinkedList<MetricDTO>();
	private static MetricQueue queueInstance = null;
    
	private Integer maxQueue = 100; 
    
	public static MetricQueue getStreamInstance() {
 
		if (queueInstance == null) {
			queueInstance = new MetricQueue();
		}
		return queueInstance;
	}
 
	public Queue<MetricDTO> get() {
		return queue;
	}
 
	public void add(MetricDTO value) {
		synchronized (queue) {
			queue.add(value);
			
			if (this.getTotalSize()>maxQueue){
				this.poll();
			}
		}
	}
 
	public void remove(MetricDTO value) {
		synchronized (queue) {
			queue.remove(value);
		}
	}
 
	public MetricDTO poll() {
		MetricDTO data = queue.poll();
		return data;
	}
 
	public boolean isEmpty() {
		return queue.isEmpty();
	}
 
	public int getTotalSize() {
		return queue.size();
	}

	public Integer getMaxQueue() {
		return maxQueue;
	}

	public void setMaxQueue(Integer maxQueue) {
		this.maxQueue = maxQueue;
	}
}
