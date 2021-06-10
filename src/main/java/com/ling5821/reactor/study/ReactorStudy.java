package com.ling5821.reactor.study;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * @author lsj
 * @date 2021/6/10 14:00
 */
public class ReactorStudy {
    public static void main(String[] args) {
        subscribeTest();
        subscribeTest2();
    }

    public static void createFlux() {
        Flux<String> flux1 = Flux.just("foo", "bar", "footbar");
        Flux<String> flux2 = Flux.fromArray(new String[] {"foo", "bar", "footbar"});
        Flux<Integer> number = Flux.range(5,3);
    }

    public static void createMono() {

        Mono<String> empty = Mono.empty();
        Mono<String> data = Mono.just("foo");
    }

    public static void subscribeTest() {
        Flux<Integer> range = Flux.range(1, 3);
        range.subscribe(System.out::println);
    }

    public static void subscribeTest2() {
        Flux<Integer> map = Flux.range(1, 4).map(i -> {
//            if (i <= 3)
//                return i;
//            throw new RuntimeException("go to 4");
            return i;
        });
        SampleSubscriber<Integer> ss = new SampleSubscriber<>();
        map.subscribe(System.out::println, System.err::println, ()-> System.out.println("Done"));
        map.subscribe(ss);

    }

    static class SampleSubscriber<T> extends BaseSubscriber<T> {
        @Override
        protected void hookOnSubscribe(Subscription subscription) {
            System.out.println("订阅");
            request(1);
        }

        @Override
        protected void hookOnNext(T value) {
            System.out.println(value);
            request(1);
        }
    }

}
