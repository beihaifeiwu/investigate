package com.freetmp.investigate.reactivex

import rx.Observable;

println "**** empty ****";
Observable.empty().subscribe(
	{ println "empty: " + it},
	{ println "empty: error- " + it.getMessage()},
	{ println "empty: Sequence complete"}
);

println "**** error ****";
Observable.error(new Throwable("badness")).subscribe(
	{ println "error: " + it},
	{ println "error: error- " + it.getMessage()},
	{ println "error: Sequence complete"}
);

println "**** never ****";
Observable.never().subscribe(
	{ println "never: " + it},
	{ println "never: error- " + it.getMessage()},
	{ println "never: Sequence complete"}
);
println "**** end ****";

numbers = Observable.from([1,2,3,4,5]);
numbers.map({it * it}).subscribe(
	{ println(it)},
	{ println("Error: " + it.getMessage())},
	{ println("Sequence complete")}	
);

numbers = Observable.from([1,2,3]);

multiples = { n -> Observable.from([n*2, n*3])};

numbers.flatMap(multiples).subscribe(
	{ println(it)},
	{ println("Error: " + it.getMessage())},
	{ println("Sequence complete")}
);

numbers = Observable.from([1,2,3,4,5]);
numbers.scan({a,b -> a + b}).subscribe(
	{ println(it)},
	{ println("Error: " + it.getMessage())},
	{ println("Sequence complete")}
);

numbers = Observable.from([1, 2, 3, 4, 5, 6, 7, 8]);
groupFunc = {return (0 == (it % 2))}
numbers.groupBy(groupFunc).flatMap({ it.reduce([it.getKey()],{a,b -> a << b})}).subscribe(
	{ println(it)},
	{ println("Error: " + it.getMessage())},
	{ println("Sequence complete")}
);

numbers.buffer(3).subscribe(
	{ println(it)},
	{ println("Error: " + it.getMessage())},
	{ println("Sequence complete")}
);

numbers.buffer(2,3).subscribe(
	{ println(it)},
	{ println("Error: " + it.getMessage())},
	{ println("Sequence complete")}
);

numbers.buffer(3,2).subscribe(
	{ println(it)},
	{ println("Error: " + it.getMessage())},
	{ println("Sequence complete")}
);