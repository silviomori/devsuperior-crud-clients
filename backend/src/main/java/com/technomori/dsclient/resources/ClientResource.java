package com.technomori.dsclient.resources;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technomori.dsclient.dto.ClientDTO;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll() {
		List<ClientDTO> clients = Arrays.asList(
			new ClientDTO[] { new ClientDTO(1L, "Silvio", "12312312312", 1000d, Instant.now(), 0),
			new ClientDTO(2L, "Luciana", "23123123123", 2000d, Instant.now(), 1),
			new ClientDTO(3L, "Maria", "31231231231", 3000d, Instant.now(), 2) });
		return ResponseEntity.ok().body(clients);
	}

}
