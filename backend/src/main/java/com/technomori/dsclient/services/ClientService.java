package com.technomori.dsclient.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.technomori.dsclient.dto.ClientDTO;
import com.technomori.dsclient.entities.Client;
import com.technomori.dsclient.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Transactional(readOnly = true)
	public List<ClientDTO> findAll() {
		List<Client> entities = repository.findAll();
		return entities.stream().map(item -> new ClientDTO(item))
			.collect(Collectors.toList());
	}

}
