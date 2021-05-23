package br.edu.ifsp.scl.pipegene.external.persistence;

import br.edu.ifsp.scl.pipegene.external.persistence.entities.ExecutionStatusEntity;
import br.edu.ifsp.scl.pipegene.external.persistence.entities.ProjectEntity;
import br.edu.ifsp.scl.pipegene.external.persistence.entities.ProviderEntity;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FakeDatabase {
    public static Map<UUID, ExecutionStatusEntity> EXECUTION_STATUS_MAP = new HashMap<>();
    public static Map<UUID, ProviderEntity> PROVIDERS = loadMockProviders();
    public static Map<UUID, ProjectEntity> PROJECTS = loadMockProjects();


    private static Map<UUID, ProviderEntity> loadMockProviders() {
        return Stream.of(
                ProviderEntity.of(
                        UUID.fromString("baca1f38-5501-476f-a7f5-fe5958a55772"),
                        Collections.singletonList("maf"),
                        Collections.singletonList("maf")
                ),
                ProviderEntity.of(
                        UUID.fromString("0fd493f8-fb58-455a-8cb9-d3561d111e70"),
                        Collections.singletonList("taf"),
                        Collections.singletonList("taf")
                )
        ).collect(Collectors.toMap(ProviderEntity::getId, Function.identity()));
    }


    private static Map<UUID, ProjectEntity> loadMockProjects() {
        return Stream.of(
                ProjectEntity.of(
                        UUID.fromString("53bb719a-e982-4785-bb53-40dc71d6dd9a"),
                        "e6524f24-1ef8-4cc9-8538-31db80941b7b_GBM_MEMo.maf",
                        "Fake Project"
                ),
                ProjectEntity.of(
                        UUID.fromString("e1d33cc3-f04d-45c8-8998-20cd0d4af878"),
                        "e6524f24-1ef8-4cc9-8538-31db80941b7b_GBM_MEMo.maf",
                        "Fake Project 2"
                )

        ).collect(Collectors.toMap(ProjectEntity::getId, Function.identity()));
    }
}
