package com.marble.service;

import com.marble.dto.ClientDto;

import java.util.List;

public interface ClientService {
	
	ClientDto createClient(ClientDto clientDto);
	ClientDto getClientById(Integer ClientId);
	List<ClientDto> getAllClients();
	ClientDto updateClient(Integer ClientId,ClientDto clientDto);
	void deleteClient (Integer ClientId);
	
	
}
