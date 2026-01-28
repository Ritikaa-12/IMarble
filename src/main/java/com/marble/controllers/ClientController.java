package com.marble.controllers;

import com.marble.dto.ClientDto;
import com.marble.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','RECEPTIONIST')")
    @PostMapping
    public ResponseEntity<ClientDto> createclient(@RequestBody ClientDto clientDto) {
        return new ResponseEntity<>(clientService.createClient(clientDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Integer id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ClientDto>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
    public ResponseEntity<ClientDto> updateClient(
            @PathVariable Integer id,
            @RequestBody ClientDto clientDto) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserId = auth.getName(); // this is sub from JWT

        // If role is CLIENT → allow only own update
        boolean isClient = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_CLIENT"));

        if (isClient && !loggedInUserId.equals(String.valueOf(id))) {
            return ResponseEntity.status(403).build(); // Forbidden
        }

        return ResponseEntity.ok(clientService.updateClient(id, clientDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Integer id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok("Client deleted successfully");
    }
}
