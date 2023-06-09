package br.edu.ifsp.scl.pipegene.web.controller;

import br.edu.ifsp.scl.pipegene.domain.Pipeline;
import br.edu.ifsp.scl.pipegene.domain.PipelineStep;
import br.edu.ifsp.scl.pipegene.usecases.pipeline.PipelineCRUD;
import br.edu.ifsp.scl.pipegene.web.model.pipeline.request.*;
import br.edu.ifsp.scl.pipegene.web.model.pipeline.response.PipelineResponse;
import br.edu.ifsp.scl.pipegene.web.model.pipeline.response.UpdatePipelineResponse;
import br.edu.ifsp.scl.pipegene.web.model.pipeline.response.UpdatePipelineStepResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestMapping("api/v1/projects/{projectId}/pipelines")
@RestController
public class PipelineController {

    private final PipelineCRUD pipelineCRUD;

    public PipelineController(PipelineCRUD pipelineCRUD) {
        this.pipelineCRUD = pipelineCRUD;
    }

    @PostMapping
    public ResponseEntity<PipelineResponse> addNewPipeline(
            @PathVariable UUID projectId,
            @RequestBody @Valid CreatePipelineRequest createPipelineRequest) {
        Pipeline pipeline = pipelineCRUD.addNewPipeline(projectId, createPipelineRequest);

        return ResponseEntity.ok(PipelineResponse.createJustId(pipeline.getId()));
    }

    @GetMapping
    public ResponseEntity<List<PipelineResponse>> listAll(
            @PathVariable UUID projectId) {
        List<Pipeline> pipelines = pipelineCRUD.findAllPipeline(projectId);

        return ResponseEntity.ok(
                pipelines.stream()
                        .map(PipelineResponse::createFromPipeline)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{pipelineId}")
    public ResponseEntity<PipelineResponse> findById(
            @PathVariable UUID projectId,
            @PathVariable UUID pipelineId) {
        Pipeline pipeline = pipelineCRUD.findByProjectIdAndPipelineId(projectId, pipelineId);

        return ResponseEntity.ok(PipelineResponse.createFromPipeline(pipeline));
    }

    @GetMapping("/{pipelineId}/steps")
    public ResponseEntity<List<PipelineStepDTO>> listAllSteps(
            @PathVariable UUID projectId,
            @PathVariable UUID pipelineId) {
        List<PipelineStepDTO> pipelineSteps = pipelineCRUD.listAllPipelineStepsByPipelineId(projectId, pipelineId);

        return ResponseEntity.ok(pipelineSteps);
    }

    @GetMapping("/{pipelineId}/steps/{stepId}")
    public ResponseEntity<PipelineStepDTO> findStepById(
            @PathVariable UUID projectId,
            @PathVariable UUID pipelineId,
            @PathVariable UUID stepId) {
        PipelineStepDTO pipelineStep = pipelineCRUD.findPipelineStepById(projectId, pipelineId, stepId);

        return ResponseEntity.ok(pipelineStep);
    }

    @PatchMapping("/{pipelineId}")
    public ResponseEntity<PipelineResponse> updatePipeline(
            @PathVariable UUID pipelineId,
            @RequestBody @Valid UpdatePipelineRequest updatePipelineRequest) {
        Pipeline updatedPipeline = pipelineCRUD.updatePipeline(pipelineId, updatePipelineRequest);

        return ResponseEntity.ok(PipelineResponse.createFromPipelineFull(updatedPipeline));
    }

    @DeleteMapping("/{pipelineId}/steps/{stepId}")
    public ResponseEntity<PipelineResponse> deletePipelineStep(
            @PathVariable UUID pipelineId,
            @PathVariable UUID stepId) {
        Pipeline updatedPipeline = pipelineCRUD.deletePipeline(pipelineId, stepId);

        return ResponseEntity.ok(PipelineResponse.createFromPipelineFull(updatedPipeline));
    }

    @PostMapping("/{pipelineId}/clone")
    public ResponseEntity<PipelineResponse> clonePipeline(
            @PathVariable UUID pipelineId,
            @RequestBody @Valid ClonePipelineRequest clonePipelineRequest) {
        Pipeline clonedPipeline = pipelineCRUD.clonePipeline(pipelineId, clonePipelineRequest);

        return ResponseEntity.ok(PipelineResponse.createFromImportedPipeline(clonedPipeline));
    }

}
