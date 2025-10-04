package com.marble.service.impl;

import com.marble.dto.ClientDto;
import com.marble.entities.Client;
import com.marble.repos.ClientRepository;
import com.marble.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository ClientRepository;

    public ClientServiceImpl(ClientRepository ClientRepository) {
        this.ClientRepository = ClientRepository;
    }

    // Entity -> DTO
    private ClientDto entityToDto(Client client) {
        ClientDto dto = new ClientDto();
        dto.setClientId(client.getClientId());
        dto.setName(client.getName());
        dto.setMobile(client.getMobile());
        dto.setEmail(client.getEmail());
        dto.setAddress(client.getAddress());
        dto.setType(client.getType());
        dto.setGstNo(client.getGstNo());
        return dto;
        
    }

    // DTO -> Entity
    private Client dtoToEntity(ClientDto dto) {
      Client client =new Client();
      client.setClientId(dto.getClientId());
      client.setName(dto.getName());
      client.setMobile(dto.getMobile());
      client.setEmail(dto.getEmail());
      client.setAddress(dto.getAddress());
      client.setType(dto.getType());
      client.setGstNo(dto.getGstNo());
    	return client;
    }
    
    @Override
    public ClientDto createClient(ClientDto clientDto) {
    	Client client=dtoToEntity(clientDto);
    	Client saved=ClientRepository.save(client);
    	return entityToDto(saved);
    }
  

    @Override
    public ClientDto getClientById(Integer clientId) {
    	Client client=ClientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("client not Found with ID :" + clientId));
    	return entityToDto(client);
    }
  
    @Override
    public List<ClientDto> getAllClients(){
    	return ClientRepository.findAll().stream().map(this::entityToDto).collect(Collectors.toList());
    }
   

    @Override
    public ClientDto updateClient(Integer clientId,ClientDto dto) {
    	Client client=ClientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not Found With ID :" + clientId));
    	client.setClientId(dto.getClientId());
        client.setName(dto.getName());
        client.setMobile(dto.getMobile());
        client.setEmail(dto.getEmail());
        client.setAddress(dto.getAddress());
        client.setType(dto.getType());
        client.setGstNo(dto.getGstNo());
        
        Client updated=ClientRepository.save(client);
        return entityToDto(updated);
      	
    }
   
    @Override
    public void deleteClient(Integer clientId) {
    	Client client=ClientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found with ID"+ clientId));
    	ClientRepository.delete(client);
    }
 
}

