package br.edu.ifsp.scl.pipegene.domain;

import java.util.UUID;

public class GroupParticipation {

    private UUID id;
    private Group group;
    private UUID receiverId;
    private GroupParticipationStatusEnum status;
    private UUID SubmitterId;

    private GroupParticipation(UUID id, Group group, UUID receiverId, GroupParticipationStatusEnum status, UUID submitterId) {
        this.id = id;
        this.group = group;
        this.receiverId = receiverId;
        this.status = status;
        SubmitterId = submitterId;
    }

    private GroupParticipation(UUID id) {
        this.id = id;
    }

    public static GroupParticipation createOnlyWithId(UUID id){
        return new GroupParticipation(id);
    }
    public static GroupParticipation createWithAllFields(UUID id, Group group, UUID receiverId, GroupParticipationStatusEnum status, UUID submitterId){
        return new GroupParticipation(id, group, receiverId, status, submitterId);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public UUID getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(UUID receiverId) {
        this.receiverId = receiverId;
    }

    public GroupParticipationStatusEnum getStatus() {
        return status;
    }

    public void setStatus(GroupParticipationStatusEnum status) {
        this.status = status;
    }

    public UUID getSubmitterId() {
        return SubmitterId;
    }

    public void setSubmitterId(UUID submitterId) {
        SubmitterId = submitterId;
    }
}