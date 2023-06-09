package br.edu.ifsp.scl.pipegene.usecases.execution.queue;

import br.edu.ifsp.scl.pipegene.web.model.execution.request.CreateExecutionRequest;

import java.util.UUID;

public interface QueueService {

    UUID add(CreateExecutionRequest request);

    ExecutionQueueElement pool();

}
