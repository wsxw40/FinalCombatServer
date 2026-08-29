package com.pearl.o2o.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.collections.CollectionUtils;

public class PerformanceDatas {
	//key url,  for read
	private Map<String, InterfaceEntry> datas = new HashMap<String, InterfaceEntry>();
	//buffer for flush the new record into datas
	//key:servlet name 
	private Map<String, Queue<Integer>> queueMap = new ConcurrentHashMap<String, Queue<Integer>>();
	
	private  int size = 500;
	
	public void addInvokeRecord(String servletName, int costTime){
		Queue<Integer> queue = queueMap.get(servletName);
		if (queue == null) {
			synchronized (queueMap) {
				if (queueMap.get(servletName) == null) {
					queueMap.put(servletName, new LinkedBlockingQueue<Integer>());
				}
			}
		}
		queue = queueMap.get(servletName);
		queue.offer(costTime);
		
		//generate
		if (queue.size() > size){
			flush();
		}
	}
	
	public void addMemcacheInvokeRecord(String servletClass, int hit, int miss){
		InterfaceEntry entry = datas.get(servletClass);
		if (entry == null) {
			synchronized(datas){
				entry = datas.get(servletClass);
				if (entry == null){
					entry = new InterfaceEntry(servletClass,0);
					datas.put(servletClass, entry);
				}
			}
		}
		entry.addMemcacheInvoke(hit, miss);
	}
	
	//generate report
	public void flush(){//copy on write
		Map<String, Queue<Integer>> temp = queueMap;
		queueMap = new ConcurrentHashMap<String, Queue<Integer>>(); 
		
		for (Map.Entry<String, Queue<Integer>> entry : temp.entrySet() ) {
			for (int cost : entry.getValue()){
				innerAddInvokeRecord(entry.getKey(),cost);
			}
		}
		//release
		temp = null;
	}
	
	//should be invoked in single thread
	private void innerAddInvokeRecord(String servletName, int costTime){
		InterfaceEntry entry = datas.get(servletName);
		if (entry == null) {
			datas.put(servletName, new InterfaceEntry(servletName,costTime));
			return ;
		}
		entry.addInvoke(costTime);
	}
	
	
	
	public static class InterfaceEntry{
		private String servletName;
		private long maxCost;
		private long minCost;
		private long invokeCounts;
		private long totalCost;
		private long memcacheHit;
		private long memcacheMiss;
		
		public long getAverageCost(){
			return totalCost/invokeCounts;
		}
		
		public float getCacheHitRate(){
			return (float)memcacheHit/(memcacheHit + memcacheMiss) * 100;
		}
		
		
		public InterfaceEntry(String servletName,int cost) {
			this.servletName = servletName;
			this.maxCost = cost;
			this.minCost = cost;
			this.invokeCounts = 1;
			this.totalCost = cost;
		}
		
		public void addInvoke(int cost){
			invokeCounts ++;
			totalCost += cost;
			if (cost > maxCost) {
				maxCost = cost;
			}else if(cost < minCost){ 
				minCost = cost;
			}
		}
		
		public synchronized void addMemcacheInvoke(int hit, int miss){
			memcacheHit += hit ;
			memcacheMiss += miss; 
		}

		public String getServletName() {
			return servletName;
		}

		public long getMaxCost() {
			return maxCost;
		}

		public long getMinCost() {
			return minCost;
		}

		public long getInvokeCounts() {
			return invokeCounts;
		}

		public long getTotalCost() {
			return totalCost;
		}

		public long getMemcacheHit() {
			return memcacheHit;
		}

		public long getMemcacheMiss() {
			return memcacheMiss;
		}
		
	}
	
	//vo method, as smarty4j no support Map
	public List<InterfaceEntry> getDatas() {
		
		List<InterfaceEntry> result = new ArrayList<InterfaceEntry>();
		result.addAll(datas.values());
		Collections.sort(result, new Comparator<InterfaceEntry>(){
			@Override
			public int compare(InterfaceEntry o1, InterfaceEntry o2) {
				return Long.valueOf(o2.getAverageCost()).compareTo(Long.valueOf(o1.getAverageCost()));
			}
		});
		return result;
	}
}
		
