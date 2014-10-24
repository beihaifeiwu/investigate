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

	public static void main(String[] args) {
		hello("Ben", "George");
		hello_jdk_8("Ben", "George");
	}

}
