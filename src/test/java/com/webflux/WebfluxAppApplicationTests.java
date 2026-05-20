package com.webflux;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;

@SpringBootTest
class WebfluxAppApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void workWithMono(){

	/*	// created Mono
		Mono<String> m1 = Mono.just("Learning about Mono")
				.log();

		// consume Mono by subscribing
		m1.subscribe(System.out::println);*/


		Mono<String> m1 = Mono.just("Welcome to the learning path...");
		Mono<String> m2 = Mono.just("Here we are learning about Mono...");
		Mono<Integer> m3 = Mono.just(50089);

		// use of zip is to combine multiple mono
		Mono<Tuple3<String, String, Integer>> combinedMono = Mono.zip(m1, m2, m3);

	/*	combinedMono.subscribe(comb -> {
			System.out.println(comb);
		});*/

		combinedMono.subscribe(data -> {
			System.out.println(data.getT1());
			System.out.println(data.getT2());
			System.out.println(data.getT3());
		});


		Mono<String> mapMono = m1.map(m -> m.toUpperCase());
		mapMono.subscribe(data -> System.out.println(data));


		Mono<String[]> stringMono = m1.flatMap(f -> Mono.just(f.split(" ")));
		stringMono.subscribe(data ->
		{
			for(String s: data){
				System.out.println(s);
			}
		});


		Flux<String> flatMany = m2.flatMapMany(flat -> Flux.just(flat.split(" "))).log();
		flatMany.subscribe(data -> {
			System.out.println(data);
		});
	}

}
