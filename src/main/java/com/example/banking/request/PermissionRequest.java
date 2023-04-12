package com.example.banking.request;

import com.example.banking.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionRequest {
    private String permission_name;
    private Long roleId;
}
