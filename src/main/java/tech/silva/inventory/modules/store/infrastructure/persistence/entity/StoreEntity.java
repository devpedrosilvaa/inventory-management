package tech.silva.inventory.modules.store.infrastructure.persistence.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import tech.silva.inventory.modules.shared.enums.StatusStore;
import tech.silva.inventory.modules.store.domain.model.Address;

import java.time.LocalDateTime;

@Entity
@Table(name = "stores")
@EntityListeners(AuditingEntityListener.class)
public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String email;
    private String phoneNumber;
    private String cnpj;
    @Embedded
    private Address address;
    private StatusStore status = StatusStore.ACTIVE;
    private Long userId;

    @CreatedDate
    private LocalDateTime createdDate;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
    @LastModifiedBy
    private String lastModifiedBy;

    public StoreEntity() {
    }

    public StoreEntity(Long id, String name, String description, String email, String phoneNumber, String cnpj, Address address, StatusStore status, Long userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cnpj = cnpj;
        this.address = address;
        this.status = status;
        this.userId = userId;
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

    public Long getIdUser() {
        return userId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }
}
