package com.abc.event_registration_system.dto.request;

import javax.validation.constraints.NotNull;


public class PasswordChangerDTO {

    @NotNull(message = "Old password is required")
    private String oldPassword;

    @NotNull(message = "New password is required")
    private String newPassword;



    public PasswordChangerDTO(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public PasswordChangerDTO() {
    }


    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}

