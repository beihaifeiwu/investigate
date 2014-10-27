package com.freetmp.investigate.reactivex;

import rx.Observable;
import rx.functions.Action1;

public class RxJavaTry {
	
	public static void hello(String...names){
		Observable.from(names).subscribe(new Action1<String>() {

			public void call(String s) {
				
				System.out.println("Hello " + s + "!");
			}
		});
	}
	
	public static void hello_jdk_8(String...names){
		Observable.from(names).subscribe((name)->{System.out.println("Hello " + name + "!");});
	}
	
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

	public static void main(String[] args) {
		hello("Ben", "George");
		hello_jdk_8("Ben", "George");
		synchronous_create();
		asynchronous_create();
		simple_composition();
	}

}
