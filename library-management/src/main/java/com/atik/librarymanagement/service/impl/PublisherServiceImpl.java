package com.atik.librarymanagement.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.atik.librarymanagement.model.Publisher;
import com.atik.librarymanagement.repository.PublisherRepository;
import com.atik.librarymanagement.service.PublisherService;

@Service
public class PublisherServiceImpl implements PublisherService {

	@Autowired
	private PublisherRepository repository;

	@Override
	public HttpStatus create(List<Publisher> publishers) throws IllegalArgumentException {

		try {
			
			publishers.stream().forEach(publisher -> {
				
				publisher.setId(UUID.randomUUID().toString());

				repository.save(publisher);
			});

			return HttpStatus.CREATED;

		} catch (IllegalArgumentException e) {

			throw new IllegalArgumentException();

		}
	}

	@Override
	public List<Publisher> getPublishers() {

		return repository.findAll();

	}

	@Override
	public Publisher getPublisher(String id) throws IllegalArgumentException {

		try {

			Optional<Publisher> optional = repository.findById(id);

			if (optional.isPresent())
				return optional.get();

			return null;

		} catch (IllegalArgumentException e) {

			throw new IllegalArgumentException();
		}

	}

	@Override
	public HttpStatus deletePublishers() {

		repository.deleteAll();

		return HttpStatus.NO_CONTENT;

	}

	@Override
	public HttpStatus deletePublisher(String id) {

		repository.deleteById(id);

		return HttpStatus.NO_CONTENT;

	}

	@Override
	public HttpStatus update(Publisher publisher) throws IllegalArgumentException {

		try {

			Publisher updatePublisher = getPublisher(publisher.getId());

			if (Objects.nonNull(updatePublisher)) {

				if (Objects.nonNull(publisher.getName()))
					updatePublisher.setName(publisher.getName());

				if (Objects.nonNull(publisher.getAddress()))
					updatePublisher.setAddress(publisher.getAddress());
			}

			repository.save(updatePublisher);

			return HttpStatus.NO_CONTENT;

		} catch (IllegalArgumentException e) {

			throw new IllegalArgumentException();
		}

	}

}
