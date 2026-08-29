/**
 * 
 */
package com.pearl.o2o.utils;


/**
 * @author lifengyang
 *
 */
public class Either<L,R> {
	private L left;
	private R right;
	
	private Either(L left,R right){
		this.left = left;
		this.right = right;
	}
	
	public static <L,R> Either<L, R> left(L left){
		return new Either<L, R>(left, null);
	}
	
	public boolean isLeft(){
		return null != left;
	}
	
	public L left(){
		return this.left;
	}
	public static <L,R> Either<L, R> right(R right){
		return new Either<L, R>(null, right);
	}
	
	public boolean isRight(){
		return null != right;
	}
	
	public R right(){
		return this.right;
	}
	
}
