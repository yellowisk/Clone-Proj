package br.edu.ifsp.scl.pipegene.external.persistence.entities;

import br.edu.ifsp.scl.pipegene.domain.Provider;
import br.edu.ifsp.scl.pipegene.domain.ProviderOperation;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ProviderEntity {
    private UUID id;
    private String name;
    private String description;
    private String url;

    private Boolean isPublic;

    private UUID groupId;
    private Set<String> inputSupportedTypes;
    private Set<String> outputSupportedTypes;
    private Set<ProviderOperation> operations;


    private ProviderEntity(UUID id, String name, String description, String url, Boolean isPublic, UUID groupId, Collection<String> inputSupportedTypes,
                           Collection<String> outputSupportedTypes, Collection<ProviderOperation> operations) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.isPublic = isPublic;
        this.groupId = groupId;
        this.inputSupportedTypes = new HashSet<>(inputSupportedTypes);
        this.outputSupportedTypes = new HashSet<>(outputSupportedTypes);
        this.operations = new HashSet<>(operations);
    }

    public static ProviderEntity of(UUID id, String name, String description, String url, Boolean isPublic, UUID groupId,
                                    Collection<String> inputSupportedTypes, Collection<String> outputSupportedTypes,
                                    Collection<ProviderOperation> operations) {
        return new ProviderEntity(id, name, description, url, isPublic, groupId, inputSupportedTypes, outputSupportedTypes, operations);
    }

    public static ProviderEntity createFromProviderWithoutId(Provider provider) {
        return new ProviderEntity(UUID.randomUUID(), provider.getName(), provider.getDescription(), provider.getUrl(),
                provider.getPublic(), provider.getGroupId(), provider.getInputSupportedTypes(),
                provider.getOutputSupportedTypes(), provider.getOperations());
    }

    public Provider convertToProvider() {
        return Provider.createWithAllValues(id, name, description, url, isPublic, groupId, inputSupportedTypes, outputSupportedTypes, operations);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Set<String> getInputSupportedTypes() {
        return inputSupportedTypes;
    }

    public void setInputSupportedTypes(Set<String> inputSupportedTypes) {
        this.inputSupportedTypes = inputSupportedTypes;
    }

    public Set<String> getOutputSupportedTypes() {
        return outputSupportedTypes;
    }

    public void setOutputSupportedTypes(Set<String> outputSupportedTypes) {
        this.outputSupportedTypes = outputSupportedTypes;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<ProviderOperation> getOperations() {
        return operations;
    }

    public void setOperations(Set<ProviderOperation> operations) {
        this.operations = operations;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public UUID getGroupId() {
        return groupId;
    }

    public void setGroupId(UUID groupId) {
        this.groupId = groupId;
    }
}
