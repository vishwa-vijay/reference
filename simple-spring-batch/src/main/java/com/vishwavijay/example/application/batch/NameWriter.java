package com.vishwavijay.example.application.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class NameWriter implements ItemWriter<String> {

	@Override
	public void write(List<? extends String> names) throws Exception {
		names.stream().forEach(System.out::println);
	}

}
