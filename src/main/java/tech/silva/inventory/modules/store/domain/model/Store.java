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
    private StatusStore status = StatusStore.ACTIVE;
    private Long idUser;

    public Store() {
    }

        public Store(Long id, String name, String description, String email, String phoneNumber, String cnpj, Address address, StatusStore status, Long idUser) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cnpj = cnpj;
        this.address = address;
        this.status = status;
        this.idUser = idUser;
    }

    public Store(String name, String description, String email, String phoneNumber, String cnpj, Address address) {
        this.name = name;
        this.description = description;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cnpj = cnpj;
        this.address = address;
    }

    public Store(String name, String description, String email, String phoneNumber, String cnpj) {
        this.name = name;
        this.description = description;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cnpj = cnpj;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public StatusStore getStatus() {
        return status;
    }

    public void setStatus(StatusStore status) {
        this.status = status;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}
