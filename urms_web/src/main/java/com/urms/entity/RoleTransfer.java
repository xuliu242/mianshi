package com.urms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class RoleTransfer {
    private Integer key;
    private String label;
    private boolean disabled;

}
