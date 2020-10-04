package me.alexpower.example;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(final String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(final String... args) throws Exception {

		// BEFORE
		BiConsumer<String, List<String>> consumer = (x, y) -> System.out.println(x);
		BiConsumer<String, List<String>> explicityConsumer = (String x, List<String> y) -> System.out.println(x);
		// AFTER
		BiConsumer<String, List<String>> varConsumer = (var x, var y) -> System.out.println(x);
		BiConsumer<String, List<String>> varWithAnnotations = (@NonNull var x, @NonNull var y) -> System.out.println(x);
		// COMPILE ERROR
		BiConsumer<String, List<String>> annotations = (@NonNull x, @NonNull y) -> System.out.println(x);

		int result = Operation.ADD.apply(2, 3);
		log.info("2 + 3 = {}", result);
	}

	enum Operation {
		ADD((x, y) -> x + y), SUBTRACT((x, y) -> x - y), MULTIPLY((x, y) -> x * y);

		Operation(BiFunction<Integer, Integer, Integer> operation) {
			this.operation = operation;
		}

		private final BiFunction<Integer, Integer, Integer> operation;

		public int apply(int x, int y) {
			return operation.apply(x, y);
		}
	}
}