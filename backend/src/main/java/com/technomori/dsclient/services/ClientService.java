package com.technomori.dsclient.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.technomori.dsclient.dto.ClientDTO;
import com.technomori.dsclient.entities.Client;
import com.technomori.dsclient.exceptions.ResourceNotFoundException;
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

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Client entity = repository.findById(id).orElseThrow(() -> 
			new ResourceNotFoundException(String.format("Client %d not found", id)));
		return new ClientDTO(entity);
	}

	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client entity = new Client();
		copyClientData(dto, entity);
		entity = repository.save(entity);
		return new ClientDTO(entity);
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		Client entity = null;
		try {
			entity = repository.getOne(id);
			copyClientData(dto, entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(
				String.format("Client ID %d not found", id));
		}
		entity = repository.save(entity);
		return new ClientDTO(entity);
	}

	/**
	 * Copies data from the DataTransferObject to the entity object.
	 *
	 * @param dto    data copied from
	 * @param entity data copied to
	 */
	private void copyClientData(ClientDTO dto, Client entity) {
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
	}

}
