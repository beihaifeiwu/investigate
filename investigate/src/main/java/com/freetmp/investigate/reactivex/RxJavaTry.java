package com.freetmp.investigate.reactivex;

import java.util.Arrays;

import rx.Observable;
import rx.functions.Action1;

public class RxJavaTry {
	
	/**
	 * 使用匿名内部类风格的流
	 * @param names
	 */
	public static void hello(String...names){
		Observable.from(names).subscribe(new Action1<String>() {

			public void call(String s) {
				
				System.out.println("Hello " + s + "!");
			}
		});
	}
	
	/**
	 * 使用lambda表达式风格的流
	 * @param names
	 */
	public static void hello_jdk_8(String...names){
		Observable.from(names).subscribe((name)->{System.out.println("Hello " + name + "!");});
	}
	
	/**
	 * 创建阻塞的流
	 */
	public static void synchronous_create(){
		Observable<String> observable = 
				Observable.create((aSubscriber) ->{
			for(int i = 0; i < 50; i++){
				if(false == aSubscriber.isUnsubscribed()){
					aSubscriber.onNext("value_sync" + i);
				}
			}
			if(false == aSubscriber.isUnsubscribed()){
				aSubscriber.onCompleted();
			}
		});
		
		observable.subscribe((value) -> {System.out.println(value);});
		
	}
	
	/**
	 * 创建非阻塞的流
	 */
	public static void asynchronous_create(){
		Observable.create((aSubscriber) -> {
			final Thread t = new Thread(()->{
				for(int i = 0; i < 50; i++){
					if(true == aSubscriber.isUnsubscribed()){
						return;
					}
					aSubscriber.onNext("value_async" + i);
				}
				if(false == aSubscriber.isUnsubscribed()){
					aSubscriber.onCompleted();
				}
			});
			t.start();
		}).subscribe((value)->{System.out.println(value);});
	}
	
	/**
	 * 简单组合
	 */
	public static void simple_composition(){
		Observable.create((aSubscriber) -> {
			final Thread t = new Thread(()->{
				for(int i = 0; i < 50; i++){
					if(true == aSubscriber.isUnsubscribed()){
						return;
					}
					aSubscriber.onNext("value_async" + i);
				}
				if(false == aSubscriber.isUnsubscribed()){
					aSubscriber.onCompleted();
				}
			});
			t.start();
		}).skip(10).take(5).map((stringValues) -> {return stringValues + "_xform";})
		  .subscribe((value) -> {System.out.println("onNext => " + value);});
	}
	
	/**
	 * 使用了startWith的组合
	 */
	public static void startwith_composition(){
		Observable.from(new Integer[]{1,2,3})
				  .startWith(-3,-2,-1,0)
				  .subscribe(
						  (val)->{System.out.println(val);}, 
						  (e)->{System.out.println("Error: " + e.getMessage());}, 
						  ()->{System.out.println("Sequence complete");}
				  );
		
	}
	
	/**
	 * 使用了merge的组合
	 */
	public static void merge_composition(){
		Observable.merge(Observable.from(new Integer[]{1,3,5,7}),
						 Observable.from(new Integer[]{2,4,6,8}))
				  .subscribe(
						  (val)->{System.out.println(val);}, 
						  (e)->{System.out.println("Error: " + e.getMessage());}, 
						  ()->{System.out.println("Sequence complete");}						  
				  );
	}
	
	/**
	 * 使用了zip的组合
	 */
	public static void zip_composition(){
		Observable.zip(Observable.from(new Integer[]{1,3,5,7,9}),
					   Observable.from(new Integer[]{2,4,6}),
					   (f,s) -> {return Arrays.asList(f,s);})
				  .subscribe(
						  (val)->{System.out.println(val);}, 
						  (e)->{System.out.println("Error: " + e.getMessage());}, 
						  ()->{System.out.println("Sequence complete");}						  
				  );
	}

	public static void main(String[] args) {
		hello("Ben", "George");
		hello_jdk_8("Ben", "George");
		synchronous_create();
		asynchronous_create();
		simple_composition();
		startwith_composition();
		merge_composition();
		zip_composition();
	}

}
