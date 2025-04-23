package tech.silva.inventory.modules.store.domain.model;

import tech.silva.inventory.modules.shared.enums.StatusStore;

public class Store {
    private Long id;
    private String name;
    private String description;
    private String email;
    private String phoneNumber;
    private String cnpj;
    private Address address;
    private StatusStore status;

    public Store() {
    }

    public Store(Long id, String name, String description, String email, String phoneNumber, String cnpj, Address address, StatusStore status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cnpj = cnpj;
        this.address = address;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCnpj() {
        return cnpj;
    }

    public Address getAddress() {
        return address;
    }

    public StatusStore getStatus() {
        return status;
    }
}
