package com.ibm.msreskill.cart.utilities;

import java.util.concurrent.atomic.AtomicLong;

public class GenSeq {
	
	    private static AtomicLong cartSeq = new AtomicLong(1001);
	    private static AtomicLong orderSeq = new AtomicLong(2001);

	    public static long getNextCartId() {
	        return cartSeq.getAndIncrement();
	    }
	    
	    public static long getNextOrderId() {
	        return orderSeq.getAndIncrement();
	    }
	
	
}
