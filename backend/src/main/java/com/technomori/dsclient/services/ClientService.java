package com.technomori.dsclient.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	public Page<ClientDTO> findAll(PageRequest pageRequest) {
		Page<Client> pageEntities = repository.findAll(pageRequest);
		return pageEntities.map(entity -> new ClientDTO(entity));
	}

}
