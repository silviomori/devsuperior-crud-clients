package com.technomori.dsclient.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technomori.dsclient.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
